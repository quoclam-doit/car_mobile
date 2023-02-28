package com.example.beecar.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beecar.DAO.ReceiptDAO;
import com.example.beecar.DAO.ScheduleDAO;
import com.example.beecar.DAO.TripDAO;
import com.example.beecar.Model.Receipt;
import com.example.beecar.Model.Schedule;
import com.example.beecar.Model.Trip;
import com.example.beecar.R;
import com.example.beecar.my_interface.ClickItemVehicles;

import java.util.ArrayList;
import java.util.List;

public class ReceiptClAdapter extends RecyclerView.Adapter<ReceiptClAdapter.viewholder> {
    List<Receipt> list;
    Context context;
    ReceiptDAO receiptDAO;
    Trip trip;
    Schedule schedule;

    public ReceiptClAdapter(List<Receipt> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ReceiptClAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_receipt_cl,null);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptClAdapter.viewholder holder, int position) {
        final  Receipt receipt = list.get(position);
        receiptDAO = new ReceiptDAO(context);
        holder.tvName.setText(receipt.getName_client());
        holder.tvDayOder.setText(receipt.getOder_time());
        if (receipt.getStatus()== 0){
            holder.tvStatus.setText("đã tạo đơn");
            holder.tvStatus.setTextColor(Color.GREEN);
        }
        if (receipt.getStatus()==1){
            holder.tvStatus.setText("đã hủy");
            holder.tvStatus.setTextColor(Color.RED);
        }
        if (receipt.getStatus() == 2) {
            holder.tvStatus.setText("đã xác nhận");
            holder.tvStatus.setTextColor(Color.BLUE);
        }
        holder.tvTotal.setText(receipt.getTotal()+"");

        holder.item.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                ScheduleDAO scheduleDAO = new ScheduleDAO(context);
                TripDAO tripDAO = new TripDAO(context);
                trip = new Trip();


                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Hủy đơn?");
                builder.setMessage("Bạn có muốn hủy đơn: "+ receipt.getId());
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        receipt.setStatus(1);
                        for (Trip t: tripDAO.selectTripOfClient(receipt.getClient_id())){
                            if (t.getReceipt_id()== receipt.getId()){
                                trip = t;
                                break;
                            }
                        }

                        for (Schedule s:scheduleDAO.selectAll()){
                            if (s.getReceipt_id()== receipt.getId()){
                                schedule = s;
                                break;
                            }
                        }

                        if (receiptDAO.update(receipt)){
                            scheduleDAO.delete(schedule.getId());
                            tripDAO.delete(trip.getId());//ok
                            Toast.makeText(context, "hủy thành công", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                            return;
                            // xóa trip or  tài xế

                        }




                    }
                });
                builder.setNegativeButton("khong xoa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                // tao dialog
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        LinearLayout item;
        TextView tvName;
        TextView tvDayOder;
        TextView tvStatus;
        TextView tvTotal;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item_receipt);
            tvName = itemView.findViewById(R.id.tv_name_kh);
            tvDayOder = itemView.findViewById(R.id.tv_day_oder_kh);
            tvStatus = itemView.findViewById(R.id.tv_status_kh);
            tvTotal = itemView.findViewById(R.id.tv_total);
        }
    }
}
