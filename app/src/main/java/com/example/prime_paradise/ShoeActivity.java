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
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ShoeActivity extends AppCompatActivity {

    ImageView imgBack, imgMain, imgS1, imgS2, imgS3, imgS4, imgCart;
    TextView imgFav, txtShoeTitle, txtShoePrice, txtCost, tvtemp;
    CardView c1, c2, c3, c4;
    private View view, view2, view3, view4;
    DB_Opera db;
    DB_Opera2 db2;
    Bitmap bitmap, bitmap2, bitmap3, bitmap4;
    int id, count=1, item=1;
    String price;
    Double totcost=0.0;
    ArrayList<Cart> carts;
    ArrayList<Wishlist> wishlists;
    byte[] covimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_shoe);

        imgBack = findViewById(R.id.imgBack);
        imgMain = findViewById(R.id.imgShoe);
        imgFav = findViewById(R.id.ivfav);
        imgS1 = findViewById(R.id.imgShoe1);
        imgS2 = findViewById(R.id.imgShoe2);
        imgS3 = findViewById(R.id.imgShoe3);
        imgS4 = findViewById(R.id.imgShoe4);
        imgCart = findViewById(R.id.imgCart);
        c1 = findViewById(R.id.cardShoe1);
        c2 = findViewById(R.id.cardShoe2);
        c3 = findViewById(R.id.cardShoe3);
        c4 = findViewById(R.id.cardShoe4);
        txtShoeTitle = findViewById(R.id.txtShoeTitle);
        txtShoePrice = findViewById(R.id.itemPrice);
        txtCost = findViewById(R.id.txtCost);
        tvtemp = findViewById(R.id.tvtemp);
        view = findViewById(R.id.view);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);
        view4 = findViewById(R.id.view4);
        db = new DB_Opera(this);
        db2 = new DB_Opera2(this);

        id = getIntent().getIntExtra("ID",0);

        /*Wishlist w = new Wishlist();
        w.setPid(id);
        w = db2.searchWish(w);
        if (w != null){
            imgFav.setText("🤍");
        }else {
            imgFav.setText("❤");
        }

        for (item=1;item<1000;item++){
            w.setItemno(item);
            w = db2.searchWish(w);
            if (w != null){
                break;
            }else {
                //loop continus
            }
        }*/

        Shoe s = new Shoe();
        s.setPid(id);
        s = db.searchShoe(s);
        try {
            if (s != null){
                txtShoeTitle.setText(s.getPname());
                txtShoePrice.setText(String.valueOf(s.getPrice()));
                price = String.valueOf(s.getPrice());
                txtCost.setText(price+"(x1)");
                tvtemp.setText(price);
                bitmap = BitmapFactory.decodeByteArray(s.getSimg(),0,s.getSimg().length);
                imgMain.setImageBitmap(bitmap);
                imgS1.setImageBitmap(bitmap);
                bitmap2 = BitmapFactory.decodeByteArray(s.getSimg2(),0,s.getSimg2().length);
                imgS2.setImageBitmap(bitmap2);
                bitmap3 = BitmapFactory.decodeByteArray(s.getSimg3(),0,s.getSimg3().length);
                imgS3.setImageBitmap(bitmap3);
                bitmap4 = BitmapFactory.decodeByteArray(s.getSimg4(),0,s.getSimg4().length);
                imgS4.setImageBitmap(bitmap4);
                view.setBackgroundColor(s.getScolor());
                view2.setBackgroundColor(s.getScolor2());
                view3.setBackgroundColor(s.getScolor3());
                view4.setBackgroundColor(s.getScolor4());
            }
        }catch (Exception e){
            Toast.makeText(this,"System ran into an error",Toast.LENGTH_SHORT).show();
            e.getStackTrace();
        }
    }

    public void c1(View view){
        c1.setCardBackgroundColor(Color.parseColor("#DEC6E3"));
        c2.setCardBackgroundColor(Color.WHITE);
        c3.setCardBackgroundColor(Color.WHITE);
        c4.setCardBackgroundColor(Color.WHITE);
        imgS1.setAlpha((float) 1.0);
        imgS2.setAlpha((float) 0.3);
        imgS3.setAlpha((float) 0.3);
        imgS4.setAlpha((float) 0.3);
        imgMain.setImageBitmap(bitmap);
    }

    public void c2(View view){
        c1.setCardBackgroundColor(Color.WHITE);
        c2.setCardBackgroundColor(Color.parseColor("#DEC6E3"));
        c3.setCardBackgroundColor(Color.WHITE);
        c4.setCardBackgroundColor(Color.WHITE);
        imgS1.setAlpha((float) 0.3);
        imgS2.setAlpha((float) 1.0);
        imgS3.setAlpha((float) 0.3);
        imgS4.setAlpha((float) 0.3);
        imgMain.setImageBitmap(bitmap2);
    }

    public void c3(View view){
        c1.setCardBackgroundColor(Color.WHITE);
        c2.setCardBackgroundColor(Color.WHITE);
        c3.setCardBackgroundColor(Color.parseColor("#DEC6E3"));
        c4.setCardBackgroundColor(Color.WHITE);
        imgS1.setAlpha((float) 0.3);
        imgS2.setAlpha((float) 0.3);
        imgS3.setAlpha((float) 1.0);
        imgS4.setAlpha((float) 0.3);
        imgMain.setImageBitmap(bitmap3);
    }

    public void c4(View view){
        c1.setCardBackgroundColor(Color.WHITE);
        c2.setCardBackgroundColor(Color.WHITE);
        c3.setCardBackgroundColor(Color.WHITE);
        c4.setCardBackgroundColor(Color.parseColor("#DEC6E3"));
        imgS1.setAlpha((float) 0.3);
        imgS2.setAlpha((float) 0.3);
        imgS3.setAlpha((float) 0.3);
        imgS4.setAlpha((float) 1.0);
        imgMain.setImageBitmap(bitmap4);
    }

    public void wishlists(View view){
        /*Wishlist w = new Wishlist();
        if(imgFav.getText().toString().equals("🤍")){
            w.setItemno(item);
            w.setUname("selena_g");//////////////
            w.setPid(id);
            w.setType("Shoe");
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
            w.setPid(id);
            if (db2.deleteWish(w)>0){
                Toast.makeText(this,"Product Removed from Wishlist",Toast.LENGTH_SHORT).show();
                imgFav.setText("🤍");
            }
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
                ProgressDialog pd = new ProgressDialog(ShoeActivity.this);
                pd.setTitle("Order Placement");
                pd.setMessage("Processing...");
                pd.show();
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Double icost = Double.parseDouble(tvtemp.getText().toString());
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
                        o.setOrderid(6);
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

    public void addCart(View view){
        count++;
        totcost = Double.parseDouble(price) * count;
        txtCost.setText(totcost+"(x"+count+")");
        tvtemp.setText(totcost.toString());
    }

    public void minusCart(View view){
        if (count==1){
            //does nothing
        }else {
            count--;
            totcost = Double.parseDouble(price) * count;
            txtCost.setText(totcost+"(x"+count+")");
            tvtemp.setText(totcost.toString());
        }
    }

    public void addtocart(View view){
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
        c.setType("Shoe");
        c.setPname(txtShoeTitle.getText().toString());
        Double totcost = Double.parseDouble(price) * count;
        c.setPrice(totcost);
        c.setQty(count);
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,arrayOutputStream);
        covimg = arrayOutputStream.toByteArray();
        c.setCovimg(covimg);
        if (db2.addCart(c)>0){
            Toast.makeText(this,"Product Added to Cart",Toast.LENGTH_SHORT).show();
        }
    }

    public void back(View view){
        onBackPressed();
    }
}