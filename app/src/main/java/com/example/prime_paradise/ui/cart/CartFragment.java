package com.example.prime_paradise.ui.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.prime_paradise.Cart;
import com.example.prime_paradise.CartActivity;
import com.example.prime_paradise.CartAdapter;
import com.example.prime_paradise.ClothActivity;
import com.example.prime_paradise.DB_Opera2;
import com.example.prime_paradise.MainActivity;
import com.example.prime_paradise.NextActivity;
import com.example.prime_paradise.ProductAdapter;
import com.example.prime_paradise.R;
import com.example.prime_paradise.ShoeActivity;
import com.example.prime_paradise.WearableActivity;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    ListView cartlist;
    ArrayList<Cart> carts;
    DB_Opera2 db2;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cart, container, false);

        cartlist = root.findViewById(R.id.listCart);
        db2 = new DB_Opera2(getActivity());

        try {
            carts = db2.viewCart();
            if (carts.size() > 0) {
                CartAdapter cartAdapter = new CartAdapter(getActivity(), carts); //context-->getActivity
                cartlist.setAdapter(cartAdapter);
            }
        }catch (Exception ex){
            Toast.makeText(getActivity(),"No Items Found",Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(getActivity(), CartActivity.class);
        startActivity(intent);

        return root;
    }
}