package com.example.beecar.Model;

import java.io.Serializable;

public class User implements Serializable {
    int id;
    String user_name;
    String password;
    String full_name;
    int position;

    public User() {
    }

    public User(String user_name, String password, String full_name, int position) {
        this.user_name = user_name;
        this.password = password;
        this.full_name = full_name;
        this.position = position;
    }

    public static final String TB_Name = "tb_user";
    public static final String COL_id = "id";
    public static final String COL_user_name = "user_name";
    public static final String COL_password = "password";
    public static final String COL_full_name = "full_name";
    public static final String COL_position = "position";


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
