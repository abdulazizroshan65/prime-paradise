package com.example.prime_paradise;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Color;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.pmml4s.model.Model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import top.defaults.colorpicker.ColorPickerPopup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WearFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WearFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WearFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WearFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WearFragment newInstance(String param1, String param2) {
        WearFragment fragment = new WearFragment();
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

    private View mColorPreview, mColorPreview2, mColorPreview3, mColorPreview4;
    private int mDefaultColor, mDefaultColor2, mDefaultColor3, mDefaultColor4;
    EditText txtpid, txtpname, txtprice;
    ImageView imgPimg, imgPimg2, imgPimg3, imgPimg4, imgSearch;
    byte[] imgByte, imgByte2, imgByte3, imgByte4;
    DB_Opera db;
    Button btnIns, btnUpd,btnDlt,btnClearall;
    RadioGroup rdogrpCateg, rdogrpGender;
    RadioButton rbCloth, rbShoe, rbMale, rbFemale;
    TextView txtSugget;

    String category="", gender;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_wear, container, false);

        db = new DB_Opera(getActivity());
        mColorPreview = root.findViewById(R.id.color1);
        mColorPreview2 = root.findViewById(R.id.color2);
        mColorPreview3 = root.findViewById(R.id.color3);
        mColorPreview4 = root.findViewById(R.id.color4);
        txtpid = root.findViewById(R.id.txtpID);
        txtpname = root.findViewById(R.id.txtPname);
        txtprice = root.findViewById(R.id.txtPrice);
        rdogrpGender = root.findViewById(R.id.rdogrpGender);
        rdogrpCateg = root.findViewById(R.id.rdogrpCateg);
        rbMale = root.findViewById(R.id.rbMale);
        rbFemale = root.findViewById(R.id.rbFemale);
        rbCloth = root.findViewById(R.id.rbCloth);
        rbShoe = root.findViewById(R.id.rbShoe);
        txtSugget = root.findViewById(R.id.txtSugget);

        rbMale.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                boolean checked = ((RadioButton)v).isChecked();
                if (checked) {
                    gender="Male";
                }
            }
        });
        rbFemale.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                boolean checked = ((RadioButton)v).isChecked();
                if (checked) {
                    gender="Female";
                }
            }
        });

        rbCloth.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                boolean checked = ((RadioButton)v).isChecked();
                if (checked) {
                    category="Cloth";
                    rbMale.setEnabled(true);
                    rbFemale.setEnabled(true);
                    mColorPreview.setEnabled(false);
                    mColorPreview2.setEnabled(false);
                    mColorPreview3.setEnabled(false);
                    mColorPreview4.setEnabled(false);
                    imgPimg2.setEnabled(false);
                    imgPimg3.setEnabled(false);
                    imgPimg4.setEnabled(false);
                }
            }
        });
        rbShoe.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                boolean checked = ((RadioButton)v).isChecked();
                if (checked) {
                    category="Shoe";
                    rbMale.setEnabled(false);
                    rbFemale.setEnabled(false);
                    mColorPreview.setEnabled(true);
                    mColorPreview2.setEnabled(true);
                    mColorPreview3.setEnabled(true);
                    mColorPreview4.setEnabled(true);
                    imgPimg2.setEnabled(true);
                    imgPimg3.setEnabled(true);
                    imgPimg4.setEnabled(true);
                }
            }
        });

        btnIns = root.findViewById(R.id.btnInsert);
        btnIns.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (category=="Cloth"){
                    Cloth c = new Cloth();
                    c.setPid(Integer.parseInt(txtpid.getText().toString()));
                    c.setPname(txtpname.getText().toString());
                    c.setPrice(Double.parseDouble(txtprice.getText().toString()));
                    c.setCimg(imgByte);
                    if (gender=="Male"){
                        c.setGender("Male");
                    }else if (gender=="Female"){
                        c.setGender("Female");
                    }
                    c.setCimg(imgByte);
                    if (db.addCloth(c)>0){
                        Toast.makeText(getActivity(),"Product Added Successfully",Toast.LENGTH_SHORT).show();
                        clr();
                    }
                }else if (category=="Shoe"){
                    Shoe s = new Shoe();
                    s.setPid(Integer.parseInt(txtpid.getText().toString()));
                    s.setPname(txtpname.getText().toString());
                    s.setPrice(Double.parseDouble(txtprice.getText().toString()));
                    s.setScolor(mDefaultColor);
                    s.setScolor2(mDefaultColor2);
                    s.setScolor3(mDefaultColor3);
                    s.setScolor4(mDefaultColor4);
                    s.setSimg(imgByte);
                    s.setSimg2(imgByte2);
                    s.setSimg3(imgByte3);
                    s.setSimg4(imgByte4);
                    if (db.addShoe(s)>0){
                        Toast.makeText(getActivity(),"Product Added Successfully",Toast.LENGTH_SHORT).show();
                        clr();
                    }
                }else {
                    Toast.makeText(getActivity(),"Category Not Selected",Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgSearch = root.findViewById(R.id.imgSearch);
        imgSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (category=="Shoe"){
                    Shoe s = new Shoe();
                    s.setPid(Integer.parseInt(txtpid.getText().toString()));
                    s = db.searchShoe(s);
                    try {
                        if (s != null){
                            txtpname.setText(s.getPname());
                            txtprice.setText(String.valueOf(s.getPrice()));
                            mColorPreview.setBackgroundColor(s.getScolor());
                            mColorPreview2.setBackgroundColor(s.getScolor2());
                            mColorPreview3.setBackgroundColor(s.getScolor3());
                            mColorPreview4.setBackgroundColor(s.getScolor4());
                            Bitmap bitmap = BitmapFactory.decodeByteArray(s.getSimg(),0,s.getSimg().length);
                            imgPimg.setImageBitmap(bitmap);
                            Bitmap bitmap2 = BitmapFactory.decodeByteArray(s.getSimg2(),0,s.getSimg2().length);
                            imgPimg2.setImageBitmap(bitmap2);
                            Bitmap bitmap3 = BitmapFactory.decodeByteArray(s.getSimg3(),0,s.getSimg3().length);
                            imgPimg3.setImageBitmap(bitmap3);
                            Bitmap bitmap4 = BitmapFactory.decodeByteArray(s.getSimg4(),0,s.getSimg4().length);
                            imgPimg4.setImageBitmap(bitmap4);
                        }else {
                            Toast.makeText(getActivity(),"Product Not Found",Toast.LENGTH_SHORT).show();
                            clr();
                        }
                    }catch (Exception e){
                        Toast.makeText(getActivity(),"Product Not Found",Toast.LENGTH_SHORT).show();
                        e.getStackTrace();
                    }
                }else if (category=="Cloth"){
                    Cloth c = new Cloth();
                    c.setPid(Integer.parseInt(txtpid.getText().toString()));
                    c = db.searchCloth(c);
                    try {
                        if (c != null){
                            txtpname.setText(c.getPname());
                            txtprice.setText(String.valueOf(c.getPrice()));
                            Bitmap bitmap = BitmapFactory.decodeByteArray(c.getCimg(),0,c.getCimg().length);
                            imgPimg.setImageBitmap(bitmap);
                            if (c.getGender()=="Male"){
                                rdogrpGender.check(R.id.rbMale);
                            }else {
                                rdogrpGender.check(R.id.rbFemale);
                            }
                        }else {
                            Toast.makeText(getActivity(),"Product Not Found",Toast.LENGTH_SHORT).show();
                            clr();
                        }
                    }catch (Exception e){
                        Toast.makeText(getActivity(),"Product Not Found",Toast.LENGTH_SHORT).show();
                        e.getStackTrace();
                    }
                }else {
                    Toast.makeText(getActivity(),"Select a Category",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDlt = root.findViewById(R.id.btnDlt);
        btnDlt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (rbShoe.isSelected()){
                    Shoe s = new Shoe();
                    s.setPid(Integer.parseInt(txtpid.getText().toString()));
                    if (db.deleteShoe(s)>0){
                        Toast.makeText(getActivity(),"Product Deleted Successfully",Toast.LENGTH_SHORT).show();
                        clr();
                    }
                }else if (rbCloth.isSelected()){
                    Cloth c = new Cloth();
                    c.setPid(Integer.parseInt(txtpid.getText().toString()));
                    if (db.deleteCloth(c)>0){
                        Toast.makeText(getActivity(),"Product Deleted Successfully",Toast.LENGTH_SHORT).show();
                        clr();
                    }
                }
            }
        });

        btnUpd = root.findViewById(R.id.btnUpd);
        btnUpd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (category=="Shoe"){
                    Shoe s = new Shoe();
                    s.setPid(Integer.parseInt(txtpid.getText().toString()));
                    s.setPname(txtpname.getText().toString());
                    s.setPrice(Double.parseDouble(txtprice.getText().toString()));
                    s.setSimg(imgByte);
                    s.setSimg2(imgByte2);
                    s.setSimg3(imgByte3);
                    s.setSimg4(imgByte4);
                    s.setScolor(mDefaultColor);
                    s.setScolor(mDefaultColor2);
                    s.setScolor(mDefaultColor3);
                    s.setScolor(mDefaultColor4);
                    if (db.updateShoe(s)>0){
                        Toast.makeText(getActivity(),"Product Updated Successfully",Toast.LENGTH_SHORT).show();
                        clr();
                    }
                }else if (category=="Cloth"){
                    Cloth c = new Cloth();
                    c.setPid(Integer.parseInt(txtpid.getText().toString()));
                    c.setPname(txtpname.getText().toString());
                    c.setGender(gender);
                    c.setPrice(Double.parseDouble(txtprice.getText().toString()));
                    c.setCimg(imgByte);
                    if (db.updateCloth(c)>0){
                        Toast.makeText(getActivity(),"Product Updated Successfully",Toast.LENGTH_SHORT).show();
                        clr();
                    }
                }


            }
        });

        txtSugget.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View v)
            {
                //final Model model = Model.fromFile(WearFragment.class.getClassLoader().getResource("model.pmml").getFile());

                File file = new File("model.pmml");
                Model model = Model.fromFile(file);

                Map<String, Integer> values = Map.of(
                        "category", 20,
                        "market_price", 1500
                );

                Object[] valuesMap = Arrays.stream(model.inputNames())
                        .map(values::get)
                        .toArray();

                Object[] result = model.predict(valuesMap);
                Log.i("predicted", " : "+result);
                txtprice.setText("predicted : "+result);
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

        mDefaultColor4 = 0;
        mColorPreview4.setOnClickListener(new View.OnClickListener() {
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
                        mDefaultColor4 = color;
                        mColorPreview4.setBackgroundColor(mDefaultColor4);
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

        imgPimg4 = root.findViewById(R.id.imgPimg4);
        imgPimg4.setOnClickListener(new View.OnClickListener()
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
                startActivityForResult(Intent.createChooser(intent,"Select Product Image"),14);
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
        }else if (requestCode==13){
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
        }else {
            if (data!=null){
                Uri uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(),uri);
                    ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,arrayOutputStream);
                    imgByte4 = arrayOutputStream.toByteArray();
                    imgPimg4.setImageBitmap(bitmap);
                }catch (IOException e){
                    Log.e("Error", "MSG "+e.getMessage());
                }
            }
        }

    }

    public void clr(){
        rbCloth.isSelected();
        txtpid.setText(null);
        txtpname.setText(null);
        txtprice.setText(null);
        rdogrpGender.clearCheck();
        mColorPreview.setBackgroundColor(mDefaultColor);
        mColorPreview2.setBackgroundColor(mDefaultColor);
        mColorPreview3.setBackgroundColor(mDefaultColor);
        mColorPreview4.setBackgroundColor(mDefaultColor);
        imgPimg.setImageResource(R.drawable.ic_launcher_background);
        imgPimg2.setImageResource(R.drawable.ic_launcher_background);
        imgPimg3.setImageResource(R.drawable.ic_launcher_background);
        imgPimg4.setImageResource(R.drawable.ic_launcher_background);
    }
}