package com.example.a91902.androidble.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import com.example.a91902.androidble.R;
import com.example.a91902.androidble.Utility.SharedPrefernceUtils;
import com.example.a91902.androidble.login.SignInActivity;

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
                if(SharedPrefernceUtils.getInstance().getString("register_user").equalsIgnoreCase("")){
                    startActivity(new Intent(SplashActivity.this, SignInActivity.class));
                }else{
                    //startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                }
                finish();
            }

        },5000);
    }
}
