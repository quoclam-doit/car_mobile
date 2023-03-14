package com.example.beecar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beecar.DAO.ClientDAO;
import com.example.beecar.DAO.DriverDAO;
import com.example.beecar.DAO.UserDAO;
import com.example.beecar.Database.MyDbHelper;
import com.example.beecar.Model.Driver;
import com.example.beecar.Model.User;

import java.util.List;

public class RegisterDriverActivity extends AppCompatActivity {
    Toolbar toolbar ;

    EditText lastName , firtName , userNameDriver , passNameDriver, emailDriver, phoneDriver ;
    TextView errorlastName , errorfirtName , erroreruserName , errorPass, errorEmail, errorPhone ;
    Button btnDriver;
    ImageView img_gplx;

    DriverDAO driverDAO;
    UserDAO userDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_driver);
        toolbar = findViewById(R.id.tool_bar_register_driver);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        driverDAO = new DriverDAO(getApplicationContext());


        lastName = findViewById(R.id.ed_last_name);
        firtName = findViewById(R.id.ed_first_name);
        userNameDriver = findViewById(R.id.ed_user_name_register_driver);
        passNameDriver = findViewById(R.id.ed_password_register_driver);
//        emailDriver = findViewById(R.id.ed_email_register_driver);
//        phoneDriver = findViewById(R.id.ed_phone_register_driver);


        errorlastName = findViewById(R.id.error_last_name);
        errorfirtName = findViewById(R.id.error_first_name);
        erroreruserName = findViewById(R.id.error_user_name);
        errorPass = findViewById(R.id.error_password);
//        errorEmail = findViewById(R.id.error_email);
//        errorPhone = findViewById(R.id.error_phone);
        img_gplx = findViewById(R.id.image_gplx);

        btnDriver = findViewById(R.id.btn_register_driver);
        img_gplx.setOnClickListener(view -> {
            startCaputer();
        });
        btnDriver.setOnClickListener(view ->{
                RegisterDiver();
        });
    }


        public  void RegisterDiver (){
                MyDbHelper db = new MyDbHelper(this);

            userDAO = new UserDAO(this);
            driverDAO = new DriverDAO(this);

            String str_userName = userNameDriver.getText().toString().trim();
            String str_passWord = passNameDriver.getText().toString().trim();
            String str_lName = lastName.getText().toString().trim();
            String str_fName = firtName.getText().toString().trim();

            if (str_lName.equals("")){
                errorlastName.setText("Không được để trống trường này");
                return;
            }else {
                errorlastName.setText("");
            }

            if (str_fName.equals("")){
                errorfirtName.setText("Không được để trống trường này");
                return;
            }else {
                errorfirtName.setText("");
            }

            if ((1>=str_userName.length())||(str_userName.length()>=15)){
                erroreruserName.setText("Độ dài 1-15 kí tự");

                return;
            }else {
                erroreruserName.setText("");
            }

            for (User u : userDAO.selectAll()){
                if (str_userName.equalsIgnoreCase(u.getUser_name())){
                    erroreruserName.setText("Tài khoản đã tồn tại");
                    return;
                }

            }

            if ((5>=str_passWord.length())||(str_passWord.length()>=15)){
                errorPass.setText("Độ dài 5-15 kí tự");
                return;
            }else {
                errorPass.setText("");
            }


            User obj = new User();
            obj.setUser_name(str_userName);
            obj.setFull_name(str_lName+" "+str_fName);
            obj.setPassword(str_passWord);
            obj.setPosition(2);

            if (userDAO.insert(obj) ){
                List<User>list = userDAO.selectAll();
                for (User u : list) {
                    if ( u.getUser_name().equals(obj.getUser_name())){
                        Driver objD = new Driver();
                        objD.setUser_name(u.getUser_name());
                        objD.setPassword(u.getPassword());
                        objD.setFull_name(u.getFull_name());
                        objD.setLuongcb(1000);
                        objD.setImage_gplx(db.getBytes(img_gplx));
                        objD.setStatus_driver(0);
                        objD.setUser_id(u.getId());
                        if(driverDAO.insert(objD)){
                            Toast.makeText(this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                            Log.e("SIZE", driverDAO.selectAll().size()+"");
                            startActivity(new Intent(this,MainActivity.class));
                            return;
                        }

                        else {
                            Toast.makeText(this, "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }

        }

    private void startCaputer() {
        Intent capture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(capture,8888);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==8888 && resultCode == RESULT_OK){
            Bitmap phBitmap = (Bitmap) data.getExtras().get("data");
            img_gplx.setImageBitmap(phBitmap);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() ==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}