package com.example.a91902.androidble.home;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.example.a91902.androidble.R;
import static com.example.a91902.androidble.database.Constants.*;

public class CartActivity extends AppCompatActivity {
    String string1[]=new String[10];
    String productPrice[]=new String[10];
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Cursor cursor=getContentResolver().query(CONTENT_CART,null,null,null,null);
        cursor.moveToFirst();
        for (int i=0;i<cursor.getCount();i++){
            string1[i]=cursor.getString(cursor.getColumnIndex(PRODUCT_NAME));
            productPrice[i]=cursor.getString(cursor.getColumnIndex(PRODUCT_PRICE));
            cursor.moveToNext();
        }
        gridView = findViewById(R.id.grid_view_card);
        ProductAdapter productAdapter = new ProductAdapter(this,string1, productPrice,"Cart");
        gridView.setAdapter(productAdapter);
    }
}
