package com.example.cafemanagerapp.Activity.handlelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class CreatAccountActivity extends AppCompatActivity {
    public ImageView logo;
    public TextView title, tagline,login;
    public EditText editemail, editpassword;
    public Button creatButton;
    public RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_account);
        Anhxa();
        Eventclickbutton();

    }

    private void Eventclickbutton() {
        creatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(CreatAccountActivity.this,LoginActivity.class);
                startActivities(new Intent[]{intent});
            }
        });


    }

    private void Anhxa() {
        logo=findViewById(R.id.logocreat);
        title=findViewById(R.id.titlecreat);
        tagline=findViewById(R.id.taglinecreat);
        editemail=findViewById(R.id.emailcreat);
        login=findViewById(R.id.logincreataccount);
        editpassword=findViewById(R.id.passwordcreat);
        creatButton=findViewById(R.id.buttoncreat);
        requestQueue = Volley.newRequestQueue(this);

    }
    private void registerUser() {
        String email = editemail.getText().toString();
        String password = editpassword.getText().toString();
        String url = Server.Duongdancreataccount;


        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("email", email);
            jsonBody.put("password", password);
        } catch (Exception e) {
            Log.e("Register", "Error creating JSON body", e);
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Register", "Response received: " + response.toString());
                        try {
                            String message = response.getString("message");
                            Toast.makeText(CreatAccountActivity.this, message, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(CreatAccountActivity.this, "Lỗi định dạng JSON!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("Register", "Error response received", error);
                        if (error.networkResponse != null && error.networkResponse.data != null) {
                            String errorResponse = new String(error.networkResponse.data);
                            Log.e("Register", "Error details: " + errorResponse);
                            Toast.makeText(CreatAccountActivity.this, errorResponse, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CreatAccountActivity.this,"Lỗi đăng ký. Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        requestQueue.add(jsonObjectRequest);
    }


}