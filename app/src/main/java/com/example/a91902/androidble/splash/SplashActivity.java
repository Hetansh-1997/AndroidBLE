package com.example.a91902.androidble.splash;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.a91902.androidble.R;
import com.example.a91902.androidble.Utility.SharedPrefernceUtils;
import com.example.a91902.androidble.home.ProductActivity;
import com.example.a91902.androidble.login.SignInActivity;

import static com.example.a91902.androidble.database.Constants.*;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }
    public void init(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String selection="`"+STATUS+"` = 1";
                Cursor cursor=getContentResolver().query(CONTENT_USER, null, selection, null, null);
                Toast.makeText(SplashActivity.this, ""+cursor.getCount(), Toast.LENGTH_SHORT).show();
                if(cursor.getCount()>0){
                    startActivity(new Intent(SplashActivity.this,ProductActivity.class));
                    cursor.close();
                    finish();
                }else{
                    startActivity(new Intent(SplashActivity.this,SignInActivity.class));
                    cursor.close();
                    finish();
                }

            }

        },1000);
    }
}
