

package com.example.cafemanagerapp.Activity.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cafemanagerapp.Activity.Adapter.Giohangadapter;
import com.example.cafemanagerapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Objects;

public class Gio_hang_sp extends AppCompatActivity {
    public ImageView imgViewSp;
    public TextView txtViewNameSp, txtViewGiaSp;
    public ImageButton btnXoaSanPham;
    public Button btnDatHang;
    public TextView txtSoTien, txtSoLuong, txtKhuyenMaiDaApDung, txtTongTien;
    public Toolbar khuyemai, diachi, phuongthucthanhtoan;
    public String namesp, hinhsp;
    public int giasp = 0, soluong = 0;
    public RecyclerView listView;
    public ArrayList<Gio_hanglist> arrayList;
    public Giohangadapter giohangadapter;
    public Toolbar toolbar, themgiohang;
    public int tienchkhuyenmai = 0, tongtien = 0;
    float tienkhuyenmai = 0;
    String sdt = "";
    String khuyenmai = "", phuongthuc = "", hoten = "", adress = "",notecontent="";

    public TextView chondiachi, chonkhuyenmai, chonphuongthuc;
    private static final int REQUEST_CODE = 1;

    private static final int Dia_chi_REQUEST_CODE = 2;
    private static final int Phuongthuc_REQUEST_CODE = 3;
    String chitiet;
    String hinhhoadon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang_sp);

        Anhxa();
        Loaddata();
        Themsanpham();
        updatedata();
        Eventthemthongtin();
    }


    private void Themsanpham() {
        themgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Gio_hang_sp.this, Trang_chuActivity.class);
                startActivity(intent);

            }
        });
    }

    public void Loaddata() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {

            namesp = bundle.getString("namesp");
            hinhsp = bundle.getString("hinhsp");
            soluong = bundle.getInt("soluong");
            giasp = bundle.getInt("giasp");
            chitiet=bundle.getString("chitiet");
            notecontent=bundle.getString("notecontent");
        }


        Gio_hanglist newItem = new Gio_hanglist(soluong, namesp, hinhsp, chitiet, giasp);
        addItemToCart(newItem);

    }




    private void Eventthemthongtin() {

        khuyemai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Gio_hang_sp.this, khuyenmai_activity.class);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });
        diachi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Gio_hang_sp.this, diachigiaohang_activity.class);
                startActivityForResult(intent, Dia_chi_REQUEST_CODE);
            }
        });

        phuongthucthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Gio_hang_sp.this, Phuongthucthanhtoan_activity.class);
                startActivityForResult(intent, Phuongthuc_REQUEST_CODE);
            }
        });


        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Gio_hang_sp.this, Hoa_donactivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("tienchkhuyenmai", tienchkhuyenmai);
                bundle.putFloat("tienkhuyenmai", tienkhuyenmai);
                bundle.putInt("tongtien", tongtien);
                bundle.putString("phuongthuc", phuongthuc);
                bundle.putString("hoten", hoten);
                bundle.putString("sdt", sdt);
                bundle.putString("diachi", adress);
                bundle.putString("hinhhoadon",hinhhoadon);

                StringBuilder stringBuilder = new StringBuilder();
                for (Gio_hanglist sp : arrayList) {
                    stringBuilder.append(sp.getNamesp()).append(",");
                }
                if (stringBuilder.length() > 0) {
                    stringBuilder.setLength(stringBuilder.length() - 1);
                }

                bundle.putString("namecacsp", stringBuilder.toString());
                bundle.putInt("giahoadon", Integer.valueOf( txtTongTien.getText().toString()));
                bundle.putInt("soluong",soluong);
                bundle.putString("notecontent",notecontent);
                Gson gson = new Gson();
                String jsonArrayList = gson.toJson(arrayList);
                bundle.putString("arrayList", jsonArrayList);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }


    public void updatedata() {
        SharedPreferences sharedPreferences = getSharedPreferences("CartPrefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String cartJson = sharedPreferences.getString("cart", "[]");
        ArrayList<Gio_hanglist> cartList = gson.fromJson(cartJson, new TypeToken<ArrayList<Gio_hanglist>>() {
        }.getType());

        arrayList.clear();
        arrayList.addAll(cartList);
        giohangadapter.notifyDataSetChanged();
    }

    public void addItemToCart(Gio_hanglist item) {
        SharedPreferences sharedPreferences = getSharedPreferences("CartPrefs", MODE_PRIVATE);
        Gson gson = new Gson();
        ArrayList<Gio_hanglist> cartList = gson.fromJson(sharedPreferences.getString("cart", "[]"), new TypeToken<ArrayList<Gio_hanglist>>() {}.getType());
        boolean itemExists = false;
        for (Gio_hanglist existingItem : cartList) {
            if (existingItem.getNamesp().equals(item.getNamesp())) {
                existingItem.setSoluong(existingItem.getSoluong() + item.getSoluong());
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            cartList.add(item);
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("cart", gson.toJson(cartList));
        editor.apply();
        arrayList.clear();
        arrayList.addAll(cartList);
        giohangadapter.notifyDataSetChanged();

        tienchkhuyenmai = 0;
        for (Gio_hanglist cartItem : arrayList) {
            int giasanpham = cartItem.getSoluong() * cartItem.getGiatien();
            hinhhoadon=cartList.get(0).getHinhsp();
            tienchkhuyenmai += giasanpham;
        }

        txtSoTien.setText(String.valueOf(tienchkhuyenmai));

            txtTongTien.setText(String.valueOf(tienchkhuyenmai));



    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {

            khuyenmai = data.getStringExtra("khuyenmai");
            if (khuyenmai != null) {
                if (khuyenmai.equals("Giảm giá 30%")) {
                    tienkhuyenmai = (float) (tienchkhuyenmai * 0.3);
                    chonkhuyenmai.setText(khuyenmai);
                    txtKhuyenMaiDaApDung.setText(String.valueOf(tienkhuyenmai));
                    tongtien = tienchkhuyenmai - (int) tienkhuyenmai;
                    txtTongTien.setText(String.valueOf(tongtien));
                } else if (khuyenmai.equals("Giảm giá 40%")) {
                    tienkhuyenmai = (float) (tienchkhuyenmai * 0.4);
                    chonkhuyenmai.setText(khuyenmai);
                    txtKhuyenMaiDaApDung.setText(String.valueOf(tienkhuyenmai));
                    tongtien = tienchkhuyenmai - (int) tienkhuyenmai;
                    txtTongTien.setText(String.valueOf(tongtien));
                } else if (khuyenmai.equals("Giảm giá 15%")) {
                    tienkhuyenmai = (float) (tienchkhuyenmai * 0.15);
                    chonkhuyenmai.setText(khuyenmai);
                    txtKhuyenMaiDaApDung.setText(String.valueOf(tienkhuyenmai));
                    tongtien = tienchkhuyenmai - (int) tienkhuyenmai;
                    txtTongTien.setText(String.valueOf(tongtien));
                }
            } else {
                chonkhuyenmai.setText("Chưa chọn khuyến mãi");
                tongtien = tienchkhuyenmai;
            }
        } else if (requestCode == Dia_chi_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            hoten = data.getStringExtra("hoten");
            sdt = data.getStringExtra("sdt");
            adress = data.getStringExtra("diachi");

            chondiachi.setText("Đã chọn địa chỉ giao hàng");

        } else if (requestCode == Phuongthuc_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            phuongthuc = data.getStringExtra("phuongthuc");
            chonphuongthuc.setText(phuongthuc);
        }
    }


    private void Anhxa() {
        khuyemai = findViewById(R.id.khuyenmai);
        diachi = findViewById(R.id.diachigiaohang);
        phuongthucthanhtoan = findViewById(R.id.phuongthucthanhtoan);
       btnXoaSanPham = findViewById(R.id.xoasanpham);
        btnDatHang = findViewById(R.id.btndathang);
        txtSoTien = findViewById(R.id.txtsotien);
        txtSoLuong = findViewById(R.id.txtsoluong);
        txtKhuyenMaiDaApDung = findViewById(R.id.txtkhuyenmaidaapdung);
        txtTongTien = findViewById(R.id.txttongtien);
        listView = findViewById(R.id.listviewgiohang);
        listView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<>();
        giohangadapter = new Giohangadapter(getApplicationContext(), arrayList);
        listView.setAdapter(giohangadapter);
        toolbar = findViewById(R.id.toolbargiohang);
        themgiohang = findViewById(R.id.themvaogiohang);
        chondiachi = findViewById(R.id.chondiahcigiaohang);
        chonkhuyenmai = findViewById(R.id.chonkhuyenmai);
        chonphuongthuc = findViewById(R.id.chonphuongthucthanhtoan);
        setSupportActionBar(toolbar);


    }


    public void setSupportActionBar(Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

}

