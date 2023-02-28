package com.example.beecar;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beecar.Adapter.ReceiptClAdapter;
import com.example.beecar.DAO.ClientDAO;
import com.example.beecar.DAO.ReceiptDAO;
import com.example.beecar.Model.Client;
import com.example.beecar.Model.Receipt;
import com.example.beecar.Model.User;
import com.example.beecar.Model.Vehicles;
import com.example.beecar.my_interface.ClickItemVehicles;

public class HuyHDon_Activity extends AppCompatActivity {
    Toolbar toolbarCn;
    RecyclerView recyclerView;
    ReceiptDAO receiptDAOCN;
    Receipt objr = null;
    ClientDAO clientDAO;
    ReceiptClAdapter adapter;
    Client objC = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huy_hdon);
        toolbarCn = findViewById(R.id.toolbar_cn2);
        setSupportActionBar(toolbarCn);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Hóa đơn đã hủy");
        User objU = (User) getIntent().getSerializableExtra("obj");
        receiptDAOCN = new ReceiptDAO(this);
        recyclerView = findViewById(R.id.recy_huyhdon);
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
        adapter = new ReceiptClAdapter(receiptDAOCN.getListHuy(objC),this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }
}