package com.example.beecar.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.beecar.Database.MyDbHelper;
import com.example.beecar.Model.Schedule;
import com.example.beecar.Model.Trip;

import java.util.ArrayList;

public class TripDAO {
    MyDbHelper dbHelper;
    Context context;

    public TripDAO(Context context) {
        this.context = context;
        dbHelper = new MyDbHelper(context);
        dbHelper.getWritableDatabase();
    }

    public ArrayList<Trip> selectTripOfClient(int id){
        ArrayList<Trip> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select*from tb_trip where client_id="+id+" and status_trip =1";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
              Trip trip = new Trip();
              trip.setId(cursor.getInt(0));
              trip.setDia_diem(cursor.getString(1));
              trip.setName(cursor.getString(2));
              trip.setBien_ks(cursor.getString(3));
              trip.setStart_time(cursor.getString(4));
              trip.setEnd_time(cursor.getString(5));
              trip.setStatus_trip(cursor.getInt(6));
              trip.setClient_id(cursor.getInt(7));
              trip.setReceipt_id(cursor.getInt(8));
              list.add(trip);
              cursor.moveToNext();
            }
        }
        Log.e("scheduleListOfDrive",list.size()+"");
        cursor.close();
        return list;
    }
    public ArrayList<Trip> selectAll(){
        ArrayList<Trip> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select*from tb_trip";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Trip trip = new Trip();
                trip.setId(cursor.getInt(0));
                trip.setDia_diem(cursor.getString(1));
                trip.setName(cursor.getString(2));
                trip.setBien_ks(cursor.getString(3));
                trip.setStart_time(cursor.getString(4));
                trip.setEnd_time(cursor.getString(5));
                trip.setStatus_trip(cursor.getInt(6));
                trip.setClient_id(cursor.getInt(7));
                trip.setReceipt_id(cursor.getInt(8));
                list.add(trip);
                cursor.moveToNext();
            }
        }
        Log.e("scheduleListOfDrive",list.size()+"");
        cursor.close();
        return list;
    }
    public ArrayList<Trip> selectTripOfClientXacNhan(int id){
        ArrayList<Trip> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select*from tb_trip where id="+id+"";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Trip trip = new Trip();
                trip.setId(cursor.getInt(0));
                trip.setDia_diem(cursor.getString(1));
                trip.setName(cursor.getString(2));
                trip.setBien_ks(cursor.getString(3));
                trip.setStart_time(cursor.getString(4));
                trip.setEnd_time(cursor.getString(5));
                trip.setStatus_trip(cursor.getInt(6));
                trip.setClient_id(cursor.getInt(7));
                trip.setReceipt_id(cursor.getInt(8));
                list.add(trip);
                cursor.moveToNext();
            }
        }
        Log.e("scheduleListOfDrive",list.size()+"");
        cursor.close();
        return list;
    }
    public boolean insert(Trip trip){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Trip.COL_dia_diem,trip.getDia_diem());
        values.put(Trip.COL_name, trip.getName());
        values.put(Trip.COL_bien_ks, trip.getBien_ks());
        values.put(Trip.COL_start_time,trip.getStart_time());
        values.put(Trip.COL_end_time,trip.getEnd_time());
        values.put(Trip.COL_status_trip,trip.getStatus_trip());
        values.put(Trip.COL_client_id,trip.getClient_id());
        values.put(Trip.COL_receipt_id,trip.getReceipt_id());
         long row = db.insert(Trip.TB_name,null,values);
        return row>0;
    }

    public boolean update(Trip trip){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Trip.COL_dia_diem,trip.getDia_diem());
        values.put(Trip.COL_name, trip.getName());
        values.put(Trip.COL_bien_ks, trip.getBien_ks());
        values.put(Trip.COL_start_time,trip.getStart_time());
        values.put(Trip.COL_end_time,trip.getEnd_time());
        values.put(Trip.COL_status_trip,trip.getStatus_trip());
        values.put(Trip.COL_client_id,trip.getClient_id());
        values.put(Trip.COL_receipt_id,trip.getReceipt_id());
        int row = db.update(Trip.TB_name,values,"id=?",new String[]{trip.getId()+""});
        return row>0;
    }

    public  boolean delete(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int row = db.delete(Trip.TB_name,"id=?",new String[]{id+""});
        return row>0;

    }
}
