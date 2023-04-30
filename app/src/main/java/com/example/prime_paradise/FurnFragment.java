package com.example.prime_paradise;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import top.defaults.colorpicker.ColorPickerPopup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FurnFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FurnFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FurnFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FurnFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FurnFragment newInstance(String param1, String param2) {
        FurnFragment fragment = new FurnFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private View mColorPreview, mColorPreview2, mColorPreview3;
    private int mDefaultColor, mDefaultColor2, mDefaultColor3;
    EditText txtpid, txtpname, txtprice;
    ImageView imgPimg, imgPimg2, imgPimg3, imgSearch;
    byte[] imgByte, imgByte2, imgByte3;
    DB_Opera db;
    Button btnIns, btnUpd,btnDlt,btnClearall;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_furn, container, false);

        db = new DB_Opera(getActivity());
        mColorPreview = root.findViewById(R.id.color);
        mColorPreview2 = root.findViewById(R.id.color2);
        mColorPreview3 = root.findViewById(R.id.color3);
        txtpid = root.findViewById(R.id.txtpID);
        txtpname = root.findViewById(R.id.txtPname);
        txtprice = root.findViewById(R.id.txtPrice);

        btnIns = root.findViewById(R.id.btnInsert);
        btnIns.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Furniture f = new Furniture();
                f.setPid(Integer.parseInt(txtpid.getText().toString()));
                f.setPname(txtpname.getText().toString());
                f.setPrice(Double.parseDouble(txtprice.getText().toString()));
                f.setFimg(imgByte);
                f.setFimg2(imgByte2);
                f.setFimg3(imgByte3);
                f.setFcolor(mDefaultColor);
                f.setFcolor2(mDefaultColor2);
                f.setFcolor3(mDefaultColor3);
                if (db.addFurn(f)>0){
                    Toast.makeText(getActivity(),"Product Added Successfully",Toast.LENGTH_SHORT).show();
                    clr();
                }
            }
        });

        imgSearch = root.findViewById(R.id.imgSearch);
        imgSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Furniture f = new Furniture();
                f.setPid(Integer.parseInt(txtpid.getText().toString()));
                f = db.searchFurn(f);
                try {
                    if (f != null){
                        txtpname.setText(f.getPname());
                        txtprice.setText(String.valueOf(f.getPrice()));
                        mColorPreview.setBackgroundColor(f.getFcolor());
                        mColorPreview2.setBackgroundColor(f.getFcolor2());
                        mColorPreview3.setBackgroundColor(f.getFcolor3());
                        Bitmap bitmap = BitmapFactory.decodeByteArray(f.getFimg(),0,f.getFimg().length);
                        imgPimg.setImageBitmap(bitmap);
                        Bitmap bitmap2 = BitmapFactory.decodeByteArray(f.getFimg2(),0,f.getFimg2().length);
                        imgPimg2.setImageBitmap(bitmap2);
                        Bitmap bitmap3 = BitmapFactory.decodeByteArray(f.getFimg3(),0,f.getFimg3().length);
                        imgPimg3.setImageBitmap(bitmap3);
                    }else {
                        Toast.makeText(getActivity(),"Product Not Found",Toast.LENGTH_SHORT).show();
                        clr();
                    }
                }catch (Exception e){
                    Toast.makeText(getActivity(),"Product Not Found",Toast.LENGTH_SHORT).show();
                    e.getStackTrace();
                }
            }
        });

        btnDlt = root.findViewById(R.id.btnDlt);
        btnDlt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Furniture f = new Furniture();
                f.setPid(Integer.parseInt(txtpid.getText().toString()));
                if (db.deleteFurn(f)>0){
                    Toast.makeText(getActivity(),"Product Deleted Successfully",Toast.LENGTH_SHORT).show();
                    clr();
                }
            }
        });

        btnUpd = root.findViewById(R.id.btnUpd);
        btnUpd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Furniture f = new Furniture();
                f.setPid(Integer.parseInt(txtpid.getText().toString()));
                f.setPname(txtpname.getText().toString());
                f.setPrice(Double.parseDouble(txtprice.getText().toString()));
                f.setFimg(imgByte);
                f.setFimg2(imgByte2);
                f.setFimg3(imgByte3);
                f.setFcolor(mDefaultColor);
                f.setFcolor2(mDefaultColor2);
                f.setFcolor3(mDefaultColor3);
                if (db.updateFurn(f)>0){
                    Toast.makeText(getActivity(),"Product Updated Successfully",Toast.LENGTH_SHORT).show();
                    clr();
                }
            }
        });

        btnClearall = root.findViewById(R.id.btnClearall);
        btnClearall.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                clr();
            }
        });

        mDefaultColor = 0; // set the default color to 0 as it is black
        mColorPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new ColorPickerPopup.Builder(getActivity())
                        .initialColor(Color.RED) //set initial color of the color picker dialog
                        .enableBrightness(true) // enable color brightness slider
                        .enableAlpha(true) // enable color alpha changer on slider
                        .okTitle("Choose") // positive button
                        .cancelTitle("Cancel") // negative button
                        .showIndicator(true) // color indicator
                        .showValue(true) // shows color hex code
                        .build().show(v, new ColorPickerPopup.ColorPickerObserver() {
                    @Override
                    public void onColorPicked(int color) {
                        mDefaultColor = color;
                        mColorPreview.setBackgroundColor(mDefaultColor);
                        //gfgTextView.setTextColor(mDefaultColor);
                    }
                });
            }
        });

        mDefaultColor2 = 0;
        mColorPreview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new ColorPickerPopup.Builder(getActivity())
                        .initialColor(Color.RED) //set initial color of the color picker dialog
                        .enableBrightness(true) // enable color brightness slider
                        .enableAlpha(true) // enable color alpha changer on slider
                        .okTitle("Choose") // positive button
                        .cancelTitle("Cancel") // negative button
                        .showIndicator(true) // color indicator
                        .showValue(true) // shows color hex code
                        .build().show(v, new ColorPickerPopup.ColorPickerObserver() {
                    @Override
                    public void onColorPicked(int color) {
                        mDefaultColor2 = color;
                        mColorPreview2.setBackgroundColor(mDefaultColor2);
                    }
                });
            }
        });

        mDefaultColor3 = 0;
        mColorPreview3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new ColorPickerPopup.Builder(getActivity())
                        .initialColor(Color.RED) //set initial color of the color picker dialog
                        .enableBrightness(true) // enable color brightness slider
                        .enableAlpha(true) // enable color alpha changer on slider
                        .okTitle("Choose") // positive button
                        .cancelTitle("Cancel") // negative button
                        .showIndicator(true) // color indicator
                        .showValue(true) // shows color hex code
                        .build().show(v, new ColorPickerPopup.ColorPickerObserver() {
                    @Override
                    public void onColorPicked(int color) {
                        mDefaultColor3 = color;
                        mColorPreview3.setBackgroundColor(mDefaultColor3);
                    }
                });
            }
        });

        imgPimg = root.findViewById(R.id.imgPimg);
        imgPimg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra("crop","true");
                intent.putExtra("aspectX",0);
                intent.putExtra("aspectY",0);
                intent.putExtra("outputX",110);
                intent.putExtra("outputY",110);
                intent.putExtra("return-data",true);
                startActivityForResult(Intent.createChooser(intent,"Select Product Image"),11);
            }
        });

        imgPimg2 = root.findViewById(R.id.imgPimg2);
        imgPimg2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra("crop","true");
                intent.putExtra("aspectX",0);
                intent.putExtra("aspectY",0);
                intent.putExtra("outputX",110);
                intent.putExtra("outputY",110);
                intent.putExtra("return-data",true);
                startActivityForResult(Intent.createChooser(intent,"Select Product Image"),12);
            }
        });

        imgPimg3 = root.findViewById(R.id.imgPimg3);
        imgPimg3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra("crop","true");
                intent.putExtra("aspectX",0);
                intent.putExtra("aspectY",0);
                intent.putExtra("outputX",110);
                intent.putExtra("outputY",110);
                intent.putExtra("return-data",true);
                startActivityForResult(Intent.createChooser(intent,"Select Product Image"),13);
            }
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==11){
            if (data!=null){
                Uri uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(),uri);
                    ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,arrayOutputStream);
                    imgByte = arrayOutputStream.toByteArray();
                    imgPimg.setImageBitmap(bitmap);
                }catch (IOException e){
                    Log.e("Error", "MSG "+e.getMessage());
                }
            }
        }else if (requestCode==12){
            if (data!=null){
                Uri uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(),uri);
                    ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,arrayOutputStream);
                    imgByte2 = arrayOutputStream.toByteArray();
                    imgPimg2.setImageBitmap(bitmap);
                }catch (IOException e){
                    Log.e("Error", "MSG "+e.getMessage());
                }
            }
        }else {
            if (data!=null){
                Uri uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(),uri);
                    ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,arrayOutputStream);
                    imgByte3 = arrayOutputStream.toByteArray();
                    imgPimg3.setImageBitmap(bitmap);
                }catch (IOException e){
                    Log.e("Error", "MSG "+e.getMessage());
                }
            }
        }

    }

    public void clr(){
        txtpid.setText(null);
        txtpname.setText(null);
        txtprice.setText(null);
        mColorPreview.setBackgroundColor(mDefaultColor);
        mColorPreview2.setBackgroundColor(mDefaultColor);
        mColorPreview3.setBackgroundColor(mDefaultColor);
        imgPimg.setImageResource(R.drawable.ic_launcher_background);
        imgPimg2.setImageResource(R.drawable.ic_launcher_background);
        imgPimg3.setImageResource(R.drawable.ic_launcher_background);
    }
}