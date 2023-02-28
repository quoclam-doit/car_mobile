package com.example.beecar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beecar.DAO.CategoryDAO;
import com.example.beecar.DAO.UserDAO;
import com.example.beecar.DAO.VehiclesDAO;
import com.example.beecar.Database.MyDbHelper;
import com.example.beecar.Model.Category;
import com.example.beecar.Model.User;
import com.example.beecar.Model.Vehicles;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    MyDbHelper myDbHelper;
    UserDAO userDAO;

    TextView tvRegister;
    EditText ed_userName;
    EditText ed_password;
    Button btnLogin;
    LoadingDialog dialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        admin();
        addcategory();
        tvRegister = findViewById(R.id.tv_register);
        dialog  = new LoadingDialog(MainActivity.this);
        tvRegister.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction()== MotionEvent.ACTION_DOWN){
                    tvRegister.setTextColor(Color.BLUE);
                }
                if (motionEvent.getAction()== MotionEvent.ACTION_UP){
                    tvRegister.setTextColor(Color.WHITE);
                }
                return false;
            }
        });
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(view -> {
            loginApp();
        });



        tvRegister.setOnClickListener(view -> {
            Intent i = new Intent(this,PositionActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);

        });



    }

    private void addcategory() {
        CategoryDAO categoryDAO = new CategoryDAO(this);
        if (categoryDAO.selectAll().size()==0){
            Category obj1 = new Category("Xe 4 chỗ");
            categoryDAO.insert(obj1);
            Category obj2 = new Category("Xe 6 chỗ");
            categoryDAO.insert(obj2);
            Category obj3 = new Category("Xe 2 chỗ");
            categoryDAO.insert(obj3);
        }



    }



    private void loginApp() {
        userDAO = new UserDAO(this);
        ed_userName = findViewById(R.id.ed_user_name_lg);
        ed_password = findViewById(R.id.ed_password_lg);
        String str_UserName = ed_userName.getText().toString().trim();
        String str_Password = ed_password.getText().toString().trim();


            for (User obj : userDAO.selectAll()) {
                if (obj.getUser_name().equalsIgnoreCase(str_UserName) && obj.getPassword().equalsIgnoreCase(str_Password)) {
                    if (obj.getPosition() == 0) {
                        Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent i3 = new Intent(this, NavigationQuanLy.class);
                        dialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                i3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i3);

                                dialog.dismiss();
                            }
                        }, 3000);

                        return;
                    }
                    if (obj.getPosition() == 1) {
                        Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent ic = new Intent(this, HomeClient.class);
                        ic.putExtra("obj", obj);
                        dialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                ic.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(ic);
                                dialog.dismiss();
                                finish();

                            }
                        }, 3000);


                        return;
                    }
                    if (obj.getPosition() == 2) {
                        Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent id = new Intent(this, NavigationDrawerForDriver.class);
                        id.putExtra("obj", obj);
                        dialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                id.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(id);
                                dialog.dismiss();
                            }
                        }, 3000);

                        return;
                    }
                }
//                else {
//                    Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }

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
        Toast.makeText(this, "ấn 2 lần để thoát", Toast.LENGTH_SHORT).show();
            if (count == 2) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }

        }
    public void admin(){
        ArrayList<User> users = new ArrayList<>();
        userDAO = new UserDAO(this);
        users.clear();
        for (User u: userDAO.selectAll()){
            if (u.getPosition() ==0) {
                users.add(u);
            }
        }
        if (users.size() == 0) {
            User admin = new User("codedoan", "codedoan", "codedoan.com", 0);
            userDAO.insert(admin);
        }
    }
//        public void addCar(){
//            VehiclesDAO vehiclesDAO = new VehiclesDAO(this);
//            if (vehiclesDAO.selectAll().size()<=0){
//                Vehicles obj1 = new Vehicles(R.drawable.picture1,"Kia Morning",30000,500000,0,"25/11/2022",1);
//                Vehicles obj2 = new Vehicles(R.drawable.picture1,"Hyundai Grand i10",40000,700000,0,"25/11/2022",1);
//                Vehicles obj3 = new Vehicles(R.drawable.picture1,"Vinfast Fadil",40000,700000,0,"25/11/2022",1);
//                Vehicles obj4 = new Vehicles(R.drawable.picture1,"Toyota Wigo",30000,500000,0,"25/11/2022",1);
//                Vehicles obj5 = new Vehicles(R.drawable.picture1,"Honda Brio",30000,500000,0,"25/11/2022",1);
//                Vehicles obj6 = new Vehicles(R.drawable.picture1,"Suzuki Celerio",30000,500000,0,"25/11/2022",6);
//                Vehicles obj7 = new Vehicles(R.drawable.picture1,"Toyota Raize",40000,700000,0,"25/11/2022",1);
//                Vehicles obj8 = new Vehicles(R.drawable.picture1,"Mitsubishi Mirage",30000,500000,0,"25/11/2022",1);
//                Vehicles obj9 = new Vehicles(R.drawable.picture1,"Huyndai Solati",100000,1500000,0,"25/11/2022",1);
//                Vehicles obj10 = new Vehicles(R.drawable.picture1,"Ford Transit Limousine",120000,1800000,0,"25/11/2022",1);
//                Vehicles obj11 = new Vehicles(R.drawable.picture1,"Toyota Hiace",100000,1500000,0,"25/11/2022",1);
//                Vehicles obj12 = new Vehicles(R.drawable.picture1,"Toyota Gravia",100000,1500000,0,"25/11/2022",1);
//                Vehicles obj13 = new Vehicles(R.drawable.picture1,"Mercedes Sprinter",120000,1800000,0,"25/11/2022",1);
//                Vehicles obj14 = new Vehicles(R.drawable.picture1,"Gaz-Gazelle Next Nga",120000,1800000,0,"25/11/2022",1);
//                Vehicles obj15 = new Vehicles(R.drawable.picture1,"Ford Transit Luxury Van",150000,2000000,0,"25/11/2022",1);
//
//                vehiclesDAO.insert(obj1);
//                vehiclesDAO.insert(obj2);
//                vehiclesDAO.insert(obj3);
//                vehiclesDAO.insert(obj4);
//                vehiclesDAO.insert(obj5);
//                vehiclesDAO.insert(obj6);
//                vehiclesDAO.insert(obj7);
//                vehiclesDAO.insert(obj8);
//                vehiclesDAO.insert(obj9);
//                vehiclesDAO.insert(obj10);
//                vehiclesDAO.insert(obj11);
//                vehiclesDAO.insert(obj12);
//                vehiclesDAO.insert(obj13);
//                vehiclesDAO.insert(obj14);
//                vehiclesDAO.insert(obj15);
//
//            }
//        }
    }
