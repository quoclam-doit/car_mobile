package com.example.beecar.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beecar.DAO.ReceiptDAO;
import com.example.beecar.Model.Receipt;
import com.example.beecar.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.viewholder> {
    List<Receipt> list;
    Context context;

    public ReceiptAdapter(Context context, List<Receipt> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ReceiptAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_receipt, null);
        return new ReceiptAdapter.viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final Receipt receipt = list.get(position);
        holder.tvMaKH.setText("ID Client: "+ receipt.getClient_id());
        holder.tvDiaDiem.setText("Địa điểm: " +  receipt.getDia_diem());
        holder.tvNhanHieu.setText("Nhãn hiệu xe: " + receipt.getName());
        holder.tvBienKS.setText("Biển kiểm soát: " + receipt.getBien_ks());
        holder.tvStart.setText("Ngày bắt đầu: " + receipt.getStart_time());
        holder.tvEnd.setText("Ngày kết thúc: " + receipt.getEnd_time());
        if (receipt.getStatus()== 0){
            holder.tvStatus.setText("Trạng thái: Đã tạo đơn");
            holder.tvStatus.setTextColor(Color.GREEN);
        }
        if (receipt.getStatus()==1){
            holder.tvStatus.setText("Trạng thái: Đã hủy");
            holder.tvStatus.setTextColor(Color.RED);
        }
        holder.tvTotal.setText("Tổng cộng: " + receipt.getTotal()+"");
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    public class viewholder extends RecyclerView.ViewHolder {
        LinearLayout item;
        TextView tvMaKH;
        TextView tvDiaDiem;
        TextView tvNhanHieu;
        TextView tvBienKS;
        TextView tvStart;
        TextView tvEnd;
        TextView tvStatus;
        TextView tvTotal;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item_receipt);
            tvMaKH = itemView.findViewById(R.id.tv_id_client_rec);
            tvDiaDiem = itemView.findViewById(R.id.tv_dia_diem_rec);
            tvNhanHieu = itemView.findViewById(R.id.tv_nhan_hieu_rec);
            tvBienKS = itemView.findViewById(R.id.tv_bien_ks_rec);
            tvStart = itemView.findViewById(R.id.tv_start_rec);
            tvEnd = itemView.findViewById(R.id.tv_end_rec);
            tvStatus = itemView.findViewById(R.id.tv_status_rec);
            tvTotal = itemView.findViewById(R.id.tv_total_rec);
        }
    }
}
