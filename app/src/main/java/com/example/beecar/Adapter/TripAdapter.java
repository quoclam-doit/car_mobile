package com.example.beecar.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beecar.Model.Trip;
import com.example.beecar.R;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.viewholder> {
    List<Trip> list ;
    Context context;

    public TripAdapter(List<Trip> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public TripAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_trip,null);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripAdapter.viewholder holder, int position) {
        final Trip trip = list.get(position);
        holder.tvDiaDiem.setText(trip.getDia_diem());
        holder.tvStart.setText(trip.getStart_time());
        holder.tvEnd.setText(trip.getEnd_time());

//        if (trip.getStatus_trip() == 0){
//            holder.tvStatus.setText("sắp tới");
//            holder.tvStatus.setTextColor(Color.GREEN);
//        }
//        if (trip.getStatus_trip() == 1){
//            holder.tvStatus.setText("hôm nay");
//            holder.tvStatus.setTextColor(Color.YELLOW);
//        }
//        if (trip.getStatus_trip() == 2){
//            holder.tvStatus.setText("đã kết thúc");
//            holder.tvStatus.setTextColor(Color.RED);
//        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView tvDiaDiem;
        TextView tvStart;
        TextView tvEnd;
        TextView tvStatus;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            tvDiaDiem = itemView.findViewById(R.id.tv_dia_diem);
            tvStart = itemView.findViewById(R.id.tv_day_nhan);
            tvEnd = itemView.findViewById(R.id.tv_day_tra);
//            tvStatus = itemView.findViewById(R.id.tv_status_trip);
        }
    }

    private String getToday(){
        return  new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
    }

    private Date stringToDate(String aDate) {
        if(aDate==null) return null;
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat("dd/mm/yyyy");
        Date stringDate = simpledateformat.parse(aDate, pos);
        return stringDate;

    }
}
