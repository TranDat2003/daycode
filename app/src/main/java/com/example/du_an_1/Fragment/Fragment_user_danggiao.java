package com.example.du_an_1.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_1.Adapter.DanggiaoAdapter;
import com.example.du_an_1.Adapter.User_danggiao_Adapter;
import com.example.du_an_1.Dao.DonhangDao;
import com.example.du_an_1.Dao.GiohangDao;
import com.example.du_an_1.Model.Donhang;
import com.example.du_an_1.Model.Giohang;
import com.example.du_an_1.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_user_danggiao#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_user_danggiao extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_user_danggiao() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_user_danggiao.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_user_danggiao newInstance(String param1, String param2) {
        Fragment_user_danggiao fragment = new Fragment_user_danggiao();
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
    DonhangDao donhangDao;
    RecyclerView recyclerView;
    ArrayList<Donhang> donhangArrayList;
    User_danggiao_Adapter adapter;
    GiohangDao giohangDao;
    int value;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_user_danggiao, container, false);
                donhangDao=new DonhangDao(getContext());
        recyclerView=view.findViewById(R.id.userdanggiao);
        donhangArrayList=donhangDao.getall();
        giohangDao=new GiohangDao(getContext());
        SharedPreferences sharedPreferences= getContext().getSharedPreferences("userfile", Context.MODE_PRIVATE);
        value=sharedPreferences.getInt("userr",0);
        ArrayList<Donhang> donhangArrayList1=new ArrayList<>();
        for (Donhang donhang:donhangArrayList) {
            Giohang giohang=giohangDao.getid(String.valueOf(donhang.magiohang));
            if(donhang.trangthai==3&&giohang.makh==value){
                donhangArrayList1.add(donhang);
            }
        }
        adapter=new User_danggiao_Adapter(getContext(),donhangArrayList1);
//       adapterAdmin=new DonhangAdapterAdmin_choxacnhan(getContext(),donhangArrayList1);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerView.setAdapter(adapter);
        return view;
    }
}