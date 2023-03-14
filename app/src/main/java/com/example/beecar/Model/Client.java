package com.example.beecar.Model;

import java.io.Serializable;

public class Client implements Serializable {
    int id;
    String user_name;
    String password;
    String full_name;
    String email;
    String phone;
    int user_id;

    public Client() {
    }

    public static final String TB_Name = "tb_client";
    public static final String COL_id = "id";
    public static final String COL_user_name = "user_name";
    public static final String COL_password = "password";
    public static final String COL_full_name = "full_name";
    public static final String COL_email = "email";
    public static final String COL_phone = "phone";
    public static final String COL_user_id = "user_id";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
