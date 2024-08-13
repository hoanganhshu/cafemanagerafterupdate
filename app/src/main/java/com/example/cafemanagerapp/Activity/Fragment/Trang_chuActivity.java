package com.example.cafemanagerapp.Activity.Fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cafemanagerapp.Activity.Adapter.Sanphamadapter;
import com.example.cafemanagerapp.Activity.util.Server;
import com.example.cafemanagerapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Model.Sanpham;

public class Trang_chuActivity extends AppCompatActivity {
    public SearchView searchView;
    public ViewFlipper viewFlipper;
    public Button tabCoffee, tabTea, tabSmoothie;
    public Button filterAll, filterRanking, filterPrice, filterPromo;
    public ListView productList ;
    public ArrayList<Sanpham> arrayList;
    public Sanphamadapter sanphamadapter;
    public View footerview;
    public TextView textView;
    public List<Sanpham> list;
    public Toolbar edittextsearch;
    int page = 1;
    BottomNavigationView bottomNavigationView;

    boolean isloading = false;
    static mhandler mhandler;
    boolean limitdata = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu2);
        Anhxa();
        tabCoffee.post(new Runnable() {
            @Override
            public void run() {
                tabCoffee.performClick();
            }
        });
        filterAll.post(new Runnable() {
            @Override
            public void run() {
                filterAll.performClick();
            }
        });
        filterAll.performClick();



        Loadmoredata();
        Choicewhat();
        setviewflipper();
        Eventclickbutton();





    }

    private void Eventclickbutton() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.trangchu) {


                } else if (item.getItemId() == R.id.lichsu) {
                    Intent intent1 = new Intent(Trang_chuActivity.this, Lichsu_activity.class);
                    startActivity(intent1);
                } else if (item.getItemId() == R.id.taikhoan) {
                    Intent intent2 = new Intent(Trang_chuActivity.this, Taikhoan_activity.class);

                    startActivity(intent2);
                }


                return true;

            }});}

    private void setviewflipper() {

        if (viewFlipper != null) {
            viewFlipper.setAutoStart(true);
            viewFlipper.setFlipInterval(3000);
            ImageView img3 = new ImageView(this);
            img3.setImageResource(R.drawable.img_3);

            ImageView img4 = new ImageView(this);
            img4.setImageResource(R.drawable.img_4);

            ImageView img5 = new ImageView(this);
            img5.setImageResource(R.drawable.img_5);

            ImageView img6 = new ImageView(this);
            img6.setImageResource(R.drawable.img_6);

            viewFlipper.addView(img3);
            viewFlipper.addView(img4);
            viewFlipper.addView(img5);
            viewFlipper.addView(img6);
        } else {
            Log.e("Trang_chuActivity", "viewFlipper là null");
        }
    }

    private void Choicewhat() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = Server.Duongdan;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    Log.d("VolleyResponse", "JSON Array length: " + jsonArray.length());
                    if (jsonArray.length() > 0) {
                        productList.removeFooterView(footerview);
                        arrayList.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int idloaisp = jsonObject.getInt("idloaisp");
                                int idsp = jsonObject.getInt("id");
                                int giacusp = jsonObject.getInt("giacusp");
                                int giasp = jsonObject.getInt("giasanpham");
                                float danhgiasp=jsonObject.getInt("danhgiasp");
                                String namesp = jsonObject.getString("tenloaisanpham");
                                String hinhsp = jsonObject.getString("hinhsanpham");
                                String motasp=jsonObject.getString("motasp");
                                arrayList.add(new Sanpham(namesp, idsp, giasp, giacusp, hinhsp,danhgiasp,motasp,idloaisp));
                                sanphamadapter.notifyDataSetChanged();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        filterProductsByIdLoaiSP(1);
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VolleyError", "Error: " + error.toString());
                Toast.makeText(Trang_chuActivity.this, "Load failed", Toast.LENGTH_SHORT).show();

            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        requestQueue.add(stringRequest);

        tabCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabSmoothie.setPressed(false);
                tabTea.setPressed(false);
                tabCoffee.setTextColor(Color.parseColor("#FFFFFF"));
                tabCoffee.setBackgroundColor(Color.parseColor("#8B4513"));
                tabSmoothie.setTextColor(Color.parseColor("#8B4513"));
                tabSmoothie.setBackgroundColor(Color.parseColor("#FFFFFF"));
                tabTea.setBackgroundColor(Color.parseColor("#FFFFFF"));
                tabTea.setTextColor(Color.parseColor("#8B4513"));
                filterProductsByIdLoaiSP(1);

            }
        });
        tabSmoothie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabCoffee.setPressed(false);
                tabTea.setPressed(false);

                tabCoffee.setTextColor(Color.parseColor("#8B4513"));
                tabCoffee.setBackgroundColor(Color.parseColor("#FFFFFF"));
                tabSmoothie.setTextColor(Color.parseColor("#FFFFFF"));
                tabSmoothie.setBackgroundColor(Color.parseColor("#8B4513"));
                tabTea.setBackgroundColor(Color.parseColor("#FFFFFF"));
                tabTea.setTextColor(Color.parseColor("#8B4513"));
                filterProductsByIdLoaiSP(3);

            }
        });
        tabTea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabCoffee.setPressed(false);
                tabSmoothie.setPressed(false);
                tabCoffee.setTextColor(Color.parseColor("#8B4513"));
                tabCoffee.setBackgroundColor(Color.parseColor("#FFFFFF"));
                tabSmoothie.setTextColor(Color.parseColor("#8B4513"));
                tabSmoothie.setBackgroundColor(Color.parseColor("#FFFFFF"));
                tabTea.setBackgroundColor(Color.parseColor("#8B4513"));
                tabTea.setTextColor(Color.parseColor("#FFFFFF"));
                filterProductsByIdLoaiSP(2);

            }
        });
        filterPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterAll.setPressed(false);
                filterRanking.setTextColor(Color.parseColor("#8B4513"));
                filterRanking.setBackgroundColor(Color.parseColor("#FFFFFF"));
                filterAll.setTextColor(Color.parseColor("#8B4513"));
                filterAll.setBackgroundColor(Color.parseColor("#FFFFFF"));
                filterPrice.setTextColor(Color.parseColor("#8B4513"));
                filterPrice.setBackgroundColor(Color.parseColor("#FFFFFF"));
                filterPromo.setBackgroundColor(Color.parseColor("#8B4513"));
                filterPromo.setTextColor(Color.parseColor("#FFFFFF"));
                ArrayList<Sanpham> filteredList = new ArrayList<>();

                for (Sanpham sp : arrayList) {
                    if (sp.getGiacusp() != 0) {
                        filteredList.add(sp);
                    }
                }

                sanphamadapter.updateList(filteredList);
                sanphamadapter.notifyDataSetChanged();
            }


        });
        filterPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterAll.setPressed(false);
                filterRanking.setPressed(false);
                filterPromo.setPressed(false);
                filterRanking.setTextColor(Color.parseColor("#8B4513"));
                filterRanking.setBackgroundColor(Color.parseColor("#FFFFFF"));
                filterAll.setTextColor(Color.parseColor("#8B4513"));
                filterAll.setBackgroundColor(Color.parseColor("#FFFFFF"));
                filterPrice.setTextColor(Color.parseColor("#FFFFFF"));
                filterPrice.setBackgroundColor(Color.parseColor("#8B4513"));
                filterPromo.setBackgroundColor(Color.parseColor("#FFFFFF"));
                filterPromo.setTextColor(Color.parseColor("#8B4513"));
                List<Sanpham> danhSachSanPham = sanphamadapter.getSanphamArrayList();


                Collections.sort(danhSachSanPham, new Comparator<Sanpham>() {
                    @Override
                    public int compare(Sanpham sp1, Sanpham sp2) {
                        return Integer.compare(sp1.getGiasp(), sp2.getGiasp());
                    }});
                sanphamadapter.notifyDataSetChanged();


            }});
        filterRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterAll.setPressed(false);
                filterPrice.setPressed(false);
                filterPromo.setPressed(false);
                filterRanking.setTextColor(Color.parseColor("#FFFFFF"));
                filterRanking.setBackgroundColor(Color.parseColor("#8B4513"));
                filterAll.setTextColor(Color.parseColor("#8B4513"));
                filterAll.setBackgroundColor(Color.parseColor("#FFFFFF"));
                filterPrice.setTextColor(Color.parseColor("#8B4513"));
                filterPrice.setBackgroundColor(Color.parseColor("#FFFFFF"));
                filterPromo.setBackgroundColor(Color.parseColor("#FFFFFF"));
                filterPromo.setTextColor(Color.parseColor("#8B4513"));
                List<Sanpham> danhSachSanPham = sanphamadapter.getSanphamArrayList();


                Collections.sort(danhSachSanPham, new Comparator<Sanpham>() {
                    @Override
                    public int compare(Sanpham sp1, Sanpham sp2) {
                        return Float.compare(sp1.getDanhgiasp(), sp2.getDanhgiasp());
                    }});
                sanphamadapter.notifyDataSetChanged();


            }
        });
        filterAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterRanking.setPressed(false);
                filterPromo.setPressed(false);
                filterPrice.setPressed(false);
                filterRanking.setTextColor(Color.parseColor("#8B4513"));
                filterRanking.setBackgroundColor(Color.parseColor("#FFFFFF"));
                filterAll.setTextColor(Color.parseColor("#FFFFFF"));
                filterAll.setBackgroundColor(Color.parseColor("#8B4513"));
                filterPrice.setTextColor(Color.parseColor("#8B4513"));
                filterPrice.setBackgroundColor(Color.parseColor("#FFFFFF"));
                filterPromo.setBackgroundColor(Color.parseColor("#FFFFFF"));
                filterPromo.setTextColor(Color.parseColor("#8B4513"));
                arrayList.clear();
                arrayList.addAll(list);

            }
        });
        sanphamadapter.notifyDataSetChanged();



    }
    private void filterProductsByIdLoaiSP(int idLoaiSP) {
        ArrayList<Sanpham> filteredList = new ArrayList<>();
        for (Sanpham sp : arrayList) {
            if (sp.getIdloaisp() == idLoaiSP) {
                filteredList.add(sp);
            }
        }
        sanphamadapter.updateList(filteredList);
    }



    private void Loadmoredata() {
        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Trang_chuActivity.this, Viewsanpham.class);
                intent.putExtra("giasp",arrayList.get(position).getGiasp());
                intent.putExtra("namesp",arrayList.get(position).getNamesp());
                intent.putExtra("hinhsp",arrayList.get(position).getHinhsp());
                intent.putExtra("motasp",arrayList.get(position).getMotasp());
                intent.putExtra("xephang",arrayList.get(position).getDanhgiasp());
                startActivity(intent);
            }
        });
        productList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem+visibleItemCount==totalItemCount&&totalItemCount!=2&&isloading==false&&limitdata==false){
                    isloading=true;
                    mthread thread=new mthread();
                    thread.start();
                }
            }});

    }


    private void Anhxa() {
        viewFlipper = findViewById(R.id.viewflippertrangchu);
        viewFlipper.setFlipInterval(2000);
        tabCoffee = findViewById(R.id.tab_coffee);
        tabTea = findViewById(R.id.tab_tea);
        tabSmoothie = findViewById(R.id.tab_smoothie);
        filterAll = findViewById(R.id.filter_all);
        filterRanking = findViewById(R.id.filter_ranking);
        filterPrice = findViewById(R.id.filter_price);
        filterPromo = findViewById(R.id.filter_promo);
        productList = findViewById(R.id.listviewdongsp);
        edittextsearch=findViewById(R.id.toolbarsearch);
        textView=findViewById(R.id.danhgiadongsp);
        arrayList=new ArrayList<>();
        searchView=findViewById(R.id.search_view1);
        setSupportActionBar(edittextsearch);
        sanphamadapter=new Sanphamadapter(getApplicationContext(),arrayList);
        list=new ArrayList<>(arrayList);
        LayoutInflater layoutInflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview=layoutInflater.inflate(R.layout.progressbar,null);
        mhandler = new mhandler();
        bottomNavigationView=findViewById(R.id.trangchubottom);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Hôm nay bạn muốn uống gì");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterProducts(newText);
                return true;
            }
        });

        return true;}
    private void filterProducts(String query) {
        ArrayList<Sanpham> filteredList = new ArrayList<>();
        for (Sanpham sp : arrayList) {
            if (sp.getNamesp().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(sp);
            }
        }
        sanphamadapter.updateList(filteredList);

    }

    public class mhandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    productList.addFooterView(footerview);
                    break;
                case 1 :
                    page++;
                    Choicewhat();
                    isloading=false;
                    break;

            }
            super.handleMessage(msg);
        }
    }

    public class mthread extends Thread{
        @Override
        public void run() {
            mhandler.sendMessage(mhandler.obtainMessage(0));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    productList.removeFooterView(footerview);
                    productList.setAdapter(sanphamadapter);
                }
            });
            mhandler.sendMessage(mhandler.obtainMessage(1));
            super.run();
        }
    }








}