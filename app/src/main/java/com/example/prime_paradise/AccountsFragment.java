package com.example.prime_paradise;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountsFragment newInstance(String param1, String param2) {
        AccountsFragment fragment = new AccountsFragment();
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

    EditText txtUser2, txtFname2, txtEmail2, txtDate2, txtPass2;
    ImageView imgSearch2, imgPimg3;
    Button btnIns, btnUpdt,btnDel,btnClrall;
    RadioGroup rdogrpGender;
    RadioButton rbMale, rbFemale;
    TextView txtShowh;
    byte[] imageByte2;
    DB_Opera db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_accounts, container, false);

        db = new DB_Opera(getActivity());
        txtUser2 = root.findViewById(R.id.txtUser2);
        txtFname2 = root.findViewById(R.id.txtFname2);
        txtEmail2 = root.findViewById(R.id.txtEmail2);
        txtDate2 = root.findViewById(R.id.txtDate2);
        txtPass2 = root.findViewById(R.id.txtPass2);
        rdogrpGender = root.findViewById(R.id.rdogrpGender);
        rbMale = root.findViewById(R.id.rbMale);
        rbFemale = root.findViewById(R.id.rbFemale);
        btnIns = root.findViewById(R.id.btnInsert);

        txtShowh = root.findViewById(R.id.txtShowh);
        txtShowh.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(txtShowh.getText().toString().equals("Show")){
                    txtPass2.setTransformationMethod(null);
                    txtShowh.setText("Hide");
                } else{
                    txtPass2.setTransformationMethod(new PasswordTransformationMethod());
                    txtShowh.setText("Show");
                }
            }
        });

        imgPimg3 = root.findViewById(R.id.imgPimg);
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
                startActivityForResult(Intent.createChooser(intent,"Select User Image"),11);
            }
        });

        imgSearch2 = root.findViewById(R.id.imgSearch2);
        imgSearch2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                User u = new User();
                u.setUsername(txtUser2.getText().toString());
                u = db.searchUser(u);
                try {
                    if (u != null){
                        txtFname2.setText(u.getName());
                        txtPass2.setText(u.getPassword());
                        txtEmail2.setText(u.getEmail());
                        txtDate2.setText(u.getDob());
                        Bitmap bitmap = BitmapFactory.decodeByteArray(u.getProfilepic(),0,u.getProfilepic().length);
                        imgPimg3.setImageBitmap(bitmap);
                        if (u.getSex()=="Male"){
                            rdogrpGender.check(R.id.rbMale);
                        }else {
                            rdogrpGender.check(R.id.rbFemale);
                        }
                        txtPass2.setText(u.getPassword());
                    }else {
                        Toast.makeText(getActivity(),"User Not Found",Toast.LENGTH_SHORT).show();
                        clr();
                    }
                }catch (Exception e){
                    Toast.makeText(getActivity(),"User Not Found",Toast.LENGTH_SHORT).show();
                    e.getStackTrace();
                }
            }
        });

        btnIns = root.findViewById(R.id.btnInsert);
        btnIns.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Admin a = new Admin();
                a.setUname(txtUser2.getText().toString());
                a.setPass(txtPass2.getText().toString());
                if (db.addAdmin(a)>0){
                    Toast.makeText(getActivity(),"Admin Added Successfully",Toast.LENGTH_SHORT).show();
                    clr();
                }
            }
        });

        btnUpdt = root.findViewById(R.id.btnUpd);
        btnUpdt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                User u = new User();
                u.setUsername(txtUser2.getText().toString());
                u.setName(txtFname2.getText().toString());
                u.setPassword(txtPass2.getText().toString());
                u.setEmail(txtEmail2.getText().toString());
                u.setDob(txtDate2.getText().toString());
                u.setProfilepic(imageByte2);
                rbMale.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((RadioButton)v).isChecked();
                        if (checked) {
                            u.setSex("Male");
                        }
                    }
                });
                rbFemale.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((RadioButton)v).isChecked();
                        if (checked) {
                            u.setSex("Female");
                        }
                    }
                });

                if (db.updateUser(u)>0){
                    Toast.makeText(getActivity(),"User Record Updated",Toast.LENGTH_SHORT).show();
                    clr();
                }
            }
        });

        btnDel = root.findViewById(R.id.btnDlt);
        btnDel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                User u = new User();
                u.setUsername(txtUser2.getText().toString());
                if (db.deleteUser(u)>0){
                    Toast.makeText(getActivity(),"User Record Deleted",Toast.LENGTH_SHORT).show();
                    clr();
                }
            }
        });

        btnClrall = root.findViewById(R.id.btnClrall);
        btnClrall.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                clr();
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
                    imageByte2 = arrayOutputStream.toByteArray();
                    imgPimg3.setImageBitmap(bitmap);
                }catch (IOException e){
                    Log.e("Error", "MSG "+e.getMessage());
                }
            }
        }
    }

    public void clr(){
        txtUser2.setText(null);
        txtFname2.setText(null);
        txtEmail2.setText(null);
        txtDate2.setText(null);
        rdogrpGender.clearCheck();
        txtPass2.setText(null);
        txtUser2.requestFocus();
    }
}