package com.example.prime_paradise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WearableActivity extends AppCompatActivity {

    ArrayList<Shoe> shoes;
    ArrayList<Cloth> cloths;
    CardView cardShoe, cardCloth;
    ListView proList;
    DB_Opera db;
    DB_Opera2 db2;
    int id, price;
    String name, toggle="shoe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_wearable);

        proList = findViewById(R.id.listProducts);
        db = new DB_Opera(this);
        db2 = new DB_Opera2(this);
        cardShoe = findViewById(R.id.cardShoe1);
        cardCloth = findViewById(R.id.cardCloth);

        cardShoe.setCardBackgroundColor(Color.parseColor("#DEC6E3"));
        cardCloth.setCardBackgroundColor(Color.WHITE);
        shoes = db.viewShoes();
        if (shoes.size()>0){
            ProductAdapter productAdapter = new ProductAdapter(this,shoes); //context-->getActivity
            proList.setAdapter(productAdapter);
        }else {
            Toast.makeText(this,"No Products Found",Toast.LENGTH_SHORT).show();
        }
    }

    public void shoes(View view){
        toggle = "shoe";
        cardShoe.setCardBackgroundColor(Color.parseColor("#DEC6E3"));
        cardCloth.setCardBackgroundColor(Color.WHITE);
        shoes = db.viewShoes();
        if (shoes.size()>0){
            ProductAdapter productAdapter = new ProductAdapter(this,shoes); //context-->getActivity
            proList.setAdapter(productAdapter);
        }else {
            Toast.makeText(this,"No Products Found",Toast.LENGTH_SHORT).show();
        }
    }

    public void cloth(View view){
        toggle = "cloth";
        cardCloth.setCardBackgroundColor(Color.parseColor("#DEC6E3"));
        cardShoe.setCardBackgroundColor(Color.WHITE);
        cloths = db.viewClothes();
        if (cloths.size()>0){
            ClothAdapter clothAdapter = new ClothAdapter(this,cloths); //context-->getActivity
            proList.setAdapter(clothAdapter);
        }else {
            Toast.makeText(this,"No Products Found",Toast.LENGTH_SHORT).show();
        }
    }

    public void getProduct(View view){
        if (toggle=="shoe"){
            int index = (int)view.getTag();
            if (index==0){
                id = shoes.get(index).getPid();
                name = shoes.get(index).getPname();
                price = (int)shoes.get(index).getPrice();
            }else {
                id = shoes.get(index*2).getPid();
                name = shoes.get(index*2).getPname();
                price = (int)shoes.get(index*2).getPrice();
            }
            Intent intent = new Intent(WearableActivity.this, ShoeActivity.class);
            intent.putExtra("ID", id);
            startActivity(intent);
        }else {
            int index = (int)view.getTag();
            if (index==0){
                id = cloths.get(index).getPid();
                name = cloths.get(index).getPname();
                price = (int)cloths.get(index).getPrice();
            }else {
                id = cloths.get(index*2).getPid();
                name = cloths.get(index*2).getPname();
                price = (int)cloths.get(index*2).getPrice();
            }
            Intent intent = new Intent(WearableActivity.this, ClothActivity.class);
            intent.putExtra("ID", id);
            startActivity(intent);
        }
    }

    public void getProduct2(View view){
        if (toggle=="shoe"){
            int index = (int)view.getTag();
            if (index==0){
                id = shoes.get(index+1).getPid();
                name = shoes.get(index+1).getPname();
                price = (int)shoes.get(index+1).getPrice();
            }else {
                id = shoes.get(index*2+1).getPid();
                name = shoes.get(index*2+1).getPname();
                price = (int)shoes.get(index*2+1).getPrice();
            }
            Intent intent = new Intent(WearableActivity.this, ShoeActivity.class);
            intent.putExtra("ID", id);
            startActivity(intent);
        }else{
            int index = (int)view.getTag();
            if (index==0){
                id = cloths.get(index+1).getPid();
                name = cloths.get(index+1).getPname();
                price = (int)cloths.get(index+1).getPrice();
            }else {
                id = cloths.get(index*2+1).getPid();
                name = cloths.get(index*2+1).getPname();
                price = (int)cloths.get(index*2+1).getPrice();
            }
            Intent intent = new Intent(WearableActivity.this, ClothActivity.class);
            intent.putExtra("ID", id);
            startActivity(intent);
        }
    }

    public void back(View view){
        onBackPressed();
    }
}