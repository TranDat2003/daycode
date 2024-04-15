package com.example.du_an_1.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Chitietdonhang_Admin;
import com.example.du_an_1.Donhangchoxacnhan;
import com.example.du_an_1.ThongtinchitietDonhang;
import com.example.du_an_1.Dao.DonhangDao;
import com.example.du_an_1.Dao.GiohangDao;
import com.example.du_an_1.Dao.SanphamDao;
import com.example.du_an_1.Model.Donhang;
import com.example.du_an_1.Model.Giohang;
import com.example.du_an_1.Model.Sanpham;
import com.example.du_an_1.R;

import java.util.ArrayList;

public class ChoxacnhanAdapter extends RecyclerView.Adapter<ChoxacnhanAdapter.donhang>{
    Context context;
    ArrayList<Donhang> donhangArrayList;
    DonhangDao donhangDao;
    GiohangDao giohangDao;
    SanphamDao sanphamDao;

    public ChoxacnhanAdapter(Context context, ArrayList<Donhang> donhangArrayList) {
        this.context = context;
        this.donhangArrayList = donhangArrayList;
        donhangDao=new DonhangDao(context);
    }

    @NonNull
    @Override
    public donhang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_choxacnhan,null,false);

        return new donhang(view);
    }

    @Override
    public void onBindViewHolder(@NonNull donhang holder, int position) {
        holder.tvten.setText("Tên người đặt: "+donhangArrayList.get(position).ten);
        giohangDao=new GiohangDao(context);
        Giohang giohang=giohangDao.getid(String.valueOf(donhangArrayList.get(position).magiohang));
        sanphamDao=new SanphamDao(context);
        Sanpham sanpham=sanphamDao.getid(String.valueOf(giohang.masp));
        holder.tvma.setText("Mã sản phẩm: "+donhangArrayList.get(position).madonhang+"");
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
        holder.tvtensp.setText("Tên sản phẩm: "+sanpham.tensp);
        holder.tvtonggia.setText(String.valueOf("Tổng giá: "+donhangArrayList.get(position).gia*giohang.soluong));

        holder.imghuy.setOnClickListener(new View.OnClickListener() {
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
                donhangArrayList.remove(position);
//                            donhangArrayList.set(position, donhang);
                notifyDataSetChanged();
                Sanpham sanpham1=new Sanpham();
                sanpham1.soluong=sanpham.soluong+giohang.soluong;
                sanpham1.masp=sanpham.masp;
                sanphamDao.updatee(sanpham1);
            }
        });

        holder.imgok.setOnClickListener(new View.OnClickListener() {
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
                            donhangArrayList.remove(position);
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
            }
        }
    });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Chitietdonhang_Admin.class);
                int ma=donhangArrayList.get(position).madonhang;
                intent.putExtra("choxacnhan",ma);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return donhangArrayList.size();
    }

    class donhang extends RecyclerView.ViewHolder {
        TextView tvten,tvtonggia,tvtrangthai,tvtensp,tvma,tvngay;
        ImageButton imghuy,imgok;
        public donhang(@NonNull View itemView) {
            super(itemView);
            tvma=itemView.findViewById(R.id.textView70);
            tvten=itemView.findViewById(R.id.textView66);
            tvtensp=itemView.findViewById(R.id.textView88);
            tvtonggia=itemView.findViewById(R.id.textView89);
            tvtrangthai=itemView.findViewById(R.id.textView90);
            imghuy=itemView.findViewById(R.id.imageButton10);
            imgok=itemView.findViewById(R.id.imageButton11);


        }
    }

}
