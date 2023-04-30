package com.example.prime_paradise;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CameraActivity extends Activity {

    // Define the pic id
    private static final int pic_id = 123;

    ImageView click_image_id, preview;
    DB_Opera db;
    Bitmap bitmap;
    int id=1, currentImg;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_camera);

        preview = (ImageView)findViewById(R.id.preview);
        click_image_id = (ImageView)findViewById(R.id.click_image);
        db = new DB_Opera(this);

        id = getIntent().getIntExtra("ID", 0);
        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera_intent, pic_id);

        click_image_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera_intent, pic_id);
            }
        });
    }

    // This method will help to retrieve the image
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == pic_id) {
            Bitmap photo = (Bitmap)data.getExtras().get("data");
            preview.setImageBitmap(photo);

            Furniture f = new Furniture();
            f.setPid(id);
            f = db.searchFurn(f);
            try {
                if (f != null) {
                    bitmap = BitmapFactory.decodeByteArray(f.getFimg(), 0, f.getFimg().length);
                    click_image_id.setImageBitmap(bitmap);
                    currentImg = 1;
                }
            } catch (Exception e) {
                Toast.makeText(this, "System couldn't retrieve furniture", Toast.LENGTH_SHORT).show();
                e.getStackTrace();
            }
        }
    }

    public void switchNext(View v){
        Furniture f = new Furniture();
        f.setPid(id);
        f = db.searchFurn(f);
        try {
            if (f != null) {
                if (currentImg == 1){
                    bitmap = BitmapFactory.decodeByteArray(f.getFimg2(), 0, f.getFimg2().length);
                    currentImg = 2;
                }else if (currentImg == 2){
                    bitmap = BitmapFactory.decodeByteArray(f.getFimg3(), 0, f.getFimg3().length);
                    currentImg = 3;
                }else {
                    bitmap = BitmapFactory.decodeByteArray(f.getFimg(), 0, f.getFimg().length);
                    currentImg = 1;
                }

                click_image_id.setImageBitmap(bitmap);
            }
        } catch (Exception e) {
            Toast.makeText(this, "System couldn't retrieve furniture", Toast.LENGTH_SHORT).show();
            e.getStackTrace();
        }
    }

    public void switchPrevious(View v){
        Furniture f = new Furniture();
        f.setPid(id);
        f = db.searchFurn(f);
        try {
            if (f != null) {
                if (currentImg == 1){
                    bitmap = BitmapFactory.decodeByteArray(f.getFimg3(), 0, f.getFimg3().length);
                    currentImg = 3;
                }else if (currentImg == 2){
                    bitmap = BitmapFactory.decodeByteArray(f.getFimg(), 0, f.getFimg().length);
                    currentImg = 1;
                }else {
                    bitmap = BitmapFactory.decodeByteArray(f.getFimg2(), 0, f.getFimg2().length);
                    currentImg = 2;
                }

                click_image_id.setImageBitmap(bitmap);
            }
        } catch (Exception e) {
            Toast.makeText(this, "System couldn't retrieve furniture", Toast.LENGTH_SHORT).show();
            e.getStackTrace();
        }
    }

    public void moveRight(View v){
        float right = click_image_id.getX();
        right = right + 10;
        click_image_id.setX(right);
    }

    public void moveLeft(View v){
        float left = click_image_id.getX();
        left = left - 10;
        click_image_id.setX(left);
    }

    public void moveUp(View v){
        float up = click_image_id.getY();
        up = up - 10;
        click_image_id.setY(up);
    }

    public void moveDown(View v){
        float down = click_image_id.getY();
        down = down + 10;
        click_image_id.setY(down);
    }
    public void back(View view) { onBackPressed(); }
}