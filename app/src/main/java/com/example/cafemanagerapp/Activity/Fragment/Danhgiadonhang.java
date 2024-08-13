package com.example.cafemanagerapp.Activity.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
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

import org.json.JSONArray;
import org.json.JSONObject;

public class Danhgiadonhang extends AppCompatActivity {
    public ImageView imageView;
    public TextView titleTextView, ratingTextView, reviewTextView, publicNoticeTextView;
    public RatingBar ratingBar;
    public EditText reviewEditText;
    public Button submitButton;
    public Toolbar toolbar;
    float rate = 0;
    String data = "";
    String namesp;

    String[] namecacsp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhgiadonhang);
        Anhxa();
        senddata();
    }

    private void senddata() {
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rate = rating;
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                data = reviewEditText.getText().toString().trim();
               namecacsp=getIntent().getStringArrayExtra("namespArray");
                int iddonhang = getIntent().getIntExtra("iddonhang", 0);
                if (iddonhang == 0) {
                    Toast.makeText(Danhgiadonhang.this, "Invalid Order ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rate == 0 || data.isEmpty()) {
                    Toast.makeText(Danhgiadonhang.this, "Please enter data and rate", Toast.LENGTH_SHORT).show();
                } else {
                    sendDataToServer(namecacsp, iddonhang,rate,data);
                }
            }
        });
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

    private void Anhxa() {
        ratingBar = findViewById(R.id.ratingBar);
        reviewEditText = findViewById(R.id.danhgiasp);
        submitButton = findViewById(R.id.guiphanhoi);
        toolbar = findViewById(R.id.toolbardanhgia);
        setSupportActionBar(toolbar);
    }

    private void sendDataToServer(String[] namecacsp, int iddonhang, float rate,String data) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("namesp",new JSONArray(namecacsp));
            jsonObject.put("rate", rate);
            jsonObject.put("iddonhang", iddonhang);
            jsonObject.put("data", data);


        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = Server.Duongdandanhgiadonhang;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("status").equals("success")) {
                                Toast.makeText(Danhgiadonhang.this, "Gửi dữ liệu thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Danhgiadonhang.this, "Lỗi: " + response.getString("message"), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(Danhgiadonhang.this, "Lỗi: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }

}
