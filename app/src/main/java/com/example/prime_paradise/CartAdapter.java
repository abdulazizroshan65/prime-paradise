package com.example.prime_paradise;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {

    Context context;
    ArrayList<Cart> carts;
    ImageView imgitem;
    TextView tvName, tvPrice, tvtype, txtCost;
    DB_Opera2 db2;
    ImageButton ibdelete;
    Double totcost = 0.0;
    CardView viewitem;
    RadioButton radioButton;

    public CartAdapter(Context context, ArrayList<Cart> carts){
        this.context = context;
        this.carts = carts;
    }

    @Override
    public int getCount() {
        return carts.size();
    }

    @Override
    public Object getItem(int i) {
        return carts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.cart_view,viewGroup,false);

        db2 = new DB_Opera2(context);
        imgitem = v.findViewById(R.id.imgItem);
        tvName = v.findViewById(R.id.tvNam);
        tvPrice = v.findViewById(R.id.itemPrice);
        tvtype = v.findViewById(R.id.tvType);
        ibdelete = v.findViewById(R.id.ibDelete);
        txtCost = v.findViewById(R.id.txtCost);
        viewitem = v.findViewById(R.id.viewItem);
        radioButton = v.findViewById(R.id.radioButton);

        imgitem.setTag(i);
        tvName.setTag(i);
        tvPrice.setTag(i);
        tvtype.setTag(i);
        ibdelete.setTag(i);
        viewitem.setTag(i);
        radioButton.setTag(i);

        Cart c = carts.get(i);
        Bitmap bitmap = BitmapFactory.decodeByteArray(c.getCovimg(),0,c.getCovimg().length);
        imgitem.setImageBitmap(bitmap);
        tvName.setText(c.getPname()+" (x"+String.valueOf(c.getQty())+")");
        tvtype.setText("Category : "+c.getType());
        tvPrice.setText("රු "+ c.getPrice() + "/=");

        Double cost = c.getPrice() * c.getQty();
        //totcost = totcost + cost;
        //txtCost.setText(totcost.toString());

        return v;
    }
}
