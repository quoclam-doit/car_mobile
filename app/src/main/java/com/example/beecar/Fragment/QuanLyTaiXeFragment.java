package com.example.beecar.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beecar.Adapter.DriverAdapter;
import com.example.beecar.Adapter.VehiclesAdapter;
import com.example.beecar.DAO.DriverDAO;
import com.example.beecar.DAO.UserDAO;
import com.example.beecar.Model.Driver;
import com.example.beecar.Model.User;
import com.example.beecar.R;

import java.util.ArrayList;
import java.util.List;

public class QuanLyTaiXeFragment extends Fragment {
    RecyclerView recyclerView;
    DriverDAO driverDAO;
    DriverAdapter adapter;
    List<Driver> list;


    public QuanLyTaiXeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_tai_xe, container, false);
        recyclerView = view.findViewById(R.id.rcvTaiXe);
        driverDAO = new DriverDAO(getContext());
        list = new ArrayList<>();
        list = driverDAO.selectAll();
        adapter = new DriverAdapter(getContext(), list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }
}