package com.example.cafemanagerapp.Activity.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cafemanagerapp.Activity.Adapter.Giohangadapter;
import com.example.cafemanagerapp.Activity.util.Server;
import com.example.cafemanagerapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Hoa_donactivity extends AppCompatActivity {

    private Button btnTheoDoiDonHang;
    private TextView tvMaGiaoDich, tvThoiGian, tvGia, tvKhuyenMai, tvTongTien, tvPhuongThuc, tvHoTen, tvDienThoai, tvDiaChi;
    private RecyclerView lvThanhToanThanhCong;
    private Toolbar toolbar;

    private Giohangadapter giohangadapter;
    private String namesp = "", hinhsp = "", noteContent = "";
    private int soluong = 0, giatien = 0;
    private int iddonhang = 0;
    String hinhhoadon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_donactivity);
        initializeViews();
        loadData();
        uploadProductData(namesp, soluong);
        setupEventListeners();
    }

    private void initializeViews() {
        btnTheoDoiDonHang = findViewById(R.id.buttontheodoidonhang);
        tvMaGiaoDich = findViewById(R.id.magiaodichthanhtoan);
        tvThoiGian = findViewById(R.id.thoigianthanhtoan);
        lvThanhToanThanhCong = findViewById(R.id.listviewthanhtoanthanhcong);
        lvThanhToanThanhCong.setLayoutManager(new LinearLayoutManager(this));
        tvGia = findViewById(R.id.giathanhtoan);
        tvKhuyenMai = findViewById(R.id.khuyenmaithanhtoan);
        tvTongTien = findViewById(R.id.tongtienthanhtoan);
        tvPhuongThuc = findViewById(R.id.phuongthucthanhtoanthanhtoan);
        tvHoTen = findViewById(R.id.hotenthanhtoan);
        tvDienThoai = findViewById(R.id.sdtthanhtoan);
        tvDiaChi = findViewById(R.id.diachithanhtoan);
        toolbar = findViewById(R.id.toolbarhoadon);
        setSupportActionBar(toolbar);


    }

    private void loadData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {

            tvDienThoai.setText(bundle.getString("sdt"));
            tvHoTen.setText(bundle.getString("hoten"));
            tvTongTien.setText(String.valueOf(bundle.getInt("tongtien", 0)));
            tvDiaChi.setText(bundle.getString("diachi"));
            tvKhuyenMai.setText(String.valueOf(bundle.getFloat("tienkhuyenmai", 0)));
            tvPhuongThuc.setText(bundle.getString("phuongthuc"));
            tvGia.setText(String.valueOf(bundle.getInt("tienchkhuyenmai", 0)));

            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedTime = currentTime.format(formatter);
            tvThoiGian.setText(formattedTime);


            int min = 10000000;
            int max = 99999999;
            int randomNumber = ThreadLocalRandom.current().nextInt(min, max + 1);
            tvMaGiaoDich.setText(String.valueOf(randomNumber));


            soluong = bundle.getInt("soluong", 0);
            namesp = bundle.getString("namecacsp");
            noteContent = bundle.getString("notecontent");
            giatien = bundle.getInt("giahoadon", 0);
            hinhhoadon=bundle.getString("hinhhoadon");


            String jsonArrayList = bundle.getString("arrayList");
            if (jsonArrayList != null) {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<Gio_hanglist>>() {}.getType();
                ArrayList<Gio_hanglist> receivedArrayList = gson.fromJson(jsonArrayList, type);
                giohangadapter = new Giohangadapter(getApplicationContext(), receivedArrayList);
                lvThanhToanThanhCong.setAdapter(giohangadapter);
            } else {
                Toast.makeText(this, "No data available", Toast.LENGTH_SHORT).show();
            }

        }
    }





    private void setupEventListeners() {
        btnTheoDoiDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Hoa_donactivity.this, Theodoidonhang_activity.class);
                Gson gson = new Gson();
                ArrayList<Gio_hanglist> arrayListToSend = giohangadapter.getData(); // Thay đổi thành dữ liệu thực tế nếu có
                String jsonArrayList = gson.toJson(arrayListToSend);

                Bundle bundle = new Bundle();
                bundle.putString("jsonArrayList", jsonArrayList);
                bundle.putInt("soluong", soluong);
                bundle.putString("namesp", namesp);
                bundle.putInt("giasp", giatien);
                bundle.putString("notecontent", noteContent);
                bundle.putString("hinhsp", hinhsp);
                bundle.putInt("iddonhang", iddonhang);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


    private void uploadProductData(String product_name, int quantity) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("product_name", product_name);
            jsonObject.put("quantity", quantity);
            jsonObject.put("giahoadon",giatien);
            jsonObject.put("hinhhoadon",hinhhoadon);
            jsonObject.put("notecontent",noteContent);
            jsonObject.put("magiaodich",tvMaGiaoDich.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("JSON Data", jsonObject.toString());
        String url = Server.Duongdanhoadon;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("status").equals("success")) {
                                iddonhang = response.getInt("iddonhang");
                                Toast.makeText(Hoa_donactivity.this, "Product data saved successfully. Order ID: " + iddonhang, Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(Hoa_donactivity.this, "Error: " + response.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Log.d("Response", response.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(Hoa_donactivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("Error", error.toString());

                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    } public void setSupportActionBar(Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }}}



