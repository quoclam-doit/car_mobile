package com.example.beecar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beecar.Fragment.CongViecTaiXeFragment;
import com.example.beecar.Fragment.GioiThieuTaiXeFragment;
import com.example.beecar.Fragment.HoatDongTaiXeFragment;
import com.example.beecar.Fragment.HomeDriverFragment;
import com.example.beecar.Fragment.SupportFragment;
import com.example.beecar.Fragment.ThongKeTaiXeFragment;
import com.example.beecar.Model.User;
import com.example.beecar.MainActivity;
import com.google.android.material.navigation.NavigationView;

public class NavigationDrawerForDriver extends AppCompatActivity {
    DrawerLayout drawerLayoutDriver;
    Toolbar toolbarDriver;
    Intent intent;
    TextView name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer_for_driver);
        toolbarDriver = findViewById(R.id.toolbarDrive);
        setSupportActionBar(toolbarDriver);
        NavigationView navDriver = findViewById(R.id.nav_view_driver);
        drawerLayoutDriver = findViewById(R.id.drawerLayoutForDriver);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayoutDriver, toolbarDriver, R.string.navigationdrawer_driver_open, R.string.navigationdrawer_driver_close);
        drawerLayoutDriver.addDrawerListener(toggle);
        toggle.syncState();

        User user = (User) getIntent().getSerializableExtra("obj");


        HomeDriverFragment homeDriverFragment= new HomeDriverFragment();

        Bundle bunHome = new Bundle();
        bunHome.putSerializable("objx",user);
        homeDriverFragment.setArguments(bunHome);
        ganFragDriver(homeDriverFragment);
        navDriver.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.driverHome:
                        HomeDriverFragment homeDriverFragment= new HomeDriverFragment();
                        Bundle bunHome1 = new Bundle();
                        bunHome1.putSerializable("objx",user);
                        homeDriverFragment.setArguments(bunHome1);
                        ganFragDriver(homeDriverFragment);
                       break;
                    case R.id.driverWork:

                        CongViecTaiXeFragment congViecTaiXeFragment = new CongViecTaiXeFragment();
                        Bundle bunx = new Bundle();
                        bunx.putSerializable("objx",user);
                        congViecTaiXeFragment.setArguments(bunx);
                        ganFragDriver(congViecTaiXeFragment);
                        break;
//                    case R.id.driverActivity:
//                        ganFragDriver(new HoatDongTaiXeFragment());
//                        break;
//                    case R.id.driverThuNhap:
//                        ganFragDriver(new ThongKeTaiXeFragment());
//                        break;
                    case R.id.driverTroGiup:

                        GioiThieuTaiXeFragment fragmentH = new GioiThieuTaiXeFragment();
                        Bundle bun = new Bundle();
                        bun.putSerializable("obj",user);
                        fragmentH.setArguments(bun);
                        ganFragDriver(fragmentH);
                        break;

                    case R.id.driverThoat:
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
        fm.beginTransaction().replace(R.id.framerDrive, fg).commit();
        drawerLayoutDriver.close();
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