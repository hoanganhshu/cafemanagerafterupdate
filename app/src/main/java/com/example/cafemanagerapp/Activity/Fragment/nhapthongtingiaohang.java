package com.example.cafemanagerapp.Activity.Fragment;

import android.widget.RadioButton;
import android.widget.TextView;

public class nhapthongtingiaohang {


    String nhapten;
    String nhapsdt;
    String nhapdiachi;
    boolean ischeckedradiobutton;

    public nhapthongtingiaohang(String nhapten, String nhapsdt, String nhapdiachi, boolean ischeckedradiobutton) {
        this.nhapten = nhapten;
        this.nhapsdt = nhapsdt;
        this.nhapdiachi = nhapdiachi;
        this.ischeckedradiobutton = ischeckedradiobutton;
    }

    public String getNhapten() {
        return nhapten;
    }

    public void setNhapten(String nhapten) {
        this.nhapten = nhapten;
    }

    public String getNhapsdt() {
        return nhapsdt;
    }

    public void setNhapsdt(String nhapsdt) {
        this.nhapsdt = nhapsdt;
    }

    public String getNhapdiachi() {
        return nhapdiachi;
    }

    public void setNhapdiachi(String nhapdiachi) {
        this.nhapdiachi = nhapdiachi;
    }

    public boolean isIscheckedradiobutton() {
        return ischeckedradiobutton;
    }

    public void setIscheckedradiobutton(boolean ischeckedradiobutton) {
        this.ischeckedradiobutton = ischeckedradiobutton;
    }
}
