package com.example.cafemanagerapp.Activity.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.example.cafemanagerapp.R;

import java.util.ArrayList;

public class Phuongthucthanhtoan_activity extends AppCompatActivity {
    public Toolbar toolbar;
    public Toolbar toolbarTienMat;
    public Toolbar toolbarTheVisa;
    public Toolbar toolbarChuyenKhoan;
    public Toolbar toolbarZaloPay;
    public RadioGroup radioGroup;
    public RadioButton radioButtonck,radioButtonzalopay,radioButtontienmat,radioButtoncredit;
    public String phuongthuc="";
    public TextView textViewck,textViewcredit,textViewzalopay,textViewtienmat;
    private static final int Phuongthuc_REQUEST_CODE=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phuongthucthanhtoan);
        Anhxa();
        clickphuongthuc();
    }

    private void clickphuongthuc() {
        radioButtonck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phuongthuc="Chuyển khoản ngân hàng";
                radioButtoncredit.setChecked(false);
                radioButtontienmat.setChecked(false);
                radioButtonzalopay.setChecked(false);


            }
        });
        radioButtoncredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phuongthuc="Credit or debit card";
                radioButtonzalopay.setChecked(false);
                radioButtontienmat.setChecked(false);
                radioButtonck.setChecked(false);

            }
        });
        radioButtontienmat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phuongthuc="Thanh toán tiền mặt";
                radioButtoncredit.setChecked(false);
                radioButtonzalopay.setChecked(false);
                radioButtonck.setChecked(false);

            }
        });
        radioButtonzalopay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phuongthuc="ZaloPay";
                radioButtoncredit.setChecked(false);
                radioButtontienmat.setChecked(false);
                radioButtonck.setChecked(false);

            }
        });
    }

    private void Anhxa() {

        toolbarTienMat = findViewById(R.id.thanhtoantienmat);
        toolbarTheVisa = findViewById(R.id.thevisa);
        toolbarChuyenKhoan = findViewById(R.id.chuyenkhoannganhang);
        toolbarZaloPay = findViewById(R.id.thanhtoanzalopay);
        textViewck=findViewById(R.id.chuyenkhoan);
        textViewcredit=findViewById(R.id.credit);
        textViewtienmat=findViewById(R.id.tienmat);
        radioButtonzalopay=findViewById(R.id.buttonzalopay);
        radioButtontienmat=findViewById(R.id.buttontienmat);
        radioButtoncredit=findViewById(R.id.buttoncredit);
        radioButtonck=findViewById(R.id.buttontchuyenkhoan);
        textViewzalopay=findViewById(R.id.zalopay);
       toolbar=findViewById(R.id.toolbarphuongthucthanhtoan);
       setSupportActionBar(toolbar);
    }

    public void setSupportActionBar(Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent();
                    intent.putExtra("phuongthuc",phuongthuc);
                    setResult(RESULT_OK,intent);
                    finish();

                }
            });
        }

}}