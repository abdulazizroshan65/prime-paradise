package com.example.prime_paradise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Group;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProductexpandActivity extends AppCompatActivity {

    ArrayList<Furniture> furnitures;
    ListView proList;
    DB_Opera db;
    ImageView arrow;
    Group hiddenGroup;
    CardView base_cardview;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_productexpand);

        proList = findViewById(R.id.listProducts);
        base_cardview = findViewById(R.id.base_cardview);
        arrow = findViewById(R.id.show);
        hiddenGroup = findViewById(R.id.card_group);
        db = new DB_Opera(this);

        base_cardview.setOnClickListener(view -> {
            TransitionManager.beginDelayedTransition(base_cardview, new AutoTransition());
            if (hiddenGroup.getVisibility() == View.VISIBLE) {
                hiddenGroup.setVisibility(View.GONE);
                arrow.setImageResource(android.R.drawable.arrow_down_float);
            } else {
                hiddenGroup.setVisibility(View.VISIBLE);
                arrow.setImageResource(android.R.drawable.arrow_up_float);
            }
        });

        furnitures = db.viewFurns();
        if (furnitures.size()>0){
            ProductAdapterExpand productAdapterExpand = new ProductAdapterExpand(this,furnitures); //context-->getActivity
            proList.setAdapter(productAdapterExpand);
        }else {
            Toast.makeText(this,"No Products Found",Toast.LENGTH_SHORT).show();
        }
    }

    public void openProduct(View view){
        int index = (int)view.getTag();
        id = furnitures.get(index).getPid();
        Intent intent = new Intent(ProductexpandActivity.this, FurnitureActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}