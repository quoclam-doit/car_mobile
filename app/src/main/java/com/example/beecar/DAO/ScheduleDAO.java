package com.example.beecar.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.beecar.Database.MyDbHelper;
import com.example.beecar.Model.Schedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ScheduleDAO {
    MyDbHelper dbHelper;
    Context context;

    public ScheduleDAO(Context context) {
        this.context = context;
        dbHelper = new MyDbHelper(context);
        dbHelper.getWritableDatabase();
    }

    public ArrayList<Schedule> selectAll(){
        ArrayList<Schedule> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select*from tb_schedule";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToNext()){
            while (!cursor.isAfterLast()){
                Schedule schedule = new Schedule();
                schedule.setId(cursor.getInt(0));
                schedule.setDia_diem(cursor.getString(1));
                schedule.setName(cursor.getString(2));
                schedule.setBien_ks(cursor.getString(3));
                schedule.setStatus_schedule(cursor.getInt(4));
                schedule.setStart_time(cursor.getString(5));
                schedule.setEnd_time(cursor.getString(6));
                schedule.setDriver_id(cursor.getInt(7));
                schedule.setReceipt_id(cursor.getInt(8));
                list.add(schedule);
                cursor.moveToNext();
            }
        }
        Log.e("scheduleListOfDrive",list.size()+"");
        cursor.close();
        return list;

    }

    public ArrayList<Schedule> selectOfDriver(int id){
        ArrayList<Schedule> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select * from tb_schedule where driver_id="+id+" and status_schedule = 1";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Schedule schedule = new Schedule();
                schedule.setId(cursor.getInt(0));
                schedule.setDia_diem(cursor.getString(1));
                schedule.setName(cursor.getString(2));
                schedule.setBien_ks(cursor.getString(3));
                schedule.setStatus_schedule(cursor.getInt(4));
                schedule.setStart_time(cursor.getString(5));
                schedule.setEnd_time(cursor.getString(6));
                schedule.setDriver_id(cursor.getInt(7));
                schedule.setReceipt_id(cursor.getInt(8));
                list.add(schedule);
                cursor.moveToNext();
            }
        }
        Log.e("scheduleListOfDrive",list.size()+"");
        cursor.close();
        return list;
    }


    public ArrayList<Schedule> selectOfDriverToday(int id){
        ArrayList<Schedule> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select * from tb_schedule where driver_id="+id+" and start_time like '%"+getToday()+"'";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Schedule schedule = new Schedule();
                schedule.setId(cursor.getInt(0));
                schedule.setDia_diem(cursor.getString(1));
                schedule.setName(cursor.getString(2));
                schedule.setBien_ks(cursor.getString(3));
                schedule.setStatus_schedule(cursor.getInt(4));
                schedule.setStart_time(cursor.getString(5));
                schedule.setEnd_time(cursor.getString(6));
                schedule.setDriver_id(cursor.getInt(7));
                schedule.setReceipt_id(cursor.getInt(8));
                list.add(schedule);
                cursor.moveToNext();
            }
        }
        Log.e("scheduleListOfDrive",list.size()+"");
        cursor.close();
        return list;
    }

    public boolean insert(Schedule obj){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Schedule.COL_dia_diem,obj.getDia_diem());
        values.put(Schedule.COL_name, obj.getName());
        values.put(Schedule.COL_bien_ks, obj.getBien_ks());
        values.put(Schedule.COL_status,obj.getStatus_schedule());
        values.put(Schedule.COL_start_time,obj.getStart_time());
        values.put(Schedule.COL_end_time,obj.getEnd_time());
        values.put(Schedule.COL_driver_id,obj.getDriver_id());
        values.put(Schedule.COL_receipt_id,obj.getReceipt_id());
        Log.e("id",obj.getDriver_id()+"");
        long row = db.insert(Schedule.TB_name,null,values);
        return row>0;
    }


    public boolean update(Schedule obj){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Schedule.COL_dia_diem,obj.getDia_diem());
        values.put(Schedule.COL_name, obj.getName());
        values.put(Schedule.COL_bien_ks, obj.getBien_ks());
        values.put(Schedule.COL_status,obj.getStatus_schedule());
        values.put(Schedule.COL_start_time,obj.getStart_time());
        values.put(Schedule.COL_end_time,obj.getEnd_time());
        values.put(Schedule.COL_driver_id,obj.getDriver_id());
        values.put(Schedule.COL_receipt_id,obj.getReceipt_id());
        int row = db.update(Schedule.TB_name,values,"id=?",new String[]{obj.getId()+""});
        return row>0;
    }

    public  boolean delete(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int row = db.delete(Schedule.TB_name,"id=?",new String[]{id+""});
        return row>0;

    }

    private String getToday(){
        return  new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
    }


}
