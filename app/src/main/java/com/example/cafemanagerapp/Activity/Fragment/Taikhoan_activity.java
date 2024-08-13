package com.example.cafemanagerapp.Activity.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.cafemanagerapp.Activity.handlelogin.ForgotpasswordActivity;
import com.example.cafemanagerapp.Activity.handlelogin.LoginActivity;
import com.example.cafemanagerapp.R;

public class Taikhoan_activity extends AppCompatActivity {

    public Toolbar doipassToolbar,phanhoiToolbar,logoutToolbar,lienheToolbar,toolbartaikhoan;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taikhoan);
        Anhxa();
        setuptoolbar();

    }

    private void setuptoolbar() {
        setSupportActionBar(toolbartaikhoan);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbartaikhoan.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        setSupportActionBar(doipassToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            doipassToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Taikhoan_activity.this, ForgotpasswordActivity.class);
                    startActivities(new Intent[]{intent});
                }
            });
        }
        setSupportActionBar(phanhoiToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            phanhoiToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Taikhoan_activity.this, phanhoi_activity.class);
                    startActivities(new Intent[]{intent});
                }
            });
        }
        setSupportActionBar(logoutToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            logoutToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Taikhoan_activity.this, LoginActivity.class);
                    startActivities(new Intent[]{intent});
                }
            });
        }
        setSupportActionBar(lienheToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            lienheToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Taikhoan_activity.this, lienhe_activity.class);
                    startActivities(new Intent[]{intent});
                }
            });
        }

    }

    private void Anhxa() {
        phanhoiToolbar = findViewById(R.id.phanhoi);
        lienheToolbar = findViewById(R.id.lienhe);
        doipassToolbar = findViewById(R.id.doipass);
        logoutToolbar = findViewById(R.id.logout);
        toolbartaikhoan=findViewById(R.id.toolbartaikhoan);
    }
}