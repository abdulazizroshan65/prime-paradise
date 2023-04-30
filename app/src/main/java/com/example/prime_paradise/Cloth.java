package com.example.prime_paradise;

public class Cloth {

    private int pid;
    private String gender;
    private String pname;
    private double price;
    byte[] cimg;


    public int getPid() { return pid; }
    public void setPid(int pid) { this.pid = pid; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getPname() { return pname; }
    public void setPname(String pname) { this.pname = pname; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public byte[] getCimg() { return cimg; }
    public void setCimg(byte[] cimg) { this.cimg = cimg; }
}
