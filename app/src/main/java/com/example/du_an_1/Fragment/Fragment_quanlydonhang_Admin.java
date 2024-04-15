package com.example.du_an_1.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_1.Adapter.DonhangAdapterAdmin_choxacnhan;
import com.example.du_an_1.Adapter.TabadminAdapter;
import com.example.du_an_1.Dao.DonhangDao;
import com.example.du_an_1.Model.Donhang;
import com.example.du_an_1.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_quanlydonhang_Admin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_quanlydonhang_Admin extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_quanlydonhang_Admin() {
        // Required empty public constructor
    }


    public static Fragment_quanlydonhang_Admin newInstance(String param1, String param2) {
        Fragment_quanlydonhang_Admin fragment = new Fragment_quanlydonhang_Admin();
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
    DonhangDao donhangDao;
    ArrayList<Donhang> donhangArrayList;
    DonhangAdapterAdmin_choxacnhan donhangAdapterAdmin;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_quanlydonhang__admin, container, false);
        tabLayout=view.findViewById(R.id.tab);
        viewPager2=view.findViewById(R.id.view);
        FragmentManager fragmentManager= getActivity().getSupportFragmentManager();
        TabadminAdapter tabAdapter=new TabadminAdapter(getActivity());
        viewPager2.setAdapter(tabAdapter);
       tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {
               viewPager2.setCurrentItem(tab.getPosition());
           }

           @Override
           public void onTabUnselected(TabLayout.Tab tab) {

           }

           @Override
           public void onTabReselected(TabLayout.Tab tab) {

           }
       });

       viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
           @Override
           public void onPageSelected(int position) {
               super.onPageSelected(position);
               tabLayout.getTabAt(position).select();
           }
       });
        return view;
    }
}