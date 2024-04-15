package com.example.du_an_1.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_1.Adapter.PhotoAdapter;
import com.example.du_an_1.Adapter.TrangchuAdapter;
import com.example.du_an_1.Dao.SanphamDao;
import com.example.du_an_1.Model.Photo;
import com.example.du_an_1.Model.Sanpham;
import com.example.du_an_1.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_trangchu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_trangchu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_trangchu() {
        // Required empty public constructor
    }

    public static Fragment_trangchu newInstance(String param1, String param2) {
        Fragment_trangchu fragment = new Fragment_trangchu();
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
    SanphamDao sanphamdao;
   TrangchuAdapter trangchuadapter;
    ArrayList<Sanpham> sanphamArrayList;
    SearchView searchView;
    ViewPager viewPager;
    CircleIndicator indicator;
    PhotoAdapter adapter;
    ArrayList<Photo> photoArrayList;
    Timer timer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_trangchu, container, false);
        recyclerView=view.findViewById(R.id.trangchu);
        searchView=view.findViewById(R.id.search);
        sanphamdao=new SanphamDao(getContext());
        viewPager=view.findViewById(R.id.view);
        indicator=view.findViewById(R.id.circle);
        photoArrayList=getall();
        adapter=new PhotoAdapter(getContext(),photoArrayList);
        sanphamArrayList=sanphamdao.getall();
        ArrayList<Sanpham> sanphamArrayList1=new ArrayList<>();
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        autoslide();
        adapter.registerDataSetObserver(indicator.getDataSetObserver());
        for (Sanpham sanpham:sanphamArrayList) {
            if(sanpham.trangthai==0){
                sanphamArrayList1.add(sanpham);
            }
        }
        trangchuadapter =new TrangchuAdapter(getContext(),sanphamArrayList1);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerView.setAdapter(trangchuadapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Sanpham> sanphamArrayList1=new ArrayList<>();
                for (Sanpham sanpham :sanphamArrayList) {
                    if(sanpham.tensp.toLowerCase().contains(newText.toLowerCase())){
                        sanphamArrayList1.add(sanpham);
                    }
                    trangchuadapter=new TrangchuAdapter(getContext(),sanphamArrayList1);
                    recyclerView.setAdapter(trangchuadapter);
                }
                return false;
            }
        });
        return view;
    }

    public ArrayList<Photo> getall(){
        ArrayList<Photo> arrayList=new ArrayList<>();
        arrayList.add(new Photo(R.drawable.nuocepdau));
        arrayList.add(new Photo(R.drawable.cam2));
        arrayList.add(new Photo(R.drawable.cantay2));
        arrayList.add(new Photo(R.drawable.tradao2));
        arrayList.add(new Photo(R.drawable.trachanh2));
        return arrayList;
    }

    private void autoslide(){
        if(photoArrayList==null||photoArrayList.isEmpty()||viewPager==null){
            return;
        }

        if(timer==null){
            timer=new Timer();
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int curenttime=viewPager.getCurrentItem();
                        int total=photoArrayList.size()-1;
                        if(curenttime<total){
                            curenttime++;
                            viewPager.setCurrentItem(curenttime);
                        }else{
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        },500,3000);
    }
    public void onDestroy() {
        super.onDestroy();
        if(timer!=null){
            timer.cancel();
            timer=null;
        }
    }

}