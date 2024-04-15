package com.example.du_an_1.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1.Database.DPHelper;
import com.example.du_an_1.Model.Sanpham;

import java.util.ArrayList;

public class SanphamDao {
    Context context;
    DPHelper dpHelper;
    SQLiteDatabase sqLiteDatabase;

    public SanphamDao(Context context) {
        this.context = context;
        dpHelper=new DPHelper(context);
        sqLiteDatabase =dpHelper.getWritableDatabase();
    }

    public void insert(Sanpham sanpham){
        ContentValues values=new ContentValues();
        values.put("tenSP",sanpham.tensp);
        values.put("giaSP",sanpham.gia+"");
        values.put("thongtin",sanpham.mota+"");
        values.put("maLoai",sanpham.maloai+"");
        values.put("SOLUONG",sanpham.soluong+"");
        values.put("TRANGTHAI",0);
        values.put("IMG",sanpham.img);
        sqLiteDatabase.insert("SANPHAM",null,values);
    }

    public void delete(Sanpham sanpham){
        ContentValues values=new ContentValues();
        values.put("TRANGTHAI",sanpham.trangthai);
        String t=String.valueOf(sanpham.masp);
        sqLiteDatabase.update("SANPHAM",values,"maSP=?",new String[]{t});
    }

    public void update(Sanpham sanpham){
        ContentValues values=new ContentValues();
        values.put("maSP",sanpham.masp+"");
        values.put("tenSP",sanpham.tensp);
        values.put("giaSP",sanpham.gia+"");
        values.put("thongtin",sanpham.mota+"");
        values.put("maLoai",sanpham.maloai+"");
        values.put("SOLUONG",sanpham.soluong+"");
        values.put("TRANGTHAI",sanpham.trangthai);
        values.put("IMG",sanpham.img);
        String t=String.valueOf(sanpham.masp);
        sqLiteDatabase.update("SANPHAM",values,"maSP=?",new String[]{t});
    }

    public void updatee(Sanpham sanpham){
        ContentValues values=new ContentValues();
        values.put("SOLUONG",sanpham.soluong+"");
        String t=String.valueOf(sanpham.masp);
        sqLiteDatabase.update("SANPHAM",values,"maSP=?",new String[]{t});
    }

    public ArrayList<Sanpham> getall() {
        String t = "select * from SANPHAM";
        return getdata(t);
    }

    public ArrayList<Sanpham> getdata(String sql,String...AStrings) {
        ArrayList<Sanpham> loaispArrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,AStrings);
        while (cursor.moveToNext()) {
            Sanpham sanpham=new Sanpham();
            sanpham.masp = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSP")));
            sanpham.tensp = cursor.getString(cursor.getColumnIndex("tenSP"));
            sanpham.gia=Integer.parseInt(cursor.getString(cursor.getColumnIndex("giaSP")));
            sanpham.mota=cursor.getString(cursor.getColumnIndex("thongTin"));
            sanpham.maloai=Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai")));
            sanpham.soluong=Integer.parseInt(cursor.getString(cursor.getColumnIndex("SOLUONG")));
            sanpham.trangthai=Integer.parseInt(cursor.getString(cursor.getColumnIndex("TRANGTHAI")));
            sanpham.img=cursor.getString(cursor.getColumnIndex("IMG"));
            loaispArrayList.add(sanpham);
        }
        return loaispArrayList;
    }
    public Sanpham getid(String tt){
        String t="SELECT * FROM SANPHAM WHERE maSP=?";
        ArrayList<Sanpham> sanphamArrayList= getdata(t,tt);
        return sanphamArrayList.get(0);
    }
}
