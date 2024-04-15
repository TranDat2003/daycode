package com.example.du_an_1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1.Dao.KhachhangDao;
import com.example.du_an_1.Model.Khachhang;

public class Doimatkhau extends AppCompatActivity {
    EditText edmkc,edmkm,edmkm2;
    Button btok,bthuy;
    KhachhangDao khachhangDao;
    Khachhang khachhang;
    ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment__doimk);
        edmkc=findViewById(R.id.editTextText28);
        edmkm=findViewById(R.id.editTextText29);
        edmkm2=findViewById(R.id.editTextText30);
        imageView=findViewById(R.id.imageView18);
        btok=findViewById(R.id.button31);
        bthuy=findViewById(R.id.button32);
        khachhangDao=new KhachhangDao(Doimatkhau.this);
        SharedPreferences preferences= getSharedPreferences("userfile", Context.MODE_PRIVATE);
        int y=preferences.getInt("userr",0);
        khachhang=khachhangDao.getid(String.valueOf(y));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edmkm.getText().length()==0||edmkm2.getText().length()==0||edmkc.getText().length()==0){
                    Toast.makeText(Doimatkhau.this, "Bạn ko được bỏ trống", Toast.LENGTH_SHORT).show();
                }else{
                    if(!edmkc.getText().toString().equals(khachhang.mk)){
                        Toast.makeText(Doimatkhau.this, "Mật khẩu bạn cung cấp sai", Toast.LENGTH_SHORT).show();
                    }else{
                        if(edmkm.getText().toString().equals(edmkm2.getText().toString())){
                            Khachhang khachhang1=new Khachhang();
                            khachhang1.mk=edmkm.getText().toString();
                            khachhang1.makh=y;
                            khachhangDao.doimk(khachhang1);
                            edmkc.setText("");
                            edmkm.setText("");
                            edmkm2.setText("");
                            Toast.makeText(Doimatkhau.this, "Bạn đã đổi mk thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Doimatkhau.this, "Nhập lại mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
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
    }
}
