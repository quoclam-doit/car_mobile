package com.example.beecar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.beecar.Adapter.ReceiptAdapter;
import com.example.beecar.Adapter.ReceiptClAdapter;
import com.example.beecar.DAO.ClientDAO;
import com.example.beecar.DAO.ReceiptDAO;
import com.example.beecar.Model.Client;
import com.example.beecar.Model.Receipt;
import com.example.beecar.Model.User;
import com.example.beecar.Model.Vehicles;
import com.example.beecar.my_interface.ClickItemVehicles;

public class MyHDon_Activity extends AppCompatActivity {
    Toolbar toolbarCn;
    ReceiptDAO receiptDAOCN;
    Receipt objr = null;
    ClientDAO clientDAO;
    Client objC = null;
    RecyclerView recyclerView;
    ReceiptClAdapter receiptClAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_hdon);
        toolbarCn = findViewById(R.id.toolbar_cn);
        setSupportActionBar(toolbarCn);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Hóa đơn của tôi");
        User objU = (User) getIntent().getSerializableExtra("obj");
        receiptDAOCN = new ReceiptDAO(this);
        recyclerView = findViewById(R.id.recy_hdon);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        clientDAO = new ClientDAO(this);
        receiptDAOCN = new ReceiptDAO(this);
        for (Client c: clientDAO.selectAll()){
            if (c.getUser_id() == objU.getId()){
                objC = c;
                break;
            }
        }
        receiptClAdapter = new ReceiptClAdapter(receiptDAOCN.getList(objC),this);
        recyclerView.setAdapter(receiptClAdapter);


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

}