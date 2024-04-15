package com.example.du_an_1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Dao.LoaisanphamDao;
import com.example.du_an_1.Fragment.Chitietsanpham;
import com.example.du_an_1.Model.Loaisanpham;
import com.example.du_an_1.Model.Sanpham;
import com.example.du_an_1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TrangchuAdapter extends RecyclerView.Adapter<TrangchuAdapter.trangchu>{
    Context context;
    ArrayList<Sanpham> sanphamArrayList;
    LoaisanphamDao loaidao;
    Sanpham sanpham;

    public TrangchuAdapter(Context context, ArrayList<Sanpham> sanphamArrayList) {
        this.context = context;
        this.sanphamArrayList = sanphamArrayList;
    }

    @NonNull
    @Override
    public trangchu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trangchu,null,false);
        return new trangchu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull trangchu holder, int position) {
        loaidao=new LoaisanphamDao(context);
        sanpham=sanphamArrayList.get(position);
       holder.tvname.setText("Tên sản phẩm: "+sanphamArrayList.get(position).tensp);
       holder.tvgia.setText("Giá: "+sanphamArrayList.get(position).gia+"");
       holder.tvma.setText("Mã sản phẩm: "+sanphamArrayList.get(position).masp+"");
       Loaisanpham loaisp=loaidao.getID(String.valueOf(sanpham.maloai));
        Picasso.get().load(sanphamArrayList.get(position).img).into(holder.imageView);
       holder. itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(context, Chitietsanpham.class);
               int r=sanphamArrayList.get(position).masp;
               intent.putExtra("am",r);
               context.startActivity(intent);

           }
       });
    }

    @Override
    public int getItemCount() {
        return sanphamArrayList.size();
    }

    class trangchu extends RecyclerView.ViewHolder {
        TextView tvname,tvgia,tvma;
        ImageView imageView;
        public trangchu(@NonNull View itemView) {
            super(itemView);
            tvname=itemView.findViewById(R.id.textView34);
            tvgia=itemView.findViewById(R.id.textView35);
            tvma=itemView.findViewById(R.id.textView49);
            imageView=itemView.findViewById(R.id.imageView6);

        }
    }
}
