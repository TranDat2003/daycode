package com.example.du_an_1.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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

public class User_Choxacnhan_Adapter extends RecyclerView.Adapter<User_Choxacnhan_Adapter.choxacnhan>{
    Context context;
    ArrayList<Donhang> donhangArrayList;
    DonhangDao donhangDao;
    GiohangDao giohangDao;
    SanphamDao sanphamDao;
    EditText edsdt,eddiachi,edsoluong,edten;
    Button btok,bthuy;

    public User_Choxacnhan_Adapter(Context context, ArrayList<Donhang> donhangArrayList) {
        this.context = context;
        this.donhangArrayList = donhangArrayList;
        donhangDao=new DonhangDao(context);
    }

    @NonNull
    @Override
    public choxacnhan onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_choxacnhan,null,false);
        return new choxacnhan(view);
    }

    @Override
    public void onBindViewHolder(@NonNull choxacnhan holder, int position) {
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

        holder.btxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                builder.setTitle("Thông báo");
                builder.setMessage("bạn có muốn xóa đơn hàng này ko");
                builder.setIcon(R.drawable.notification);
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
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

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
             builder.show();
            }
        });

        holder.btsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(view.getContext());
                dialog.setContentView(R.layout.diaolog_suadonhang);
                edsoluong=dialog.findViewById(R.id.editTextText20);
                edsdt=dialog.findViewById(R.id.editTextText22);
                eddiachi=dialog.findViewById(R.id.editTextText21);
                edten=dialog.findViewById(R.id.editTextText31);

                eddiachi.setText(donhangArrayList.get(position).diachi);
                edten.setText(donhangArrayList.get(position).ten);
                edsdt.setText(String.valueOf(donhangArrayList.get(position).sdt));
                edsoluong.setText(String.valueOf(giohang.soluong));
                btok=dialog.findViewById(R.id.button20);
                bthuy=dialog.findViewById(R.id.button21);
                btok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Donhang donhang=new Donhang();
                        donhang.diachi=eddiachi.getText().toString();
                        donhang.sdt=Integer.parseInt(edsdt.getText().toString());
                        donhang.gia=donhangArrayList.get(position).gia;
                        donhang.thanhtoan=donhangArrayList.get(position).thanhtoan;
                        donhang.trangthai=donhangArrayList.get(position).trangthai;
                        donhang.magiohang=donhangArrayList.get(position).magiohang;
                        donhang.ten=edten.getText().toString();
                        donhang.phuongthuc=donhangArrayList.get(position).phuongthuc;
                        donhang.ngay=donhangArrayList.get(position).ngay;
                        donhang.madonhang=donhangArrayList.get(position).madonhang;
                        donhangDao.update(donhang);


                        notifyDataSetChanged();
                        dialog.dismiss();
                        Toast.makeText(dialog.getContext(), "Đã sửa thành công", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return donhangArrayList.size();
    }

    class choxacnhan extends RecyclerView.ViewHolder {
        TextView tvma,tvten,tvtensp,tvtonggia,tvtrangthai,tvngay;
        ImageButton btxoa,btsua;
        public choxacnhan(@NonNull View itemView) {
            super(itemView);
            tvma=itemView.findViewById(R.id.textView122);
            tvten=itemView.findViewById(R.id.textView125);
            tvtensp=itemView.findViewById(R.id.textView128);
            tvtonggia=itemView.findViewById(R.id.textView129);
            tvtrangthai=itemView.findViewById(R.id.textView130);
            tvngay=itemView.findViewById(R.id.textView178);
            btsua=itemView.findViewById(R.id.imageButton16);
            btxoa=itemView.findViewById(R.id.imageButton14);
        }
    }
}
