package com.example.a91902.androidble.database;

import android.net.Uri;

public class Constants {
    //these are very important.every database should have this
    private static final String CONTENT_PROTOCOL= "content://";
    private static final String AUTHORITY= "com.example.a91902.androidble";
    private static final String PATH_USER= "_userdata";
    public static final String PATH_CART= "_usercart";
    public static final String TABLE_USER= "tbl_user";
    public static final String TABLE_CART="tbl_cart";
    //columns of table
    /**public static  String USERNAME= "userName";**/
    public static  String NAME= "name";
    public static  String PHONE_NUMBER= "phoneNumber";
    public static  String ID= "id";
    public static String EXPIRY="expiry";
    public static  String STATUS = "status";
    public static String PRODUCT_NAME="product_name";
    public static String PRODUCT_PRICE="product_price";
    public static String PRODUCT_IMAGE="product_image";

    //uri
    public static final Uri CONTENT_USER = Uri.parse(CONTENT_PROTOCOL+AUTHORITY+"/"+PATH_USER);
    public static final Uri CONTENT_CART = Uri.parse(CONTENT_PROTOCOL+AUTHORITY+"/"+PATH_CART);

}
