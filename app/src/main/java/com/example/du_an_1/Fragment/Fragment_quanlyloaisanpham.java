
package com.example.du_an_1.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.du_an_1.Adapter.LoaisanphamAdapter;
import com.example.du_an_1.Adapter.SanphamAdapter;
import com.example.du_an_1.Dao.LoaisanphamDao;
import com.example.du_an_1.Model.Loaisanpham;
import com.example.du_an_1.Model.Sanpham;
import com.example.du_an_1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_quanlyloaisanpham#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_quanlyloaisanpham extends Fragment {


    private String mParam1;
    private String mParam2;

    public Fragment_quanlyloaisanpham() {
        // Required empty public constructor
    }


    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    EditText edten,edma;
    Button btok,bthuy;
    LoaisanphamDao loaidao;
    LoaisanphamAdapter loaispAdapter;
    ArrayList<Loaisanpham> loaispArrayList;
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_loaisanpham, container, false);
        floatingActionButton=view.findViewById(R.id.flbloaisp);
        loaidao=new LoaisanphamDao(getContext());
        recyclerView=view.findViewById(R.id.recyloaisp);
        searchView=view.findViewById(R.id.searchb);
        loaispArrayList=loaidao.getall();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Loaisanpham> loaisanphamArrayList=new ArrayList<>();
                for (Loaisanpham loaisanpham :loaispArrayList) {
                    if(loaisanpham.ten.toLowerCase().contains(newText.toLowerCase())&&loaisanpham.trangthai==0){
                        loaisanphamArrayList.add(loaisanpham);
                    }
                    loaispAdapter=new LoaisanphamAdapter(getContext(),loaisanphamArrayList);
                    recyclerView.setAdapter(loaispAdapter);
                }
                return false;
            }
        });

        getview();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_them_loai_san_pham);
                edten=dialog.findViewById(R.id.edtenloaisp);
                edma=dialog.findViewById(R.id.editTextText13);
                edma.setText(String.valueOf(loaispArrayList.size()+1));
                btok=dialog.findViewById(R.id.button5);
                bthuy=dialog.findViewById(R.id.button6);
                btok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Loaisanpham loaisp=new Loaisanpham();
                        int i=0;
                        for (Loaisanpham loaisanpham:loaispArrayList) {
                            if(edten.getText().toString().toLowerCase().equals(loaisanpham.ten.toLowerCase())){
                                i++;
                            }
                        }
                        if(TextUtils.isEmpty(edten.getText().toString())){
                            Toast.makeText(getContext(), "bạn không được bỏ trống", Toast.LENGTH_SHORT).show();
                        }else{
                            if(i>0) {
                                Toast.makeText(getContext(), "loại sản phẩm này đã có", Toast.LENGTH_SHORT).show();
                            }else{
                                String t=edten.getText().toString();
                                loaisp.ten=t;
                                loaisp.trangthai=0;
                                loaidao.insert(loaisp);
                                getview();
                                dialog.dismiss();
                            }
                        }
                    }
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
        ArrayList<Loaisanpham> loaisanphamArrayList=new ArrayList<>();

        for (Loaisanpham loaisanpham:loaispArrayList) {
            if(loaisanpham.trangthai==0){
                loaisanphamArrayList.add(loaisanpham);
            }
        }
        loaispAdapter=new LoaisanphamAdapter(getContext(),loaisanphamArrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerView.setAdapter(loaispAdapter);
    }
}