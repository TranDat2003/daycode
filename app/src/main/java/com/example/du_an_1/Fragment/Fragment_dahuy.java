package com.example.du_an_1.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_1.Adapter.DahuyAdapter;
import com.example.du_an_1.Adapter.DonhangAdapterAdmin_choxacnhan;
import com.example.du_an_1.Dao.DonhangDao;
import com.example.du_an_1.Model.Donhang;
import com.example.du_an_1.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_dahuy#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_dahuy extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_dahuy() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_dahuy.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_dahuy newInstance(String param1, String param2) {
        Fragment_dahuy fragment = new Fragment_dahuy();
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
    ArrayList<Donhang> donhangArrayList;

    DonhangDao donhangDao;
    DonhangAdapterAdmin_choxacnhan adapterAdmin;
    DahuyAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_dahuy, container, false);
        donhangDao=new DonhangDao(getActivity());
        recyclerView=view.findViewById(R.id.dahuy);
        donhangArrayList=donhangDao.getall();
        ArrayList<Donhang> donhangArrayList1=new ArrayList<>();
        for (Donhang donhang:donhangArrayList) {
            if(donhang.trangthai==2||donhang.trangthai==5){
                donhangArrayList1.add(donhang);
            }
        }
        adapter=new DahuyAdapter(getContext(),donhangArrayList1);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerView.setAdapter(adapter);
        return view;
    }
    public void onResume() {
        super.onResume();
        donhangArrayList.clear();
        donhangArrayList=donhangDao.getall();
        ArrayList<Donhang> donhangArrayList1=new ArrayList<>();
        for (Donhang donhang:donhangArrayList) {
            if(donhang.trangthai==2||donhang.trangthai==5){
                donhangArrayList1.add(donhang);
            }
        }
        adapter=new DahuyAdapter(getContext(),donhangArrayList1);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerView.setAdapter(adapter);
    }
}