package com.example.prime_paradise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DB_Opera2 extends SQLiteOpenHelper {
    public DB_Opera2(Context context) { super(context, "DB_PP2", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE tbl_Elec (PID INTEGER PRIMARY KEY, PNAME VARCHAR(50), PRICE DOUBLE, " +
                "EColor INTEGER, EColor2 INTEGER, EColor3 INTEGER, EIMG BLOG, EIMG2 BLOG, EIMG3 BLOG, " +
                "RAM INTEGER, ROM INTEGER, Screen DOUBLE, Battery INTEGER, Chip VARCHAR(50), Camera INTEGER)";
        sqLiteDatabase.execSQL(sql);
        sql = "CREATE TABLE tbl_Groc (PID INTEGER PRIMARY KEY, PNAME VARCHAR(50), PRICE DOUBLE, GIMG BLOG)";
        sqLiteDatabase.execSQL(sql);
        sql = "CREATE TABLE tbl_Cart (ItemNo INTEGER PRIMARY KEY, Uname VARCHAR(20), PID INTEGER, Type VARCHAR(15), " +
                "PNAME VARCHAR(50), PRICE DOUBLE, QTY INTEGER, CovIMG BLOG)";
        sqLiteDatabase.execSQL(sql);
        sql = "CREATE TABLE tbl_Wishlist (ItemNo INTEGER PRIMARY KEY, Uname VARCHAR(20), PID INTEGER, Type VARCHAR(15), " +
                "PNAME VARCHAR(50), PRICE DOUBLE, CoverIMG BLOG)";
        sqLiteDatabase.execSQL(sql);
        sql = "CREATE TABLE tbl_Order (OrderID INTEGER PRIMARY KEY, Uname VARCHAR(20), Items INTEGER, COST DOUBLE)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS tbl_Elec";
        sqLiteDatabase.execSQL(sql);
        sql = "DROP TABLE IF EXISTS tbl_Groc";
        sqLiteDatabase.execSQL(sql);
        sql = "DROP TABLE IF EXISTS tbl_Cart";
        sqLiteDatabase.execSQL(sql);
        sql = "DROP TABLE IF EXISTS tbl_Wishlist";
        sqLiteDatabase.execSQL(sql);
        sql = "DROP TABLE IF EXISTS tbl_Order";
        sqLiteDatabase.execSQL(sql);
    }

    public long addCart(Cart c){
        SQLiteDatabase myDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ItemNo",c.getItemno());
        contentValues.put("Uname",c.getUname());
        contentValues.put("PID",c.getPid());
        contentValues.put("Type",c.getType());
        contentValues.put("PNAME",c.getPname());
        contentValues.put("PRICE",c.getPrice());
        contentValues.put("QTY",c.getQty());
        contentValues.put("CovIMG",c.getCovimg());
        return myDatabase.insert("tbl_Cart",null,contentValues);
    }

    public long addOrder(Order o){
        SQLiteDatabase myDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("OrderID",o.getOrderid());
        contentValues.put("Uname",o.getUname());
        contentValues.put("Items",o.getItems());
        contentValues.put("COST",o.getCost());
        return myDatabase.insert("tbl_Order",null,contentValues);
    }

    public long addWish(Wishlist w){
        SQLiteDatabase myDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ItemNo",w.getItemno());
        contentValues.put("Uname",w.getUname());
        contentValues.put("PID",w.getPid());
        contentValues.put("Type",w.getType());
        contentValues.put("PNAME",w.getPname());
        contentValues.put("PRICE",w.getPrice());
        contentValues.put("CoverIMG",w.getCoverimg());
        return myDatabase.insert("tbl_Wishlist",null,contentValues);
    }

    public int deleteCart(Cart c){
        SQLiteDatabase myDatabase = getWritableDatabase();
        return myDatabase.delete("tbl_Cart","ItemNo="+c.getItemno(),null);
    }

    public int deleteWish(Wishlist w){
        SQLiteDatabase myDatabase = getWritableDatabase();
        return myDatabase.delete("tbl_Wishlist","PID="+w.getPid(),null);
    }

    public Cart searchCart(Cart c){
        SQLiteDatabase myDatabase = getReadableDatabase();
        String sql = "SELECT * FROM tbl_Cart WHERE ItemNo='" + c.getItemno()+"'";
        Cursor cursor = myDatabase.rawQuery(sql,null);
        int count = cursor.getCount();
        Cart cart = new Cart();

        if (count !=0){
            if (cursor.moveToFirst()){
                cart.setItemno(cursor.getInt(cursor.getColumnIndex("ItemNo")));
                cart.setUname(cursor.getString(cursor.getColumnIndex("Uname")));
                cart.setPid(cursor.getInt(cursor.getColumnIndex("PID")));
                cart.setType(cursor.getString(cursor.getColumnIndex("Type")));
                cart.setPname(cursor.getString(cursor.getColumnIndex("PNAME")));
                cart.setPrice(cursor.getDouble(cursor.getColumnIndex("PRICE")));
                cart.setQty(cursor.getInt(cursor.getColumnIndex("QTY")));
                cart.setCovimg(cursor.getBlob(cursor.getColumnIndex("CovIMG")));
            }else {
                cart = null;
            }
        }
        return cart;
    }

    public Order searchOrder(Order o){
        SQLiteDatabase myDatabase = getReadableDatabase();
        String sql = "SELECT * FROM tbl_Order WHERE OrderID='" + o.getOrderid()+"'";
        Cursor cursor = myDatabase.rawQuery(sql,null);
        int count = cursor.getCount();
        Order order = new Order();

        if (count !=0){
            if (cursor.moveToFirst()){
                order.setOrderid(cursor.getInt(cursor.getColumnIndex("OrderID")));
                order.setUname(cursor.getString(cursor.getColumnIndex("Uname")));
                order.setItems(cursor.getInt(cursor.getColumnIndex("Items")));
                order.setCost(cursor.getDouble(cursor.getColumnIndex("COST")));
            }else {
                order = null;
            }
        }
        return order;
    }

    public ArrayList<Cart> viewCart(){
        SQLiteDatabase myDatabase = getReadableDatabase();
        String sql = "SELECT * FROM tbl_Cart";
        Cursor cursor = myDatabase.rawQuery(sql,null);
        ArrayList<Cart> carts = new ArrayList<Cart>();
        int count = cursor.getCount();

        if (count !=0){
            while (cursor.moveToNext()){
                Cart c = new Cart();
                c.setItemno(cursor.getInt(0));
                c.setUname(cursor.getString(1));
                c.setPid(cursor.getInt(2));
                c.setType(cursor.getString(3));
                c.setPname(cursor.getString(4));
                c.setPrice(cursor.getDouble(5));
                c.setQty(cursor.getInt(6));
                c.setCovimg(cursor.getBlob(7));
                carts.add(c);
            }
        }else {
            carts = null;
        }
        return carts;
    }

    public ArrayList<Order> viewOrder(){
        SQLiteDatabase myDatabase = getReadableDatabase();
        String sql = "SELECT * FROM tbl_Order";
        Cursor cursor = myDatabase.rawQuery(sql,null);
        ArrayList<Order> orders = new ArrayList<Order>();
        int count = cursor.getCount();

        if (count !=0){
            while (cursor.moveToNext()){
                Order o = new Order();
                o.setOrderid(cursor.getInt(0));
                o.setUname(cursor.getString(1));
                o.setItems(cursor.getInt(2));
                o.setCost(cursor.getDouble(3));
                orders.add(o);
            }
        }else {
            orders = null;
        }
        return orders;
    }

    public ArrayList<Wishlist> viewWish(){
        SQLiteDatabase myDatabase = getReadableDatabase();
        String sql = "SELECT * FROM tbl_Wishlist";
        Cursor cursor = myDatabase.rawQuery(sql,null);
        ArrayList<Wishlist> wishlists = new ArrayList<Wishlist>();
        int count = cursor.getCount();

        if (count !=0){
            while (cursor.moveToNext()){
                Wishlist w = new Wishlist();
                w.setItemno(cursor.getInt(0));
                w.setUname(cursor.getString(1));
                w.setPid(cursor.getInt(2));
                w.setType(cursor.getString(3));
                w.setPname(cursor.getString(4));
                w.setPrice(cursor.getDouble(5));
                w.setCoverimg(cursor.getBlob(7));
                wishlists.add(w);
            }
        }else {
            wishlists = null;
        }
        return wishlists;
    }
}
