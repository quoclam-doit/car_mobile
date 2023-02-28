package com.example.beecar.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.beecar.Database.MyDbHelper;
import com.example.beecar.Model.Client;

import com.example.beecar.Model.Receipt;
import com.example.beecar.Model.Vehicles;

import java.util.ArrayList;

public class ReceiptDAO {
    MyDbHelper myDbHelper;

    public ReceiptDAO(Context context) {
        myDbHelper = new MyDbHelper(context);
        myDbHelper.getReadableDatabase();
    }

    public ArrayList<Receipt> selectAll(){
        ArrayList<Receipt> list = new ArrayList<>();
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        String sql = "SELECT*FROM tb_receipt where status_receipt = 0";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Receipt objR = new Receipt();
                objR.setId(cursor.getInt(0));
                objR.setName_client(cursor.getString(1));
                objR.setName_driver(cursor.getString(2));
                objR.setOder_time(cursor.getString(3));
                objR.setStart_time(cursor.getString(4));
                objR.setEnd_time(cursor.getString(5));
                objR.setStatus_driver(cursor.getInt(6));
                objR.setStatus(cursor.getInt(7));
                objR.setTotal(cursor.getInt(8));
                objR.setDia_diem(cursor.getString(9));
                objR.setClient_id(cursor.getInt(10));
                objR.setVehicles_id(cursor.getInt(11));
                    list.add(objR);
                    cursor.moveToNext();
            }

        }
        cursor.close();
        return list;
    }
    public ArrayList<Receipt> selectAllFull(){
        ArrayList<Receipt> list = new ArrayList<>();
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        String sql = "SELECT*FROM tb_receipt";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Receipt objR = new Receipt();
                objR.setId(cursor.getInt(0));
                objR.setName_client(cursor.getString(1));
                objR.setName_driver(cursor.getString(2));
                objR.setOder_time(cursor.getString(3));
                objR.setStart_time(cursor.getString(4));
                objR.setEnd_time(cursor.getString(5));
                objR.setStatus_driver(cursor.getInt(6));
                objR.setStatus(cursor.getInt(7));
                objR.setTotal(cursor.getInt(8));
                objR.setDia_diem(cursor.getString(9));
                objR.setClient_id(cursor.getInt(10));
                objR.setVehicles_id(cursor.getInt(11));
                list.add(objR);
                cursor.moveToNext();
            }

        }
        cursor.close();
        return list;
    }
    // get cac hoa don cua 1 client
    public ArrayList<Receipt> getList(Client obj){
        ArrayList<Receipt> list = new ArrayList<>();
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        String sql = "SELECT*FROM tb_receipt where client_id = "+obj.getId()+" and (status_receipt=0 or status_receipt=2)";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Receipt objR = new Receipt();
                objR.setId(cursor.getInt(0));
                objR.setName_client(cursor.getString(1));
                objR.setName_driver(cursor.getString(2));
                objR.setOder_time(cursor.getString(3));
                objR.setStart_time(cursor.getString(4));
                objR.setEnd_time(cursor.getString(5));
                objR.setStatus_driver(cursor.getInt(6));
                objR.setStatus(cursor.getInt(7));
                objR.setTotal(cursor.getInt(8));
                objR.setDia_diem(cursor.getString(9));
                objR.setClient_id(cursor.getInt(10));
                objR.setVehicles_id(cursor.getInt(11));
                list.add(objR);
                cursor.moveToNext();
            }

        }
        cursor.close();
        return list;

    }

    public ArrayList<Receipt> getListHuy(Client obj){
        ArrayList<Receipt> list = new ArrayList<>();
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        String sql = "SELECT*FROM tb_receipt where client_id = "+obj.getId()+" and status_receipt=1";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Receipt objR = new Receipt();
                objR.setId(cursor.getInt(0));
                objR.setName_client(cursor.getString(1));
                objR.setName_driver(cursor.getString(2));
                objR.setOder_time(cursor.getString(3));
                objR.setStart_time(cursor.getString(4));
                objR.setEnd_time(cursor.getString(5));
                objR.setStatus_driver(cursor.getInt(6));
                objR.setStatus(cursor.getInt(7));
                objR.setTotal(cursor.getInt(8));
                objR.setDia_diem(cursor.getString(9));
                objR.setClient_id(cursor.getInt(10));
                objR.setVehicles_id(cursor.getInt(11));
                list.add(objR);
                cursor.moveToNext();
            }

        }
        cursor.close();
        return list;

    }

    public  boolean insert(Receipt objR){
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Receipt.COL_name_client,objR.getName_client());
        values.put(Receipt.COL_name_driver,objR.getName_driver());
        values.put(Receipt.COL_oder,objR.getOder_time());
        values.put(Receipt.COL_start,objR.getStart_time());
        values.put(Receipt.COL_end,objR.getEnd_time());
        values.put(Receipt.COL_status_driver,objR.getStatus_driver());
        values.put(Receipt.COL_status,objR.getStatus());
        values.put(Receipt.COL_total,objR.getTotal());
        values.put(Receipt.COL_dia_diem,objR.getDia_diem());
        values.put(Receipt.COL_client_id,objR.getClient_id());
        values.put(Receipt.COL_vehicles_id,objR.getVehicles_id());
        long row = db.insert(Receipt.TB_name,null,values);
        return row>0;
    }
    public  boolean update(Receipt objR){
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Receipt.COL_name_client,objR.getName_client());
        values.put(Receipt.COL_name_driver,objR.getName_driver());
        values.put(Receipt.COL_oder,objR.getOder_time());
        values.put(Receipt.COL_start,objR.getStart_time());
        values.put(Receipt.COL_end,objR.getEnd_time());
        values.put(Receipt.COL_status_driver,objR.getStatus_driver());
        values.put(Receipt.COL_status,objR.getStatus());
        values.put(Receipt.COL_total,objR.getTotal());
        values.put(Receipt.COL_dia_diem,objR.getDia_diem());
        values.put(Receipt.COL_client_id,objR.getClient_id());
        values.put(Receipt.COL_vehicles_id,objR.getVehicles_id());
        int row = db.update(Receipt.TB_name,values,"id=?",new String[]{objR.getId()+""});
        return row>0;
    }


    public boolean delete(int objV) {
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        int row = db.delete(Vehicles.TB_name,"id=?",new String[]{objV+""});
        return row>0;
    }

}
