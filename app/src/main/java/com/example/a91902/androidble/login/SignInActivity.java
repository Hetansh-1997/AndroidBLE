package com.example.a91902.androidble.login;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.a91902.androidble.MyApp;
import com.example.a91902.androidble.R;
import com.example.a91902.androidble.home.ProductActivity;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.a91902.androidble.database.Constants.*;

public class SignInActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    TextView textViewPhone,textViewPassword;
    AlertDialog.Builder builder;
    String number,id,expiry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        textViewPhone= findViewById(R.id.edit_text_phone_number);
        textViewPassword= findViewById(R.id.edit_text_password);
        final String[] json = new String[1];
        final String url = "http://192.168.1.103:3000/tokens";
        findViewById(R.id.text_view_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phone=textViewPhone.getText().toString();
                String password=textViewPassword.getText().toString();
                if (phone.isEmpty() && password.isEmpty()){
                    dialogbox("Enter Phone Number and Password");
                }else if(password.isEmpty()){
                    dialogbox("Enter Phone Password");
                }else{
                    progressDialog=new ProgressDialog(SignInActivity.this);
                    progressDialog.setMessage("Validating");
                    progressDialog.show();
                    JSONObject postparams = new JSONObject();
                    try {
                        postparams.put("phone", phone);
                        postparams.put("password", password);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                            url, postparams,
                            new Response.Listener() {
                                @Override
                                public void onResponse(Object response) {
                                    json[0] =response.toString();
                                        try {
                                            JSONObject jsonObject=new JSONObject(json[0]);
                                            number=jsonObject.getString("phone");
                                            id=jsonObject.getString("id");
                                            expiry=jsonObject.getString("expiry");
                                            ContentValues values=new ContentValues();
                                            values.put(NAME,"");
                                            values.put(PHONE_NUMBER,number);
                                            values.put(STATUS,"1");
                                            values.put(ID,id);
                                            values.put(EXPIRY,expiry);
                                            getContentResolver().insert(CONTENT_USER,values);
                                            Intent intent=new Intent(SignInActivity.this,ProductActivity.class);
                                            intent.putExtra("id",id);
                                            intent.putExtra("phone",number);
                                            intent.putExtra("activity","Sign");
                                            startActivity(intent);
                                            finish();
                                            progressDialog.dismiss();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    dialogbox("Please Turn on the internet");
                                }
                            });
                    MyApp.getInstance().addToRequestQueue(jsonObjReq, "getRequest");
                }
            }
        });
    }

    public void dialogbox(String text){
        builder=new AlertDialog.Builder(SignInActivity.this);
        builder.setMessage(text)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.setTitle("Error");
        alertDialog.show();
    }
}
