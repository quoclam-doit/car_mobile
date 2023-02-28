package com.example.beecar.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.beecar.Database.MyDbHelper;
import com.example.beecar.Model.User;

import java.util.ArrayList;

public class UserDAO {
    MyDbHelper myDbHelper;

    public UserDAO(Context context) {
       myDbHelper = new MyDbHelper(context);
       myDbHelper.getWritableDatabase();
    }

    public ArrayList<User> selectAll(){
        ArrayList<User> list = new ArrayList<>();
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        String sql = "SELECT*FROM tb_user";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                User objU = new User();
                objU.setId(cursor.getInt(0));
                objU.setUser_name(cursor.getString(1));
                objU.setPassword(cursor.getString(2));
                objU.setFull_name(cursor.getString(3));
                objU.setPosition(cursor.getInt(4));
                list.add(objU);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }

    public boolean insert(User objU){
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.COL_user_name,objU.getUser_name());
        values.put(User.COL_password,objU.getPassword());
        values.put(User.COL_full_name,objU.getFull_name());
        values.put(User.COL_position,objU.getPosition());
        long row = db.insert(User.TB_Name,null,values);
        return row>0;
    }
    public boolean update(User objU){
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.COL_user_name,objU.getUser_name());
        values.put(User.COL_password,objU.getPassword());
        values.put(User.COL_full_name,objU.getFull_name());
        values.put(User.COL_position,objU.getPosition());
        int row = db.update(User.TB_Name,values,"id=?", new String[]{objU.getId()+""});

        return row>0;
    }








}
