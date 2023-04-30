package com.example.prime_paradise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class NextActivity extends AppCompatActivity  {

    EditText txtUser, txtPass, txtCpass;
    TextView tvPassDetails, tvPassStrong;
    String fname,eMail,birthDate,sex;
    String uname,pass,cpass;
    int length;
    DB_Opera database;
    ImageView imgProfile;
    byte[] profilepic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_next);
        database = new DB_Opera(this);

        imgProfile = findViewById(R.id.imgProfile);
        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPass);
        txtCpass = findViewById(R.id.txtCpass);
        tvPassStrong = findViewById(R.id.tvPassStrong);
        tvPassDetails = findViewById(R.id.tvPassDetails);

        fname = getIntent().getStringExtra("Full Name");
        eMail = getIntent().getStringExtra("Email");
        birthDate = getIntent().getStringExtra("DOB");
        sex = getIntent().getStringExtra("Gender");

        txtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                length = txtPass.getText().length();
                if(length>0 && length<=7){
                    tvPassStrong.setText("Password Strength is Weak !!!");
                    tvPassStrong.setTextColor(Color.RED);
                }else if(length>7 && length<=11){
                    tvPassStrong.setText("Password Strength is Good !!!");
                    tvPassStrong.setTextColor(Color.parseColor("#B7950B"));
                }else if(length>11 && length<=15){
                    tvPassStrong.setText("Password Strength is Strong !!!");
                    tvPassStrong.setTextColor(Color.parseColor("#196F3D"));
                }
            }
        });

        txtCpass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tvPassDetails.setText("");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                pass = txtPass.getText().toString();
                cpass = txtCpass.getText().toString();
                if(pass.equals(cpass)){
                    tvPassDetails.setText("✔ Passwords are a match !!!");
                    tvPassDetails.setTextColor(Color.parseColor("#196F3D"));
                }else{
                    tvPassDetails.setText("❌ Passwords do not match !!!");
                    tvPassDetails.setTextColor(Color.RED);
                }
            }
        });
    }

    public void getProfilepic(View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("crop","true");
        intent.putExtra("aspectX",0);
        intent.putExtra("aspectY",0);
        intent.putExtra("outputX",110);
        intent.putExtra("outputY",110);
        intent.putExtra("return-data",true);
        startActivityForResult(Intent.createChooser(intent,"Select Profile Image"),11);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==11){
            if (data!=null){
                Uri uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
                    ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,0,arrayOutputStream);
                    profilepic = arrayOutputStream.toByteArray();
                    imgProfile.setImageBitmap(bitmap);
                }catch (IOException e){
                    Log.e("Error", "MSG "+e.getMessage());
                }
            }
        }
    }

    public void Signup(View view){
        User u = new User();
        uname = txtUser.getText().toString();
        pass = txtPass.getText().toString();
        if (!uname.isEmpty() && !pass.isEmpty() && !cpass.isEmpty()) {
            if (length>=6 && length<=15) {
                u.setUsername(uname);
                u.setProfilepic(profilepic);
                u.setName(fname);
                u.setEmail(eMail);
                u.setDob(birthDate);
                u.setSex(sex);
                u.setPassword(pass);
                if (database.addUser(u)>0) {
                    Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(NextActivity.this, MainActivity.class);
                    intent.putExtra("Username", txtUser.getText().toString());
                    startActivity(intent);

                    /*HomeFragment obj = new HomeFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("Username", uname);
                    obj.setArguments(bundle);

                    Fragment homeFragment = new HomeFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.bottomNavigationView, homeFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();*/
                }
            }else {
                Toast.makeText(this,"Password is too short", Toast.LENGTH_SHORT).show();
                clrPassword(view);
                clrConfpass(view);
            }
        }else {
            Toast.makeText(this,"Fields Cannot Be Empty", Toast.LENGTH_SHORT).show();
        }
    }

    public void Back(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Warning Alert");
        builder.setMessage("If you go back, you may lose entered data. Do you wish to continue?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onBackPressed();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
        builder.show();
    }

    public void clrUsername(View view){
        txtUser.setText(null);
        txtUser.requestFocus();
    }
    public void clrPassword(View view){
        txtPass.setText(null);
        txtPass.requestFocus();
    }
    public void clrConfpass(View view){
        txtCpass.setText(null);
        txtCpass.requestFocus();
    }
    public void clrAll(View view){
        txtUser.setText(null);
        txtPass.setText(null);
        txtCpass.setText(null);
        txtUser.requestFocus();
    }
}