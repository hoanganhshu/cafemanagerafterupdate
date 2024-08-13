package com.example.cafemanagerapp.Activity.Fragment;

import java.io.Serializable;
import java.util.List;

public class Gio_hanglist implements Serializable {
     int soluong;
     String namesp;
     String hinhsp;
     String motasp;
     int giatien;


    public Gio_hanglist(int soluong, String namesp, String hinhsp, String motasp, int giatien) {
        this.soluong = soluong;
        this.namesp = namesp;
        this.hinhsp = hinhsp;
        this.motasp = motasp;
        this.giatien = giatien;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getNamesp() {
        return namesp;
    }

    public void setNamesp(String namesp) {
        this.namesp = namesp;
    }

    public String getHinhsp() {
        return hinhsp;
    }

    public void setHinhsp(String hinhsp) {
        this.hinhsp = hinhsp;
    }

    public String getMotasp() {
        return motasp;
    }

    public void setMotasp(String motasp) {
        this.motasp = motasp;
    }

    public int getGiatien() {
        return giatien;
    }

    public void setGiatien(int giatien) {
        this.giatien = giatien;
    }
}
