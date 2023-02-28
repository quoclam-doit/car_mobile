package com.example.beecar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beecar.DAO.ClientDAO;
import com.example.beecar.DAO.UserDAO;
import com.example.beecar.Model.Client;
import com.example.beecar.Model.User;

import java.util.List;
import java.util.regex.Pattern;

public class RegisterClientActivity extends AppCompatActivity {
    Toolbar toolbar ;
    EditText ed_firstName;
    EditText ed_lastName;
    EditText ed_userName;
    EditText ed_password;
    TextView er_fName;
    TextView er_lName;
    TextView er_user_name;
    TextView er_password;
    Button btn_register;
    UserDAO userDAO;
    ClientDAO clientDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client);
        toolbar = findViewById(R.id.tool_bar_register_client);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_orange);



        ed_firstName = findViewById(R.id.ed_first_name_client);
        ed_lastName = findViewById(R.id.ed_last_name_client);
        ed_userName = findViewById(R.id.ed_user_name_register_client);
        ed_password = findViewById(R.id.ed_password_register_client);

        er_fName = findViewById(R.id.error_first_name_client);
        er_lName = findViewById(R.id.error_last_name_client);
        er_user_name = findViewById(R.id.error_user_name_client);
        er_password = findViewById(R.id.error_password_client);

        btn_register = findViewById(R.id.btn_register_client);
        btn_register.setOnClickListener(view -> {
            addUserClient();
        });

    }
    String regexFirstName = "^\\[A-Z]{1}+\\w{4,14}";

    public void addUserClient(){
        //
        userDAO = new UserDAO(this);
        clientDAO = new ClientDAO(this);
        //

         String str_fName = ed_firstName.getText().toString().trim();
         String str_LName = ed_lastName.getText().toString().trim();
         String str_userName = ed_userName.getText().toString().trim();
         String str_password = ed_password.getText().toString().trim();


         // check last name
        if (str_LName.equals("")){
            er_lName.setText("*không để trống");
            return;
        }else {
            er_lName.setText("");
        }
        // check first name
        if (str_fName.equals("")){
            er_fName.setText("*không để trống");
            return;
        }else {
            er_fName.setText("");
        }
         // check user name
        if ((5>=str_userName.length())||(str_userName.length()>=15)){
            er_user_name.setText("độ dài 5-15 kí tự");

            return;
        }else {
            er_user_name.setText("");
        }

        for (User u : userDAO.selectAll()){
            if (str_userName.equalsIgnoreCase(u.getUser_name())){
                er_user_name.setText("tài khoản đã tồn tại");
                return;
            }

        }

        if ((5>=str_password.length())||(str_password.length()>=15)){
            er_password.setText("độ dài 5-15 kí tự");

            return;
        }else {
            er_password.setText("");
        }


        //add user
        User obj = new User();
        obj.setUser_name(str_userName);
        obj.setFull_name(str_LName +" "+str_fName);
        obj.setPassword(str_password);
        obj.setPosition(1);


        if (userDAO.insert(obj)){
            List<User> list = userDAO.selectAll();
            for (User u: list){
                if (u.getUser_name().equals(obj.getUser_name())){
                    //add client
                    Client objC = new Client();
                    objC.setUser_name(u.getUser_name());
                    objC.setPassword(u.getPassword());
                    objC.setFull_name(u.getFull_name());
                    objC.setUser_id(u.getId());
                    if (clientDAO.insert(objC)){
                        Toast.makeText(this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                        Log.e("SIZE", clientDAO.selectAll().size()+"");
                        startActivity(new Intent(this,MainActivity.class));

                    }else {
                        Toast.makeText(this, "no", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        }



    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}