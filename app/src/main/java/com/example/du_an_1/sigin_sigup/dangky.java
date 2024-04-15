package com.example.du_an_1.sigin_sigup;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1.Dao.KhachhangDao;
import com.example.du_an_1.Model.Khachhang;
import com.example.du_an_1.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class dangky extends AppCompatActivity {
TextInputEditText eduser,edmk,edmk2,edname,eddiachi,ednamsinh;
Button btok;
TextView textView;
ImageView imageView;
KhachhangDao khachhangdao;
int o=0;
int p=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        eduser=findViewById(R.id.user);
        edmk=findViewById(R.id.edmk);
        edmk2=findViewById(R.id.edmk2);
        edname=findViewById(R.id.edname);
        textView=findViewById(R.id.dangnhap);
        imageView=findViewById(R.id.imageView11);
        eddiachi=findViewById(R.id.ediachi);
        ednamsinh=findViewById(R.id.namsinh);

        btok=findViewById(R.id.button3);

        khachhangdao=new KhachhangDao(dangky.this);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(dangky.this, login.class);
                startActivity(intent);
                finish();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(dangky.this, login.class);
                startActivity(intent);
                finish();
            }
        });
        btok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int u=0;
                SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
                if(edname.getText().length()==0||eddiachi.getText().length()==0||edmk.getText().length()==0||edmk2.getText().length()==0||eduser.getText().length()==0||ednamsinh.getText().length()==0){
                    Toast.makeText(dangky.this, "Bạn bắt buộc phải nhập đủ tất cả trường dữ liệu", Toast.LENGTH_SHORT).show();
                } else if(!eduser.getText().toString().startsWith("0") || eduser.getText().length() < 10 || eduser.getText().length() > 10 || !TextUtils.isDigitsOnly(eduser.getText().toString())){
                    Toast.makeText(dangky.this, "Số điện thoại phải là số và có 10 hoặc 11 chữ số và bắt đầu bằng số 0", Toast.LENGTH_SHORT).show();
                } else if(!edmk.getText().toString().equals(edmk2.getText().toString())) {
                    Toast.makeText(dangky.this, "Mật khẩu nhập lại không trùng khớp", Toast.LENGTH_SHORT).show();
                } else {
                    ArrayList<Khachhang> khachhangArrayList=khachhangdao.getall();
                    for (Khachhang khachhang: khachhangArrayList) {
                        if(eduser.getText().toString().equals(khachhang.phone)){
                            u=1;
                        }
                    }
                    if(u==0){
                        Khachhang khachhang=new Khachhang();
                        khachhang.name=edname.getText().toString();
                        khachhang.mk=edmk.getText().toString();
                        khachhang.phone=eduser.getText().toString();
                        khachhang.address=eddiachi.getText().toString();
                        try {
                            khachhang.ngaysinh=dateFormat.parse(ednamsinh.getText().toString());
                        } catch (ParseException e) {
//                            throw new RuntimeException(e);
                            Toast.makeText(dangky.this, "Ngày tháng năm không hợp lệ. Định dạng đúng là dd-MM-yyyy", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        khachhangdao.insert(khachhang);

                        ednamsinh.setText("");
                        eduser.setText("");
                        edmk.setText("");
                        edname.setText("");
                        eddiachi.setText("");
                        edmk2.setText("");
                        o++;
                        AlertDialog.Builder builder=new AlertDialog.Builder(dangky.this);
                        builder.setTitle("Thông báo");
                        builder.setMessage("Bạn đã đăng ký thành công");
                        builder.setIcon(R.drawable.notification);
                        builder.setPositiveButton("Đăng nhập ngay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent=new Intent(dangky.this, login.class);
                                startActivity(intent);
                            }
                        });

                        builder.setNegativeButton("Ở lại", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        builder.show();
                    } else {
                        Toast.makeText(dangky.this, "Số điện thoại đã được đăng ký", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}