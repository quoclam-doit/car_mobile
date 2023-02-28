package com.example.beecar.Model;

import java.io.Serializable;

public class Receipt implements Serializable {
    int id;
    String name_client;
    String name_driver;
    String dia_diem;
    String oder_time;
    String start_time;
    String end_time;
    int status_driver;
    int status;
    int total;
    int client_id;

    int vehicles_id;
    public static final String TB_name ="tb_receipt";
    public static final String COL_name_client ="name_client";
    public static final String COL_name_driver ="name_driver";
    public static final String COL_dia_diem ="dia_diem";
    public static final String COL_oder ="oder_time";
    public static final String COL_start ="start_time";
    public static final String COL_end ="end_time";
    public static final String COL_status_driver ="status_driver";
    public static final String COL_status ="status_receipt";
    public static final String COL_total ="total";
    public static final String COL_client_id ="client_id";
    public static final String COL_driver_id ="driver_id";
    public static final String COL_vehicles_id ="vehicles_id";


    public Receipt() {
    }

    public int getId() {
        return id;
    }

    public String getDia_diem() {
        return dia_diem;
    }

    public void setDia_diem(String dia_diem) {
        this.dia_diem = dia_diem;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_client() {
        return name_client;
    }

    public void setName_client(String name_client) {
        this.name_client = name_client;
    }

    public String getName_driver() {
        return name_driver;
    }

    public void setName_driver(String name_driver) {
        this.name_driver = name_driver;
    }

    public String getOder_time() {
        return oder_time;
    }

    public void setOder_time(String oder_time) {
        this.oder_time = oder_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getStatus_driver() {
        return status_driver;
    }

    public void setStatus_driver(int status_driver) {
        this.status_driver = status_driver;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getVehicles_id() {
        return vehicles_id;
    }

    public void setVehicles_id(int vehicles_id) {
        this.vehicles_id = vehicles_id;
    }


}
