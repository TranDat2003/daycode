package com.example.du_an_1.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.du_an_1.Dao.KhachhangDao;
import com.example.du_an_1.Model.Khachhang;
import com.example.du_an_1.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Doimk#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Doimk extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Doimk() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Doimk.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Doimk newInstance(String param1, String param2) {
        Fragment_Doimk fragment = new Fragment_Doimk();
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
    EditText edmkc,edmkm,edmkm2;
    Button btok,bthuy;
    KhachhangDao khachhangDao;
    Khachhang khachhang;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment__doimk, container, false);
        edmkc=view.findViewById(R.id.editTextText28);
        edmkm=view.findViewById(R.id.editTextText29);
        edmkm2=view.findViewById(R.id.editTextText30);
        btok=view.findViewById(R.id.button31);
        bthuy=view.findViewById(R.id.button32);
        khachhangDao=new KhachhangDao(getContext());
        SharedPreferences preferences= getContext().getSharedPreferences("userfile", Context.MODE_PRIVATE);
        int y=preferences.getInt("userr",0);
        khachhang=khachhangDao.getid(String.valueOf(y));

        btok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edmkm.getText().length()==0||edmkm2.getText().length()==0||edmkc.getText().length()==0){
                    Toast.makeText(getContext(), "Bạn ko được bỏ trống", Toast.LENGTH_SHORT).show();
                }else{
                if(!edmkc.getText().toString().equals(khachhang.mk)){
                    Toast.makeText(getContext(), "Mật khẩu bạn cung cấp sai", Toast.LENGTH_SHORT).show();
                }else{
                 if(edmkm.getText().toString().equals(edmkm2.getText().toString())){
                     Khachhang khachhang1=new Khachhang();
                     khachhang1.mk=edmkm.getText().toString();
                     khachhang1.makh=y;
                     khachhangDao.doimk(khachhang1);
                     edmkc.setText("");
                     edmkm.setText("");
                     edmkm2.setText("");
                     Toast.makeText(getContext(), "Bạn đã đổi mk thành công", Toast.LENGTH_SHORT).show();
                 }else{
                     Toast.makeText(getContext(), "Nhập lại mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                 }
                }
            }}
        });

        bthuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edmkc.setText("");
                edmkm.setText("");
                edmkm2.setText("");
            }
        });

        return view;
    }
}