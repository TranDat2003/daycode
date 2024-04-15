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
import com.example.du_an_1.Dao.DonhangDao;
import com.example.du_an_1.Dao.GiohangDao;
import com.example.du_an_1.Dao.SanphamDao;
import com.example.du_an_1.Model.Donhang;
import com.example.du_an_1.Model.Giohang;
import com.example.du_an_1.Model.Sanpham;
import com.example.du_an_1.R;
import com.example.du_an_1.ThongtinchitietDonhang;

import java.util.ArrayList;

public class XacnhanAdapter extends RecyclerView.Adapter<XacnhanAdapter.xacnhan> {
    Context context;
    ArrayList<Donhang> donhangArrayList;
    DonhangDao donhangDao;
    GiohangDao giohangDao;
    SanphamDao sanphamDao;

    public XacnhanAdapter(Context context, ArrayList<Donhang> donhangArrayList) {
        this.context = context;
        this.donhangArrayList = donhangArrayList;
        donhangDao=new DonhangDao(context);
    }

    @NonNull
    @Override
    public xacnhan onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daxacnhan,null,false);
        return new xacnhan(view);
    }

    @Override
    public void onBindViewHolder(@NonNull xacnhan holder, int position) {
        holder.tvten.setText("Tên người đặt: "+donhangArrayList.get(position).ten);
        giohangDao=new GiohangDao(context);
        Giohang giohang=giohangDao.getid(String.valueOf(donhangArrayList.get(position).magiohang));
        sanphamDao=new SanphamDao(context);
        Sanpham sanpham=sanphamDao.getid(String.valueOf(giohang.masp));
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
        holder.tvma.setText("Mã sản phẩm: "+donhangArrayList.get(position).madonhang+"");
        holder.tvtonggia.setText(String.valueOf("Tổng giá: "+donhangArrayList.get(position).gia*giohang.soluong));
        holder.imgok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Thông báo");
                builder.setMessage("Đơn hàng đang được giao ");
                builder.setIcon(R.drawable.notification);

                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Donhang donhang = new Donhang();
                        donhang.diachi = donhangArrayList.get(position).diachi;
                        donhang.sdt = donhangArrayList.get(position).sdt;
                        donhang.gia = donhangArrayList.get(position).gia;
                        donhang.thanhtoan = donhangArrayList.get(position).thanhtoan;
                        donhang.trangthai = 3;
                        donhang.ten=donhangArrayList.get(position).ten;
                        donhang.magiohang = donhangArrayList.get(position).magiohang;
                        donhang.phuongthuc = donhangArrayList.get(position).phuongthuc;
                        donhang.ngay = donhangArrayList.get(position).ngay;
                        donhang.madonhang = donhangArrayList.get(position).madonhang;
                        donhangDao.update(donhang);
                        notifyDataSetChanged();
                        donhangArrayList.remove(position);
//                            donhangArrayList.set(position, donhang);

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
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Chitietdonhang_Admin.class);

                intent.putExtra("choxacnhan",donhangArrayList.get(position).madonhang);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return donhangArrayList.size();
    }

    class xacnhan extends RecyclerView.ViewHolder {
        TextView tvten,tvtonggia,tvtensp,tvtrangthai,tvma;
        ImageButton imgok;
        public xacnhan(@NonNull View itemView) {
            super(itemView);
            imgok=itemView.findViewById(R.id.imageButton12);
            tvten=itemView.findViewById(R.id.textView91);
            tvma=itemView.findViewById(R.id.textView107);
            tvtonggia=itemView.findViewById(R.id.textView93);
            tvtensp=itemView.findViewById(R.id.textView92);
            tvtrangthai=itemView.findViewById(R.id.textView94);
        }
    }
}
