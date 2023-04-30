package com.example.prime_paradise;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
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

public class CartActivity extends AppCompatActivity {

    ListView cartlist;
    ArrayList<Cart> carts;
    DB_Opera2 db2;
    TextView txtCost;
    Double totcost = 0.0;
    int count, temp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_cart);

        cartlist = findViewById(R.id.listCart);
        db2 = new DB_Opera2(this);
        txtCost = findViewById(R.id.txtCost);

        display();
    }

    public void display(){
        try {
            carts = db2.viewCart();
            if (carts.size() > 0) {
                CartAdapter cartAdapter = new CartAdapter(this, carts); //context-->getActivity
                cartlist.setAdapter(cartAdapter);
            }
        }catch (Exception ex){
            Toast.makeText(this,"No Items Found",Toast.LENGTH_SHORT).show();
        }
    }

    public void clicked(View view){
        int index = (int)view.getTag();
        Double cost = carts.get(index).getPrice() * carts.get(index).getQty();
        count = carts.get(index).getQty();
        temp++;
        totcost = totcost + cost;
        txtCost.setText(totcost.toString());
    }

    public void del(View view){
        int index = (int)view.getTag();
        int no = carts.get(index).getItemno();
        Cart c = new Cart();
        c.setItemno(no);
        if (db2.deleteCart(c)>0){
            Toast.makeText(this,"Item Removed from Cart",Toast.LENGTH_SHORT).show();
            display();
        }
    }

    public void order(View view){
        Intent intent = new Intent(CartActivity.this, MainActivity.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Order");
        builder.setMessage("Are you sure you want to place the order?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ProgressDialog pd = new ProgressDialog(CartActivity.this);
                pd.setTitle("Order Placement");
                pd.setMessage("Processing...");
                pd.show();
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(intent);

                        Order o = new Order();
                        ArrayList<Order> orders;
                        orders = db2.viewOrder();
                        int number = orders.size()+1;
                        int itemno=0;
                        if (orders.size()>0){
                            for (int x=0; x<number; x++){
                                int y = x + 1;
                                try {
                                    o.setOrderid(y);
                                    o = db2.searchOrder(o);
                                    if (o != null){
                                        itemno = y;
                                    }
                                }catch (Exception ex){
                                    //Toast.makeText(this,"Unable to Add to Cart",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }else {
                            itemno=1;
                        }

                        o.setOrderid(4);
                        o.setUname("selena_g");
                        o.setItems(temp);
                        o.setCost(totcost);
                        if (db2.addOrder(o)>0){
                            Toast.makeText(getBaseContext(),"Order Placed Successfully",Toast.LENGTH_SHORT).show();
                        }
                    }
                },1500);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { }
        });
        builder.show();
    }
}