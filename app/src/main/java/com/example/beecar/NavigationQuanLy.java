package com.example.beecar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.beecar.Fragment.CongViecTaiXeFragment;

import com.example.beecar.Fragment.GioiThieuTaiXeFragment;
import com.example.beecar.Fragment.HoatDongTaiXeFragment;
import com.example.beecar.Fragment.HomeManagerFragment;
import com.example.beecar.Fragment.QuanLyHoaDonFragment;
import com.example.beecar.Fragment.QuanLyTaiXeFragment;
import com.example.beecar.Fragment.QuanLyXeFragment;
import com.example.beecar.Fragment.ThemXeFragment;
import com.example.beecar.Fragment.ThongKeTaiXeFragment;
import com.google.android.material.navigation.NavigationView;

public class NavigationQuanLy extends AppCompatActivity {
    DrawerLayout drawerLayoutQuanLy;
    Toolbar toolbarquanly;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_quan_ly);
        toolbarquanly = findViewById(R.id.toolbarquanly);
        setSupportActionBar(toolbarquanly);
        NavigationView navDriver = findViewById(R.id.nav_view_quanly);
        drawerLayoutQuanLy = findViewById(R.id.drawerLayoutQuanLy);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayoutQuanLy, toolbarquanly, R.string.navigationquanly_driver_open, R.string.navigationquanly_driver_close);
        drawerLayoutQuanLy.addDrawerListener(toggle);
        toggle.syncState();
        ganFragDriver(new HomeManagerFragment());
        navDriver.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                            ganFragDriver(new HomeManagerFragment());
                        break;
                    case R.id.QLHome:
                        ganFragDriver(new QuanLyHoaDonFragment());
                        break;
                    case R.id.QLTaiXe:
                        ganFragDriver(new QuanLyTaiXeFragment());
                        break;
                    case R.id.QLXe:
                        ganFragDriver(new QuanLyXeFragment());
                        break;
                    case R.id.QLThoat:
                      logOut();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }
    public void ganFragDriver(Fragment fg) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.framerquanly, fg).commit();
        drawerLayoutQuanLy.close();
    }

    public void  logOut(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Đăng xuất");
        builder.setMessage("Bạn có muốn đăng xuất ? ");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                Intent intent1 = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent1);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        // tao dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    int count = 0;
    @Override
    public void onBackPressed() {
        count++;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                count = 0;
            }
        },3000);
        Toast.makeText(this, "vuốt thêm lần nữa để thoát", Toast.LENGTH_SHORT).show();
        if (count == 2) {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }

    }
}

