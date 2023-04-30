package com.example.prime_paradise;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class HomeFragment extends Fragment {

    TextView fav1;
    CardView card1, cardShoes1, cardelec, cardfurn, cardgroc;
    ImageView imgLogout;
    Button btnBrowse;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ViewPager mViewPager = (ViewPager) root.findViewById(R.id.viewPage);
        ImageAdapter adapterView = new ImageAdapter(getActivity());
        mViewPager.setAdapter(adapterView);

        fav1 = root.findViewById(R.id.fav1);
        card1 = root.findViewById(R.id.card1);
        imgLogout = root.findViewById(R.id.imgLogin);
        btnBrowse = root.findViewById(R.id.btnBrowse);
        cardShoes1 = root.findViewById(R.id.cardShoe1);
        cardfurn = root.findViewById(R.id.cardfurn);
        cardelec = root.findViewById(R.id.cardelec);
        cardgroc = root.findViewById(R.id.cardgroc);

        cardShoes1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), WearableActivity.class);
                startActivity(intent);
            }
        });

        cardelec.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), ElecActivity.class);
                startActivity(intent);
            }
        });

        cardfurn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), FurnActivity.class);
                startActivity(intent);
            }
        });

        cardgroc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), GrocActivity.class);
                startActivity(intent);
            }
        });

        imgLogout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Logout");
                builder.setMessage("Do you wish to logout of your account?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { }
                });
                builder.show();
            }
        });

        card1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), ShoeActivity.class);
                startActivity(intent);
            }
        });

        fav1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(fav1.getText().toString().equals("🤍")){
                    fav1.setText("❤");
                }else if(fav1.getText().toString().equals("❤")){
                    fav1.setText("🤍");
                }
            }
        });

        return root;
    }
}