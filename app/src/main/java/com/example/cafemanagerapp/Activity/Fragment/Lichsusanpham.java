package com.example.cafemanagerapp.Activity.Fragment;

public class Lichsusanpham {
    String magiaodich;
    int giahoadon;
    String productName;
    String hinhhoadon;
    int iddonhang;
    String thongbaodonhang;
    int nhandonhang;

    public Lichsusanpham(String magiaodich, int giahoadon, String productName, String hinhhoadon, int iddonhang, String thongbaodonhang, int nhandonhang) {
        this.magiaodich = magiaodich;
        this.giahoadon = giahoadon;
        this.productName = productName;
        this.hinhhoadon = hinhhoadon;
        this.iddonhang = iddonhang;
        this.thongbaodonhang = thongbaodonhang;
        this.nhandonhang = nhandonhang;
    }

    public String getMagiaodich() {
        return magiaodich;
    }

    public void setMagiaodich(String magiaodich) {
        this.magiaodich = magiaodich;
    }

    public int getGiahoadon() {
        return giahoadon;
    }

    public void setGiahoadon(int giahoadon) {
        this.giahoadon = giahoadon;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getHinhhoadon() {
        return hinhhoadon;
    }

    public void setHinhhoadon(String hinhhoadon) {
        this.hinhhoadon = hinhhoadon;
    }

    public int getIddonhang() {
        return iddonhang;
    }

    public void setIddonhang(int iddonhang) {
        this.iddonhang = iddonhang;
    }

    public String getThongbaodonhang() {
        return thongbaodonhang;
    }

    public void setThongbaodonhang(String thongbaodonhang) {
        this.thongbaodonhang = thongbaodonhang;
    }

    public int getNhandonhang() {
        return nhandonhang;
    }

    public void setNhandonhang(int nhandonhang) {
        this.nhandonhang = nhandonhang;
    }
}