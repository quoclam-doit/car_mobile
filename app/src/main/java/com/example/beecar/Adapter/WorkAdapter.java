package com.example.beecar.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beecar.DAO.ReceiptDAO;
import com.example.beecar.Model.Receipt;
import com.example.beecar.Model.Schedule;
import com.example.beecar.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.viewholder>{
    List<Schedule> list ;
    Context context;
    ReceiptDAO receiptDAO ;
    Receipt receipt = null;




    public WorkAdapter(List<Schedule> list, Context context) {
        this.list = list;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WorkAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_work,null);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkAdapter.viewholder holder, int position) {
        final Schedule schedule = list.get(position);
        receiptDAO= new ReceiptDAO(context);

        Log.e("size",receiptDAO.selectAllFull().size()+"");
        for (Receipt obj: receiptDAO.selectAllFull() ){
            if(schedule.getReceipt_id() == obj.getId()){
               receipt = obj;
                break;
            }
        }
        Log.e("receipt id",receipt.getId()+"");
        holder.tvDiaDiemDon.setText(schedule.getDia_diem());
        holder.tvStart.setText(schedule.getStart_time());
        holder.tvEnd.setText(schedule.getEnd_time());
        holder.tvNameKH.setText(receipt.getName_client());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfDialog(schedule);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        LinearLayout item;
        TextView tvDiaDiemDon;
        TextView tvStart;
        TextView tvEnd;
        TextView tvNameKH;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.line_item);
            tvDiaDiemDon= itemView.findViewById(R.id.tv_dia_diem_don);
            tvNameKH= itemView.findViewById(R.id.tv_name_khach);
            tvStart= itemView.findViewById(R.id.tv_day_di);
            tvEnd= itemView.findViewById(R.id.tv_day_ketthuc);



        }
    }
    public void showInfDialog(Schedule schedule) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater =((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_show_inf_tai_xe, null);
        EditText ed_name_kh = view.findViewById(R.id.ed_name_clientdr);
        EditText ed_location = view.findViewById(R.id.ed_location_dr);
        EditText ed_date_start = view.findViewById(R.id.ed_date_startdr);
        EditText ed_date_end = view.findViewById(R.id.ed_date_enddr);
        ed_name_kh.setText(receipt.getName_client());
        ed_location.setText(receipt.getDia_diem());
        ed_date_start.setText(receipt.getStart_time());
        ed_date_end.setText(receipt.getEnd_time());
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private String getToday(){
        return  new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
    }
}
