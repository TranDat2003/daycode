package com.example.du_an_1.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1.Database.DPHelper;
import com.example.du_an_1.Model.Khachhang;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class KhachhangDao {
    Context context;
    DPHelper dpHelper;
    SQLiteDatabase sqLiteDatabase;

    public KhachhangDao(Context context) {
        this.context = context;
        dpHelper=new DPHelper(context);
        sqLiteDatabase=dpHelper.getWritableDatabase();
    }
    public void insert(Khachhang khachhang){
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        ContentValues values=new ContentValues();
        values.put("hoTen",khachhang.name);
        values.put("diaChi",khachhang.address);
        values.put("soDT",khachhang.phone);
        values.put("matKhau",khachhang.mk);
        values.put("TRANGTHAI",0);
        values.put("NAMSINH",dateFormat.format(khachhang.ngaysinh));
        sqLiteDatabase.insert("KHACHHANG",null,values);
    }
    public void update(Khachhang khachhang){
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        ContentValues values=new ContentValues();
        values.put("hoTen",khachhang.name);
        values.put("diaChi",khachhang.address);
        values.put("soDT",khachhang.phone);
        values.put("TRANGTHAI",khachhang.trangthai);
        values.put("NAMSINH",dateFormat.format(khachhang.ngaysinh));
        sqLiteDatabase.update("KHACHHANG",values,"maKH=?",new String[]{khachhang.makh+""});
    }

    public void doimk(Khachhang khachhang){
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        ContentValues values=new ContentValues();
        values.put("matKhau",khachhang.mk);
        sqLiteDatabase.update("KHACHHANG",values,"maKH=?",new String[]{khachhang.makh+""});
    }

    public void quenmk(Khachhang khachhang){
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        ContentValues values=new ContentValues();
        values.put("matKhau",khachhang.mk);
        sqLiteDatabase.update("KHACHHANG",values,"soDT=?",new String[]{khachhang.phone+""});
    }
    public void updatee(Khachhang khachhang){
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        ContentValues values=new ContentValues();
        values.put("hoTen",khachhang.name);
        values.put("diaChi",khachhang.address);
        values.put("soDT",khachhang.phone);
        values.put("NAMSINH",dateFormat.format(khachhang.ngaysinh));
        sqLiteDatabase.update("KHACHHANG",values,"maKH=?",new String[]{khachhang.makh+""});
    }
    public void delete(Khachhang khachhang){
        ContentValues values=new ContentValues();
        values.put("TRANGTHAI",khachhang.trangthai);
        sqLiteDatabase.update("KHACHHANG",values,"maKH=?",new String[]{khachhang.makh+""});
    }

    public ArrayList<Khachhang> getall(){
        String t="select * from KHACHHANG";
        return getdata(t);
    }

    public Khachhang getid(String tt){
        String t="SELECT * FROM KHACHHANG WHERE maKH=?";
        ArrayList<Khachhang> khachhangArrayList=getdata(t,tt);
        return khachhangArrayList.get(0);
    }
    public Khachhang getsdt(String tt){
        String t="SELECT * FROM KHACHHANG WHERE soDT=?";
        ArrayList<Khachhang> khachhangArrayList=getdata(t,tt);
        return  khachhangArrayList.get(0);
    }

    public ArrayList<Khachhang> getdata(String sql, String...AStrings){
        ArrayList<Khachhang> khachhangArrayList=new ArrayList<>();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        Cursor cursor=sqLiteDatabase.rawQuery(sql,AStrings);
        while (cursor.moveToNext()){

            Khachhang khachhang=new Khachhang();
            khachhang.makh=Integer.parseInt(cursor.getString(cursor.getColumnIndex("maKH")));
            khachhang.trangthai=Integer.parseInt(cursor.getString(cursor.getColumnIndex("TRANGTHAI")));
            khachhang.name=cursor.getString(cursor.getColumnIndex("hoTen"));
            khachhang.address=cursor.getString(cursor.getColumnIndex("diaChi"));
            khachhang.phone=cursor.getString(cursor.getColumnIndex("soDT"));
            khachhang.mk=cursor.getString(cursor.getColumnIndex("matKhau"));
            try {
                khachhang.ngaysinh=dateFormat.parse(cursor.getString(cursor.getColumnIndex("NAMSINH")));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            khachhangArrayList.add(khachhang);
        }
        return khachhangArrayList;
    }
    public ArrayList<Khachhang> chechlogin(String t,String tt){
//        String ttt="select * from KHACHHANG where soDT=? and matKhau=?";
//        ArrayList<Khachhang> khachhangArrayList=getdata(ttt,tt);
//        return  khachhangArrayList.get(0);
        ArrayList<Khachhang> khachhangArrayList=new ArrayList<>();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        Cursor cursor=sqLiteDatabase.rawQuery("select * from KHACHHANG where soDT=? and matKhau=?",new String[]{t,tt});
        while (cursor.moveToNext()){
            Khachhang khachhang=new Khachhang();
            khachhang.makh=Integer.parseInt(cursor.getString(cursor.getColumnIndex("maKH")));
            khachhang.name=cursor.getString(cursor.getColumnIndex("hoTen"));
            khachhang.address=cursor.getString(cursor.getColumnIndex("diaChi"));
            khachhang.phone=cursor.getString(cursor.getColumnIndex("soDT"));
            khachhang.mk=cursor.getString(cursor.getColumnIndex("matKhau"));
            try {
                khachhang.ngaysinh=dateFormat.parse(cursor.getString(cursor.getColumnIndex("NAMSINH")));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            khachhang.trangthai=Integer.parseInt(cursor.getString(cursor.getColumnIndex("TRANGTHAI")));
            khachhangArrayList.add(khachhang);
        }
        return  khachhangArrayList;
//            return cursor.getCount()>0;

    }
}
