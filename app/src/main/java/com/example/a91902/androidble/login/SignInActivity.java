package com.example.a91902.androidble.login;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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

public class SignInActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    TextView textViewPhone,textViewPassword;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        textViewPhone= findViewById(R.id.edit_text_phone_number);
        textViewPassword= findViewById(R.id.edit_text_password);
        final String[] json = new String[1];
        final String url = "https://ecom-kalpesh.herokuapp.com/notes";
        findViewById(R.id.text_view_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone=textViewPhone.getText().toString();
                String password=textViewPassword.getText().toString();
                if (phone.isEmpty() && password.isEmpty()){
                    dialogbox("Enter Phone Number and Password");
                }else if(password.isEmpty()){
                    dialogbox("Enter Phone Password");
                }else{
                    progressDialog=new ProgressDialog(SignInActivity.this);
                    progressDialog.setMessage("Validating");
                    progressDialog.show();
                    JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                            url, null,
                            new Response.Listener() {
                                @Override
                                public void onResponse(Object response) {
                                    json[0] =response.toString();
                                        try {
                                            JSONObject jsonObject=new JSONObject(json[0]);
                                            String str =jsonObject.getString("Title");
                                            if(str.equals("this is an Awesome WebPage")){
                                                startActivity(new Intent(SignInActivity.this,ProductActivity.class));
                                                progressDialog.dismiss();
                                                finish();
                                            }
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
