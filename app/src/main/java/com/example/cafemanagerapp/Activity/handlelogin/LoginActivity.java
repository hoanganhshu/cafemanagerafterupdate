package com.example.cafemanagerapp.Activity.handlelogin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cafemanagerapp.Activity.Fragment.Trang_chuActivity;
import com.example.cafemanagerapp.R;
import com.example.cafemanagerapp.Activity.util.Server;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private ImageView logo;
    private TextView title, tagline, forgotPassword, register;
    private EditText editemail, editpassword;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Anhxa();
        Handlelogin();

    }

    private void Anhxa() {
        logo = findViewById(R.id.logologin);
        title = findViewById(R.id.titlelogin);
        tagline = findViewById(R.id.taglinelogin);
        editemail = findViewById(R.id.emaillogin);
        editpassword = findViewById(R.id.passwordlogin);
        loginButton = findViewById(R.id.login_button);
        forgotPassword = findViewById(R.id.forgot_passwordlogin);
        register = findViewById(R.id.registerlogin);
    }

    private void Handlelogin() {
        loginButton.setOnClickListener(v -> {
            if (editemail!= null && editpassword != null) {
                String emailInput = editemail.getText().toString();
                String passwordInput = editpassword.getText().toString();



                if (emailInput.isEmpty() || passwordInput.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                handleLogin(emailInput, passwordInput);
            }}

        });

        forgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ForgotpasswordActivity.class);
            startActivity(intent);
        });

        register.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, CreatAccountActivity.class);
            startActivity(intent);
        });
    }

    public void handleLogin(@NonNull String emailInput, String passwordInput) {
        String url = Server.Duongdanlogin;
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("email", emailInput);
            jsonBody.put("password", passwordInput);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Login", "Error creating JSON body", e);
            return;
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Login", "Response received: " + response.toString());
                        try {
                            boolean success = response.getBoolean("success");

                            if (success) {
                                onLoginSuccess();
                            } else {
                                ;
                                onLoginFailed();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            onLoginFailed();
                            Toast.makeText(LoginActivity.this, "Trong catch", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Login", "Error response received", error);
                        onLoginFailed();

                    }
                });

        requestQueue.add(jsonObjectRequest);
    }

    private void onLoginSuccess() {
        Intent intent = new Intent(LoginActivity.this, Trang_chuActivity.class);
        startActivity(intent);
    }

    private void onLoginFailed() {
        Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
    }
}
