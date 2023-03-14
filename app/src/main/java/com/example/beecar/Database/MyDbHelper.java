package com.example.beecar.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public class MyDbHelper extends SQLiteOpenHelper {
    public static final String Name_db = "beeCar.db";
    public static final int Version_db =2 ;


    public MyDbHelper(@Nullable Context context) {
        super(context, Name_db, null, Version_db);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //car
        String tb_category = "create table if not exists tb_category (id integer primary key autoincrement,name_category string)";
        sqLiteDatabase.execSQL(tb_category);
        String tb_vehicles = "create table if not exists tb_vehicles (id integer primary key autoincrement,image_car blog,name_car string,bien_ks string,count_muon int,price_time int ,price_date int,day_bd date,day_dk date,vehicles_status int,id_category references tb_category(id)) ";
        sqLiteDatabase.execSQL(tb_vehicles);

        // user
        String tb_user = "create table if not exists  tb_user (id integer primary key autoincrement, user_name string,password string, email string, phone string, full_name string,position int)";
        sqLiteDatabase.execSQL(tb_user);
        String tb_driver = "create table if not exists  tb_driver (id integer primary key autoincrement, user_name string,password string,full_name string,image_gplx blog,luongcb int,status_driver int , user_id references tb_user(id))";
        sqLiteDatabase.execSQL(tb_driver);
        String tb_client = "create table if not exists  tb_client (id integer primary key autoincrement, user_name string,password string,full_name string, user_id references tb_user(id))";
        sqLiteDatabase.execSQL(tb_client);
            //
        String tb_schedule = "create table if not exists  tb_schedule (id integer primary key autoincrement,dia_diem string,status_schedule string, start_time date, end_time date," +
                " driver_id references tb_driver(id)," +
                " receipt_id references tb_receipt(id))";
        sqLiteDatabase.execSQL(tb_schedule);
        String tb_trip ="create table if not exists  tb_trip (id integer primary key autoincrement,dia_diem string,start_time date, end_time date,status_trip int," +
                "client_id references tb_client(id)," +
                "receipt_id references tb_receipt(id))";
        sqLiteDatabase.execSQL(tb_trip);

        // receipt
        String tb_receipt = "create table if not exists tb_receipt (id integer primary key autoincrement,name_client string,name_driver string,oder_time date, start_time date,end_time date,status_driver int , status_receipt int, total int,dia_diem string , " +
                "client_id references tb_client(id)," +
                "" +
                "vehicles_id references tb_vehicles(id))";
        sqLiteDatabase.execSQL(tb_receipt);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public byte[] getBytes(ImageView imageView) {
        try {
            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] bytesData = stream.toByteArray();
            stream.close();
            return bytesData;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
