package com.example.beecar.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beecar.Adapter.VehiclesAdapter;
import com.example.beecar.Adapter.VehiclesManagerAdapter;
import com.example.beecar.DAO.VehiclesDAO;
import com.example.beecar.Model.Vehicles;
import com.example.beecar.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class QuanLyXeFragment extends Fragment {
    RecyclerView rcvXe;
    FloatingActionButton fabAddXe;
    VehiclesDAO vehiclesDAO;
    VehiclesManagerAdapter adapter;
    ThemXeFragment themXeFragment;
    List<Vehicles> list;

    public QuanLyXeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_xe, container, false);
        rcvXe = (RecyclerView) view.findViewById(R.id.rcvXe);
        vehiclesDAO = new VehiclesDAO(getContext());
        list = new ArrayList<>();
        list = vehiclesDAO.selectAll();
        adapter = new VehiclesManagerAdapter(getContext(), list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvXe.setLayoutManager(linearLayoutManager);
        rcvXe.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        fabAddXe = (FloatingActionButton) view.findViewById(R.id.fabAddXe);
        fabAddXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themXeFragment = new ThemXeFragment();
                ganFragDriver(themXeFragment);
            }
        });
        return view;
    }
    public void ganFragDriver(Fragment fg) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.framerquanly, fg).commit();
    }
}