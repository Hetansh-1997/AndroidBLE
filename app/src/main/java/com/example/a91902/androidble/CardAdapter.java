package com.example.a91902.androidble;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a91902.androidble.R;

import org.w3c.dom.Text;

import java.util.List;

public class CardAdapter extends BaseAdapter {

    //List<ProductList> productLists;
    private Context context;
    private int image[];
    private String productName[];
    private String productPrice[];
    private LayoutInflater inflater;
    public CardAdapter(Context context,String productName[],String productPrice[]) {
        this.context = context;
        this.productName=productName;
        this.productPrice=productPrice;
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
            gridView= inflater.inflate(R.layout.product_list_view,null);
        }
        ImageView product_image= gridView.findViewById(R.id.image_view_product_image);
        TextView product_name=  gridView.findViewById(R.id.text_view_product_name);
        TextView product_price= gridView.findViewById(R.id.text_view_product_price);

        product_image.setImageResource(image[i]);
        product_name.setText(productName[i]);
        String pro_price="\u20B9"+" "+productPrice[i];
        product_price.setText(pro_price);
        return gridView;
    }

}

