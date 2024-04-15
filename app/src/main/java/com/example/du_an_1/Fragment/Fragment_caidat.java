package com.example.du_an_1.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an_1.Caidatcanhan;
import com.example.du_an_1.Chamsockhachhang;
import com.example.du_an_1.Dao.KhachhangDao;
import com.example.du_an_1.Doimatkhau;
import com.example.du_an_1.Model.Khachhang;
import com.example.du_an_1.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_caidat#newInstance} factory method to
 * create an instance of this fragment.
 */
public class
Fragment_caidat extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_caidat() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_caidat.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_caidat newInstance(String param1, String param2) {
        Fragment_caidat fragment = new Fragment_caidat();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
TextView textView;
    LinearLayout thongtin,matkhau,chamsockhachhang;
    Button button;
    int value;
    Khachhang khachhang;
    KhachhangDao khachhangDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_caidat, container, false);
        khachhangDao=new KhachhangDao(getContext());
        SharedPreferences sharedPreferences= getActivity().getSharedPreferences("userfile", Context.MODE_PRIVATE);
        value=sharedPreferences.getInt("userr",0);
        khachhang=khachhangDao.getid(String.valueOf(value));
        textView=view.findViewById(R.id.textView168);
        thongtin=view.findViewById(R.id.lin);
        textView.setText(khachhang.name);
        matkhau=view.findViewById(R.id.lin1);
        chamsockhachhang=view.findViewById(R.id.lin2);
        button=view.findViewById(R.id.button4);
        Toast.makeText(getContext(), ""+khachhang.trangthai+"", Toast.LENGTH_SHORT).show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Khachhang khachhang1=new Khachhang();
                khachhang1.makh=khachhang.makh;
                khachhang1.trangthai=2;
                khachhangDao.delete(khachhang1);

            }
        });

        thongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Caidatcanhan.class);
                startActivity(intent);
            }
        });

        matkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Doimatkhau.class);
                startActivity(intent);
            }
        });

        chamsockhachhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Chamsockhachhang.class);
                startActivity(intent);
            }
        });
        return view;
    }
    public void onResume() {
        super.onResume();
        textView.setText(khachhang.name);
    }
    public void onStart() {

        super.onStart();
        textView.setText(khachhang.name);
    }
    public void onPause() {

        super.onPause();
        textView.setText(khachhang.name);
    }
}