package com.example.a91902.androidble.home;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.a91902.androidble.R;
import static com.example.a91902.androidble.database.Constants.*;
public class DescriptionActivity extends AppCompatActivity {
    TextView productName,productPrice;
    ViewPager viewPager;
    ImageView imageView;
    String name,price;
    Bundle image;
    int resid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        /*viewPager=findViewById(R.id.image_view_product_image);

        ViewPageAdapter viewPageAdapter=new ViewPageAdapter(this);
        viewPager.setAdapter(viewPageAdapter);*/
        Intent intent=getIntent();
        image=intent.getExtras();
        resid=image.getInt("image");
        name=intent.getStringExtra("Name");
        price=intent.getStringExtra("Price");
        productName=findViewById(R.id.text_product_name);
        imageView=findViewById(R.id.image_view_product);
        productPrice=findViewById(R.id.text_product_price);
        productName.setText(name);
        productPrice.setText(price);
        imageView.setImageResource(resid);
        findViewById(R.id.add_to_cart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues=new ContentValues();
                contentValues.put(PRODUCT_NAME,productName.getText().toString());
                contentValues.put(PRODUCT_PRICE,productPrice.getText().toString());
                contentValues.put(PRODUCT_IMAGE,resid);
                Log.d("Product ",name+" Price "+price+" Name "+productName.getText().toString()+" "+resid);
                getContentResolver().insert(CONTENT_CART,contentValues);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
