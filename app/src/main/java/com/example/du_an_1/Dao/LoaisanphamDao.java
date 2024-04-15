package com.example.du_an_1.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.du_an_1.Database.DPHelper;
import com.example.du_an_1.Model.Loaisanpham;

import java.util.ArrayList;

public class LoaisanphamDao {

    Context context;
    SQLiteDatabase database;
    DPHelper dpHelper;

    public LoaisanphamDao(Context context) {
        this.context = context;
        dpHelper=new DPHelper(context);
        database=dpHelper.getWritableDatabase();
    }

    public void insert(Loaisanpham loaisp){
        ContentValues values=new ContentValues();
        values.put("tenLoai",loaisp.ten);
        values.put("TRANGTHAI",loaisp.trangthai);
        database.insert("LOAISANPHAM",null,values);
        Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
    }
    public void delete(String t){
        ContentValues values=new ContentValues();
        values.put("TRANGTHAI",1);
        database.update("LOAISANPHAM",values,"maLoai=?",new String[]{t});
    }

    public void update(Loaisanpham loaisp){
        ContentValues values=new ContentValues();
        values.put("tenLoai",loaisp.ten);
        String t=String.valueOf(loaisp.ma);
        database.update("LOAISANPHAM",values,"maLoai=?",new String[]{t});
    }

    public ArrayList<Loaisanpham> getall(){
        String t="select * from LOAISANPHAM";
        return getdata(t);
    }

    public Loaisanpham getID(String id){
        String sql="SELECT * FROM LOAISANPHAM WHERE maLoai=?";
        ArrayList<Loaisanpham> list=getdata(sql,id);
        return list.get(0);
    }
    public ArrayList<Loaisanpham> getdata(String sql, String...AStrings) {
        ArrayList<Loaisanpham> loaispArrayList = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql,AStrings);
        while (cursor.moveToNext()) {
            Loaisanpham loaisp = new Loaisanpham();
            loaisp.ma = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai")));
            loaisp.ten = cursor.getString(cursor.getColumnIndex("tenLoai"));
            loaisp.trangthai=Integer.parseInt(cursor.getString(cursor.getColumnIndex("TRANGTHAI")));
            loaispArrayList.add(loaisp);
        }
        return loaispArrayList;
    }


}
