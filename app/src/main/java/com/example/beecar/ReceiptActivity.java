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
import android.widget.TextView;
import android.widget.Toast;

import com.example.beecar.DAO.ClientDAO;
import com.example.beecar.DAO.ReceiptDAO;
import com.example.beecar.DAO.TripDAO;
import com.example.beecar.DAO.VehiclesDAO;
import com.example.beecar.Model.Client;
import com.example.beecar.Model.Receipt;
import com.example.beecar.Model.Trip;
import com.example.beecar.Model.Vehicles;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ReceiptActivity extends AppCompatActivity {
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

    TripDAO tripDAO;
    Receipt receiptData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        toolbar = findViewById(R.id.tool_bar);
        imgXe = findViewById(R.id.img_xe);
        tvNameXe = findViewById(R.id.ten_xe);
        tvPriceDay = findViewById(R.id.tv_price_date);
        tvDayBd = findViewById(R.id.tv_day_bd);
        tvChuyen = findViewById(R.id.tv_luot_thue);
        tvIdXe = findViewById(R.id.tv_id_xe);
        tvBien = findViewById(R.id.bien_so);
        tvDayDk = findViewById(R.id.date_dk);
//        tvStatusXe = findViewById(R.id.status_xe);
        tvFullName = findViewById(R.id.tv_full_name_client);
        tvDayOder = findViewById(R.id.tv_date_oder);
        tvTotal = findViewById(R.id.total);
        tvDayStart = findViewById(R.id.tv_date_nhan);
        tvDayEnd = findViewById(R.id.tv_date_tra);
        tvDonGia = findViewById(R.id.tv_don_gia);

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
        Bitmap bitmap = BitmapFactory.decodeByteArray(vehicles.getImage(),0,vehicles.getImage().length);
        imgXe.setImageBitmap(bitmap);
        tvNameXe.setText(vehicles.getName_car());
        tvPriceDay.setText(vehicles.getPrice_date()+" /VNĐ ngày");
        tvDayBd.setText(vehicles.getDay_bd());
        tvChuyen.setText(vehicles.getCount_muon()+" /chuyến");
        tvIdXe.setText(vehicles.getId()+"");
        tvBien.setText(vehicles.getBien_ks());
        tvDayDk.setText(vehicles.getDay_dk());

        tvFullName.setText(client.getFull_name());
        tvDayOder.setText(obj.getOder_time());
        tvDayStart.setText(obj.getStart_time());
        tvDayEnd.setText(obj.getEnd_time());
        tvDonGia.setText(vehicles.getPrice_date()+" /VNĐ ngày");
        tvTotal.setText(obj.getTotal()+" VNĐ");

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
                addTrip(receiptData,client);
                // chỗ viết code add chuyến đi
                Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }else{
                Toast.makeText(this, "Đặt đơn không thành công", Toast.LENGTH_SHORT).show();
                return;
            }

        });

    }

    private void updateSatusXe(Receipt obj, Vehicles vehicles) {

        vehicles.setVehicles_status(1);
        vehicles.setCount_muon(vehicles.getCount_muon()+1);
        if (vehiclesDAO.update(vehicles)) {
            Log.e("updateVehicles", "ok");

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
        trip.setStatus_trip(0);



        if (tripDAO.insert(trip)){

        }else {
            Log.e("ERROR","error add trip");
            return;
        }



    }

    private String getToday(){
        return  new SimpleDateFormat("hh:mm dd/MM/yyyy", Locale.getDefault()).format(new Date());
    }
    private Date stringToDate(String aDate) {
        if(aDate==null) return null;
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat("hh:mm dd/mm/yyyy");
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