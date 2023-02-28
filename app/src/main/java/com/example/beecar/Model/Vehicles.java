package com.example.beecar.Model;

import java.util.Arrays;

public class Vehicles {
    int id;
    byte[] image;
    String name_car;
    String bien_ks;
    int count_muon;
    int price_time;
    int price_date;
    String day_bd;
    String day_dk;
    int vehicles_status;
    int id_category;
    public  static final String TB_name = "tb_vehicles";

    public  static final String COL_image_car = "image_car";

    public  static final String COL_name_car = "name_car";
    public  static final String COL_bien_ks = "bien_ks";
    public  static final String COL_price_time = "price_time";
    public  static final String COL_price_date = "price_date";
    public  static final String COL_count_muon = "count_muon";
    public  static final String COL_day_bd = "day_bd";
    public  static final String COL_day_dk = "day_dk";
    public  static final String COL_vehicles_status = "vehicles_status";
    public  static final String COL_id_category = "id_category";





    public Vehicles() {
    }

    public int getVehicles_status() {
        return vehicles_status;
    }

    public void setVehicles_status(int vehicles_status) {
        this.vehicles_status = vehicles_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay_bd() {
        return day_bd;
    }

    public void setDay_bd(String day_bd) {
        this.day_bd = day_bd;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getBien_ks() {
        return bien_ks;
    }

    public void setBien_ks(String bien_ks) {
        this.bien_ks = bien_ks;
    }

    public String getDay_dk() {
        return day_dk;
    }

    public void setDay_dk(String day_dk) {
        this.day_dk = day_dk;
    }

    public String getName_car() {
        return name_car;
    }

    public void setName_car(String name_car) {
        this.name_car = name_car;
    }

    public int getCount_muon() {
        return count_muon;
    }

    public void setCount_muon(int count_muon) {
        this.count_muon = count_muon;
    }

    public int getPrice_time() {
        return price_time;
    }

    public void setPrice_time(int price_time) {
        this.price_time = price_time;
    }

    public int getPrice_date() {
        return price_date;
    }

    public void setPrice_date(int price_date) {
        this.price_date = price_date;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    @Override
    public String toString() {
        return
                "id: " + id +'\n' +
                "Tên xe: " + name_car + '\n' +
                "Biển kiểm soát: " + bien_ks + '\n' +
                "Số lần cho thuê: " + count_muon + '\n' +
                "Giá theo ngày: " + price_date +'\n' +
                "Ngày bảo dưỡng: " + day_bd  +'\n' +
                "Ngày đăng kiểm: " + day_dk +'\n'
                ;
    }
}
