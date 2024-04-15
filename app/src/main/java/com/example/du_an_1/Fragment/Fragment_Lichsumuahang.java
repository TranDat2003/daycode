package com.example.du_an_1.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.du_an_1.Adapter.TabuserAdapter;
import com.example.du_an_1.Model.Donhang;
import com.example.du_an_1.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Lichsumuahang#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Lichsumuahang extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Lichsumuahang() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_quanlydonhang.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Lichsumuahang newInstance(String param1, String param2) {
        Fragment_Lichsumuahang fragment = new Fragment_Lichsumuahang();
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

    ArrayList<Donhang> donhangArrayList;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    TabuserAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_quanlydonhang, container, false);
        tabLayout=view.findViewById(R.id.tablayout);
        viewPager2=view.findViewById(R.id.viewpage);
        adapter=new TabuserAdapter(getActivity());
        viewPager2.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int i) {
                if(i==0){
                    tab.setText("Đang xác nhận");
                } else if(i==1) {
                    tab.setText("Đã xác nhận");
                } else if(i==2) {
                    tab.setText("Đang giao");
                } else if(i==3) {
                    tab.setText("Đã giao");
                } else {
                    tab.setText("Đã hủy");
                }
            }
        }).attach();

        return view;
    }
}