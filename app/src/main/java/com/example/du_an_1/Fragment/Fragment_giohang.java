package com.example.du_an_1.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an_1.Adapter.GiohangAdapter;
import com.example.du_an_1.Dao.DonhangDao;
import com.example.du_an_1.Dao.GiohangDao;
import com.example.du_an_1.Dao.KhachhangDao;
import com.example.du_an_1.Dao.SanphamDao;
import com.example.du_an_1.Model.Donhang;
import com.example.du_an_1.Model.Giohang;
import com.example.du_an_1.Model.Khachhang;
import com.example.du_an_1.Model.Sanpham;
import com.example.du_an_1.R;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_giohang#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_giohang extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_giohang() {
        // Required empty public constructor
    }


    public static Fragment_giohang newInstance(String param1, String param2) {
        Fragment_giohang fragment = new Fragment_giohang();
        Bundle args = new Bundle();
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
    ArrayList<Giohang> giohangArrayList;
    GiohangAdapter giohangAdapter;
    Button button;
    EditText edsdt,edten,edadress;
    Button btok,bthuy;
    TextView ngay;
    SanphamDao sanphamDao;
    int y=0,value;
    KhachhangDao khachhangDao;
    ArrayList<Giohang> giohangArrayList1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_giohang, container, false);
        recyclerView=view.findViewById(R.id.recygiohang);
        button=view.findViewById(R.id.button23);
        sanphamDao=new SanphamDao(getContext());
        khachhangDao=new KhachhangDao(getContext());
        GiohangDao giohangDao=new GiohangDao(getContext());
        SharedPreferences sharedPreferences= getContext().getSharedPreferences("userfile", Context.MODE_PRIVATE);
        value=sharedPreferences.getInt("userr",0);
        getview();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(giohangArrayList1.isEmpty()){
                    Toast.makeText(getContext(), "giỏ hàng rỗng ", Toast.LENGTH_SHORT).show();
                }else{

                Dialog dialog=new Dialog(view.getContext());
                dialog.setContentView(R.layout.dialog_them_don_hang);
                SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");


                RadioGroup radioGroup = dialog.findViewById(R.id.radioGroup);
                RadioButton radioButton1 = dialog.findViewById(R.id.radioButton);
                RadioButton radioButton2 = dialog.findViewById(R.id.radioButton2);
                ngay=dialog.findViewById(R.id.textView62);

                edsdt=dialog.findViewById(R.id.editTextText16);
                edten=dialog.findViewById(R.id.editTextText17);
                edadress=dialog.findViewById(R.id.editTextText18);
                btok=dialog.findViewById(R.id.button18);
                bthuy=dialog.findViewById(R.id.button19);
                ngay.setText("Ngày"+dateFormat.format(new java.util.Date()));
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if(i==R.id.radioButton){
                            y=1;
                        }if(i==R.id.radioButton2){
                            y=2;
                        }
                    }
                });



                btok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for (Giohang giohang:giohangArrayList) {
                            if(giohang.trangthai==0){
                            Sanpham sanpham=sanphamDao.getid(String.valueOf(giohang.masp));
                        if(edten.getText().length()==0||edsdt.getText().length()==0||edadress.getText().length()==0||y==0){
                            Toast.makeText(getContext(), "bạn không được để trống", Toast.LENGTH_SHORT).show();
                        }else{
                            if(TextUtils.isDigitsOnly(edsdt.getText().toString())){
                                if(sanpham.soluong<=0){
                                    Toast.makeText(getContext(), "sản phẩm này đã hết", Toast.LENGTH_SHORT).show();
                                }else{
                                    Donhang donhang=new Donhang();
                                    DonhangDao donhangDao=new DonhangDao(getContext());
                                    donhang.ten=edten.getText().toString();
                                    donhang.diachi=edadress.getText().toString();
                                    donhang.sdt=Integer.parseInt(edsdt.getText().toString());
                                    donhang.ngay=new Date(new java.util.Date().getTime());
                                    donhang.phuongthuc=y;
                                    donhang.magiohang=giohang.magh;
                                    donhang.gia=sanpham.gia*giohang.soluong;
                                    donhang.trangthai=0;
                                    donhang.thanhtoan=0;
                                    donhangDao.insert(donhang);
                                    Toast.makeText(getContext(), "Bạn đã đặt hàng thành công", Toast.LENGTH_SHORT).show();

                                    Sanpham sanpham1=new Sanpham();
                                    sanpham1.soluong=sanpham.soluong-giohang.soluong;
                                    sanpham1.masp=giohang.masp;
                                    sanphamDao.updatee(sanpham1);

                                    Giohang giohang1=new Giohang();
                                    giohang1.trangthai=1;
                                    giohang1.magh=giohang.magh;
                                    giohangDao.updatee(giohang1);
                                    getview();

                                    giohangDao.check(String.valueOf(giohang.masp),String.valueOf(giohang.trangthai));
                                    dialog.dismiss();
                                }

                            }else{
                                Toast.makeText(getContext(), "bạn nhập sai định dạng ",Toast.LENGTH_LONG).show();
                            }
                        }
                    }}}
                });
                bthuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }}
        });
        return view;
    }
    public void getview(){
        GiohangDao giohangDao=new GiohangDao(getContext());
        giohangArrayList=giohangDao.getall();
        giohangArrayList1=new ArrayList<>();
        for (Giohang giohang:giohangArrayList) {
            Sanpham sanpham=sanphamDao.getid(String.valueOf(giohang.masp));
            Khachhang khachhang=khachhangDao.getid(String.valueOf(giohang.makh));
            if(giohang.trangthai==0&&sanpham.trangthai==0&&giohang.makh==value){
                giohangArrayList1.add(giohang);
            }
        }
        giohangAdapter=new GiohangAdapter(getContext(),giohangArrayList1);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerView.setAdapter(giohangAdapter);
    }
}