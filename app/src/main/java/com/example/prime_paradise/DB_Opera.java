package com.example.prime_paradise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DB_Opera extends SQLiteOpenHelper {
    public DB_Opera(Context context) {
        super(context, "DB_PP", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE tbl_User (Username VARCHAR(30) PRIMARY KEY, IMG BLOG, Name VARCHAR(30), Email VARCHAR(30), " +
                "DOB VARCHAR(15), Sex VARCHAR(10) , Password VARCHAR(20))";
        sqLiteDatabase.execSQL(sql);
        sql = "CREATE TABLE tbl_Admin (Uname VARCHAR(30) PRIMARY KEY, Pass VARCHAR(20))";
        sqLiteDatabase.execSQL(sql);
        sql = "CREATE TABLE tbl_Shoe (PID INTEGER PRIMARY KEY, PNAME VARCHAR(50), PRICE DOUBLE, " +
                "SColor INTEGER, SColor2 INTEGER, SColor3 INTEGER, SColor4 INTEGER, SIMG BLOG, SIMG2 BLOG, SIMG3 BLOG, SIMG4 BLOG)";
        sqLiteDatabase.execSQL(sql);
        sql = "CREATE TABLE tbl_Cloth (PID INTEGER PRIMARY KEY, Gender VARCHAR(10), PNAME VARCHAR(50), PRICE DOUBLE, CIMG BLOG)";
        sqLiteDatabase.execSQL(sql);
        sql = "CREATE TABLE tbl_Furn (PID INTEGER PRIMARY KEY, PNAME VARCHAR(50), PRICE DOUBLE, FColor INTEGER, FColor2 INTEGER, " +
                "FColor3 INTEGER, FIMG BLOG, FIMG2 BLOG, FIMG3 BLOG)";
        sqLiteDatabase.execSQL(sql);
        sql = "CREATE TABLE tbl_Cart (ItemNo INTEGER PRIMARY KEY, PID INTEGER, Type VARCHAR(15), PNAME VARCHAR(50), PRICE DOUBLE, QTY INTEGER, " +
                "CovIMG BLOG)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS tbl_User";
        sqLiteDatabase.execSQL(sql);
        sql = "DROP TABLE IF EXISTS tbl_Admin";
        sqLiteDatabase.execSQL(sql);
        sql = "DROP TABLE IF EXISTS tbl_Shoe";
        sqLiteDatabase.execSQL(sql);
        sql = "DROP TABLE IF EXISTS tbl_Cloth";
        sqLiteDatabase.execSQL(sql);
        sql = "DROP TABLE IF EXISTS tbl_Furn";
        sqLiteDatabase.execSQL(sql);
        sql = "DROP TABLE IF EXISTS tbl_Cart";
        sqLiteDatabase.execSQL(sql);
    }

    public long addUser(User u){
        SQLiteDatabase myDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username",u.getUsername());
        contentValues.put("IMG",u.getProfilepic());
        contentValues.put("Name",u.getName());
        contentValues.put("Email",u.getEmail());
        contentValues.put("DOB",u.getDob());
        contentValues.put("Sex",u.getSex());
        contentValues.put("Password",u.getPassword());
        return myDatabase.insert("tbl_User",null,contentValues);
    }

    public long addAdmin(Admin a){
        SQLiteDatabase myDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Uname",a.getUname());
        contentValues.put("Pass",a.getPass());
        return myDatabase.insert("tbl_Admin",null,contentValues);
    }

    public long addShoe(Shoe s){
        SQLiteDatabase myDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("PID",s.getPid());
        contentValues.put("PNAME",s.getPname());
        contentValues.put("PRICE",s.getPrice());
        contentValues.put("SColor",s.getScolor());
        contentValues.put("SColor2",s.getScolor2());
        contentValues.put("SColor3",s.getScolor3());
        contentValues.put("SColor4",s.getScolor4());
        contentValues.put("SIMG",s.getSimg());
        contentValues.put("SIMG2",s.getSimg2());
        contentValues.put("SIMG3",s.getSimg3());
        contentValues.put("SIMG4",s.getSimg4());
        return myDatabase.insert("tbl_Shoe",null,contentValues);
    }

    public long addCloth(Cloth c){
        SQLiteDatabase myDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("PID",c.getPid());
        contentValues.put("Gender",c.getGender());
        contentValues.put("PNAME",c.getPname());
        contentValues.put("PRICE",c.getPrice());
        contentValues.put("CIMG",c.getCimg());
        return myDatabase.insert("tbl_Cloth",null,contentValues);
    }

    public long addFurn(Furniture f){
        SQLiteDatabase myDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("PID",f.getPid());
        contentValues.put("PNAME",f.getPname());
        contentValues.put("PRICE",f.getPrice());
        contentValues.put("FColor",f.getFcolor());
        contentValues.put("FColor2",f.getFcolor2());
        contentValues.put("FColor3",f.getFcolor3());
        contentValues.put("FIMG",f.getFimg());
        contentValues.put("FIMG2",f.getFimg2());
        contentValues.put("FIMG3",f.getFimg3());
        return myDatabase.insert("tbl_Furn",null,contentValues);
    }

    public int updateUser(User u){
        SQLiteDatabase myDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IMG",u.getProfilepic());
        contentValues.put("Name",u.getName());
        contentValues.put("Email",u.getEmail());
        contentValues.put("DOB",u.getDob());
        contentValues.put("Sex",u.getSex());
        contentValues.put("Password",u.getPassword());
        return myDatabase.update("tbl_User",contentValues,"Username='"+u.getUsername()+"'",null);
    }

    public int updateAdmin(Admin a){
        SQLiteDatabase myDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Uname",a.getUname());
        contentValues.put("Pass",a.getPass());
        return myDatabase.update("tbl_Admin",contentValues,"Uname='"+a.getUname()+"'",null);
    }

    public int updateShoe(Shoe s){
        SQLiteDatabase myDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("PNAME",s.getPname());
        contentValues.put("PRICE",s.getPrice());
        contentValues.put("SColor",s.getScolor());
        contentValues.put("SColor2",s.getScolor2());
        contentValues.put("SColor3",s.getScolor3());
        contentValues.put("SColor4",s.getScolor4());
        contentValues.put("SIMG",s.getSimg());
        contentValues.put("SIMG2",s.getSimg2());
        contentValues.put("SIMG3",s.getSimg3());
        contentValues.put("SIMG4",s.getSimg4());
        return myDatabase.update("tbl_Shoe",contentValues,"PID="+s.getPid(),null);
    }

    public int updateCloth(Cloth c){
        SQLiteDatabase myDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Gender",c.getGender());
        contentValues.put("PNAME",c.getPname());
        contentValues.put("PRICE",c.getPrice());
        contentValues.put("CIMG",c.getCimg());
        return myDatabase.update("tbl_Cloth",contentValues,"PID="+c.getPid(),null);
    }

    public int updateFurn(Furniture f){
        SQLiteDatabase myDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("PNAME",f.getPname());
        contentValues.put("PRICE",f.getPrice());
        contentValues.put("FColor",f.getFcolor());
        contentValues.put("FColor2",f.getFcolor2());
        contentValues.put("FColor3",f.getFcolor3());
        contentValues.put("FIMG",f.getFimg());
        contentValues.put("FIMG2",f.getFimg2());
        contentValues.put("FIMG3",f.getFimg3());
        return myDatabase.update("tbl_Furn",contentValues,"PID="+f.getPid(),null);
    }

    public int deleteUser(User u){
        SQLiteDatabase myDatabase = getWritableDatabase();
        return myDatabase.delete("tbl_User","Username='"+u.getUsername()+"'",null);
    }

    public int deleteAdmin(Admin a){
        SQLiteDatabase myDatabase = getWritableDatabase();
        return myDatabase.delete("tbl_Admin","Uname='"+a.getUname()+"'",null);
    }

    public int deleteShoe(Shoe s){
        SQLiteDatabase myDatabase = getWritableDatabase();
        return myDatabase.delete("tbl_Shoe","PID="+s.getPid(),null);
    }

    public int deleteCloth(Cloth c){
        SQLiteDatabase myDatabase = getWritableDatabase();
        return myDatabase.delete("tbl_Cloth","PID="+c.getPid(),null);
    }

    public int deleteFurn(Furniture f){
        SQLiteDatabase myDatabase = getWritableDatabase();
        return myDatabase.delete("tbl_Furn","PID="+f.getPid(),null);
    }

    public User searchUser(User u){
        SQLiteDatabase myDatabase = getReadableDatabase();
        String sql = "SELECT * FROM tbl_User WHERE Username='" + u.getUsername()+"'";
        Cursor cursor = myDatabase.rawQuery(sql,null);
        int count = cursor.getCount();
        User user = new User();

        if (count !=0){
            if (cursor.moveToFirst()){
                user.setUsername(cursor.getString(cursor.getColumnIndex("Username")));
                user.setProfilepic(cursor.getBlob(cursor.getColumnIndex("IMG")));
                user.setName(cursor.getString(cursor.getColumnIndex("Name")));
                user.setEmail(cursor.getString(cursor.getColumnIndex("Email")));
                user.setDob(cursor.getString(cursor.getColumnIndex("DOB")));
                user.setSex(cursor.getString(cursor.getColumnIndex("Sex")));
                user.setPassword(cursor.getString(cursor.getColumnIndex("Password")));
            }else {
                user = null;
            }
        }
        return user;
    }

    public Admin searchAdmin(Admin a){
        SQLiteDatabase myDatabase = getReadableDatabase();
        String sql = "SELECT * FROM tbl_Admin WHERE Uname='" + a.getUname()+"'";
        Cursor cursor = myDatabase.rawQuery(sql,null);
        int count = cursor.getCount();
        Admin admin = new Admin();

        if (count !=0){
            if (cursor.moveToFirst()){
                admin.setUname(cursor.getString(cursor.getColumnIndex("Uname")));
                admin.setPass(cursor.getString(cursor.getColumnIndex("Pass")));
            }else {
                admin = null;
            }
        }
        return admin;
    }

    public Shoe searchShoe(Shoe s){
        SQLiteDatabase myDatabase = getReadableDatabase();
        String sql = "SELECT * FROM tbl_Shoe WHERE PID='" + s.getPid()+"'";
        Cursor cursor = myDatabase.rawQuery(sql,null);
        int count = cursor.getCount();
        Shoe shoe = new Shoe();

        if (count !=0){
            if (cursor.moveToFirst()){
                shoe.setPid(cursor.getInt(cursor.getColumnIndex("PID")));
                shoe.setPname(cursor.getString(cursor.getColumnIndex("PNAME")));
                shoe.setPrice(cursor.getDouble(cursor.getColumnIndex("PRICE")));
                shoe.setScolor(cursor.getInt(cursor.getColumnIndex("SColor")));
                shoe.setScolor2(cursor.getInt(cursor.getColumnIndex("SColor2")));
                shoe.setScolor3(cursor.getInt(cursor.getColumnIndex("SColor3")));
                shoe.setScolor4(cursor.getInt(cursor.getColumnIndex("SColor4")));
                shoe.setSimg(cursor.getBlob(cursor.getColumnIndex("SIMG")));
                shoe.setSimg2(cursor.getBlob(cursor.getColumnIndex("SIMG2")));
                shoe.setSimg3(cursor.getBlob(cursor.getColumnIndex("SIMG3")));
                shoe.setSimg4(cursor.getBlob(cursor.getColumnIndex("SIMG4")));
            }else {
                shoe = null;
            }
        }
        return shoe;
    }

    public Cloth searchCloth(Cloth c){
        SQLiteDatabase myDatabase = getReadableDatabase();
        String sql = "SELECT * FROM tbl_Cloth WHERE PID='" + c.getPid()+"'";
        Cursor cursor = myDatabase.rawQuery(sql,null);
        int count = cursor.getCount();
        Cloth cloth = new Cloth();

        if (count !=0){
            if (cursor.moveToFirst()){
                cloth.setPid(cursor.getInt(cursor.getColumnIndex("PID")));
                cloth.setGender(cursor.getString(cursor.getColumnIndex("Gender")));
                cloth.setPname(cursor.getString(cursor.getColumnIndex("PNAME")));
                cloth.setPrice(cursor.getDouble(cursor.getColumnIndex("PRICE")));
                cloth.setCimg(cursor.getBlob(cursor.getColumnIndex("CIMG")));
            }else {
                cloth = null;
            }
        }
        return cloth;
    }

    public Furniture searchFurn(Furniture f){
        SQLiteDatabase myDatabase = getReadableDatabase();
        String sql = "SELECT * FROM tbl_Furn WHERE PID='" + f.getPid()+"'";
        Cursor cursor = myDatabase.rawQuery(sql,null);
        int count = cursor.getCount();
        Furniture furniture = new Furniture();

        if (count !=0){
            if (cursor.moveToFirst()){
                furniture.setPid(cursor.getInt(cursor.getColumnIndex("PID")));
                furniture.setPname(cursor.getString(cursor.getColumnIndex("PNAME")));
                furniture.setPrice(cursor.getDouble(cursor.getColumnIndex("PRICE")));
                furniture.setFcolor(cursor.getInt(cursor.getColumnIndex("FColor")));
                furniture.setFcolor2(cursor.getInt(cursor.getColumnIndex("FColor2")));
                furniture.setFcolor3(cursor.getInt(cursor.getColumnIndex("FColor3")));
                furniture.setFimg(cursor.getBlob(cursor.getColumnIndex("FIMG")));
                furniture.setFimg2(cursor.getBlob(cursor.getColumnIndex("FIMG2")));
                furniture.setFimg3(cursor.getBlob(cursor.getColumnIndex("FIMG3")));
            }else {
                furniture = null;
            }
        }
        return furniture;
    }

    public ArrayList<User> viewUsers(){
        ArrayList<User> userArrayList = new ArrayList<User>();
        String sql = "SELECT * FROM tbl_User";
        SQLiteDatabase myDatabase = getReadableDatabase();
        Cursor cursor = myDatabase.rawQuery(sql,null);

        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                User u = new User();
                u.setUsername(cursor.getString(0));
                u.setProfilepic(cursor.getBlob(1));
                u.setName(cursor.getString(2));
                u.setEmail(cursor.getString(3));
                u.setDob(cursor.getString(4));
                u.setSex(cursor.getString(5));
                u.setPassword(cursor.getString(6));
                userArrayList.add(u);
            }
        }else {
            userArrayList = null;
        }
        return userArrayList;
    }

    public ArrayList<Admin> viewAdmins(){
        ArrayList<Admin> adminArrayList = new ArrayList<Admin>();
        String sql = "SELECT * FROM tbl_Admin";
        SQLiteDatabase myDatabase = getReadableDatabase();
        Cursor cursor = myDatabase.rawQuery(sql,null);

        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Admin a = new Admin();
                a.setUname(cursor.getString(0));
                a.setPass(cursor.getString(1));
                adminArrayList.add(a);
            }
        }else {
            adminArrayList = null;
        }
        return adminArrayList;
    }

    public ArrayList<Shoe> viewShoes(){
        SQLiteDatabase myDatabase = getReadableDatabase();
        String sql = "SELECT * FROM tbl_Shoe";
        Cursor cursor = myDatabase.rawQuery(sql,null);
        ArrayList<Shoe> shoes = new ArrayList<Shoe>();
        int count = cursor.getCount();

        if (count !=0){
            while (cursor.moveToNext()){
                Shoe s = new Shoe();
                s.setPid(cursor.getInt(0));
                s.setPname(cursor.getString(1));
                s.setPrice(cursor.getDouble(2));
                s.setScolor(cursor.getInt(3));
                s.setScolor2(cursor.getInt(4));
                s.setScolor3(cursor.getInt(5));
                s.setScolor4(cursor.getInt(6));
                s.setSimg(cursor.getBlob(7));
                s.setSimg2(cursor.getBlob(8));
                s.setSimg3(cursor.getBlob(9));
                s.setSimg4(cursor.getBlob(10));
                shoes.add(s);
            }
        }else {
            shoes = null;
        }
        return shoes;
    }

    public ArrayList<Cloth> viewClothes(){
        SQLiteDatabase myDatabase = getReadableDatabase();
        String sql = "SELECT * FROM tbl_Cloth";
        Cursor cursor = myDatabase.rawQuery(sql,null);
        ArrayList<Cloth> clothes = new ArrayList<Cloth>();
        int count = cursor.getCount();

        if (count !=0){
            while (cursor.moveToNext()){
                Cloth c = new Cloth();
                c.setPid(cursor.getInt(0));
                c.setGender(cursor.getString(1));
                c.setPname(cursor.getString(2));
                c.setPrice(cursor.getDouble(3));
                c.setCimg(cursor.getBlob(4));
                clothes.add(c);
            }
        }else {
            clothes = null;
        }
        return clothes;
    }

    public ArrayList<Furniture> viewFurns(){
        SQLiteDatabase myDatabase = getReadableDatabase();
        String sql = "SELECT * FROM tbl_Furn";
        Cursor cursor = myDatabase.rawQuery(sql,null);
        ArrayList<Furniture> furnitures = new ArrayList<Furniture>();
        int count = cursor.getCount();

        if (count !=0){
            while (cursor.moveToNext()){
                Furniture f = new Furniture();
                f.setPid(cursor.getInt(0));
                f.setPname(cursor.getString(1));
                f.setPrice(cursor.getDouble(2));
                f.setFcolor(cursor.getInt(3));
                f.setFcolor2(cursor.getInt(4));
                f.setFcolor3(cursor.getInt(5));
                f.setFimg(cursor.getBlob(6));
                f.setFimg2(cursor.getBlob(7));
                f.setFimg3(cursor.getBlob(8));
                furnitures.add(f);
            }
        }else {
            furnitures = null;
        }
        return furnitures;
    }
}