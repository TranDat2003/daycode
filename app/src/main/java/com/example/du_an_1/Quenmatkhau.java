package com.example.du_an_1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.du_an_1.Dao.KhachhangDao;
import com.example.du_an_1.Model.Khachhang;
import com.example.du_an_1.sigin_sigup.dangky;
import com.example.du_an_1.sigin_sigup.login;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class Quenmatkhau extends AppCompatActivity {
    TextInputEditText edsdt,edmk,edmk2;
    Button button;
    int value,x=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quenmatkhau);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        SharedPreferences sharedPreferences= getSharedPreferences("userfile", Context.MODE_PRIVATE);
//        value=sharedPreferences.getInt("userr",0);
        KhachhangDao khachhangDao=new KhachhangDao(Quenmatkhau.this);
        button=findViewById(R.id.button14);
        edsdt=findViewById(R.id.sdt);
        edmk=findViewById(R.id.mk);
        edmk2=findViewById(R.id.mk1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Khachhang> khachhangArrayList=khachhangDao.getall();
                for (Khachhang khachhang:khachhangArrayList) {
                    if(edsdt.getText().toString().equals(khachhang.phone)){
                       x=1;
                    }
                }if(edmk.getText().length()==0||edmk2.getText().length()==0||edsdt.getText().length()==0){
                    Toast.makeText(Quenmatkhau.this, "bạn không được để trống dữ liệu", Toast.LENGTH_SHORT).show();
                }else if(!edsdt.getText().toString().startsWith("0") || edsdt.getText().length() < 10 || edsdt.getText().length() > 10 || !TextUtils.isDigitsOnly(edsdt.getText().toString())){
                    Toast.makeText(Quenmatkhau.this, "SDT phải là số và bắt đầu là số 0 tối đa 10 ký tự", Toast.LENGTH_SHORT).show();
                }else if(x==0){
                    Toast.makeText(Quenmatkhau.this, "bạn đã nhập sai số điện thoại", Toast.LENGTH_SHORT).show();
                }if(x==1){
                    if(edmk.getText().toString().equals(edmk2.getText().toString())){
                        Khachhang khachhang1=new Khachhang();
                        khachhang1.phone=edsdt.getText().toString();
                        khachhang1.mk=edmk.getText().toString();
                        khachhangDao.quenmk(khachhang1);

                        edsdt.setText("");
                        edmk.setText("");
                        edmk2.setText("");

                        AlertDialog.Builder builder=new AlertDialog.Builder(Quenmatkhau.this);
                        builder.setTitle("Thông báo");
                        builder.setMessage("Bạn đã lấy lại mật khẩu thành công");
                        builder.setIcon(R.drawable.notification);
                        builder.setPositiveButton("Đăng nhập ngay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent=new Intent(Quenmatkhau.this, login.class);
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
                        x=0;
                    }else{
                        Toast.makeText(Quenmatkhau.this, "nhập lại mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}