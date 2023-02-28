package com.example.beecar.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beecar.Adapter.ReceiptManagerAdapter;
import com.example.beecar.DAO.ReceiptDAO;
import com.example.beecar.Model.Receipt;
import com.example.beecar.R;

import java.util.ArrayList;
import java.util.List;


public class HomeManagerFragment extends Fragment {
    RecyclerView recyclerView;
    ReceiptDAO receiptDAO;
    ReceiptManagerAdapter adapter;
    List<Receipt> list;


    public HomeManagerFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home_manager, container, false);
        recyclerView = view.findViewById(R.id.recy_receipt);
        receiptDAO = new ReceiptDAO(getContext());
        list = new ArrayList<>();
        list = receiptDAO.selectAll();
        adapter = new ReceiptManagerAdapter(list,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        return view;
    }
}