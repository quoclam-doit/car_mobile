package com.example.beecar.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beecar.DAO.VehiclesDAO;
import com.example.beecar.Model.Vehicles;
import com.example.beecar.R;

import java.util.Calendar;
import java.util.List;

public class VehiclesManagerAdapter extends RecyclerView.Adapter<VehiclesManagerAdapter.viewholder> {
    List<Vehicles> list;
    VehiclesDAO vehiclesDAO;
    Context context;
    EditText ed_date_kt;
    EditText ed_date_bd;

    public VehiclesManagerAdapter(Context context, List<Vehicles> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public VehiclesManagerAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vehicels, null);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehiclesManagerAdapter.viewholder holder, int position) {
        final  Vehicles obj = list.get(position);
        vehiclesDAO = new VehiclesDAO(context); // dòng khởi tạo đã khởi tạo đâu mà dùng
        Bitmap bitmap = BitmapFactory.decodeByteArray(obj.getImage(),0,obj.getImage().length);
        holder.img.setImageBitmap(bitmap);
        holder.tvName.setText(obj.getName_car());
        holder.tvPrice.setText(obj.getPrice_date()+"/ngày");
        holder.tvDayBd.setText(obj.getDay_bd());
        holder.tvCountThue.setText(obj.getCount_muon()+" chuyến");
        holder.tvName.setOnClickListener(view ->{
            showDialogUpdateThongTinXe(obj);
        });
        holder.item.setOnClickListener(view -> {
            showOneVehicles(obj);
        });

    }

    private void showOneVehicles(Vehicles vehicles) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông tin.");
        Vehicles new_gv =  vehiclesDAO.selectOne(vehicles.getId());
        builder.setMessage(new_gv.toString());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class viewholder extends RecyclerView.ViewHolder {
        LinearLayout item;
        ImageView img;
        TextView tvName;
        TextView tvPrice;
        TextView tvDayBd;
        TextView tvCountThue;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            img = itemView.findViewById(R.id.img_vehicles);
            tvName = itemView.findViewById(R.id.tv_name_car);
            tvPrice = itemView.findViewById(R.id.tv_price_date);
            tvDayBd = itemView.findViewById(R.id.tv_date_bd);
            tvCountThue = itemView.findViewById(R.id.tv_lan_thue);
        }
    }
    private void showDialogUpdateThongTinXe(Vehicles vehicles){
        Bitmap bitmap = BitmapFactory.decodeByteArray(vehicles.getImage(),0,vehicles.getImage().length);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater =((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_update_xe, null);
        ImageView img = view.findViewById(R.id.img_car);
        EditText ed_name_car = view.findViewById(R.id.ed_name_car);
        EditText ed_price_date = view.findViewById(R.id.ed_price_date);
        EditText ed_price_time = view.findViewById(R.id.ed_price_time);
        ed_date_kt = view.findViewById(R.id.ed_date_kt);
        ed_date_bd = view.findViewById(R.id.ed_date_bd);
        builder.setView(view);





        img.setImageBitmap(bitmap);
        ed_name_car.setText(vehicles.getName_car());
        ed_price_date.setText(vehicles.getPrice_date()+"");
        ed_price_time.setText(vehicles.getPrice_time()+"");
        ed_date_kt.setText(vehicles.getDay_dk());
        ed_date_bd.setText(vehicles.getDay_bd());

        ed_date_kt.setOnClickListener(view1 -> {
            showDialogPickerDk();
        });

        ed_date_bd.setOnClickListener(view1 -> {
            showDialogPickerBd();
        });


            builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
        builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String name_car = ed_name_car.getText().toString();
                String price_date = ed_price_date.getText().toString().trim();
                String price_time = ed_price_time.getText().toString().trim();
                String date_kt = ed_date_kt.getText().toString();
                String date_bd = ed_date_bd.getText().toString();

                vehicles.setName_car(name_car);
                vehicles.setPrice_date(Integer.parseInt(price_date));
                vehicles.setPrice_time(Integer.parseInt(price_time));
                vehicles.setDay_dk(date_kt);
                vehicles.setDay_bd(date_bd);
                if(vehiclesDAO.update(vehicles)){
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    reloadData();
                }else {
                    Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void showDialogPickerDk() {
        Calendar cal1 = Calendar.getInstance();
        int year = cal1.get(Calendar.YEAR);
        int month = cal1.get(Calendar.MONTH);
        int day = cal1.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog1 = new DatePickerDialog(context, android.R.style.Theme_Holo_Light_Dialog_MinWidth,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1+1;
                String str = i2+"/"+i1+"/"+i;
                ed_date_kt.setText(str);
            }
        },year,month,day);
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog1.show();
    }

    private void showDialogPickerBd() {
        Calendar cal1 = Calendar.getInstance();
        int year = cal1.get(Calendar.YEAR);
        int month = cal1.get(Calendar.MONTH);
        int day = cal1.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog1 = new DatePickerDialog(context, android.R.style.Theme_Holo_Light_Dialog_MinWidth,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1+1;
                String str = i2+"/"+i1+"/"+i;
                ed_date_bd.setText(str);
            }
        },year,month,day);
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog1.show();
    }






    private void reloadData(){
        list.clear();
        list = vehiclesDAO.selectAll();
        notifyDataSetChanged();
    }
}
