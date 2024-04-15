package com.example.du_an_1;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.du_an_1.Dao.KhachhangDao;
import com.example.du_an_1.Fragment.Fragment_caidatcanhan;
import com.example.du_an_1.Model.Khachhang;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Caidatcanhan extends AppCompatActivity {
    TextView tvma,tvten,tvsdt,tvdiachi,tvnamsinh;
    Button button;
    KhachhangDao khachhangDao;
    EditText edma,edten,edsdt,edaddress,ednamsinh;
    Button btok,bthuy;
    int value;
    ArrayList<Khachhang> khachhangArrayList;
    Khachhang khachhang;
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_caidatcanhan);
        tvma=findViewById(R.id.textView151);
        tvten=findViewById(R.id.textView152);
        tvsdt=findViewById(R.id.textView153);
        tvdiachi=findViewById(R.id.textView154);
        tvnamsinh=findViewById(R.id.textView173);
        button=findViewById(R.id.button28);
        imageView=findViewById(R.id.imageView18);
        khachhangDao=new KhachhangDao(Caidatcanhan.this);
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");

        SharedPreferences sharedPreferences= getSharedPreferences("userfile", Context.MODE_PRIVATE);
        value=sharedPreferences.getInt("userr",0);
        khachhang=khachhangDao.getid(String.valueOf(value));
        tvma.setText("Mã người dùng: "+value+"");
        tvten.setText("Tên người dùng "+khachhang.name);
        tvdiachi.setText("Địa chỉ: "+khachhang.address);
        tvsdt.setText("Số điện thoại: "+khachhang.phone);
        tvnamsinh.setText("Ngày sinh: "+dateFormat.format(khachhang.ngaysinh));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(Caidatcanhan.this);
                SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
                dialog.setContentView(R.layout.dialog_thongtincanhan);
                edma=dialog.findViewById(R.id.editTextText24);
                edten=dialog.findViewById(R.id.editTextText25);
                edsdt=dialog.findViewById(R.id.editTextText26);
                edaddress=dialog.findViewById(R.id.editTextText27);
                ednamsinh=dialog.findViewById(R.id.editTextText3);
                btok=dialog.findViewById(R.id.button29);
                bthuy=dialog.findViewById(R.id.button30);
                edma.setText(khachhang.makh+"");
                edten.setText(khachhang.name);
                ednamsinh.setText(dateFormat.format(khachhang.ngaysinh));

                edsdt.setText(khachhang.phone);
                edaddress.setText(khachhang.address);
                btok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(edsdt.getText().length()==0||ednamsinh.getText().length()==0||edten.getText().length()==0||edaddress.getText().length()==0){
                            Toast.makeText(Caidatcanhan.this, "Bạn không được bỏ trống dữ liệu", Toast.LENGTH_SHORT).show();
                            return;
                        }else if(!edsdt.getText().toString().startsWith("0") || edsdt.getText().length() < 10 || edsdt.getText().length() > 10 || !TextUtils.isDigitsOnly(edsdt.getText().toString())){
                            Toast.makeText(Caidatcanhan.this, "SDT phải là số và có tối đa 10 ký tự", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Khachhang khachhang1=new Khachhang();
                        khachhangArrayList=new ArrayList<>();
                        khachhang1.makh=Integer.parseInt(edma.getText().toString());
                        khachhang1.name=edten.getText().toString();
                        khachhang1.address=edaddress.getText().toString();
                        khachhang1.phone=edsdt.getText().toString();
                        try {
                            khachhang1.ngaysinh=dateFormat.parse(ednamsinh.getText().toString());
                        } catch (ParseException e) {
                            Toast.makeText(Caidatcanhan.this, "Ngày tháng năm không hợp lệ. Định dạng đúng là dd-MM-yyyy", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        khachhangDao.updatee(khachhang1);

                        khachhangArrayList=khachhangDao.getall();
                        tvma.setText("Mã người dùng: " + khachhang1.makh);
                        tvten.setText("Tên người dùng " + khachhang1.name);
                        tvdiachi.setText("Địa chỉ: " + khachhang1.address);
                        tvsdt.setText("Số điện thoại: " + khachhang1.phone);
                        tvnamsinh.setText("Ngày sinh: " + dateFormat.format(khachhang1.ngaysinh));
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
    }
}
