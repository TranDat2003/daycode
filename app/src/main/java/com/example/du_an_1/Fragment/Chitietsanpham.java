package com.example.du_an_1.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1.Dao.GiohangDao;
import com.example.du_an_1.Dao.LoaisanphamDao;
import com.example.du_an_1.Dao.SanphamDao;
import com.example.du_an_1.Model.Giohang;
import com.example.du_an_1.Model.Loaisanpham;
import com.example.du_an_1.Model.Sanpham;
import com.example.du_an_1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Chitietsanpham extends AppCompatActivity {
    TextView tvten,tvloai,tvgia,tvsoluong,tvmota,tvma;
    Button btmua,bthuy;
    SanphamDao sanphamdao;
    ArrayList<Sanpham> sanphamArrayList;
    ArrayList<Giohang> giohangArrayList;
    GiohangDao giohangDao;
    int r,g=0,e=0;
    int t;
    ImageView imageView,viewm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_chitietsanpham);
        sanphamdao=new SanphamDao(Chitietsanpham.this);
        Intent intent=getIntent();
        t=intent.getIntExtra("am",0);
        Sanpham sanpham=sanphamdao.getid(String.valueOf(t));
        imageView=findViewById(R.id.imgg);
        SharedPreferences preferences= getSharedPreferences("userfile",MODE_PRIVATE);
        r=preferences.getInt("userr",0);
        sanphamArrayList=sanphamdao.getall();
        LoaisanphamDao loaisanphamDao=new LoaisanphamDao(Chitietsanpham.this);
        Loaisanpham loaisanpham=loaisanphamDao.getID(String.valueOf(sanpham.maloai));
        viewm=findViewById(R.id.imageView7);
        tvten=findViewById(R.id.textView36);
        tvloai=findViewById(R.id.textView37);
        tvgia=findViewById(R.id.textView40);
        tvsoluong=findViewById(R.id.textView44);
        tvmota=findViewById(R.id.textView43);
        tvma=findViewById(R.id.textView48);
        giohangDao=new GiohangDao(Chitietsanpham.this);
        btmua=findViewById(R.id.button13);

        Toast.makeText(Chitietsanpham.this, ""+r+"", Toast.LENGTH_SHORT).show();
        tvten.setText("Tên sản phẩm: "+sanpham.tensp);
        tvgia.setText("Gia: "+sanpham.gia+"");
        tvmota.setText("Giới thiệu về sản phẩm :"+sanpham.mota+"");
        tvloai.setText("Loại sản phẩm: "+loaisanpham.ten);
        tvsoluong.setText("Số lượng: "+sanpham.soluong+"");
        tvma.setText("Mã sản phảm"+sanpham.masp+"");
        Picasso.get().load(sanpham.img).into(viewm);

       imageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               finish();
           }
       });

        btmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n=0;
                giohangArrayList=giohangDao.getall();
                Sanpham sanpham1=sanphamdao.getid(String.valueOf(t));
                for (Giohang giohang:giohangArrayList) {
                    if(giohang.masp!=t){
                        e=0;
                    }else{
                        if(giohang.masp==t&&giohang.trangthai==0){
                            e++;
                            g=giohang.soluong;
                        }
                    }
                }

                if(giohangArrayList.isEmpty()||e==0&&sanpham1.soluong>0){
                    Giohang giohangg=new Giohang();
                    GiohangDao giohangDao=new GiohangDao(Chitietsanpham.this);
                    giohangg.makh=r;
                    giohangg.trangthai=0;
                    giohangg.masp=t;
                    giohangg.soluong=1;
                    giohangDao.insert(giohangg);
                    Toast.makeText(Chitietsanpham.this, "Bạn đã thêm thành công insert", Toast.LENGTH_SHORT).show();
                }else {
                    n = g + 1;
                    if (sanpham1.soluong >= n) {
                        Giohang giohangg = new Giohang();
                        GiohangDao giohangDao = new GiohangDao(Chitietsanpham.this);
                        ArrayList<Giohang> giohangArrayList1=  giohangDao.check(String.valueOf(t),String.valueOf(0));
                        giohangg.makh = giohangArrayList1.get(0).makh;
                        giohangg.masp = giohangArrayList1.get(0).masp;
                        giohangg.soluong = n;
                        giohangg.trangthai=0;
                        giohangg.magh=giohangArrayList1.get(0).magh;
                        giohangDao.update(giohangg);

                        Toast.makeText(Chitietsanpham.this, "Bạn đã thêm thành công update", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Chitietsanpham.this, "Bạn đã đạt đến số lượng tối đa" + sanpham1.soluong + "", Toast.LENGTH_SHORT).show();
                    }
                }
                }

    });
    }}
