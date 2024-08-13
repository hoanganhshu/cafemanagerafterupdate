package com.example.cafemanagerapp.Activity.handlelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONObject;

public class ForgotpasswordActivity extends AppCompatActivity {
    public  EditText editText;
    public Button button;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        Anhxa();
        Eventclickbutton();



    }

    private void Eventclickbutton() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleForgotPassword();
            }
        });
    }

    private void Anhxa() {
        editText=findViewById(R.id.emailforgotpass);
        button=findViewById(R.id.buttonforgotpass);
        toolbar=findViewById(R.id.toolbarquenmatkhau);
        setSupportActionBar(toolbar);

    }

    private void handleForgotPassword() {
        String email = editText.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập email.", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = Server.Duongdanforgotpass;

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("email", email);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ForgotPassword", "Error creating JSON body", e);
            return;
        }

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
        (Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("ForgotPassword", "Response received: " + response.toString());
                try {
                    String message = response.getString("message");
                    Toast.makeText(ForgotpasswordActivity.this, message, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ForgotPassword", "Error response received", error);
                Toast.makeText(ForgotpasswordActivity.this, "Không thể gửi yêu cầu. Vui lòng thử lại.", Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
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



