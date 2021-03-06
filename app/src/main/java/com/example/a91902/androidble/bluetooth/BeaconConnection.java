package com.example.a91902.androidble.bluetooth;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.example.a91902.androidble.R;
import com.example.a91902.androidble.home.ProductActivity;
import com.example.a91902.androidble.login.SignInActivity;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;

public class BeaconConnection extends Activity implements BeaconConsumer {
    protected static final String TAG = "MonitoringActivity";
    private BeaconManager beaconManager;
    private BluetoothAdapter mBluetoothAdapter;
    private static final int REQUEST_ENABLE_BT = 1;
    private ProgressDialog progressDialog;
    String phone,id,activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon_connection);
        Intent intent=getIntent();
        phone=intent.getStringExtra("phone");
        id=intent.getStringExtra("id");
        activity=intent.getStringExtra("activity");

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, R.string.ble_not_supported, Toast.LENGTH_SHORT).show();
            finish();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Connecting");
        progressDialog.show();
        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
        beaconManager = BeaconManager.getInstanceForApplication(this);
        // To detect proprietary beacons, you must add a line like below corresponding to your beacon
        // type.  Do a web search for "setBeaconLayout" to get the proper expression.
         beaconManager.getBeaconParsers().add(new BeaconParser().
         setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
        beaconManager.bind(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }
    @Override
    public void onBeaconServiceConnect() {
        //beaconManager.removeAllRangeNotifiers();
        beaconManager.addRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                Toast.makeText(BeaconConnection.this, ""+beacons.size(), Toast.LENGTH_SHORT).show();
                if (beacons.size() > 0) {
                    Log.i(TAG, "The first beacon I see is about "+beacons.iterator().next().getDistance()+" meters away.");
                    progressDialog.dismiss();
                   //Toast.makeText(BeaconConnection.this, "Name = "+beacons.iterator().next().getBluetoothName()+" Distance="+beacons.iterator().next().getDistance()+" Address="+beacons.iterator().next().getBluetoothAddress()+" UUID= "+beacons.iterator().next().toString(), Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(BeaconConnection.this,ProductActivity.class);
                    intent.putExtra("id",id);
                    intent.putExtra("phone",phone);
                    intent.putExtra("activity","Sign");
                    startActivity(intent);
                    finish();
                    //getNotification();
                }
            }
        });

        try {
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {    }
    }

    /*public void getNotification(){
        Intent intent=new Intent(BeaconConnection.this, DescriptionActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,(int)System.currentTimeMillis(),intent,0);
        NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification=new Notification.Builder(this)
                .setSmallIcon(R.drawable.shoe2)
                .setContentTitle("Shoe at cheaper rate")
                .setContentText("Shoe starting at RS 299")
                .setContentIntent(pendingIntent)
                .build();

        notificationManager.notify(0,notification);

    }*/
    @Override
    protected void onResume() {
        super.onResume();
        progressDialog.show();
        /*if (!mBluetoothAdapter.isEnabled()) {
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }*/
    }
}
