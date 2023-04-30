package com.example.prime_paradise;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Group;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import java.util.ArrayList;

public class ProductAdapterExpand extends BaseAdapter {
    Context context;
    ArrayList<Furniture> furnitures;
    ImageView ivProduct,arrow;
    Group hiddenGroup;
    TextView tvPname, txtCost;
    Button btnBuy, btnCart;
    CardView base_cardview;

    public ProductAdapterExpand(Context context, ArrayList<Furniture> furnitures){
        this.context = context;
        this.furnitures = furnitures;
    }

    @Override
    public int getCount() {
        return furnitures.size();
    }

    @Override
    public Object getItem(int i) {
        return furnitures.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.productview_expand,viewGroup,false);

        base_cardview = v.findViewById(R.id.base_cardview);
        ivProduct = v.findViewById(R.id.ivProduct);
        arrow = v.findViewById(R.id.show);
        hiddenGroup = v.findViewById(R.id.card_group);
        tvPname = v.findViewById(R.id.tvPname);
        txtCost = v.findViewById(R.id.txtCost);
        btnBuy = v.findViewById(R.id.btnBuy);
        btnCart = v.findViewById(R.id.btnCart);

        btnBuy.setTag(i);
        btnCart.setTag(i);
        base_cardview.setTag(i);
        hiddenGroup.setTag(i);
        arrow.setTag(i);

        Furniture furniture = furnitures.get(i);
        Bitmap bitmap = BitmapFactory.decodeByteArray(furniture.getFimg(),0,furniture.getFimg().length);
        ivProduct.setImageBitmap(bitmap);
        tvPname.setText(furniture.getPid()+". "+furniture.getPname());
        txtCost.setText("Rs "+furniture.getPrice()+"/=");

        return v;
    }
}