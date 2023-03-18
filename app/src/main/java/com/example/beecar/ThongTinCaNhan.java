package com.example.beecar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.beecar.DAO.UserDAO;
import com.example.beecar.Model.User;

public class ThongTinCaNhan extends AppCompatActivity {
TextView tvtt1, tvtt2, tvtt3,tvtt4;
Toolbar toolbar_acc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan);

        toolbar_acc = findViewById(R.id.toolbar_acc);
        setSupportActionBar(toolbar_acc);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thông tin tài khoản");
        User objU = (User) getIntent().getSerializableExtra("obj");
        tvtt1 = findViewById(R.id.tvtt1);
        tvtt2 = findViewById(R.id.tvtt2);
        tvtt3 = findViewById(R.id.tvtt3);
        tvtt4 = findViewById(R.id.tvtt4);
        tvtt1.setText(objU.getUser_name());
        tvtt2.setText(objU.getFull_name());
        tvtt3.setText(objU.getEmail());
        tvtt4.setText("0"+objU.getPhone());


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

}
