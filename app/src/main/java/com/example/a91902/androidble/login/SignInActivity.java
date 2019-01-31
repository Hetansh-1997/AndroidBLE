package com.example.a91902.androidble.login;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.a91902.androidble.Bluetooth.BeaconConnection;
import com.example.a91902.androidble.R;
public class SignInActivity extends AppCompatActivity {

    TextView textViewPhone,textViewPassword;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        textViewPhone=(TextView) findViewById(R.id.edit_text_phone_number);
        textViewPassword=(TextView) findViewById(R.id.edit_text_password);
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
                    startActivity(new Intent(SignInActivity.this,BeaconConnection.class));
                }
            }
        });
    }

    public void dialogbox(String text){
        builder=new AlertDialog.Builder(this);
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
