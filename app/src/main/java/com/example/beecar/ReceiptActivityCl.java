package com.example.beecar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beecar.Adapter.SpinAdapter;
import com.example.beecar.DAO.ClientDAO;
import com.example.beecar.DAO.DriverDAO;
import com.example.beecar.DAO.ReceiptDAO;
import com.example.beecar.DAO.ScheduleDAO;
import com.example.beecar.DAO.TripDAO;
import com.example.beecar.DAO.VehiclesDAO;
import com.example.beecar.Model.Client;
import com.example.beecar.Model.Driver;
import com.example.beecar.Model.Receipt;
import com.example.beecar.Model.Schedule;
import com.example.beecar.Model.Trip;
import com.example.beecar.Model.Vehicles;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ReceiptActivityCl extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imgXe;
    TextView tvNameXe,tvPriceDay,tvDayBd,
            tvChuyen,tvIdXe,tvBien,tvDayDk,tvStatusXe,
            tvFullName,tvDayOder,tvDayStart,tvDayEnd,
            tvDonGia,tvTotal;
    VehiclesDAO vehiclesDAO;
    ClientDAO clientDAO;
    Vehicles vehicles = null;
    ReceiptDAO receiptDAO;
    Client client = null;
    List<Driver> driverList = new ArrayList<>();
    Schedule schedule = null;
    ScheduleDAO scheduleDAO;
    Driver driver;
    DriverDAO driverDAO;
    SpinAdapter spinAdapter;
    Spinner spinner;
    TripDAO tripDAO;
    Receipt receiptData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_cl);
        toolbar = findViewById(R.id.tool_bar_cl);
        imgXe = findViewById(R.id.img_xe_cl);
        tvNameXe = findViewById(R.id.ten_xe_cl);
        tvPriceDay = findViewById(R.id.tv_price_date_cl);
        tvDayBd = findViewById(R.id.tv_day_bd_cl);
        tvChuyen = findViewById(R.id.tv_luot_thue_cl);
        tvIdXe = findViewById(R.id.tv_id_xe_cl);
        tvBien = findViewById(R.id.bien_so_cl);
        tvDayDk = findViewById(R.id.date_dk_cl);
//        tvStatusXe = findViewById(R.id.status_xe_cl);
        tvFullName = findViewById(R.id.tv_full_name_client_cl);
        tvDayOder = findViewById(R.id.tv_date_oder_cl);
        tvTotal = findViewById(R.id.total_cl);
        tvDayStart = findViewById(R.id.tv_date_nhan_cl);
        tvDayEnd = findViewById(R.id.tv_date_tra_cl);
        tvDonGia = findViewById(R.id.tv_don_gia_cl);

        //set toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thông tin xe");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // getobj
        vehiclesDAO = new VehiclesDAO(this);
        clientDAO = new ClientDAO(this);
        Receipt obj = (Receipt) getIntent().getSerializableExtra("obj");
        for (Vehicles v : vehiclesDAO.selectAll()){
            if (obj.getVehicles_id() == v.getId()){
                vehicles = v;
                break;
            }
        }
        for (Client c: clientDAO.selectAll() ){
            if (obj.getClient_id() == c.getId()){
                client = c;
                break;
            }
        }
        ////

        // spi
        spinner = findViewById(R.id.spin);
        driverDAO = new DriverDAO(this);
        driverList.clear();
        driverList.addAll(driverDAO.selectStatus());
//        driverList.removeAll();

        spinAdapter = new SpinAdapter(driverList,this);
        spinner.setAdapter(spinAdapter);


        Bitmap bitmap = BitmapFactory.decodeByteArray(vehicles.getImage(),0,vehicles.getImage().length);
        imgXe.setImageBitmap(bitmap);
        tvNameXe.setText(vehicles.getName_car());
        tvPriceDay.setText(vehicles.getPrice_date()+"");
        tvDayBd.setText(vehicles.getDay_bd());
        tvChuyen.setText(vehicles.getCount_muon()+"");
        tvIdXe.setText(vehicles.getId()+"");
        tvBien.setText(vehicles.getBien_ks());
        tvDayDk.setText(vehicles.getDay_dk());

        tvFullName.setText(client.getFull_name());
        tvDayOder.setText(obj.getOder_time());
        tvDayStart.setText(obj.getStart_time());
        tvDayEnd.setText(obj.getEnd_time());
        tvDonGia.setText(vehicles.getPrice_date()+"/ngày");
        tvTotal.setText(obj.getTotal()+"");

//        if (vehicles.getVehicles_status()==2){
//            tvStatusXe.setText("Chưa có người thuê");
//            tvStatusXe.setTextColor(Color.RED);
//        }else {
//            tvStatusXe.setText("chưa có người thuê");
//            tvStatusXe.setTextColor(Color.GREEN);
//        }

        List<Integer> listId = new ArrayList<>();
        findViewById(R.id.btn_dat_xe).setOnClickListener(view -> {
            receiptDAO = new ReceiptDAO(this);
             driver = (Driver) spinner.getSelectedItem();
            if (receiptDAO.insert(obj)){
                receiptData = new Receipt();
                for (Receipt r : receiptDAO.selectAll()){
                    listId.add(r.getId());

                }

                int idMax = Collections.max(listId);

                for (Receipt objR: receiptDAO.selectAll()){
                    if (objR.getId() == idMax && objR.getClient_id() == client.getId()){
                        receiptData = objR;
                        break;
                    }
                }



                updateSatusXe(receiptData,vehicles);
                addSchedule(receiptData,driver.getId());
                updateStatusDriver(driver, receiptData);
                addTrip(receiptData,client);
                Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                finish();
               return;
                // chỗ viết code add chuyến đi

            }else{
                Toast.makeText(this, "Dat don khong thanh cong", Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }

    private void updateStatusDriver(Driver driver,Receipt obj) {

            driver.setStatus_driver(1);
            if (driverDAO.update(driver)) {
                Log.e("Update","update status driver");

            }




    }


    public void addTrip(Receipt receipt,Client client){
        tripDAO = new TripDAO(this);
        Trip trip = new Trip();
        trip.setDia_diem(receipt.getDia_diem());
        trip.setStart_time(receipt.getStart_time());
        trip.setEnd_time(receipt.getEnd_time());
        trip.setClient_id(client.getId());
        trip.setReceipt_id(receipt.getId());
        if (stringToDate(getToday()).getTime() > stringToDate(receipt.getStart_time()).getTime()){
            trip.setStatus_trip(0);
        }


        if (tripDAO.insert(trip)){

        }else {
            Log.e("ERROR","error add trip");
            return;
        }



    }


    private void addSchedule(Receipt receipt,int driver_id) {
        scheduleDAO = new ScheduleDAO(this);
        Schedule schedule = new Schedule();
        schedule.setDia_diem(receipt.getDia_diem());
        schedule.setStart_time(receipt.getStart_time());
        schedule.setEnd_time(receipt.getEnd_time());
        schedule.setStatus_schedule(0);
        schedule.setDriver_id(driver_id);
        schedule.setReceipt_id(receipt.getId());
        Log.e("rêcipt",receipt.getId()+"");
        if (scheduleDAO.insert(schedule)){
            Log.e("Add","Add thành công lịch trình");


            Toast.makeText(this, "Add"+schedule.getDriver_id()+" "+scheduleDAO.selectOfDriver(driver_id).size(), Toast.LENGTH_SHORT).show();
        }else{
            return;
        }


    }

    private void updateSatusXe(Receipt obj, Vehicles vehicles) {
            vehicles.setVehicles_status(1);
            vehicles.setCount_muon(vehicles.getCount_muon()+1);
            if (vehiclesDAO.update(vehicles)){
                Log.e("Update","update status");

        }
    }



    private String getToday(){
        return  new SimpleDateFormat("hh:mm dd/MM/yyyy", Locale.getDefault()).format(new Date());
    }


    private Date stringToDate(String aDate) {
        if(aDate==null) return null;
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat("hh:mm dd/MM/yyyy");
        Date stringDate = simpledateformat.parse(aDate, pos);
        return stringDate;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}