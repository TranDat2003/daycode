package com.example.du_an_1.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.du_an_1.Dao.KhachhangDao;
import com.example.du_an_1.Model.Khachhang;
import com.example.du_an_1.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_caidatcanhan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_caidatcanhan extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_caidatcanhan() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_caidatcanhan.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_caidatcanhan newInstance(String param1, String param2) {
        Fragment_caidatcanhan fragment = new Fragment_caidatcanhan();
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
    TextView tvma,tvten,tvsdt,tvdiachi,tvnamsinh;
    Button button;
    KhachhangDao khachhangDao;
    EditText edma,edten,edsdt,edaddress,edmk;
    Button btok,bthuy;
    int value;
    ArrayList<Khachhang> khachhangArrayList;
    Khachhang khachhang;
    public void updateUI(Khachhang khachhang) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        tvma.setText("Mã người dùng: " + khachhang.makh);
        tvten.setText("Tên người dùng " + khachhang.name);
        tvdiachi.setText("Địa chỉ: " + khachhang.address);
        tvsdt.setText("Số điện thoại: " + khachhang.phone);
        tvnamsinh.setText("Ngày sinh: "+dateFormat.format(khachhang.ngaysinh));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Lấy Bundle từ Fragment
//       SharedPreferences preferences= getContext().getSharedPreferences("userfilee",Context.MODE_PRIVATE);
//      int y= preferences.getInt("id",0);
        View view=inflater.inflate(R.layout.fragment_caidatcanhan, container, false);
        tvma=view.findViewById(R.id.textView151);
        tvten=view.findViewById(R.id.textView152);
        tvsdt=view.findViewById(R.id.textView153);
        tvdiachi=view.findViewById(R.id.textView154);
        tvnamsinh=view.findViewById(R.id.textView173);
        button=view.findViewById(R.id.button28);
        khachhangDao=new KhachhangDao(getContext());
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");

        SharedPreferences sharedPreferences= getContext().getSharedPreferences("userfile",Context.MODE_PRIVATE);
        value=sharedPreferences.getInt("userr",0);
        khachhang=khachhangDao.getid(String.valueOf(value));
        tvma.setText("Mã người dùngggg: "+value+"");
        tvten.setText("Tên người dùng "+khachhang.name);
        tvdiachi.setText("Địa chỉ: "+khachhang.address);
        tvsdt.setText("Số điện thoại: "+khachhang.phone);
        tvnamsinh.setText("bhvjv");
//        tvnamsinh.setText("Ngày sinh: "+dateFormat.format(khachhang.ngaysinh));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_thongtincanhan);
                edma=dialog.findViewById(R.id.editTextText24);
                edten=dialog.findViewById(R.id.editTextText25);
                edsdt=dialog.findViewById(R.id.editTextText26);
                edaddress=dialog.findViewById(R.id.editTextText27);
                btok=dialog.findViewById(R.id.button29);
                bthuy=dialog.findViewById(R.id.button30);
                edma.setText(khachhang.makh+"");
                edten.setText(khachhang.name);

                edsdt.setText(khachhang.phone);
                edaddress.setText(khachhang.address);
                btok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Khachhang khachhang1=new Khachhang();
                        khachhangArrayList=new ArrayList<>();
                        khachhang1.makh=Integer.parseInt(edma.getText().toString());
                        khachhang1.name=edten.getText().toString();
                        khachhang1.address=edaddress.getText().toString();
                        khachhang1.phone=edsdt.getText().toString();
                        khachhangDao.updatee(khachhang);
                        khachhangArrayList=khachhangDao.getall();
                        updateUI(khachhang1);
                        FragmentManager manager= getActivity().getSupportFragmentManager();
                        manager.beginTransaction().detach(Fragment_caidatcanhan.this).attach(Fragment_caidatcanhan.this).commit();
                        dialog.dismiss();
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
}