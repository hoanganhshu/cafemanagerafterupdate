package Model;

import java.util.List;

public class Sanpham  {
    String namesp;
    int idsp;
    int giasp;
    int giacusp;
    String hinhsp;
    float danhgiasp;
    String motasp;
    int idloaisp;

    public Sanpham(String namesp, int idsp, int giasp, int giacusp, String hinhsp, float danhgiasp, String motasp, int idloaisp) {
        this.namesp = namesp;
        this.idsp = idsp;
        this.giasp = giasp;
        this.giacusp = giacusp;
        this.hinhsp = hinhsp;
        this.danhgiasp = danhgiasp;
        this.motasp = motasp;
        this.idloaisp = idloaisp;
    }

    public String getNamesp() {
        return namesp;
    }

    public void setNamesp(String namesp) {
        this.namesp = namesp;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
        this.giasp = giasp;
    }

    public int getGiacusp() {
        return giacusp;
    }

    public void setGiacusp(int giacusp) {
        this.giacusp = giacusp;
    }

    public String getHinhsp() {
        return hinhsp;
    }

    public void setHinhsp(String hinhsp) {
        this.hinhsp = hinhsp;
    }

    public float getDanhgiasp() {
        return danhgiasp;
    }

    public void setDanhgiasp(float danhgiasp) {
        this.danhgiasp = danhgiasp;
    }

    public String getMotasp() {
        return motasp;
    }

    public void setMotasp(String motasp) {
        this.motasp = motasp;
    }

    public int getIdloaisp() {
        return idloaisp;
    }

    public void setIdloaisp(int idloaisp) {
        this.idloaisp = idloaisp;
    }
}