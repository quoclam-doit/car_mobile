package com.example.beecar.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beecar.Adapter.WorkAdapter;
import com.example.beecar.DAO.DriverDAO;
import com.example.beecar.DAO.ScheduleDAO;
import com.example.beecar.Model.Driver;
import com.example.beecar.Model.Schedule;
import com.example.beecar.Model.User;
import com.example.beecar.R;

import org.w3c.dom.Text;


public class HomeDriverFragment extends Fragment {
    RecyclerView recyclerView;
    TextView checkList;
    ScheduleDAO scheduleDAO;
    WorkAdapter adapter;
    Driver objD = null;







    public HomeDriverFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_home_driver, container, false);
        objD = new Driver();
        checkList = view.findViewById(R.id.check_list);

        User objU = (User) getArguments().get("objx");
        DriverDAO driverDAO = new DriverDAO(getContext());

        for (Driver d: driverDAO.selectAll()){
            if (d.getUser_id() == objU.getId()){
                objD = d ;
            }
        }
        recyclerView = view.findViewById(R.id.recy_schedule_today);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        scheduleDAO = new ScheduleDAO(getContext());
        adapter = new WorkAdapter(scheduleDAO.selectOfDriver(objD.getId()),getContext());
        if (scheduleDAO.selectOfDriver(objD.getId()).size()> 0){
            checkList.setVisibility(View.GONE);
        }else{
            checkList.setVisibility(View.VISIBLE);
        }

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onResume() {
        adapter.notifyDataSetChanged();
        super.onResume();
    }
}