package com.example.prime_paradise;

public class Wishlist {

    private int itemno;
    private  String uname;
    private int pid;
    private String type;
    private String pname;
    private double price;
    byte[] coverimg;

    public int getItemno() {
        return itemno;
    }

    public void setItemno(int itemno) {
        this.itemno = itemno;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public byte[] getCoverimg() { return coverimg; }

    public void setCoverimg(byte[] coverimg) { this.coverimg = coverimg; }
}
