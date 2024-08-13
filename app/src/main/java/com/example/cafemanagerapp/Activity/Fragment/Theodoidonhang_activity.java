package com.example.cafemanagerapp.Activity.Fragment;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.WHITE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cafemanagerapp.Activity.Adapter.Giohangadapter;
import com.example.cafemanagerapp.Activity.Adapter.Sanphamadapter;
import com.example.cafemanagerapp.Activity.util.Server;
import com.example.cafemanagerapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class Theodoidonhang_activity extends AppCompatActivity {

    public RecyclerView listViewTheoDoiDonHang;
    public TextView nhandonhang1;
    public ToggleButton toggle1, toggle2, toggle3;
    public Button buttonNhanDonHang;
    public View view1,view2;
    public Toolbar toolbar;
    public ArrayList<Gio_hanglist> arrayList;
    public Giohangadapter giohangadapter;
    public Handler handler;
    public Runnable runnable;
    public static final int DELAY = 5000;

    int iddonhang=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theodoidonhang);
        Anhxa();
        Eventnhanhang();
        Loaddata();
        checkOrderStatus();
        handler = new Handler(Looper.getMainLooper());

        runnable = new Runnable() {
            @Override
            public void run() {
                checkOrderStatus();
                handler.postDelayed(this, DELAY);
            }
        };

        handler.post(runnable);
    }

    private void Loaddata() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String jsonArrayList = bundle.getString("jsonArrayList");
            if (jsonArrayList != null && !jsonArrayList.isEmpty()) {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<Gio_hanglist>>() {}.getType();
                ArrayList<Gio_hanglist> receivedArrayList = gson.fromJson(jsonArrayList, type);

                if (receivedArrayList != null && !receivedArrayList.isEmpty()) {
                    giohangadapter = new Giohangadapter(this, receivedArrayList);
                    listViewTheoDoiDonHang.setAdapter(giohangadapter);
                } else {
                    Toast.makeText(this, "No data available", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Invalid data format", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No extras found", Toast.LENGTH_SHORT).show();
        }

    }






    private void Eventnhanhang() {
        buttonNhanDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   ArrayList<String> namespList = new ArrayList<>();
                if (arrayList != null) {
                    for (int i = 0; i < arrayList.size(); i++) {
                        Gio_hanglist item = arrayList.get(i);
                        if (item != null) {
                            namespList.add(item.getNamesp());
                        }
                    }
                }

                String[] namespArray = namespList.toArray(new String[0]);
                int iddonhang = getIntent().getIntExtra("iddonhang", 0);
                Intent intent = new Intent(Theodoidonhang_activity.this, Danhgiadonhang.class);
                intent.putExtra("iddonhang", iddonhang);
                intent.putExtra("namespArray", namespArray);
                startActivity(intent);
            }
        });
    }

    private void Anhxa() {
        listViewTheoDoiDonHang = findViewById(R.id.listviewtheodoidonhang);
        listViewTheoDoiDonHang.setLayoutManager(new LinearLayoutManager(this));
        toggle1 = findViewById(R.id.toggle1);
        toggle2 = findViewById(R.id.toggle2);
        toggle3 = findViewById(R.id.toggle3);
        toggle1.setBackgroundColor(GREEN);
        buttonNhanDonHang = findViewById(R.id.buttonnhandonhang);
        view1=findViewById(R.id.view1);
        view2=findViewById(R.id.view2);
        toolbar=findViewById(R.id.toolbartheodoidonhang);
        setSupportActionBar(toolbar);
        nhandonhang1=findViewById(R.id.textviewnhandonhang);
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
    private void checkOrderStatus()   {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = Server.Duongdantheodoioiddonhang;
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("iddonhang",iddonhang);
        } catch (Exception e) {
            e.printStackTrace();

        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("Response", response.toString());
                            if (response.getString("status").equals("success")) {
                                Toast.makeText(Theodoidonhang_activity.this, "Theo dõi đơn hàng", Toast.LENGTH_SHORT).show();
                                int nhandonhang = response.getInt("nhandonhang");
                                if (nhandonhang == 1) {
                                    toggle2.setChecked(true);
                                    nhandonhang1.setVisibility(View.INVISIBLE);
                                    view2.setBackgroundColor(Color.GREEN);
                                    buttonNhanDonHang.setAlpha(0.5f);
                                    buttonNhanDonHang.setEnabled(false);
                                } else if (nhandonhang == 2) {
                                    toggle2.setChecked(true);
                                    view2.setBackgroundColor(Color.GREEN);
                                    toggle3.setChecked(true);
                                    buttonNhanDonHang.setAlpha(0.5f);
                                    buttonNhanDonHang.setEnabled(true);
                                    nhandonhang1.setVisibility(View.VISIBLE);
                                } else {
                                    buttonNhanDonHang.setAlpha(0.5f);
                                    buttonNhanDonHang.setEnabled(false);
                                    nhandonhang1.setVisibility(View.INVISIBLE);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(Theodoidonhang_activity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }




    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
    }
