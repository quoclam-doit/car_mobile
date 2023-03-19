package com.example.beecar.Model;

public class Schedule {
    int id;
    String start_time;
    String end_time;
    String dia_diem;
    String name;
    String bien_ks;
    int status_schedule;
    int driver_id;
    int receipt_id;
    public Schedule() {
    }

    public static final String TB_name = "tb_schedule";
    public static final String COL_id = "id";
    public static final String COL_dia_diem = "dia_diem";
    public static final String COL_name = "name";
    public static final String COL_bien_ks = "bien_ks";

    public static final String COL_status = "status_schedule";
    public static final String COL_start_time = "start_time";
    public static final String COL_end_time = "end_time";
    public static final String COL_driver_id = "driver_id";
    public static final String COL_receipt_id = "receipt_id";

    public int getStatus_schedule() {
        return status_schedule;
    }

    public void setStatus_schedule(int status_schedule) {
        this.status_schedule = status_schedule;
    }

    public String getDia_diem() {
        return dia_diem;
    }

    public void setDia_diem(String dia_diem) {
        this.dia_diem = dia_diem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBien_ks() {
        return bien_ks;
    }

    public void setBien_ks(String bien_ks) {
        this.bien_ks = bien_ks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public int getReceipt_id() {
        return receipt_id;
    }

    public void setReceipt_id(int receipt_id) {
        this.receipt_id = receipt_id;
    }
}
