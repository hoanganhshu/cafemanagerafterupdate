package com.example.cafemanagerapp.Activity.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.example.cafemanagerapp.R;

public class khuyenmai_activity extends AppCompatActivity {
    public RadioButton khuyenmai1;
    public RadioButton khuyenmai2;
    public RadioButton khuyenmai3;
    public Toolbar toolbar;
    public RadioGroup radioGroup;
    public TextView textView1, textView2, textView3;
    public String khuyenmai = "";
    public static final int REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khuyenmai);
        Anhxa();
        clickkhuyenmai();

    }


    private void clickkhuyenmai() {
        khuyenmai3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khuyenmai = "Giảm giá 40%";
                khuyenmai1.setChecked(false);
                khuyenmai2.setChecked(false);
                Log.d("khuyenmai_activity", "Selected khuyenmai: " + khuyenmai);
            }
        });
        khuyenmai2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khuyenmai1.setChecked(false);
                khuyenmai3.setChecked(false);
                khuyenmai = "Giảm giá 30%";
                Log.d("khuyenmai_activity", "Selected khuyenmai: " + khuyenmai);
            }
        });
        khuyenmai1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khuyenmai = "Giảm giá 15%";
                khuyenmai2.setChecked(false);
                khuyenmai3.setChecked(false);
                Log.d("khuyenmai_activity", "Selected khuyenmai: " + khuyenmai);
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
                    Intent intent = new Intent();
                    intent.putExtra("khuyenmai", khuyenmai);
                    Log.d("khuyenmai_activity", "Sending khuyenmai: " + khuyenmai);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }
    }

    private void Anhxa() {
        khuyenmai1 = findViewById(R.id.radioButtonkhuyenmai1);
        khuyenmai2 = findViewById(R.id.radioButtonkhuyenmai2);
        radioGroup = findViewById(R.id.radioGroupKhuyenMai);
        khuyenmai3 = findViewById(R.id.radioButtonkhuyenmai3);
        textView1 = findViewById(R.id.title1);
        textView2 = findViewById(R.id.tiltle2);
        textView3 = findViewById(R.id.tiltle3);
        toolbar = findViewById(R.id.toolbarkhuyenmai);
        setSupportActionBar(toolbar);

    }

}