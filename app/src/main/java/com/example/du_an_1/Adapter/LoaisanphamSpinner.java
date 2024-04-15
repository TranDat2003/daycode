package com.example.du_an_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.du_an_1.Model.Loaisanpham;
import com.example.du_an_1.R;

import java.util.ArrayList;

public class LoaisanphamSpinner extends ArrayAdapter<Loaisanpham> {
    Context context;
    ArrayList<Loaisanpham> loaispArrayList;
    TextView tv;

    public LoaisanphamSpinner(@NonNull Context context, ArrayList<Loaisanpham> loaispArrayList) {
        super(context, 0,loaispArrayList);
        this.context = context;
        this.loaispArrayList = loaispArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.iten_loaisanpham,null);
        TextView tv=convertView.findViewById(R.id.textView28);
        Loaisanpham loaisp=this.getItem(position);
        if(loaisp!=null){
         tv.setText(loaisp.ma+". "+loaisp.ten);
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.iten_loaisanpham,null);
        tv=convertView.findViewById(R.id.textView28);
        Loaisanpham loaisp=this.getItem(position);
        if(loaisp!=null){
            tv.setText(loaisp.ma+". "+loaisp.ten);
        }
        return convertView;
    }
}
