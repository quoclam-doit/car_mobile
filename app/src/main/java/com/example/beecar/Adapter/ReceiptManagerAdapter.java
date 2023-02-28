package com.example.beecar.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beecar.DAO.ReceiptDAO;
import com.example.beecar.DAO.ScheduleDAO;
import com.example.beecar.DAO.TripDAO;
import com.example.beecar.Model.Client;
import com.example.beecar.Model.Receipt;
import com.example.beecar.Model.Schedule;
import com.example.beecar.Model.Trip;
import com.example.beecar.R;

import java.util.List;

public class ReceiptManagerAdapter extends RecyclerView.Adapter<ReceiptManagerAdapter.viewholder> {
    List<Receipt> list;
    Context context;
    ReceiptDAO receiptDAO;
    Trip trip = null;
    TripDAO tripDAO;
    Schedule schedule = null;
    ScheduleDAO scheduleDAO;


    public ReceiptManagerAdapter(List<Receipt> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ReceiptManagerAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_peceipt_manager, null);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptManagerAdapter.viewholder holder, int position) {
        final Receipt receipt = list.get(position);
        receiptDAO = new ReceiptDAO(context);
        scheduleDAO = new ScheduleDAO(context);
        tripDAO = new TripDAO(context);
        holder.tvName.setText(receipt.getName_client());
        holder.tvday.setText(receipt.getOder_time());
        if (receipt.getStatus() == 0) {
            holder.tvStatus.setText("đã tạo đơn");
            holder.tvStatus.setTextColor(Color.GREEN);
        }
        if (receipt.getStatus() == 1) {
            holder.tvStatus.setText("đã hủy");
            holder.tvStatus.setTextColor(Color.RED);
        }
        if (receipt.getStatus() == 2) {
            holder.tvStatus.setText("đã xác nhận");
            holder.tvStatus.setTextColor(Color.BLUE);
        }

        holder.tvTotal.setText(receipt.getTotal() + "");
        holder.btnXacNhan.setOnClickListener(view -> {

            for (Schedule s: scheduleDAO.selectAll()){
                if (receipt.getId() == s.getReceipt_id()){
                    xacNhanReceiptCl(receipt);
                    return;
                }

            }
            xacNhanReceipt(receipt);
            return;


        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvday;
        TextView tvStatus;
        TextView tvTotal;
        TextView btnXacNhan;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name_kh);
            tvday = itemView.findViewById(R.id.tv_day_oder_kh);
            tvday = itemView.findViewById(R.id.tv_day_oder_kh);
            tvTotal = itemView.findViewById(R.id.tv_total);
            tvStatus = itemView.findViewById(R.id.tv_status_kh);
            btnXacNhan = itemView.findViewById(R.id.btn_xac_nhan);

        }
    }

    // don co lai
    private void xacNhanReceiptCl(Receipt obj) {
        Toast.makeText(context, "có lái", Toast.LENGTH_SHORT).show();

        for (Trip t: tripDAO.selectAll()){
            if (t.getReceipt_id() == obj.getId()){
                trip = t;
                break;
            }
        }
        Log.e("id trip",trip.getId()+"");

        for (Schedule s:scheduleDAO.selectAll()){
            if (s.getReceipt_id()== obj.getId()){
                schedule = s;
                break;
            }
        }
        Log.e("id schedule",schedule.getId()+"");




        //dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xác nhận ?");
        builder.setMessage("Bạn có muốn xác nhận đơn hàng của: " + obj.getName_client());
        builder.setPositiveButton("xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                obj.setStatus(2);
                schedule.setStatus_schedule(1);
                trip.setStatus_trip(1);
                if (receiptDAO.update(obj)) {


                    if (scheduleDAO.update(schedule)){

                    }else {
                        Log.e("schedule","error update");
                        return;
                    }


                    if (tripDAO.update(trip)){

                    }else {
                        Log.e("trip","error update");
                        return;
                    }

                    Toast.makeText(context, "xác nhận thành công", Toast.LENGTH_SHORT).show();
                }


            }
        });
        builder.setNegativeButton("hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        // tao dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }



    //don tu lai
    private void xacNhanReceipt(Receipt obj) {
        for (Trip t: tripDAO.selectAll()){
            if (t.getReceipt_id() == obj.getId()){
                trip = t;
                break;
            }
        }
        Log.e("id",trip.getId()+"");

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xác nhận ?");
        builder.setMessage("Bạn có muốn xác nhận đơn hàng của: " + obj.getName_client());
        builder.setPositiveButton("xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                obj.setStatus(2);
                trip.setStatus_trip(1);
                if (receiptDAO.update(obj)) {
                    tripDAO.update(trip);
                    Toast.makeText(context, "xác nhận thành công", Toast.LENGTH_SHORT).show();
                }


            }
        });
        builder.setNegativeButton("hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        // tao dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void addTrip(Receipt receipt,Client client){
        tripDAO = new TripDAO(context);
        Trip trip = new Trip();
        trip.setDia_diem(receipt.getDia_diem());
        trip.setStart_time(receipt.getStart_time());
        trip.setEnd_time(receipt.getEnd_time());
        trip.setClient_id(client.getId());
        trip.setReceipt_id(receipt.getId());
        trip.setStatus_trip(0);



        if (tripDAO.insert(trip)){

        }else {
            Log.e("ERROR","error add trip");
            return;
        }



    }


    private void addSchedule(Receipt receipt,int driver_id) {
        scheduleDAO = new ScheduleDAO(context);
        Schedule schedule = new Schedule();
        schedule.setDia_diem(receipt.getDia_diem());
        schedule.setStart_time(receipt.getStart_time());
        schedule.setEnd_time(receipt.getEnd_time());
        if (receipt.getStart_time().equals(receipt.getOder_time())){
            schedule.setStatus_schedule(1);
        }else {
            schedule.setStatus_schedule(0);
        }
        schedule.setDriver_id(driver_id);
        schedule.setReceipt_id(receipt.getId());
        Log.e("rêcipt",receipt.getId()+"");
        if (scheduleDAO.insert(schedule)){
            Log.e("Add","Add thành công lịch trình");
        }else{
            return;
        }


    }

}
