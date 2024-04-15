package com.example.du_an_1.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Adapter.LoaisanphamSpinner;
import com.example.du_an_1.Adapter.SanphamAdapter;
import com.example.du_an_1.Adapter.TrangchuAdapter;
import com.example.du_an_1.Dao.LoaisanphamDao;
import com.example.du_an_1.Dao.SanphamDao;
import com.example.du_an_1.Model.Loaisanpham;
import com.example.du_an_1.Model.Sanpham;
import com.example.du_an_1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_quanlysanpham#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_quanlysanpham extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_quanlysanpham() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_quanlysanpham.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_quanlysanpham newInstance(String param1, String param2) {
        Fragment_quanlysanpham fragment = new Fragment_quanlysanpham();
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
    FloatingActionButton actionButton;
    EditText edname,edgia,edmota,edimg,edsoluong;
    Spinner spinner;
    Button btok,bthuy;
    LoaisanphamSpinner adapter;
    SanphamDao sanphamdao;
    SanphamAdapter sanphamAdapter;
    ArrayList<Sanpham> sanphamArrayList;
    int masp;
    ArrayList<Loaisanpham> loaispArrayList;
    SearchView searchView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_quanlysanpham, container, false);
        recyclerView=view.findViewById(R.id.recysanpham);
        actionButton=view.findViewById(R.id.flsanpham);
        searchView=view.findViewById(R.id.searcha);
        LoaisanphamDao loaidao=new LoaisanphamDao(getContext());
        sanphamdao=new SanphamDao(getContext());
        loaispArrayList=loaidao.getall();

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
                    sanphamAdapter=new SanphamAdapter(getContext(),sanphamArrayList1);
                    recyclerView.setAdapter(sanphamAdapter);
                }
                return false;
            }
        });

        ArrayList<Loaisanpham> loaisanphamArrayList=new ArrayList<>();
        for (Loaisanpham loaisanpham:loaispArrayList) {
            if(loaisanpham.trangthai==0){
                loaisanphamArrayList.add(loaisanpham);
            }

        }
        adapter=new LoaisanphamSpinner(getContext(),loaisanphamArrayList);
        getview();
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_themsanpham);

                edmota=dialog.findViewById(R.id.editTextText12);
                edname=dialog.findViewById(R.id.editTextText10);
                edgia=dialog.findViewById(R.id.editTextText11);
                edsoluong=dialog.findViewById(R.id.editTextText14);
                edimg=dialog.findViewById(R.id.editTextText32);
                spinner=dialog.findViewById(R.id.spinner2);
                spinner.setAdapter(adapter);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                      masp=loaispArrayList.get(i).ma;
                        Toast.makeText(getContext(), "Bạn đã chọn loại đồ uống "+loaispArrayList.get(i).ten, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                btok=dialog.findViewById(R.id.button11);
                bthuy=dialog.findViewById(R.id.button12);
                btok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int m=0;
                        if(edname.getText().length()==0||edgia.getText().length()==0||edmota.getText().length()==0||edsoluong.getText().length()==0||edimg.getText().length()==0){
                            Toast.makeText(getContext(), "bạn phải nhập đủ trường dữ liệu", Toast.LENGTH_SHORT).show();
                        }else{
                            if(TextUtils.isDigitsOnly(edgia.getText().toString())&&TextUtils.isDigitsOnly(edsoluong.getText().toString())){
                                Sanpham sanpham = new Sanpham();
                                sanphamArrayList = sanphamdao.getall();
                                for (Sanpham sanpham1:sanphamArrayList) {
                                    if(sanpham1.tensp.toLowerCase().equals(edname.getText().toString().toLowerCase())){
                                        m++;
                                        Toast.makeText(getContext(), "Tên Sản phẩm này đã tồn tại", Toast.LENGTH_SHORT).show();
                                    }
                                }if(m==0){
                                    sanpham.maloai = masp;
                                    sanpham.tensp = edname.getText().toString();
                                    sanpham.gia = Integer.parseInt(edgia.getText().toString());
                                    sanpham.mota = edmota.getText().toString();
                                    sanpham.img=edimg.getText().toString();
                                    sanpham.soluong = Integer.parseInt(edsoluong.getText().toString());
                                    sanphamdao.insert(sanpham);
                                    sanphamArrayList = sanphamdao.getall();
                                    sanphamAdapter = new SanphamAdapter(getContext(), sanphamArrayList);
                                    recyclerView.setAdapter(sanphamAdapter);
                                    dialog.dismiss();
                                }  if(sanphamArrayList.isEmpty()){
                                    Sanpham sanphamm=new Sanpham();
                                    sanphamm.maloai = masp;
                                    sanphamm.tensp = edname.getText().toString();
                                    sanphamm.gia = Integer.parseInt(edgia.getText().toString());
                                    sanphamm.mota = edmota.getText().toString();
                                    sanphamm.soluong = Integer.parseInt(edsoluong.getText().toString());
                                    sanphamdao.insert(sanpham);
                                    sanphamArrayList = sanphamdao.getall();
                                    sanphamAdapter = new SanphamAdapter(getContext(), sanphamArrayList);
                                    recyclerView.setAdapter(sanphamAdapter);
                                    dialog.dismiss();
                                }
                            }else{
                                    Toast.makeText(getContext(), "bạn phải nhập vào một số", Toast.LENGTH_SHORT).show();
                            }
                    }}
                });
                bthuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        return view;
    }
    public void getview(){
        sanphamdao=new SanphamDao(getContext());
        sanphamArrayList=sanphamdao.getall();
        ArrayList<Sanpham> sanphamArrayList1=new ArrayList<>();
        for (Sanpham sanpham:sanphamArrayList) {
            if(sanpham.trangthai==0){
                sanphamArrayList1.add(sanpham);
            }
        }
        sanphamAdapter=new SanphamAdapter(getContext(),sanphamArrayList1);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerView.setAdapter(sanphamAdapter);
    }
}