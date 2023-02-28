package com.example.beecar.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beecar.DAO.DriverDAO;
import com.example.beecar.Model.Driver;
import com.example.beecar.Model.User;
import com.example.beecar.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GioiThieuTaiXeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GioiThieuTaiXeFragment extends Fragment {
    private TextView tvFullName;
    private TextView tvLuong;
    private ImageView img_banglai;
    private TextView tv_password;
    private  TextView tv_name;
    Driver objD = null;

    public GioiThieuTaiXeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GioiThieuTaiXeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GioiThieuTaiXeFragment newInstance(String param1, String param2) {
        GioiThieuTaiXeFragment fragment = new GioiThieuTaiXeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_gioi_thieu_tai_xe, container, false);
        tvFullName = v.findViewById(R.id.tv_full_nametaixe);
        tvLuong = v.findViewById(R.id.tv_luong);
        img_banglai = v.findViewById(R.id.img_banglai);
        tv_password = v.findViewById(R.id.tv_pass_word);
        tv_name = v.findViewById(R.id.tv_user_Name);

        User objU = (User) getArguments().get("obj");
        DriverDAO driverDAO = new DriverDAO(getContext());

        for (Driver d: driverDAO.selectAll()){
            if (d.getUser_id() == objU.getId()){
                objD = d ;
            }
        }
        tvFullName.setText(objD.getFull_name());
        tvLuong.setText(objD.getLuongcb()+"");
        tv_name.setText(objD.getUser_name());
        Bitmap bitmap = BitmapFactory.decodeByteArray(objD.getImage_gplx(),0,objD.getImage_gplx().length);
        tv_password.setText(objD.getPassword());
        img_banglai.setImageBitmap(bitmap);
//        tv_password.setOnClickListener( view ->{
//
//
//        });


        return v;
    }
}