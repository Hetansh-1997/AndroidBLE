package com.example.a91902.androidble.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.a91902.androidble.database.Constants.*;

public class MyDbHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "user";
    public static String DATABASE_CART = "cart";
    public static int DATABASE_VERSION = 15;
    public static String DATABASE_USER = "CREATE TABLE " + TABLE_USER + "(" + ID + " TEXT, " + NAME + " TEXT, " + PHONE_NUMBER + " TEXT, " + EXPIRY + " TEXT, " + STATUS + " TEXT )";
    public static String DATABASE_CART_TABLE = "CREATE TABLE " + TABLE_CART + "(" + PRODUCT_NAME + " TEXT, " + PRODUCT_PRICE + " TEXT, " + PRODUCT_IMAGE + " INT )";


    public MyDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db=this.getWritableDatabase();
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL(DATABASE_USER);
        db.execSQL(DATABASE_CART_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {

            db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_CART);
        }
        onCreate(db);
    }
}

