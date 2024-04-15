package com.example.du_an_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Model.Giohang;
import com.example.du_an_1.R;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.test>{
    Context context;
    ArrayList<Giohang> giohangArrayList;

    public TestAdapter(Context context, ArrayList<Giohang> giohangArrayList) {
        this.context = context;
        this.giohangArrayList = giohangArrayList;
    }

    @NonNull
    @Override
    public test onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test,null);
        return new test(view);
    }

    @Override
    public void onBindViewHolder(@NonNull test holder, int position) {
          holder.tvkh.setText(String.valueOf("Mã khách hàng"+giohangArrayList.get(position).makh));
        holder.tvma.setText(String.valueOf("Mã giỏ hang"+giohangArrayList.get(position).magh));
        holder.tvsp.setText(String.valueOf("Mã sản phẩm"+giohangArrayList.get(position).masp));
    }

    @Override
    public int getItemCount() {
        return giohangArrayList.size();
    }

    class test extends RecyclerView.ViewHolder {
        TextView tvma,tvkh,tvsp;
        public test(@NonNull View itemView) {
            super(itemView);
            tvma=itemView.findViewById(R.id.textView67);
            tvkh=itemView.findViewById(R.id.textView68);
            tvsp=itemView.findViewById(R.id.textView69);
        }
    }
}
