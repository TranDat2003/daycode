package com.example.du_an_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Dao.DonhangDao;
import com.example.du_an_1.Dao.GiohangDao;
import com.example.du_an_1.Dao.ThongkeDao;
import com.example.du_an_1.Model.Donhang;
import com.example.du_an_1.Model.Giohang;
import com.example.du_an_1.Model.Top;
import com.example.du_an_1.R;

import java.util.ArrayList;

public class Top10Adapter extends RecyclerView.Adapter<Top10Adapter.top>{
    Context context;
    ArrayList<Top> topArrayList;
    ThongkeDao thongkeDao;
    DonhangDao donhangDao;
    GiohangDao giohangDao;
    int y;

    public Top10Adapter(Context context, ArrayList<Top> topArrayList) {
        this.context = context;
        this.topArrayList = topArrayList;
        thongkeDao=new ThongkeDao(context);
        donhangDao=new DonhangDao(context);
        giohangDao=new GiohangDao(context);
    }

    @NonNull
    @Override
    public top onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top10,null,false);
        return new top(view);
    }

    @Override
    public void onBindViewHolder(@NonNull top holder, int position) {
        ArrayList<Top> topArrayList1=thongkeDao.getTop();
        ArrayList<Donhang> donhangArrayList=donhangDao.tinhtong();

         holder.tvten.setText(topArrayList1.get(position).tensp);
         holder.tvstt.setText(position+1+"");
         holder.tvsoluong.setText(topArrayList1.get(position).soluong+"");
    }

    @Override
    public int getItemCount() {
        return topArrayList.size();
    }

    class top extends RecyclerView.ViewHolder {
        TextView tvten,tvsoluong,tvstt;
        public top(@NonNull View itemView) {
            super(itemView);
            tvstt=itemView.findViewById(R.id.textView);
            tvten=itemView.findViewById(R.id.textView123);
            tvsoluong=itemView.findViewById(R.id.textView124);
        }
    }
}
