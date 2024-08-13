package com.example.cafemanagerapp.Activity.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cafemanagerapp.Activity.handlelogin.ForgotpasswordActivity;
import com.example.cafemanagerapp.Activity.handlelogin.LoginActivity;
import com.example.cafemanagerapp.R;

public class MainActivity extends AppCompatActivity {
    TextView textView1,textView2;
    ImageView imageView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.logomain);
        textView1=findViewById(R.id.title);
        textView2=findViewById(R.id.tagline);

        // check connection
        Intent intent=new Intent(this, LoginActivity.class);
        startActivities(new Intent[]{intent});

    }
}