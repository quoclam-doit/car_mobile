package com.example.beecar.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.beecar.Database.MyDbHelper;
import com.example.beecar.Model.Category;

import java.util.ArrayList;

public class CategoryDAO {
    MyDbHelper myDbHelper;


    public CategoryDAO(Context context) {
        myDbHelper = new MyDbHelper(context);
        myDbHelper.getReadableDatabase();
    }

    public ArrayList<Category> selectAll(){
        ArrayList<Category> list = new ArrayList<>();
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        String sql = "SELECT*FROM tb_category";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Category objC = new Category();
                objC.setId(cursor.getInt(0));
                objC.setName(cursor.getString(1));
                list.add(objC);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;

    }
    public  boolean insert(Category objc){
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Category.COL_name,objc.getName());
        long row = db.insert(Category.TB_name,null,values);
        return (row>0);
    }

    public boolean update(Category objc){
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Category.COL_name,objc.getName());
        int row = db.update(Category.TB_name,values,"id=?",new String[]{objc.getId()+""});
        return row>0;
    }
    public  boolean delete(int objC){
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        int row = db.delete(Category.TB_name,"id=?",new String[]{objC+""});
        return row>0;
    }
}
