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
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class FurnAdapter extends BaseAdapter {
    Context context;
    ArrayList<Furniture> furnitures;
    ImageView iv1, iv2;
    TextView tvName1, tvName2, tvPrice1, tvPrice2;
    ImageButton ibAC, ibAC2;
    CardView card1, card2;

    public FurnAdapter(Context context, ArrayList<Furniture> furnitures){
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
        View v = inflater.inflate(R.layout.product_view,viewGroup,false);

        card1 = v.findViewById(R.id.card1);
        card2 = v.findViewById(R.id.card2);
        iv1 = v.findViewById(R.id.iv1);
        iv2 = v.findViewById(R.id.iv2);
        tvName1 = v.findViewById(R.id.tvName1);
        tvName2 = v.findViewById(R.id.tvName2);
        tvPrice1 = v.findViewById(R.id.tvPrice);
        tvPrice2 = v.findViewById(R.id.tvPrice2);
        ibAC = v.findViewById(R.id.ibAC);
        ibAC2 = v.findViewById(R.id.ibAC2);

        ibAC.setTag(i);
        ibAC2.setTag(i);
        card1.setTag(i);
        card2.setTag(i);

        try{
            if (i==0){
                Furniture furniture = furnitures.get(i);
                Bitmap bitmap = BitmapFactory.decodeByteArray(furniture.getFimg(),0,furniture.getFimg().length);
                iv1.setImageBitmap(bitmap);
                tvName1.setText(furniture.getPname());
                tvPrice1.setText("රු "+furniture.getPrice()+"/=");
                furniture = furnitures.get(i+1);
                bitmap = BitmapFactory.decodeByteArray(furniture.getFimg(),0,furniture.getFimg().length);
                iv2.setImageBitmap(bitmap);
                tvName2.setText(furniture.getPname());
                tvPrice2.setText("රු "+furniture.getPrice()+"/=");
            }else {
                Furniture furniture = furnitures.get(2*i);
                Bitmap bitmap = BitmapFactory.decodeByteArray(furniture.getFimg(),0,furniture.getFimg().length);
                iv1.setImageBitmap(bitmap);
                tvName1.setText(furniture.getPname());
                tvPrice1.setText("රු "+furniture.getPrice()+"/=");
                furniture = furnitures.get(2*i+1); //IndexOutOfBoundsException: Index: 11, Size: 11 (crashes when scrolling down)
                bitmap = BitmapFactory.decodeByteArray(furniture.getFimg(),0,furniture.getFimg().length);
                iv2.setImageBitmap(bitmap);
                tvName2.setText(furniture.getPname());
                tvPrice2.setText("රු "+furniture.getPrice()+"/=");
            }
        }catch (Exception e){
            Toast.makeText(context, "Error : "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return v;
    }
}

