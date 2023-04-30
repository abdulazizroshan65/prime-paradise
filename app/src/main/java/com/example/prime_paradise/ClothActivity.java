package com.example.prime_paradise;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ClothActivity extends AppCompatActivity {

    ImageView imgBack, imgMain, imgCart, imgrpm;
    TextView imgFav, txtShoeTitle, txtShoePrice, txtCost, tvtemp2;
    CardView c1, c2, c3;
    View view, view2, view3;
    DB_Opera db;
    DB_Opera2 db2;
    Bitmap bitmap;
    int id, count=1;
    String price;
    Double totcost;
    ArrayList<Cart> carts;
    byte[] covimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_cloth);

        imgBack = findViewById(R.id.imgBack);
        imgMain = findViewById(R.id.imgShoe);
        imgFav = findViewById(R.id.fav13);
        imgrpm = findViewById(R.id.imgrpm);
        imgCart = findViewById(R.id.imgCart);
        c1 = findViewById(R.id.cardShoe1);
        c2 = findViewById(R.id.cardShoe2);
        c3 = findViewById(R.id.cardShoe3);
        txtShoeTitle = findViewById(R.id.txtShoeTitle);
        txtShoePrice = findViewById(R.id.itemPrice);
        txtCost = findViewById(R.id.txtCost);
        tvtemp2 = findViewById(R.id.tvtemp2);
        view = findViewById(R.id.view);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);
        db = new DB_Opera(this);
        db2 = new DB_Opera2(this);

        id = getIntent().getIntExtra("ID",0);

        Cloth c = new Cloth();
        c.setPid(id);
        c = db.searchCloth(c);
        try {
            if (c != null){
                txtShoeTitle.setText(c.getPname());
                txtShoePrice.setText(String.valueOf(c.getPrice()));
                price = String.valueOf(c.getPrice());
                txtCost.setText(price+"(x1)");
                bitmap = BitmapFactory.decodeByteArray(c.getCimg(),0,c.getCimg().length);
                imgMain.setImageBitmap(bitmap);
            }
        }catch (Exception e){
            Toast.makeText(this,"System ran into an error",Toast.LENGTH_SHORT).show();
            e.getStackTrace();
        }
    }

    public void wishlist(View view){
        /*try {
            Wishlist w = new Wishlist();
            w.setPid(id);
            if(imgFav.getText().toString().equals("🤍")){
                wishlists = db2.viewWish();
                int itemno = wishlists.size()+1;
                w.setItemno(itemno);
                w.setUname("selena_g");//////////////
                w.setPid(id);
                w.setType("Cloth");
                w.setPname(txtShoeTitle.getText().toString());
                w.setPrice(Double.parseDouble(price));
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,arrayOutputStream);
                covimg = arrayOutputStream.toByteArray();
                w.setCoverimg(covimg);
                if (db2.addWish(w)>0){
                    Toast.makeText(this,"Product Added to Wishlist",Toast.LENGTH_SHORT).show();
                    imgFav.setText("❤");
                }
            }else if(imgFav.getText().toString().equals("❤")){
                if (db2.deleteWish(w)>0){
                    Toast.makeText(this,"Product Removed from Wishlist",Toast.LENGTH_SHORT).show();
                    imgFav.setText("🤍");
                }
            }
        }catch (Exception e){
            e.getStackTrace();
        }*/
        if (imgFav.getText().toString().equals("❤")){
            imgFav.setText("🤍");
        }else {
            imgFav.setText("❤");
        }
    }

    public void buynow(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Order");
        builder.setMessage("Are you sure you want to purchase this product?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ProgressDialog pd = new ProgressDialog(ClothActivity.this);
                pd.setTitle("Order Placement");
                pd.setMessage("Processing...");
                pd.show();
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Double icost = Double.parseDouble(tvtemp2.getText().toString());
                        Order o = new Order();
                        int x;
                        for (x=1;x>20;x++){
                            o.setOrderid(x);
                            o = db2.searchOrder(o);
                            try {
                                if (o != null){
                                    ////////////
                                }else {
                                    break;
                                }
                            }catch (Exception ex){
                                Toast.makeText(getBaseContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                        o.setOrderid(4);
                        o.setUname("selena_g");
                        o.setItems(1);
                        o.setCost(icost);
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

    public void appopen(View view){
        Intent intent = new Intent(ClothActivity.this, WebViewActivity.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Fit On");
        builder.setMessage("Do you wish to fit on our clothes using a new avatar?");
        builder.setPositiveButton("NEW", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                intent.putExtra(WebViewActivity.IS_CREATE_NEW, true);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("EXISTING", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                intent.putExtra(WebViewActivity.IS_CREATE_NEW, false);
                startActivity(intent);
            }
        });
        builder.show();
    }

    public void addCart(View view){
        count++;
        totcost = Double.parseDouble(price) * count;
        txtCost.setText(totcost+"(x"+count+")");
    }

    public void minusCart(View view){
        if (count==1){
            //does nothing
        }else {
            count--;
            totcost = Double.parseDouble(price) * count;
            txtCost.setText(totcost+"(x"+count+")");
        }
    }

    public void addtocart(View view){
        try {
            Cart c = new Cart();
            carts = db2.viewCart();
            int number = carts.size()+1;
            int itemno=0;

            if (carts.size()>0){
                for (int x=0; x<number; x++){
                    int y = x + 1;
                    c.setPid(y);
                    c = db2.searchCart(c);
                    try {
                        if (c != null){
                            if (c.getItemno() != y){
                                itemno = y;
                            }
                        }
                    }catch (Exception ex){
                        Toast.makeText(this,"Unable to Add to Cart",Toast.LENGTH_SHORT).show();
                    }
                }
            }else {
                itemno=1;
            }

            c.setItemno(itemno);
            c.setUname("selena_g");//////////////
            c.setPid(id);
            c.setType("Cloth");
            c.setPname(txtShoeTitle.getText().toString());
            c.setPrice(Double.parseDouble(price));
            c.setQty(count);
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,arrayOutputStream);
            covimg = arrayOutputStream.toByteArray();
            c.setCovimg(covimg);
            if (db2.addCart(c)>0){
                Toast.makeText(this,"Product Added to Cart",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){
            Toast.makeText(this,"Unable to Add to Cart",Toast.LENGTH_SHORT).show();
        }
    }

    public void back(View view){ onBackPressed(); }
}