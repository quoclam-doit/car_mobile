package com.example.beecar.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.beecar.Adapter.PhotoAdapter;
import com.example.beecar.Model.Photo;
import com.example.beecar.Model.User;
import com.example.beecar.R;
import com.example.beecar.SearchVehiclesCoLai;
import com.example.beecar.SearchVehiclesTuLai;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


public class HomeClientFragment extends Fragment {
    LinearLayout btn_tu_lai;
    LinearLayout btn_co_lai;
    TextView tvFullName;

    ViewPager viewPager;
    CircleIndicator circleIndicator;
    PhotoAdapter photoAdapter;


    public HomeClientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_home_client, container, false);

        viewPager = v.findViewById(R.id.viewpager);
        circleIndicator = v.findViewById(R.id.circle_indicator);

        photoAdapter =new PhotoAdapter(getContext() , getListPhoto());
        viewPager.setAdapter(photoAdapter);
        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

        User obj = (User) getArguments().get("obj");
        tvFullName = v.findViewById(R.id.tv_full_name);
        tvFullName.setText(obj.getFull_name());

        btn_tu_lai = v.findViewById(R.id.layout_tu_lai);
        btn_co_lai = v.findViewById(R.id.layout_co_lai);
        btn_tu_lai.setOnClickListener(view -> {
            Intent i = new Intent(getContext(), SearchVehiclesTuLai.class);
            i.putExtra("obj",obj);
            startActivity(i);
        });

        btn_co_lai.setOnClickListener(view -> {
            Intent i1 = new Intent(getContext(), SearchVehiclesCoLai.class);
            i1.putExtra("obj",obj);
            startActivity(i1);
        });



        return v;
    }

    private List<Photo> getListPhoto(){
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.oto1));
        list.add(new Photo(R.drawable.oto2));
        list.add(new Photo(R.drawable.oto3));
        list.add(new Photo(R.drawable.oto4));

        return list;

    }
}