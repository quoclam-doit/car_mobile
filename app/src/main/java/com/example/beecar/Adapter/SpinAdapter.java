package com.example.beecar.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.beecar.Model.Driver;
import com.example.beecar.R;

import java.util.List;

public class SpinAdapter extends BaseAdapter {
    List<Driver> list;
    Context context;

    public SpinAdapter(List<Driver> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView ;

        if(view == null){
            itemView = View.inflate(viewGroup.getContext(), R.layout.item_spin,null);
        }else{
            itemView= view;
        }

        final Driver obj = list.get(i);
        TextView tvid = itemView.findViewById(R.id.tv_id);
        TextView tvFullName = itemView.findViewById(R.id.tv_fullName);
        tvid.setText(obj.getId()+"");
        tvFullName.setText(obj.getFull_name());
        return itemView;

    }
}
