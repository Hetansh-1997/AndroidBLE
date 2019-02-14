package com.example.a91902.androidble.home;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;


import com.example.a91902.androidble.CardAdapter;
import com.example.a91902.androidble.R;
import static com.example.a91902.androidble.database.Constants.*;

public class CartActivity extends AppCompatActivity {
    String string1[];
    String productPrice[];
    int images[];
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Cursor cursor=getContentResolver().query(CONTENT_CART,null,null,null,null);
        string1=new String[cursor.getCount()];
        productPrice=new String[cursor.getCount()];
        images=new int[cursor.getCount()];
        cursor.moveToFirst();
        for (int i=0;i<cursor.getCount();i++){
            string1[i]=cursor.getString(cursor.getColumnIndex(PRODUCT_NAME));
            productPrice[i]=cursor.getString(cursor.getColumnIndex(PRODUCT_PRICE));
            images[i]=cursor.getInt(cursor.getColumnIndex(PRODUCT_IMAGE));
            cursor.moveToNext();
        }
        gridView = findViewById(R.id.grid_view_card);
        CardAdapter productAdapter = new CardAdapter(this,string1, productPrice,images);
        gridView.setAdapter(productAdapter);
        cursor.close();
    }
}
