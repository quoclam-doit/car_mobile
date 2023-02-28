package com.example.beecar.Model;

public class Category {
    int id;
    String name;

    public Category() {
    }
    public static final String TB_name = "tb_category";
    public static final String COL_id = "id";
    public static final String COL_name = "name_category";


    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
