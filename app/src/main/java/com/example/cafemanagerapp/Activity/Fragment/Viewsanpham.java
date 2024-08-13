package com.example.cafemanagerapp.Activity.Fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.cafemanagerapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

public class Viewsanpham extends AppCompatActivity {
    public ImageView imageViewCoffee;
    public TextView textViewDescription,textViewTitle;
    public TextView textViewPrice;
    public TextView textViewQuantity;
    public TextView textViewTotalPrice,tongtien;
    public ImageButton buttonMinus;
    public ImageButton buttonPlus;
    public Button buttonAddToCart;

    public Button btnDa, btnNong;
    public Button btnNho, btnVua, btnLon;
    public Button btnBinhThuong, btnGiamBot;
    public CheckBox checkBoxBlackPearl, checkBoxWhitePearl, checkBoxCoconut, checkBoxJelly;
    public TextView textViewNotes;
    public Toolbar toolbar;
    int soluong=0;
    boolean ischeckedblackpearl=false;
    boolean ischeckedwhitepearl=false;
    boolean ischeckedcocount=false;
    boolean ischeckboxjelly=false;

    String namesp="",hinhsp="",note="";
    int giatien=0;
    int giasp=0;
    String douong,kichthuoc,duong;
    TextView xephang;
    StringBuilder noteBuilder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewsanpham);

      Anhxa();
        douong = "Đồ uống đá ,";
        kichthuoc = "Cỡ nhỏ ,";
        duong = "Đường bình thường ";
        btnDa.post(new Runnable() {
            @Override
            public void run() {
                btnDa.performClick();
            }
        });

        btnNho.post(new Runnable() {
            @Override
            public void run() {
                btnNho.performClick();
            }
        });

        btnBinhThuong.post(new Runnable() {
            @Override
            public void run() {
                btnBinhThuong.performClick();
            }
        });
      updatesoluong();
      loadsp();
      clickbutton();
      addNoteDetailsToBuilder();


    }

    private void clickbutton() {
        btnLon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kichthuoc="Cỡ lớn";
                btnNho.setSelected(false);
                btnVua.setSelected(false);
                btnNho.setTextColor(Color.parseColor("#8B4513"));
                btnNho.setBackgroundColor(Color.parseColor("#FFFFFF"));
                btnLon.setBackgroundColor(Color.parseColor("#8B4513"));
                btnLon.setTextColor(Color.parseColor("#FFFFFF"));
                btnVua.setTextColor(Color.parseColor("#8B4513"));
                btnVua.setBackgroundColor(Color.parseColor("#FFFFFF"));
                addNoteDetailsToBuilder();

            }
        });
        btnDa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                douong="Đồ uống đá ,";
                btnNong.setSelected(false);
                btnNong.setTextColor(Color.parseColor("#8B4513"));
                btnNong.setBackgroundColor(Color.parseColor("#FFFFFF"));
                btnDa.setBackgroundColor(Color.parseColor("#8B4513"));
                btnDa.setTextColor(Color.parseColor("#FFFFFF"));
                addNoteDetailsToBuilder();
            }
        });
        btnVua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kichthuoc="Cỡ vừa ,";
                btnNho.setSelected(false);
                btnLon.setSelected(false);
                btnNho.setTextColor(Color.parseColor("#8B4513"));
                btnNho.setBackgroundColor(Color.parseColor("#FFFFFF"));
                btnLon.setBackgroundColor(Color.parseColor("#FFFFFF"));
                btnLon.setTextColor(Color.parseColor("#8B4513"));
                btnVua.setTextColor(Color.parseColor("#FFFFFF"));
                btnVua.setBackgroundColor(Color.parseColor("#8B4513"));
                addNoteDetailsToBuilder();


            }
        });
        btnNho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kichthuoc="Cỡ nhỏ ,";
                btnLon.setSelected(false);
                btnVua.setSelected(false);
                btnNho.setTextColor(Color.parseColor("#FFFFFF"));
                btnNho.setBackgroundColor(Color.parseColor("#8B4513"));
                btnLon.setBackgroundColor(Color.parseColor("#FFFFFF"));
                btnLon.setTextColor(Color.parseColor("#8B4513"));
                btnVua.setTextColor(Color.parseColor("#8B4513"));
                btnVua.setBackgroundColor(Color.parseColor("#FFFFFF"));
                addNoteDetailsToBuilder();

            }
        });
        btnGiamBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                duong="Giảm bớt đường ";
                btnBinhThuong.setSelected(false);
                btnBinhThuong.setTextColor(Color.parseColor("#8B4513"));
                btnBinhThuong.setBackgroundColor(Color.parseColor("#FFFFFF"));
                btnGiamBot.setBackgroundColor(Color.parseColor("#8B4513"));
                btnGiamBot.setTextColor(Color.parseColor("#FFFFFF"));
                addNoteDetailsToBuilder();

            }
        });
        btnBinhThuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnGiamBot.setSelected(false);
                btnBinhThuong.setTextColor(Color.parseColor("#FFFFFF"));
                btnBinhThuong.setBackgroundColor(Color.parseColor("#8B4513"));
                btnGiamBot.setBackgroundColor(Color.parseColor("#FFFFFF"));
                btnGiamBot.setTextColor(Color.parseColor("#8B4513"));
                addNoteDetailsToBuilder();



            }
        });
        btnNong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                douong="Đồ uống nóng ,";
                btnDa.setSelected(false);
                btnNong.setTextColor(Color.parseColor("#FFFFFF"));
                btnNong.setBackgroundColor(Color.parseColor("#8B4513"));
                btnDa.setBackgroundColor(Color.parseColor("#FFFFFF"));
                btnDa.setTextColor(Color.parseColor("#8B4513"));
                addNoteDetailsToBuilder();

            }
        });
    }



    private void loadsp() {

        giasp=getIntent().getIntExtra("giasp",0)  ;
         namesp=getIntent().getStringExtra("namesp")  ;
         hinhsp=getIntent().getStringExtra("hinhsp");
         note= (String) textViewNotes.getText();
        Picasso.get().load(hinhsp).into(imageViewCoffee);
        textViewTitle.setText(namesp);
        toolbar.setTitle(namesp);
        xephang.setText(String.valueOf(getIntent().getFloatExtra("xephang",0)));
         soluong= Integer.parseInt(textViewQuantity.getText().toString());
        giatien=soluong*giasp;
        textViewPrice.setText(String.valueOf(giasp));
        tongtien.setText(String.valueOf(giatien));
        buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Viewsanpham.this, Gio_hang_sp.class);
                Bundle bundle = new Bundle();
                bundle.putString("namesp", namesp);
                bundle.putString("hinhsp", hinhsp);
                bundle.putInt("giasp", giasp);
                bundle.putInt("soluong", soluong);
                bundle.putString("notecontent",note);
                bundle.putString("chitiet",noteBuilder.toString());
                intent.putExtras(bundle);
                startActivity(intent);

            }});}


      private void updatesoluong() {
           soluong = Integer.parseInt(textViewQuantity.getText().toString());

          if (soluong <= 1) {
              buttonMinus.setVisibility(View.INVISIBLE);
              buttonPlus.setVisibility(View.VISIBLE);
          } else if (soluong >= 9) {
              buttonMinus.setVisibility(View.VISIBLE);
              buttonPlus.setVisibility(View.INVISIBLE);

          } else {
              buttonMinus.setVisibility(View.VISIBLE);
              buttonPlus.setVisibility(View.VISIBLE);
          }
          buttonPlus.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  int soluong = Integer.parseInt(textViewQuantity.getText().toString());
                  soluong++;
                  giatien=soluong*giasp;
                  textViewQuantity.setText(String.valueOf(soluong));
                  tongtien.setText(String.valueOf(giatien));
                  updatesoluong();
              }
          });
          buttonMinus.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  int soluong = Integer.parseInt(textViewQuantity.getText().toString());
                  soluong--;
                  giatien=soluong*giasp;
                  textViewQuantity.setText(String.valueOf(soluong));
                  tongtien.setText(String.valueOf(giatien));
                  updatesoluong();
              }
          });


      }





    private void Anhxa()      {
        imageViewCoffee = findViewById(R.id.imageViewCoffeeviewsp);
        textViewTitle = findViewById(R.id.textViewTitleviewsp);
        textViewDescription = findViewById(R.id.textViewDescriptionviewsp);
        textViewPrice = findViewById(R.id.giaspviewsp);
        textViewQuantity = findViewById(R.id.textViewQuantityviewsp);
        textViewTotalPrice = findViewById(R.id.textViewTotalPriceviewsp);
        buttonMinus = findViewById(R.id.buttonMinusviewsp);
        buttonPlus = findViewById(R.id.buttonPlusviewsp);
        buttonAddToCart = findViewById(R.id.buttonAddToCartviewsp);
        btnDa = findViewById(R.id.douongdaviewsp);
        btnNong = findViewById(R.id.douongnongviewsp);
        btnNho = findViewById(R.id.kichthuocnhoviewsp);
        btnVua= findViewById(R.id.kichthuocvuaviewsp);
        btnLon = findViewById(R.id.btnkichthuoclonviewsp);
        btnBinhThuong = findViewById(R.id.btnduongbinhthuongviewsp);
        btnGiamBot = findViewById(R.id.btnduonggiambotviewsp);
        checkBoxBlackPearl = findViewById(R.id.checkBoxBlackPearl);
        checkBoxWhitePearl = findViewById(R.id.checkBoxWhitePearl);
        checkBoxCoconut = findViewById(R.id.checkBoxCoconut);
        checkBoxJelly = findViewById(R.id.checkBoxJelly);
        textViewNotes = findViewById(R.id.textviewTextNotes);
        toolbar=findViewById(R.id.toolbarviewsanpham);
        tongtien=findViewById(R.id.tongtienviewsp);
        xephang=findViewById(R.id.xephang);
        setSupportActionBar(toolbar);
        ischeckedblackpearl=checkBoxBlackPearl.isChecked();
        ischeckedwhitepearl=checkBoxWhitePearl.isChecked();
        ischeckedcocount=checkBoxCoconut.isChecked();
        ischeckboxjelly=checkBoxJelly.isChecked();

      }


        public void setSupportActionBar(Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setTitle(textViewTitle.getText());
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Viewsanpham.this, Trang_chuActivity.class);
                    startActivity(intent);
                }
            });

        }
    }
    private void addNoteDetailsToBuilder() {
         noteBuilder=new StringBuilder();
        noteBuilder.append(douong).append(kichthuoc).append(duong);

        if (ischeckedblackpearl) {
            noteBuilder.append(" trân châu đen");
            giatien=giatien+5000;
        }
        if (ischeckedwhitepearl) {
            noteBuilder.append(" trân châu trắng");
            giatien=giatien+6000;
        }
        if (ischeckedcocount) {
            noteBuilder.append("dừa khô");
            giatien=giatien+3000;
        }
        if (ischeckboxjelly) {
            noteBuilder.append("thạch bảy màu");
            giatien=giatien+7000;
        }

        }
}


