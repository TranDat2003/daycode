package com.example.du_an_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1.Dao.DonhangDao;
import com.example.du_an_1.Dao.GiohangDao;
import com.example.du_an_1.Dao.KhachhangDao;
import com.example.du_an_1.Dao.SanphamDao;
import com.example.du_an_1.Model.Donhang;
import com.example.du_an_1.Model.Giohang;
import com.example.du_an_1.Model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;


public class ThongtinchitietDonhang extends AppCompatActivity {



    DonhangDao donhangDao;
    ImageView imageView,view;
    GiohangDao giohangDao;
    KhachhangDao khachhangDao;
    SanphamDao sanphamDao;

    TextView tvma,tvname,tvsdt,tvdiachi,tvsoluong,tvngay,tvtrangthai,tvtensp,tvtonggia,tvphuongthuc,tvthanhtoan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietdonhangdagiao);
        imageView=findViewById(R.id.img);
        Intent intent=getIntent();
        int y=intent.getIntExtra("choxacnhan",0);
        donhangDao=new DonhangDao(ThongtinchitietDonhang.this);
        giohangDao=new GiohangDao(ThongtinchitietDonhang.this);
        khachhangDao=new KhachhangDao(ThongtinchitietDonhang.this);
        sanphamDao=new SanphamDao(ThongtinchitietDonhang.this);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              finish();
            }
        });
        Donhang donhang=donhangDao.getid(String.valueOf(y));
        Giohang giohang=giohangDao.getid(String.valueOf(donhang.magiohang));
        Sanpham sanpham=sanphamDao.getid(String.valueOf(giohang.masp));
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");

        view=findViewById(R.id.imageView23);
        tvname=findViewById(R.id.textView111);
        tvma=findViewById(R.id.textView185);
        tvsdt=findViewById(R.id.textView112);
        tvdiachi=findViewById(R.id.textView113);
        tvtensp=findViewById(R.id.textView114);
        tvsoluong=findViewById(R.id.textView115);
        tvtonggia=findViewById(R.id.textView116);
        tvphuongthuc=findViewById(R.id.textView117);
        tvthanhtoan=findViewById(R.id.textView118);
        tvngay=findViewById(R.id.textView119);
        tvtrangthai=findViewById(R.id.textView120);

        Picasso.get().load(sanpham.img).into(view);
        tvname.setText("Tên người nhận: "+donhang.ten);
        tvma.setText(donhang.madonhang+"");
        tvsdt.setText("Số điện thoại: "+donhang.sdt);
        tvdiachi.setText("Địa chỉ nhận hàng: "+donhang.diachi);
        tvtensp.setText("Tên sản phẩm: "+sanpham.tensp);
        tvsoluong.setText("Số lượng sản phẩm: "+giohang.soluong);
        tvtonggia.setText("Tổng giá: "+String.valueOf(sanpham.gia*giohang.soluong));
        tvngay.setText("Ngày đặt hàng: "+dateFormat.format(donhang.ngay));
        if(donhang.phuongthuc==1){
            tvphuongthuc.setText("Phương thức:Thanh toán sau khi nhận hàng");
        }else{
            tvphuongthuc.setText("Phương thức: Đã thanh toán trực tuyến");
            donhang.thanhtoan=1;
        }
        if(donhang.trangthai==2){
            tvtrangthai.setText("Trạng thái:đã hủy");
        }
        if(donhang.trangthai==0){
            tvtrangthai.setText("Trạng thái:chưa xác nhận");
        }if(donhang.trangthai==1){
            tvtrangthai.setText("Trạng thái: đã xác nhận");
        }if(donhang.trangthai==3){
            tvtrangthai.setText("Trạng thái: đơn hàng đang được giao");
        }if(donhang.trangthai==4){
            tvtrangthai.setText("Trạng thái: đã giao hàng thành công");
        }if(donhang.trangthai==5){
            tvtrangthai.setText("Trạng thái: người đặt không nhận hàng");
        }
        if(donhang.thanhtoan==0){
            tvthanhtoan.setText("chưa thanh toán");
        }else{
            if(donhang.thanhtoan==1&&donhang.trangthai!=2){
                tvthanhtoan.setText("đã thanh toán");
            }else{
                if(donhang.trangthai==5&&donhang.thanhtoan==1){
                    tvthanhtoan.setText("đã thanh toán nhưng không nhận hàng");
            }else if(donhang.trangthai==2&&donhang.thanhtoan==1){
                    tvthanhtoan.setText("đã hoàn tiền");
                }
        }
        }}
    }

