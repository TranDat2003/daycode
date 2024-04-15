package com.example.du_an_1.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Dao.DonhangDao;
import com.example.du_an_1.Dao.GiohangDao;
import com.example.du_an_1.Dao.KhachhangDao;
import com.example.du_an_1.Dao.SanphamDao;
import com.example.du_an_1.Model.Donhang;
import com.example.du_an_1.Model.Giohang;
import com.example.du_an_1.Model.Khachhang;
import com.example.du_an_1.Model.Sanpham;
import com.example.du_an_1.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DonhangAdapterAdmin_choxacnhan extends RecyclerView.Adapter<DonhangAdapterAdmin_choxacnhan.donhang>{
    Context context;
    ArrayList<Donhang> donhangArrayList;
    DonhangDao donhangDao;
    int o;

    public DonhangAdapterAdmin_choxacnhan(Context context, ArrayList<Donhang> donhangArrayList) {
        this.context = context;
        this.donhangArrayList = donhangArrayList;
        donhangDao=new DonhangDao(context);
    }

    @NonNull
    @Override
    public DonhangAdapterAdmin_choxacnhan.donhang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quanlydonhang_admin,null);
        return new donhang(view);
    }

    @Override
    public void onBindViewHolder(@NonNull donhang holder, int position) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        GiohangDao giohangDao=new GiohangDao(context);
        Giohang giohang=giohangDao.getid(String.valueOf(donhangArrayList.get(position).magiohang));
        SanphamDao sanphamDao=new SanphamDao(context);
        Sanpham sanpham=sanphamDao.getid(String.valueOf(giohang.masp));
        KhachhangDao khachhangDao=new KhachhangDao(context);
        Khachhang khachhang=khachhangDao.getid(String.valueOf(giohang.makh));
        holder.tvtensp.setText("Tên sản phẩm: "+sanpham.tensp);
        holder.tvsdt.setText("Số điện thoại: "+donhangArrayList.get(position).sdt+"");
        holder.tvname.setText("Tên khách hàng: "+donhangArrayList.get(position).ten);
        holder.tvsoluong.setText("số lượng đặt: "+giohang.soluong+"");
        holder.tvdiachi.setText("Địa chỉ giao hàng: "+donhangArrayList.get(position).diachi);
        if(donhangArrayList.get(position).phuongthuc==1){
            holder.tvphuongthuc.setText("Phương thức:Thanh toán sau khi nhận hàng");
        }else{
            holder.tvphuongthuc.setText("Phương thức: Đã thanh toán trực tuyến");
            donhangArrayList.get(position).thanhtoan=1;
        }
        if(donhangArrayList.get(position).trangthai==2){
            holder.tvtrangthai.setText("Trạng thái:đã hủy");
        }
        if(donhangArrayList.get(position).trangthai==0){
            holder.tvtrangthai.setText("Trạng thái:chưa xác nhận");
        }if(donhangArrayList.get(position).trangthai==1){
            holder.tvtrangthai.setText("Trạng thái: đã xác nhận");
        }if(donhangArrayList.get(position).trangthai==3){
            holder.tvtrangthai.setText("Trạng thái: đơn hàng đang được giao");
        }if(donhangArrayList.get(position).trangthai==4){
            holder.tvtrangthai.setText("Trạng thái: đã giao hàng thành công");
        }if(donhangArrayList.get(position).trangthai==5){
            holder.tvtrangthai.setText("Trạng thái: người đặt không nhận hàng");
        }
        if(donhangArrayList.get(position).thanhtoan==0){
            holder.tvthanhtoan.setText("chưa thanh toán");
        }if(donhangArrayList.get(position).thanhtoan==1){
            holder.tvthanhtoan.setText("đã thanh toán");
        }if(donhangArrayList.get(position).trangthai==5&&donhangArrayList.get(position).thanhtoan==1){
            holder.tvthanhtoan.setText("đã hoàn tiền");
        }
        holder.tvngay.setText("Ngày đặt: "+dateFormat.format(donhangArrayList.get(position).ngay));
        holder.tvtonggia.setText("Tổng giá: "+donhangArrayList.get(position).gia+"");

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Donhang donhang = new Donhang();
                donhang.diachi = donhangArrayList.get(position).diachi;
                donhang.sdt = donhangArrayList.get(position).sdt;
                donhang.gia = donhangArrayList.get(position).gia;
                donhang.thanhtoan = donhangArrayList.get(position).thanhtoan;
                donhang.trangthai = 2;
                donhang.ten=donhangArrayList.get(position).ten;
                donhang.magiohang = donhangArrayList.get(position).magiohang;
                donhang.phuongthuc = donhangArrayList.get(position).phuongthuc;
                donhang.ngay = donhangArrayList.get(position).ngay;
                donhang.madonhang = donhangArrayList.get(position).madonhang;
                donhangDao.update(donhang);
                donhangArrayList = donhangDao.getall();
//                            donhangArrayList.set(position, donhang);
                notifyDataSetChanged();
            }
        });
        holder.btok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(donhangArrayList.get(position).trangthai==0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có muốn xác nhận đơn hàng này ");
                    builder.setIcon(R.drawable.notification);

                    builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Donhang donhang = new Donhang();
                            donhang.diachi = donhangArrayList.get(position).diachi;
                            donhang.sdt = donhangArrayList.get(position).sdt;
                            donhang.gia = donhangArrayList.get(position).gia;
                            donhang.thanhtoan = donhangArrayList.get(position).thanhtoan;
                            donhang.trangthai = 1;
                            donhang.ten=donhangArrayList.get(position).ten;
                            donhang.magiohang = donhangArrayList.get(position).magiohang;
                            donhang.phuongthuc = donhangArrayList.get(position).phuongthuc;
                            donhang.ngay = donhangArrayList.get(position).ngay;
                            donhang.madonhang = donhangArrayList.get(position).madonhang;
                            donhangDao.update(donhang);
                            donhangArrayList = donhangDao.getall();
//                            donhangArrayList.set(position, donhang);
                            notifyDataSetChanged();

                        }
                    });

                    builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.show();
                }else{
                    Toast.makeText(context, "đơn hàng đã được xác nhận", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    @Override
    public int getItemCount() {
        return donhangArrayList.size();
    }

    class donhang extends RecyclerView.ViewHolder {
        TextView tvname,tvsdt,tvdiachi,tvsoluong,tvngay,tvtrangthai,tvtensp,tvtonggia,tvphuongthuc,tvthanhtoan;
        Button button,btok;
        public donhang(@NonNull View itemView) {
            super(itemView);
            tvname=itemView.findViewById(R.id.textView77);
            tvtensp=itemView.findViewById(R.id.textView78);
            tvngay=itemView.findViewById(R.id.textView79);
            tvsoluong=itemView.findViewById(R.id.textView80);
            tvdiachi=itemView.findViewById(R.id.textView81);
            tvsdt=itemView.findViewById(R.id.textView82);
            tvtonggia=itemView.findViewById(R.id.textView83);
            tvtrangthai=itemView.findViewById(R.id.textView84);
            tvphuongthuc=itemView.findViewById(R.id.textView86);
            tvthanhtoan=itemView.findViewById(R.id.textView87);
            button=itemView.findViewById(R.id.button22);
            btok=itemView.findViewById(R.id.button24);
        }
    }
}
