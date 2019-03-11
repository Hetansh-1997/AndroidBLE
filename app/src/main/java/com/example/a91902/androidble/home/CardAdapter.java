package com.example.a91902.androidble.home;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a91902.androidble.R;

import org.w3c.dom.Text;

import java.util.List;

import static com.example.a91902.androidble.database.Constants.CONTENT_CART;
import static com.example.a91902.androidble.database.Constants.PHONE_NUMBER;
import static com.example.a91902.androidble.database.Constants.PRODUCT_NAME;
import static com.example.a91902.androidble.database.Constants.PRODUCT_PRICE;

public class CardAdapter extends BaseAdapter {

    //List<ProductList> productLists;
    public Context context;
    private String productName[];
    private String productPrice[];
    private int productImage[];
    private LayoutInflater inflater;
    int j;
    Button button;
    public CardAdapter(Context context,String productName[],String productPrice[],int productImage[]) {
        this.context = context;
        this.productName=productName;
        this.productPrice=productPrice;
        this.productImage=productImage;
    }

    @Override
    public int getCount() {
        return productName.length;
    }

    @Override
    public Object getItem(int i) {
        return productName[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View gridView=view;
        if(view==null){
            inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView= inflater.inflate(R.layout.cart_list_view,null);
        }
        ImageView product_image= gridView.findViewById(R.id.image_view_product_image);
        TextView product_name=  gridView.findViewById(R.id.text_view_product_name);
        TextView product_price= gridView.findViewById(R.id.text_view_product_price);
        j=i;
        product_name.setText(productName[i]);
        String pro_price="\u20B9"+" "+productPrice[i];
        product_price.setText(pro_price);
        product_image.setImageResource(productImage[i]);
        gridView.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selection = "" + PRODUCT_NAME + " = '"+productName[j]+"'";
                context.getContentResolver().delete(CONTENT_CART,selection,null);
                notifyDataSetChanged();
                Intent i=new Intent(context,CartActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        return gridView;
    }

}

