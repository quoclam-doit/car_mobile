package com.example.beecar.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.beecar.Database.MyDbHelper;
import com.example.beecar.Model.Client;
import com.example.beecar.Model.Driver;
import com.example.beecar.Model.User;

import java.util.ArrayList;

public class ClientDAO {

     MyDbHelper myDbHelper;

    public ClientDAO(Context context) {
        myDbHelper = new MyDbHelper(context);
        myDbHelper.getReadableDatabase();
    }
    public ArrayList<Client> selectAll(){
        ArrayList<Client> list = new ArrayList<>();
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        String sql = "SELECT*FROM tb_client";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Client objC = new Client();
                objC.setId(cursor.getInt(0));
                objC.setUser_name(cursor.getString(1));
                objC.setPassword(cursor.getString(2));
                objC.setFull_name(cursor.getString(3));
                objC.setUser_id(cursor.getInt(4));

                list.add(objC);
                cursor.moveToNext();

            }
        }
        cursor.close();
        return list;
    }

    public boolean insert(Client objC){
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Client.COL_user_name,objC.getUser_name());
        values.put(Client.COL_password,objC.getPassword());
        values.put(Client.COL_full_name,objC.getFull_name());
        values.put(Client.COL_user_id,objC.getUser_id());
        long row = db.insert(Client.TB_Name,null,values);
        return row>0;
    }
    public boolean update(Client objC){
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Client.COL_user_name,objC.getUser_name());
        values.put(Client.COL_password,objC.getPassword());
        values.put(Client.COL_full_name,objC.getFull_name());
        values.put(Client.COL_user_id,objC.getUser_id());
        int row = db.update(Client.TB_Name,values,"id=?", new String[]{objC.getId()+""});
        return row>0;
    }





}
