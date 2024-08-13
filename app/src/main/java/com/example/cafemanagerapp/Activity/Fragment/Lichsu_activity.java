package com.example.cafemanagerapp.Activity.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cafemanagerapp.Activity.Adapter.Adapterlichsudonhang;
import com.example.cafemanagerapp.Activity.util.Server;
import com.example.cafemanagerapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Lichsu_activity extends AppCompatActivity {
    public Toolbar toolbarLichSu;
    public Button choxacnhan, dahuy, danggiao, hoanthanh;
    public ListView listViewLichSu;
    public ArrayList<Lichsusanpham> arrayList;
    public ArrayList<Lichsusanpham> filteredList;
    public Adapterlichsudonhang adapterlichsudonhang;
    public String thongbao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lichsu);
        Anhxa();
        setuptoolbar();
        fetchAllData();
        setupButtonListeners();
    }

    private void fetchAllData() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = Server.Duongdanlichsu;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            arrayList.clear();

                            String status = response.getString("status");
                            if ("success".equals(status)) {
                                JSONArray dataArray = response.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {
                                    JSONObject obj = dataArray.getJSONObject(i);

                                    String productName = obj.getString("product_name");
                                    String hinhsp = obj.getString("hinhhoadon");
                                    int giahoadon = obj.getInt("giahoadon");
                                    String noteContent = obj.getString("notecontent");
                                    String magiaodich = obj.getString("magiaodich");
                                    int iddonhang = obj.getInt("iddonhang");
                                    int nhandonhang = obj.getInt("nhandonhang");

                                    Loadata(productName, hinhsp, giahoadon, magiaodich, nhandonhang, iddonhang);
                                }
                            } else {
                                Toast.makeText(Lichsu_activity.this, "No data found", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(Lichsu_activity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(Lichsu_activity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }

    private void setupButtonListeners() {
        choxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterData(0);
                updateButtonStyles(choxacnhan);
            }
        });

        dahuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterData(3);
                updateButtonStyles(dahuy);
            }
        });

        danggiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterData(1);
                updateButtonStyles(danggiao);
            }
        });

        hoanthanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterData(2);
                updateButtonStyles(hoanthanh);
            }
        });
    }

    private void filterData(int nhandonhang) {
        filteredList.clear();
        for (Lichsusanpham item : arrayList) {
            if (item.getNhandonhang() == nhandonhang) {
                filteredList.add(item);
            }
        }
        adapterlichsudonhang.notifyDataSetChanged();
    }

    private void updateButtonStyles(Button selectedButton) {
        choxacnhan.setBackgroundColor(Color.parseColor("#8B4513"));
        choxacnhan.setTextColor(Color.parseColor("#FFFFFF"));
        dahuy.setBackgroundColor(Color.parseColor("#8B4513"));
        dahuy.setTextColor(Color.parseColor("#FFFFFF"));
        danggiao.setBackgroundColor(Color.parseColor("#8B4513"));
        danggiao.setTextColor(Color.parseColor("#FFFFFF"));
        hoanthanh.setBackgroundColor(Color.parseColor("#8B4513"));
        hoanthanh.setTextColor(Color.parseColor("#FFFFFF"));

        selectedButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
        selectedButton.setTextColor(Color.parseColor("#8B4513"));
    }

    private void Anhxa() {
        choxacnhan = findViewById(R.id.choxacnhan);
        dahuy = findViewById(R.id.dahuy);
        danggiao = findViewById(R.id.danggiao);
        hoanthanh = findViewById(R.id.hoanthanh);
        listViewLichSu = findViewById(R.id.listviewlichsu);
        arrayList = new ArrayList<>();
        filteredList = new ArrayList<>();
        adapterlichsudonhang = new Adapterlichsudonhang(filteredList, this);
        listViewLichSu.setAdapter(adapterlichsudonhang);
        toolbarLichSu=findViewById(R.id.toolbarlichsu);
    }

    private void Loadata(String productName, String hinhsp, int giahoadon, String magiaodich, int nhandonhang, int iddonhang) {
        if(nhandonhang==0){
            thongbao="Đơn hàng đang chờ xác nhận";
        } else if (nhandonhang==1) {
            thongbao="Đơn hàng đang được giao";
        } else if (nhandonhang==2) {
            thongbao="Đơn hàng đã hoàn thành";
        } else if (nhandonhang==3) {
            thongbao="Đơn hàng đã bị hủy";
        }
        arrayList.add(new Lichsusanpham(magiaodich, giahoadon, productName, hinhsp, iddonhang, thongbao, nhandonhang));
    }

    private void setuptoolbar() {
        super.setSupportActionBar(toolbarLichSu);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbarLichSu.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   finish();
                }
            });

        }
    }
}
