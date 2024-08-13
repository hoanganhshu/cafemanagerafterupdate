package com.example.cafemanagerapp.Activity.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cafemanagerapp.Activity.util.Server;
import com.example.cafemanagerapp.R;

import org.json.JSONException;
import org.json.JSONObject;

public class phanhoi_activity extends AppCompatActivity {
    public Toolbar toolbar;
    public Button phanhoi;
    public TextView noidung,hoten,sdt,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phanhoi);
        AnhXa();
        guiphanhoi();

    }

    private void guiphanhoi() {

        phanhoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noidungphanhoi=noidung.getText().toString();
                String hotenphanhoi=hoten.getText().toString();
                String emailphanhoi=email.getText().toString();
                String sdtphanhoi= sdt.getText().toString();
                if(noidungphanhoi.isEmpty()&&hotenphanhoi.isEmpty()&&emailphanhoi.isEmpty()&&sdtphanhoi.isEmpty()){
                    Toast.makeText(phanhoi_activity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else {
                   sendFeedback(noidungphanhoi,hotenphanhoi,emailphanhoi,sdtphanhoi);
                }
            }
        });

    }

    private void sendFeedback(String noidungphanhoi, String hotenphanhoi, String emailphanhoi, String sdtphanhoi) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url= Server.Duongdanphanhoi;
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("noidungphanhoi", noidungphanhoi);
            jsonRequest.put("hotenphanhoi", hotenphanhoi);
            jsonRequest.put("emailphanhoi", emailphanhoi);
            jsonRequest.put("sdtphanhoi", sdtphanhoi);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(phanhoi_activity.this, "Đã gửi phản hồi", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(phanhoi_activity.this, "Đã xảy ra lỗi", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }


    private void AnhXa() {
        toolbar=findViewById(R.id.toolbarphanhoi);
        setSupportActionBar(toolbar);
        phanhoi=findViewById(R.id.buttonphanhoi);
        noidung=findViewById(R.id.noidungphanhoi);
        hoten=findViewById(R.id.hotenphanhoi);
        sdt=findViewById(R.id.sdtphanhoi);
        email=findViewById(R.id.emailphanhoi);
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