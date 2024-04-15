package com.example.du_an_1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Dao.DonhangDao;
import com.example.du_an_1.Dao.GiohangDao;
import com.example.du_an_1.Dao.SanphamDao;
import com.example.du_an_1.Donhangchoxacnhan;
import com.example.du_an_1.Model.Donhang;
import com.example.du_an_1.Model.Giohang;
import com.example.du_an_1.Model.Sanpham;
import com.example.du_an_1.R;
import com.example.du_an_1.ThongtinchitietDonhang;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class User_xacnhan_Adapter extends RecyclerView.Adapter<User_xacnhan_Adapter.xacnhan> {
    Context context;
    ArrayList<Donhang> donhangArrayList;
    DonhangDao donhangDao;
    GiohangDao giohangDao;
    SanphamDao sanphamDao;

    public User_xacnhan_Adapter(Context context, ArrayList<Donhang> donhangArrayList) {
        this.context = context;
        this.donhangArrayList = donhangArrayList;
        donhangDao=new DonhangDao(context);
    }
    @NonNull
    @Override
    public xacnhan onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_daxacnhan,null,false);
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
        holder.tvtonggia.setText(String.valueOf("Tổng giá: "+donhangArrayList.get(position).gia));
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        holder.tvngay.setText(dateFormat.format(donhangArrayList.get(position).ngay));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Donhangchoxacnhan.class);

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
        TextView tvma,tvten,tvtensp,tvtonggia,tvtrangthai,tvngay;
        public xacnhan(@NonNull View itemView) {
            super(itemView);
            tvma=itemView.findViewById(R.id.textView131);
            tvten=itemView.findViewById(R.id.textView132);
            tvtensp=itemView.findViewById(R.id.textView133);
            tvtonggia=itemView.findViewById(R.id.textView134);
            tvtrangthai=itemView.findViewById(R.id.textView135);
            tvngay=itemView.findViewById(R.id.textView182);
        }
    }
}
