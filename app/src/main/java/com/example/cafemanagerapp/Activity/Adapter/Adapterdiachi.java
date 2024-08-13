package com.example.cafemanagerapp.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.cafemanagerapp.Activity.Fragment.nhapthongtingiaohang;
import com.example.cafemanagerapp.R;

import java.util.ArrayList;

public class Adapterdiachi extends BaseAdapter {
    Context context;
    ArrayList<nhapthongtingiaohang> arrayList;
     int selectedPosition = -1;

    public Adapterdiachi(Context applicationContext, ArrayList<nhapthongtingiaohang> arrayList) {
        this.context=applicationContext;
        this.arrayList=arrayList;
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
    public static class Viewholder {
        RadioButton button;
        TextView ten,diachi,sdt;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder viewholder= new Viewholder();
        if(convertView == null){
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.dong_dia_chi,null);
            viewholder.button=convertView.findViewById(R.id.clickdiachi);
            viewholder.sdt=convertView.findViewById(R.id.sdt);
            viewholder.ten=convertView.findViewById(R.id.ten);
            viewholder.diachi=convertView.findViewById(R.id.diachi);
            convertView.setTag(viewholder);
        }else {
            viewholder= (Viewholder) convertView.getTag();
        }
        nhapthongtingiaohang thongtingiaohang= (nhapthongtingiaohang) getItem(position);
        viewholder.sdt.setText(thongtingiaohang.getNhapsdt());
        viewholder.diachi.setText(thongtingiaohang.getNhapdiachi());
        viewholder.ten.setText(thongtingiaohang.getNhapten());
        viewholder.button.setChecked(thongtingiaohang.isIscheckedradiobutton());
        viewholder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < arrayList.size(); i++) {
                    arrayList.get(i).setIscheckedradiobutton(i == position);
                }
                notifyDataSetChanged();
            }
        });


        return convertView;
    }
}
