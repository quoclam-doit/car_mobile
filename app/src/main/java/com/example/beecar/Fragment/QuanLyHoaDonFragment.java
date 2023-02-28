package com.example.beecar.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beecar.Adapter.ReceiptAdapter;
import com.example.beecar.Adapter.ReceiptClAdapter;
import com.example.beecar.Adapter.ReceiptManagerAdapter;
import com.example.beecar.DAO.ReceiptDAO;
import com.example.beecar.Model.Receipt;
import com.example.beecar.R;

import java.util.ArrayList;
import java.util.List;


public class QuanLyHoaDonFragment extends Fragment {
    RecyclerView rcvHD;
    ReceiptDAO receiptDAO;
    ReceiptClAdapter adapter;
    List<Receipt> list;


    public QuanLyHoaDonFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_hoa_don, container, false);
        rcvHD = view.findViewById(R.id.rcvHoaDon);
        receiptDAO = new ReceiptDAO(getContext());
        list = new ArrayList<>();
        list = receiptDAO.selectAllFull();
        adapter = new ReceiptClAdapter(list,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvHD.setLayoutManager(linearLayoutManager);
        rcvHD.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        list.clear();
        list.addAll(receiptDAO.selectAllFull());
        adapter = new ReceiptClAdapter(list,getContext());
        rcvHD.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}