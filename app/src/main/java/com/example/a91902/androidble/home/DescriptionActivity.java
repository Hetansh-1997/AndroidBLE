package com.example.a91902.androidble.home;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.a91902.androidble.R;

public class DescriptionActivity extends AppCompatActivity {

    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        viewPager=(ViewPager) findViewById(R.id.image_view_product_image);

        ViewPageAdapter viewPageAdapter=new ViewPageAdapter(this);
        viewPager.setAdapter(viewPageAdapter);

        /*Spinner quantitySpinner=(Spinner) findViewById(R.id.spinner_quantity);
        ArrayAdapter<String> quantityArrayAdapter=new ArrayAdapter<String>(DescriptionActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.quantity));
        quantityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(quantityArrayAdapter);

        Spinner sizeSpinner=(Spinner) findViewById(R.id.spinner_size);
        ArrayAdapter<String> sizeArrayAdapter=new ArrayAdapter<String>(DescriptionActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.size));
        sizeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(sizeArrayAdapter);*/


    }
}
