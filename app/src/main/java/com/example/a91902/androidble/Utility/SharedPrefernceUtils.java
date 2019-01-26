package com.example.a91902.androidble.Utility;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefernceUtils {

    private static String PREFERNCE_NAME="shopeasy";
    private static SharedPrefernceUtils sharedPrefernceUtils;
    private SharedPreferences sharedPreferences;

    SharedPrefernceUtils(Context context){

        PREFERNCE_NAME=PREFERNCE_NAME+context.getPackageName();
        this.sharedPreferences=context.getSharedPreferences(PREFERNCE_NAME,Context.MODE_PRIVATE);
    }

    public static SharedPrefernceUtils getInstance(){
        if(sharedPrefernceUtils == null) {
            sharedPrefernceUtils = new SharedPrefernceUtils(MyApp.getContext());
        }
        return sharedPrefernceUtils;
    }
    public void saveString(String key,String value){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public String getString(String key,String defValue){
        return sharedPreferences.getString(key,defValue);
    }
    public String getString(String key){
        return sharedPreferences.getString(key,"");
    }
}
