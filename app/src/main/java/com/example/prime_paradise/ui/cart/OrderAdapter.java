package com.example.prime_paradise.ui.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.prime_paradise.DB_Opera2;
import com.example.prime_paradise.Order;
import com.example.prime_paradise.R;

import java.util.ArrayList;

public class OrderAdapter extends BaseAdapter {

    Context context;
    ArrayList<Order> orders;
    TextView tvOID, tvOID2, tvuname, tvuname2, tvitems, tvitems2, tvcos, tvcos2;
    DB_Opera2 db2;

    public OrderAdapter(Context context, ArrayList<Order> orders){
        this.context = context;
        this.orders = orders;
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Object getItem(int i) {
        return orders.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.order_view,viewGroup,false);

        db2 = new DB_Opera2(context);
        tvOID = v.findViewById(R.id.tvOID);
        tvOID2 = v.findViewById(R.id.tvOrder2);
        tvuname = v.findViewById(R.id.tvuname);
        tvuname2 = v.findViewById(R.id.tvuname2);
        tvitems = v.findViewById(R.id.tvitems);
        tvitems2 = v.findViewById(R.id.tvitems2);
        tvcos = v.findViewById(R.id.tvcos);
        tvcos2 = v.findViewById(R.id.tvcos2);

        Order o = orders.get(i);
        int id = o.getOrderid();
        tvOID2.setText(Integer.toString(id));
        tvuname2.setText(o.getUname());
        tvitems2.setText(String.valueOf(o.getItems()));
        tvcos2.setText(String.valueOf(o.getCost()));

        return v;
    }
}
