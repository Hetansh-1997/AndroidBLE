package com.example.a91902.androidble.home;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.a91902.androidble.MyApp;
import com.example.a91902.androidble.R;
import com.example.a91902.androidble.login.SignInActivity;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import static com.example.a91902.androidble.database.Constants.CONTENT_USER;
import static com.example.a91902.androidble.database.Constants.NAME;
import static com.example.a91902.androidble.database.Constants.PHONE_NUMBER;
import static com.example.a91902.androidble.database.Constants.STATUS;

public class ProductActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    GridView gridView;
    RelativeLayout relativeLayout;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    String string[] = {"Mi A2 (Gold, 4GB RAM, 64GB Storage)", "Mi A3 (Blue, 4GB RAM, 64GB Storage)",
            "Samsung Galaxy A8+ (Black, 6GB RAM, 64GB Storage)", "Redmi 6 Pro (Black, 4GB RAM, 64GB Storage)","Mi A2 (Gold, 4GB RAM, 64GB Storage)"};
    int photo[] = {R.drawable.mobile1, R.drawable.mobile2, R.drawable.mobile3, R.drawable.mobile4,R.drawable.mobile1};
    String productPrice[] = {"16,181", "30,000", "12,999", "14,999","13,150"};
    ProgressDialog progressDialog;
    TextView name;
    View getView;
    String phone,id,activity;
    String firstname,lastname;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Intent intent=getIntent();
        phone=intent.getStringExtra("phone");
        id=intent.getStringExtra("id");
        activity=intent.getStringExtra("activity");
        final String url = "http://10.10.8.91:3000/users?phone=07718924436";
        progressDialog=new ProgressDialog(ProductActivity.this);
        progressDialog.setMessage("Validating");
        progressDialog.show();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        getView=navigationView.getHeaderView(0);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        navigationView.setNavigationItemSelectedListener(this);

        final String[] json = new String[1];
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        progressDialog.dismiss();
                        json[0]=response.toString();
                        try {
                            JSONObject jsonObject=new JSONObject(json[0]);
                            name=getView.findViewById(R.id.namegiven);
                            name.setText(jsonObject.getString("firstName")+" "+jsonObject.getString("lastName"));
                            if(activity.equals("Sign")) {
                                String selection = "" + PHONE_NUMBER + " = 07718924436";
                                ContentValues contentValues = new ContentValues();
                                contentValues.put(NAME, jsonObject.getString("firstname") + " " + jsonObject.getString("lastname"));
                                getContentResolver().update(CONTENT_USER, contentValues, selection, null);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                    }
                }){
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("token", id);
                return headers;
            }
        };
        MyApp.getInstance().addToRequestQueue(jsonObjReq, "getRequest");
        gridView = findViewById(R.id.grid_view);
        ProductAdapter productAdapter = new ProductAdapter(this, photo, string, productPrice);
        gridView.setAdapter(productAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentNew=new Intent(ProductActivity.this, DescriptionActivity.class);
                intentNew.putExtra("Name",string[i]);
                intentNew.putExtra("Price",productPrice[i]);
                intentNew.putExtra("image",photo[i]);
                startActivity(intentNew);
            }
        });
        findViewById(R.id.cart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProductActivity.this,CartActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            builder=new AlertDialog.Builder(ProductActivity.this);
            builder.setMessage("Are you sure you want to exit")
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            progressDialog.onBackPressed();
                            finish();
                        }
                    })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    progressDialog.onBackPressed();
                }
            });
            AlertDialog alertDialog=builder.create();
            alertDialog.setTitle("Exit");
            alertDialog.show();
        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem  item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_logout) {
            String selection = "" + STATUS + " = 1";
            ContentValues values=new ContentValues();
            int num=getContentResolver().delete(CONTENT_USER,selection,null);
            startActivity(new Intent(ProductActivity.this,SignInActivity.class));
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}