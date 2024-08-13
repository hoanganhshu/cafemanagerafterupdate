package com.example.cafemanagerapp.Activity.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import Model.Sanpham;
import com.example.cafemanagerapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Sanphamadapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> sanphamArrayList;

    public Sanphamadapter(Context context, ArrayList<Sanpham> sanphamArrayList) {
        this.context = context;
        this.sanphamArrayList = sanphamArrayList;
    }
    public void updateList(ArrayList<Sanpham> newList) {
        sanphamArrayList.clear();
        this.sanphamArrayList = newList;
        notifyDataSetChanged();
    }


    public List<Sanpham> getSanphamArrayList() {
        return sanphamArrayList;
    }



    @Override
    public int getCount() {
        return sanphamArrayList.size();
    }


    @Override
    public Object getItem(int position) {
        return sanphamArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class Viewholder {
        CircleImageView imageViewsp;
        TextView namesp, giasp, giacusp,idsp,idloaisp,danhgiasp,motasp;


    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder viewholder = new Viewholder();
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.dong_sp_trangchu, null);
            viewholder.imageViewsp = convertView.findViewById(R.id.imagedongsanpham);
            viewholder.namesp = convertView.findViewById(R.id.namspdongsp);
            viewholder.giacusp = convertView.findViewById(R.id.giadongsp);
            viewholder.giasp = convertView.findViewById(R.id.gianewdongsp);
            viewholder.motasp = convertView.findViewById(R.id.motaspdongsp);
            viewholder.danhgiasp = convertView.findViewById(R.id.danhgiadongsp);
            convertView.setTag(viewholder);
        } else {
            viewholder = (Viewholder) convertView.getTag();
        }

        Sanpham sanpham = (Sanpham) getItem(position);
        viewholder.namesp.setText(sanpham.getNamesp());
        viewholder.giasp.setText(String.valueOf(sanpham.getGiasp()));
        viewholder.danhgiasp.setText(String.valueOf(sanpham.getDanhgiasp()));

        viewholder.giacusp.setVisibility(View.VISIBLE);
        viewholder.giacusp.setText(String.valueOf(sanpham.getGiasp()));
        viewholder.giacusp.setPaintFlags(viewholder.giacusp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        viewholder.motasp.setText(sanpham.getMotasp());
        Picasso.get().load(sanpham.getHinhsp()).resize(60, 60).into(viewholder.imageViewsp);

        return convertView;
    }



}
