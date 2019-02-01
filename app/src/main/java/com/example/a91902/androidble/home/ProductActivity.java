package com.example.a91902.androidble.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.JsonToken;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.a91902.androidble.MyApp;
import com.example.a91902.androidble.R;
import com.example.a91902.androidble.Utility.SharedPrefernceUtils;
import com.example.a91902.androidble.login.SignInActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.a91902.androidble.database.Constants.CONTENT_USER;
import static com.example.a91902.androidble.database.Constants.STATUS;

public class ProductActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    GridView gridView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    String string[] = {"Mi A2 (Gold, 4GB RAM, 64GB Storage)", "Mi A3 (Blue, 4GB RAM, 64GB Storage)",
            "Samsung Galaxy A8+ (Black, 6GB RAM, 64GB Storage)", "Redmi 6 Pro (Black, 4GB RAM, 64GB Storage)","Mi A2 (Gold, 4GB RAM, 64GB Storage)"};
    int photo[] = {R.drawable.mobile1, R.drawable.mobile2, R.drawable.mobile3, R.drawable.mobile4,R.drawable.mobile1};
    String productPrice[] = {"16,181", "30,0000", "12,999", "14,999","13,150"};
    ProgressDialog progressDialog;
    TextView name;
    View getView;
    String firstname,lastname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Intent intent=getIntent();
        final String phone=intent.getStringExtra("phone");
        final String id=intent.getStringExtra("id");
        final String expiry=intent.getStringExtra("expiry");
        final String url = "http://192.168.43.110:3000/users?phone=07718924436";
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
        String selection="`"+STATUS+"` = 1";
        Toast.makeText(this, "ouside "+firstname, Toast.LENGTH_SHORT).show();
        Cursor cursor=getContentResolver().query(CONTENT_USER,null,selection,null,null,null);
        Toast.makeText(this, ""+cursor.getCount(), Toast.LENGTH_SHORT).show();
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        navigationView.setNavigationItemSelectedListener(this);

        /*final String[] json = new String[1];
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        progressDialog.dismiss();
                        json[0]=response.toString();
                        try {
                            JSONObject jsonObject=new JSONObject(json[0]);
                            name=(TextView) getView.findViewById(R.id.namegiven);
                            name.setText(jsonObject.getString("firstName")+" "+jsonObject.getString("lastName"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Toast.makeText(ProductActivity.this, ""+response.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(ProductActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
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
        MyApp.getInstance().addToRequestQueue(jsonObjReq, "getRequest");*/
        Toast.makeText(this, ""+SharedPrefernceUtils.getInstance().getString("Status"), Toast.LENGTH_SHORT).show();
        gridView = findViewById(R.id.grid_view);
        ProductAdapter productAdapter = new ProductAdapter(this, photo, string, productPrice);
        gridView.setAdapter(productAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(ProductActivity.this, string[i], Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ProductActivity.this, DescriptionActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}