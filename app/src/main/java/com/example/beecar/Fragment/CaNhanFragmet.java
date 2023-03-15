package com.example.beecar.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.beecar.MainActivity;
import com.example.beecar.Model.User;
import com.example.beecar.MyHDon_Activity;
import com.example.beecar.HuyHDon_Activity;
import com.example.beecar.R;
import com.example.beecar.ThongTinCaNhan;

public class CaNhanFragmet extends Fragment {
    LinearLayout linearLayoutmyHD;
    LinearLayout huyDon;
    LinearLayout btn_Dk;
    LinearLayout account;
    TextView tv_tentk, tv_account;
    public CaNhanFragmet() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  =inflater.inflate(R.layout.fragment_canhan, container, false);
        User obj = (User) getArguments().get("obj");
        tv_tentk = view.findViewById(R.id.tv_tkcanhan);
        tv_tentk.setText(obj.getUser_name());
        linearLayoutmyHD = view.findViewById(R.id.hd_myHD);
        linearLayoutmyHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MyHDon_Activity.class);
                intent.putExtra("obj",obj);
                startActivity(intent);
            }
        });

        account = view.findViewById(R.id.account_manager);
        account.setOnClickListener(view1 -> {
            Intent intent2 = new Intent(getContext(), ThongTinCaNhan.class);
            intent2.putExtra("obj",obj);

            startActivity(intent2);
        });

        huyDon = view.findViewById(R.id.huy_don);
        huyDon.setOnClickListener(view1 -> {
            Intent intent1 = new Intent(getContext(),HuyHDon_Activity.class);
            intent1.putExtra("obj",obj);
            startActivity(intent1);
        });
        btn_Dk = view.findViewById(R.id.btn_dang_xuat);
        btn_Dk.setOnClickListener(view1 -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Đăng xuất");
            builder.setMessage("Bạn có muốn đăng xuất ? ");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                        getActivity().finish();
                        startActivity(new Intent(getContext(), MainActivity.class));


                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            // tao dialog
            AlertDialog dialog = builder.create();
            dialog.show();

        });

        return view;
    }

}
