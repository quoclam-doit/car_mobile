package com.example.beecar.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.beecar.Adapter.WorkAdapter;
import com.example.beecar.DAO.DriverDAO;
import com.example.beecar.DAO.ScheduleDAO;
import com.example.beecar.Model.Driver;
import com.example.beecar.Model.Schedule;
import com.example.beecar.Model.User;
import com.example.beecar.R;

import java.util.ArrayList;
import java.util.List;


public class CongViecTaiXeFragment extends Fragment {
    RecyclerView recywork;
    WorkAdapter workAdapter;
    ScheduleDAO scheduleDAO;
    List<Schedule> list = new ArrayList<>();
    Driver objvc = null;

    public CongViecTaiXeFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cong_viec_tai_xe, container, false);

        User objU = (User) getArguments().get("objx");
        DriverDAO driverDAO = new DriverDAO(getContext());

        for (Driver d: driverDAO.selectAll()){
            if (d.getUser_id() == objU.getId()){
                objvc = d ;
            }
        }
        Log.e("obj",objvc.getId()+"");
        recywork = view.findViewById(R.id.recy_work);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recywork.setLayoutManager(linearLayoutManager);
        list.clear();
        scheduleDAO =  new ScheduleDAO(getContext());
        list.addAll(scheduleDAO.selectOfDriver(objvc.getId()));
        Toast.makeText(getContext(), list.size()+"", Toast.LENGTH_SHORT).show();
        workAdapter = new WorkAdapter(list,getContext());
        recywork.setAdapter(workAdapter);
        workAdapter.notifyDataSetChanged();
        return view;

    }
}