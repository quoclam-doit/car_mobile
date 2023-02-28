package com.example.beecar.Model;

import java.io.Serializable;

public class Driver implements Serializable {
        int id;
        String user_name;
        String password;
        String full_name;
        byte[] image_gplx;
        int luongcb;
        int status_driver;
        int user_id;

        public static final String TB_Name = "tb_driver";

        public static final String COL_user_name = "user_name";
        public static final String COL_password = "password";
        public static final String COL_full_name = "full_name";
        public static final String COL_image_gplx = "image_gplx";
        public static final String COL_luongcb = "luongcb";
        public static final String COL_status = "status_driver";
        public static final String COL_user_id = "user_id";

    public Driver() {
    }

    public byte[] getImage_gplx() {
        return image_gplx;
    }

    public void setImage_gplx(byte[] image_gplx) {
        this.image_gplx = image_gplx;
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

    public int getLuongcb() {
        return luongcb;
    }

    public void setLuongcb(int luongcb) {
        this.luongcb = luongcb;
    }

    public int getStatus_driver() {
        return status_driver;
    }

    public void setStatus_driver(int status_driver) {
        this.status_driver = status_driver;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
