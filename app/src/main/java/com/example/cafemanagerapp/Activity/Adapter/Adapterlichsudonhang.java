package com.example.cafemanagerapp.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.cafemanagerapp.Activity.Fragment.Lichsusanpham;
import com.example.cafemanagerapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapterlichsudonhang extends BaseAdapter {
    ArrayList<Lichsusanpham> arrayList;
    Context context;

    public Adapterlichsudonhang(ArrayList<Lichsusanpham> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class Viewholder{
        ImageView imageView;
        TextView magiaodich,namecacsp,giahoadon,texttoolbar;
        Toolbar toolbar;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder viewholder=new Viewholder();
        if(convertView==null){
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.dong_donhanglichsu,null);
            viewholder.imageView=convertView.findViewById(R.id.imgviewsplichsu);
            viewholder.magiaodich=convertView.findViewById(R.id.magiaodichlichsu);
            viewholder.namecacsp=convertView.findViewById(R.id.namecacsplichsu);
            viewholder.giahoadon=convertView.findViewById(R.id.giahoadonlichsu);
            viewholder.texttoolbar=convertView.findViewById(R.id.thongbaodonglichsu);
            convertView.setTag(viewholder);
        }else {
            viewholder= (Viewholder) convertView.getTag();

        }
        Lichsusanpham lichsusanpham= (Lichsusanpham) getItem(position);
        viewholder.namecacsp.setText(lichsusanpham.getProductName());
        viewholder.magiaodich.setText(String.valueOf(lichsusanpham.getMagiaodich()));
        Picasso.get().load(lichsusanpham.getHinhhoadon()).error(R.drawable.img_30).into(viewholder.imageView);
        viewholder.giahoadon.setText(String.valueOf(lichsusanpham.getGiahoadon()));
        viewholder.texttoolbar.setText(lichsusanpham.getThongbaodonhang());

        return convertView;
    }
}
