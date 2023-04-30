package com.example.prime_paradise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FurnActivity extends AppCompatActivity {

    ArrayList<Furniture> furnitures;
    ListView proList;
    DB_Opera db;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_furn);

        proList = findViewById(R.id.listProducts);
        db = new DB_Opera(this);

        furnitures = db.viewFurns();
        if (furnitures.size()>0){
            FurnAdapter furnAdapter = new FurnAdapter(this,furnitures); //context-->getActivity
            proList.setAdapter(furnAdapter);
        }else {
            Toast.makeText(this,"No Products Found",Toast.LENGTH_SHORT).show();
        }
    }

    public void getProduct(View view){
        int index = (int)view.getTag();
        if (index==0){
            id = furnitures.get(index).getPid();
        }else {
            id = furnitures.get(index * 2).getPid();
        }
        Intent intent = new Intent(FurnActivity.this, FurnitureActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }

    public void getProduct2(View view){
        int index = (int)view.getTag();
        if (index==0){
            id = furnitures.get(index + 1).getPid();
        }else {
            id = furnitures.get(index * 2 + 1).getPid();
        }
        Intent intent = new Intent(FurnActivity.this, FurnitureActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }

    public void back(View view){
        onBackPressed();
    }
}