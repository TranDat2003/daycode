package com.example.du_an_1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Chitietdonhang_Admin;
import com.example.du_an_1.Dao.DonhangDao;
import com.example.du_an_1.Dao.GiohangDao;
import com.example.du_an_1.Dao.SanphamDao;
import com.example.du_an_1.Giaohangthanhcong_Admin;
import com.example.du_an_1.Model.Donhang;
import com.example.du_an_1.Model.Giohang;
import com.example.du_an_1.Model.Sanpham;
import com.example.du_an_1.R;
import com.example.du_an_1.ThongtinchitietDonhang;

import java.util.ArrayList;

public class DagiaoAdapter extends RecyclerView.Adapter<DagiaoAdapter.dagiao>{
    Context context;
    ArrayList<Donhang> donhangArrayList;
    DonhangDao donhangDao;
    GiohangDao giohangDao;
    SanphamDao sanphamDao;

    public DagiaoAdapter(Context context, ArrayList<Donhang> donhangArrayList) {
        this.context = context;
        this.donhangArrayList = donhangArrayList;
        donhangDao=new DonhangDao(context);
    }

    @NonNull
    @Override
    public dagiao onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dagiao,null);
        return new dagiao(view);
    }

    @Override
    public void onBindViewHolder(@NonNull dagiao holder, int position) {
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
        holder.tvma.setText("Mã đơn hàng: "+donhangArrayList.get(position).madonhang+"");
        holder.tvtonggia.setText(String.valueOf("Tổng giá: "+donhangArrayList.get(position).gia));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Giaohangthanhcong_Admin.class);

                intent.putExtra("choxacnhan",donhangArrayList.get(position).madonhang);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return donhangArrayList.size();
    }

    class dagiao extends RecyclerView.ViewHolder {
        TextView tvten,tvtensp,tvtonggia,tvtrangthai,tvma;
        public dagiao(@NonNull View itemView) {
            super(itemView);
            tvma=itemView.findViewById(R.id.textView109);
            tvten=itemView.findViewById(R.id.textView99);
            tvtensp=itemView.findViewById(R.id.textView100);
            tvtonggia=itemView.findViewById(R.id.textView101);
            tvtrangthai=itemView.findViewById(R.id.textView102);

        }
    }
}
