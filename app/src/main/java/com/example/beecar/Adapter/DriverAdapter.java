package com.example.beecar.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beecar.Model.Driver;
import com.example.beecar.R;

import java.util.ArrayList;
import java.util.List;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.viewholder> {
    List<Driver> list;
    Context context;

    public DriverAdapter(Context context, List<Driver> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public DriverAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_taixe,null);
        return new DriverAdapter.viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverAdapter.viewholder holder, int position) {
        final Driver driver = list.get(position);
        holder.tvName.setText("Họ tên: "+driver.getFull_name());
        holder.tvId.setText("ID: "+driver.getId()+"");
        if (driver.getStatus_driver()== 0){
            holder.tvStatus.setText("Trạng thái: Đang rảnh");
            holder.tvStatus.setTextColor(Color.GREEN);
        }
        if (driver.getStatus_driver()==1){
            holder.tvStatus.setText("Trạng thái: Có công việc");
            holder.tvStatus.setTextColor(Color.RED);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class viewholder extends RecyclerView.ViewHolder {
        LinearLayout item;
        TextView tvName;
        TextView tvId;
        TextView tvStatus;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item_driver);
            tvName = itemView.findViewById(R.id.tv_name_driver);
            tvId = itemView.findViewById(R.id.tv_id_driver);
            tvStatus = itemView.findViewById(R.id.tv_status_driver);
        }
    }
}
