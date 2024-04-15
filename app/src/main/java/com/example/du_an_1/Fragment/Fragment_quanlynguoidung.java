package com.example.du_an_1.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Adapter.KhachhangAdapter;
import com.example.du_an_1.Adapter.LoaisanphamAdapter;
import com.example.du_an_1.Dao.KhachhangDao;
import com.example.du_an_1.Model.Khachhang;
import com.example.du_an_1.Model.Loaisanpham;
import com.example.du_an_1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_quanlynguoidung#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_quanlynguoidung extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_quanlynguoidung() {
        // Required empty public constructor
    }


    public static Fragment_quanlynguoidung newInstance(String param1, String param2) {
        Fragment_quanlynguoidung fragment = new Fragment_quanlynguoidung();
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
   RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    ArrayList<Khachhang> khachhangArrayList;
    KhachhangDao khachhangdao;
    KhachhangAdapter khachhangAdapter;
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_quanlynguoidung, container, false);
        recyclerView=view.findViewById(R.id.recykhachhang);
        floatingActionButton=view.findViewById(R.id.adduse);
        khachhangdao=new KhachhangDao(getContext());
        khachhangArrayList=khachhangdao.getall();
        searchView=view.findViewById(R.id.searchc);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Khachhang> khachhangArrayList1=new ArrayList<>();
                for (Khachhang khachhang :khachhangArrayList) {
                    if((khachhang.name.toLowerCase().contains(newText.toLowerCase())&&khachhang.trangthai==0)||khachhang.phone.toLowerCase().contains(newText.toLowerCase())){
                        khachhangArrayList1.add(khachhang);
                    }
                    khachhangAdapter=new KhachhangAdapter(getContext(),khachhangArrayList1);
                    recyclerView.setAdapter(khachhangAdapter);
                }
                return false;
            }
        });

        ArrayList<Khachhang> khachhangArrayList1 =new ArrayList<>();
        for (Khachhang khachhang:khachhangArrayList) {
            if(khachhang.trangthai==0||khachhang.trangthai==2){
                khachhangArrayList1.add(khachhang);
            }
        }
        khachhangAdapter=new KhachhangAdapter(getContext(),khachhangArrayList1);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerView.setAdapter(khachhangAdapter);

        return view;
    }
}