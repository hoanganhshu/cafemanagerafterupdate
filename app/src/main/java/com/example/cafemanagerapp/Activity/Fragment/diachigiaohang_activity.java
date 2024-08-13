package com.example.cafemanagerapp.Activity.Fragment;

import static android.app.PendingIntent.getActivity;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.cafemanagerapp.Activity.Adapter.Adapterdiachi;
import com.example.cafemanagerapp.Activity.Adapter.Sanphamadapter;
import com.example.cafemanagerapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class diachigiaohang_activity extends AppCompatActivity {
    public ListView listView;
    public Button btnthemdiachi,btnhuy,btnthem;
    public EditText nhapten,nhapdiachi,nhapsdt;
    ArrayList<nhapthongtingiaohang> arrayList;
    Adapterdiachi adapterdiachi;
    Toolbar toolbar;
    public SharedPreferences sharedPreferences;
    public static final String PREFS_NAME = "Diachi";
    public static final String KEY_ADDRESSES = "addresses";
    private static final int Dia_chi_REQUEST_CODE=2;
    String hoten="",diachi="",sdt="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diachigiaohang);
        Anhxa();
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        loadData();
        clickbutton();
        Eventadddiachi();


    }

    private void clickbutton() {
        for(nhapthongtingiaohang thongtin :arrayList){
            if(thongtin.ischeckedradiobutton) {
                hoten = thongtin.getNhapten();
                diachi = thongtin.getNhapdiachi();
                sdt = thongtin.getNhapsdt();
            }
    }}
    private void loadData() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_ADDRESSES, null);
        Type type = new TypeToken<ArrayList<nhapthongtingiaohang>>() {}.getType();
        if (json != null) {
            arrayList = gson.fromJson(json, type);
        } else {
            arrayList = new ArrayList<>();
        }
        adapterdiachi = new Adapterdiachi(this, arrayList);
        listView.setAdapter(adapterdiachi);
    }

    private void saveData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString(KEY_ADDRESSES, json);
        editor.apply();
    }

    private void Eventadddiachi() {
        btnthemdiachi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(diachigiaohang_activity.this);
                LayoutInflater inflater=getLayoutInflater();
                View dialogview=inflater.inflate(R.layout.add_dia_chi_giao_hang,null);
                btnthem=dialogview.findViewById(R.id.them);
                btnhuy=dialogview.findViewById(R.id.huy);
                nhapdiachi=dialogview.findViewById(R.id.nhapdiachi);
                nhapsdt=dialogview.findViewById(R.id.nhapsdt);
                nhapten=dialogview.findViewById(R.id.nhapten);
                builder.setView(dialogview);
                builder.setTitle("Thêm địa chỉ giao hàng");
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                btnthem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      String  hovaten= String.valueOf(nhapten.getText());
                      String  sodienthoai=String.valueOf(nhapsdt.getText());
                      String  diachigiaohang=String.valueOf(nhapdiachi.getText());
                        arrayList.add(new nhapthongtingiaohang(hovaten,sodienthoai,diachigiaohang,false));
                        adapterdiachi.notifyDataSetChanged();
                        saveData();
                        alertDialog.dismiss();

                    }
                });

            }
        });


    }

    private void Anhxa() {

        btnthemdiachi=findViewById(R.id.themdiachi);
        listView=findViewById(R.id.listviewdiachigiaohang);
        arrayList=new ArrayList<>();
        adapterdiachi=new Adapterdiachi(this,arrayList);
        listView.setAdapter(adapterdiachi);
        toolbar=findViewById(R.id.toolbardiachi);

        setSupportActionBar(toolbar);

    }

    public void setSupportActionBar(Toolbar toolbar) {
        super.setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (hoten.isEmpty() || sdt.isEmpty() || diachi.isEmpty()) {
                        Toast.makeText(diachigiaohang_activity.this, "Vui lòng chọn địa chỉ giao hàng", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent=new Intent();
                    intent.putExtra("hoten",hoten);
                    intent.putExtra("sdt",sdt);
                    intent.putExtra("diachi",diachi);
                    setResult(RESULT_OK,intent);
                    finish();

                }
            });
        }
    }


}