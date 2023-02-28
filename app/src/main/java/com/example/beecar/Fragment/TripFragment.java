package com.example.beecar.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beecar.Adapter.TripAdapter;
import com.example.beecar.DAO.ClientDAO;
import com.example.beecar.DAO.TripDAO;
import com.example.beecar.Model.Client;
import com.example.beecar.Model.Trip;
import com.example.beecar.Model.User;
import com.example.beecar.R;

import java.util.ArrayList;
import java.util.List;


public class TripFragment extends Fragment {
    RecyclerView recyclerView;
    TripDAO tripDAO;
    TripAdapter adapter;
    User user;
    Client client = null;
    List<Trip> list = new ArrayList<>();
    ClientDAO clientDAO;





    public TripFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trip, container, false);
        recyclerView = view.findViewById(R.id.recy_trip);
        clientDAO = new ClientDAO(getContext());
        tripDAO = new TripDAO(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        user = (User) getArguments().get("obj");
        client = new Client();
        for (Client c: clientDAO.selectAll()){
            if (c.getUser_id() == user.getId()){
                client = c;
                break;
            }
        }
        list.clear();
        list.addAll(tripDAO.selectTripOfClient(client.getId()));
            adapter = new TripAdapter(list,getContext());
            recyclerView.setAdapter(adapter);



        return view;
    }

}