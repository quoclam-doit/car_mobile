package com.example.beecar.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beecar.DAO.CategoryDAO;
import com.example.beecar.Model.Category;
import com.example.beecar.R;

import java.util.List;

public class CategoryOfVehiclesAdapter extends BaseAdapter {
    List<Category> list;
    CategoryDAO categoryDAO;
    Context context;

    public CategoryOfVehiclesAdapter(List<Category> list, Context context) {
        this.list = list;
        categoryDAO = new CategoryDAO(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewOfItem {
        TextView tv_number_category,tv_name_category;
        ImageView img_delete;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewOfItem viewOfItem;
        if (convertView == null) {
            viewOfItem = new ViewOfItem();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theloai, null, false);
            viewOfItem.tv_number_category = convertView.findViewById(R.id.tv_number_category);
            viewOfItem.tv_name_category = convertView.findViewById(R.id.tv_name_category);
            viewOfItem.img_delete = convertView.findViewById(R.id.img_delete_category);
            convertView.setTag(viewOfItem);
        }
        else{
            viewOfItem = (ViewOfItem)convertView.getTag();
        }
        viewOfItem.tv_number_category.setText(list.get(position).getId());
        viewOfItem.tv_name_category.setText(list.get(position).getName());

        viewOfItem.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Xóa thể loại !");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        int id = list.get(i).getId();
                        if (categoryDAO.delete(id)){
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            list.clear();
                            list.addAll(categoryDAO.selectAll());
                            notifyDataSetInvalidated();
                        }
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        return convertView;
    }



}
