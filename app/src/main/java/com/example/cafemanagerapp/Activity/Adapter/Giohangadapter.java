package com.example.cafemanagerapp.Activity.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafemanagerapp.Activity.Fragment.Gio_hanglist;
import com.example.cafemanagerapp.R;
import com.google.gson.Gson;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Giohangadapter extends RecyclerView.Adapter<Giohangadapter.ViewHolder> {
    private Context context;
    private ArrayList<Gio_hanglist> arrayList;

    public Giohangadapter(Context context, ArrayList<Gio_hanglist> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    public ArrayList<Gio_hanglist> getData() {
        return arrayList;
    }

    public interface OnRemoveItemListener {
        void onRemove(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.dong_sp_giohang, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Gio_hanglist gioHanglist = arrayList.get(position);
        holder.name.setText(gioHanglist.getNamesp());
        holder.mota.setText(gioHanglist.getMotasp());
        holder.giatien.setText(String.valueOf(gioHanglist.getGiatien()));
        holder.soluong.setText(String.valueOf(gioHanglist.getSoluong()));

        String imageUrl = gioHanglist.getHinhsp();
        Log.d("Giohangadapter", "Loading image from URL: " + imageUrl);

        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.get().load(imageUrl)
                    .error(R.drawable.img_30)
                    .into(holder.imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            Log.d("Giohangadapter", "Image loaded successfully from URL: " + imageUrl);
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.e("Giohangadapter", "Error loading image from URL: " + imageUrl, e);
                        }
                    });
        } else {
            Log.e("Giohangadapter", "Image URL is empty or null");
            holder.imageView.setImageResource(R.drawable.img_30);
        }

        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.remove(position);
                notifyDataSetChanged();

                SharedPreferences sharedPreferences = context.getSharedPreferences("CartPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String cartJson = gson.toJson(arrayList);
                editor.putString("cart", cartJson);
                editor.apply();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, giatien, mota, soluong;
        ImageButton xoa;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgviewspgiohang);
            name = itemView.findViewById(R.id.txtviewnamespgiohang);
            giatien = itemView.findViewById(R.id.txtviewgiaspgiohang);
            mota = itemView.findViewById(R.id.motaspgiohang);
            soluong = itemView.findViewById(R.id.soluonggiohang);
            xoa = itemView.findViewById(R.id.xoasanpham);
        }
    }
}
