package com.example.a91902.androidble.Bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a91902.androidble.R;

import java.util.ArrayList;

public class BluetoothConnection extends AppCompatActivity {
    BluetoothAdapter bluetoothAdapter;
    private static final int REQUEST_ENABLE_BT=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_connection);
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)){
            Toast.makeText(this, "BLE is not supported", Toast.LENGTH_SHORT).show();
            finish();
        }
        final BluetoothManager bluetoothManager=(BluetoothManager)getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter=bluetoothManager.getAdapter();
        if(bluetoothAdapter == null){
            Toast.makeText(this, "Bluetooth is not supported", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!bluetoothAdapter.isEnabled()) {
            if (!bluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
        /*bluetoothAdapter = new DeviceScanActivity.LeDeviceListAdapter();
        setListAdapter(mLeDeviceListAdapter);
        scanLeDevice(true);*/
    }
    private class LeDeviceAdapter extends BaseAdapter{
        private ArrayList<BluetoothDevice> bluetoothdevice;
        private LayoutInflater layoutInflater;
        public  LeDeviceAdapter(){
            super();
            bluetoothdevice=new ArrayList<BluetoothDevice>();
            layoutInflater=BluetoothConnection.this.getLayoutInflater();
        }

        public void addDevice(BluetoothDevice bluetoothDevice){
            if(bluetoothdevice.contains(bluetoothDevice)){
                bluetoothdevice.add(bluetoothDevice);
            }
        }


        @Override
        public int getCount() {
            return bluetoothdevice.size();
        }

        @Override
        public Object getItem(int i) {
            return bluetoothdevice.get(i);

        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if(view==null){

            }
            return view;
        }
    }
    static class ViewHolder{
        TextView deviceName;
        TextView deviceAddress;
    }
}
