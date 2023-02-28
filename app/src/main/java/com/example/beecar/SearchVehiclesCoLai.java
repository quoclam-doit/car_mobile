package com.example.beecar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.beecar.Adapter.SpinAdapter;
import com.example.beecar.Adapter.VehiclesAdapter;
import com.example.beecar.DAO.ClientDAO;
import com.example.beecar.DAO.DriverDAO;
import com.example.beecar.DAO.VehiclesDAO;
import com.example.beecar.Model.Client;
import com.example.beecar.Model.Driver;
import com.example.beecar.Model.Receipt;
import com.example.beecar.Model.User;
import com.example.beecar.Model.Vehicles;
import com.example.beecar.my_interface.ClickItemVehicles;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SearchVehiclesCoLai extends AppCompatActivity {
    Toolbar toolbar;
    EditText ed_dia_diem;
    TextView ed_date_nhan;
    TextView ed_date_tra;
    Button btn_Search;
    TextView timeNhan;

    DatePickerDialog.OnDateSetListener mDat1;
    DatePickerDialog.OnDateSetListener mDat;
    VehiclesDAO vehiclesDAO;
    VehiclesAdapter adapter;
    RecyclerView recyclerView;
    Driver driver;
    DriverDAO driverDAO;
    List<Vehicles> list = new ArrayList<>();
    ClientDAO clientDAO;
    Client objC = null;

    String dayNhan = "";
    String dayTra ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_vehicles_co_lai);
        timeNhan = findViewById(R.id.tv_hour_nhan_cl);

        toolbar = findViewById(R.id.toolbar_cl);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tìm Xe");
        ed_dia_diem = findViewById(R.id.ed_đia_diem_cl);
        ed_date_nhan = findViewById(R.id.date_picker_nhan_cl);
        ed_date_tra = findViewById(R.id.date_picker_tra_cl);
        btn_Search = findViewById(R.id.btn_tim_xe_cl);
        User objU = (User) getIntent().getSerializableExtra("obj");
        clientDAO = new ClientDAO(this);


        for (Client c: clientDAO.selectAll()){
            if (c.getUser_id() == objU.getId()){
                objC = c;
                break;
            }
        }

        recyclerView = findViewById(R.id.recy_vehicles_cl);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        vehiclesDAO = new VehiclesDAO(this);

        ed_date_nhan.setOnClickListener(view -> {
            showDialogPickerNhan();

        });

        ed_date_tra.setOnClickListener(view -> {
            showDialogPickerTra();

        });
        timeNhan.setOnClickListener(view -> {
            showDialogTime();
        });
        btn_Search.setOnClickListener(view -> {
            list.clear();
            //
            String strDiaDiem = ed_dia_diem.getText().toString().trim();
            if (strDiaDiem.equals("")){
                Toast.makeText(this, "chưa nhập địa điểm", Toast.LENGTH_SHORT).show();
                return;
            }
            String strNhan =  timeNhan.getText()+" "+ed_date_nhan.getText();
            String strTra = ed_date_tra.getText().toString();

            try {
                Date datenhan = stringToDate(strNhan);
                Date datetra = stringToDate(strTra);
                showData(datenhan,datetra,dayNhan,dayTra);
            }catch (Exception e){
                Toast.makeText(this, "Dữ liệu không hợp lệ", Toast.LENGTH_SHORT).show();
            }
            //
            adapter = new VehiclesAdapter(list,this, new ClickItemVehicles() {
                @Override
                public void onClickItemVehicles(Vehicles obj) {
                    clickItem(obj,strNhan,strTra,strDiaDiem);


                }
            });
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();


            ///



        });

    }

    private void showData(Date datenhan,Date datetra,String strNhan,String strTra) {
        list.clear();
        try {

            if (!(datenhan.getTime() <= datetra.getTime())){
                Toast.makeText(this, "Bạn nhập sai lịch phải lớn hơn ngày nhận", Toast.LENGTH_SHORT).show();
                return;
            }
        }catch (Exception e){
            Toast.makeText(this, "Bạn chưa chọn ngày", Toast.LENGTH_SHORT).show();
            return;
        }
        list.addAll(vehiclesDAO.selectAll());
        list.removeAll(vehiclesDAO.selectCarStatus2(strNhan,strTra));
        Log.e("SIZE", list.size()+"");

    }

    private void clickItem(Vehicles obj,String dateNhan,String dateTra,String diadiem) {
        long diff = stringToDate(dateTra).getTime() - stringToDate(dateNhan).getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);
        int total = 0;
        if ((stringToDate(dateNhan)+"").equals(stringToDate(dateTra)+"")){
            total = obj.getPrice_date();
        }else {
            total= (int) (obj.getPrice_date()*diffDays);
        }

        Receipt receipt = new Receipt();
        receipt.setName_client(objC.getFull_name());
        receipt.setStatus(0);
        receipt.setTotal(total);
        receipt.setOder_time(getToday()+"");
        receipt.setStart_time(dateNhan+"");
        receipt.setEnd_time(dateTra+"");

        receipt.setTotal(total);
        receipt.setDia_diem(diadiem);
        receipt.setClient_id(objC.getId());
        receipt.setName_driver("");
        receipt.setStatus_driver(0);
        receipt.setVehicles_id(obj.getId());

        Intent intent = new Intent(this,ReceiptActivityCl.class);
        intent.putExtra("obj",receipt);
        finish();
        startActivity(intent);



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






    private void showDialogPickerNhan() {
        Calendar cal1 = Calendar.getInstance();
        int year = cal1.get(Calendar.YEAR);
        int month = cal1.get(Calendar.MONTH);
        int day = cal1.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog1 = new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1+1;
                String str = i2+"/"+i1+"/"+i;
                dayNhan = str;
                ed_date_nhan.setText(str);
            }
        },year,month,day);
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog1.show();
    }





    private void showDialogTime(){

        int hour = 23;
        int minute = 55;
        boolean is24Hours = true;
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                String str = hour+":"+minute;

                timeNhan.setText(str);

            }
        },hour,minute,is24Hours);
        timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        timePickerDialog.show();
    }


    private void showDialogPickerTra() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1+1;
                String str = i2+"/"+i1+"/"+i;
                dayTra = str;
                ed_date_tra.setText( timeNhan.getText()+" "+str);
            }
        },year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



        dialog.show();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}