package com.example.du_an_1.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1.Database.DPHelper;
import com.example.du_an_1.Model.Donhang;
import com.example.du_an_1.Model.Sanpham;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DonhangDao {
    Context context;
    DPHelper dpHelper;
    SQLiteDatabase sqLiteDatabase;
    SimpleDateFormat simpleDateFormat;

    public DonhangDao(Context context) {
        this.context = context;
        dpHelper=new DPHelper(context);
        sqLiteDatabase=dpHelper.getWritableDatabase();
    }

    public void insert(Donhang donhang){
        simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
        ContentValues values=new ContentValues();
        values.put("maGH",donhang.magiohang);
        values.put("TONGGIA",donhang.gia);
        values.put("NGAY",simpleDateFormat.format(donhang.ngay));
        values.put("SDT",donhang.sdt);
        values.put("PHUONGTHUC",donhang.phuongthuc);
        values.put("DIACHI",donhang.diachi);
        values.put("THANHTOAN",donhang.thanhtoan);
        values.put("TEN",donhang.ten);
        values.put("TRANGTHAI",donhang.trangthai);

        sqLiteDatabase.insert("DONHANG",null,values);
    }

    public void delete(Donhang donhang){
        simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
        ContentValues values=new ContentValues();
        values.put("maGH",donhang.magiohang);
        values.put("TONGGIA",donhang.gia);
        values.put("NGAY",simpleDateFormat.format(donhang.ngay));
        values.put("TRANGTHAI",donhang.trangthai);
        values.put("SDT",donhang.sdt);
        values.put("PHUONGTHUC",donhang.phuongthuc);
        String t=String.valueOf(donhang.madonhang);
        values.put("DIACHI",donhang.diachi);
        values.put("THANHTOAN",donhang.thanhtoan);
        values.put("TEN",donhang.ten);
        sqLiteDatabase.update("DONHANG",values,"maDH=?",new String[]{t});
    }

    public void update (Donhang donhang){
        simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
        ContentValues values=new ContentValues();
        values.put("maGH",donhang.magiohang);
        values.put("TONGGIA",donhang.gia);
        values.put("NGAY",simpleDateFormat.format(donhang.ngay));
        values.put("TRANGTHAI",donhang.trangthai);
        values.put("SDT",donhang.sdt);
        values.put("PHUONGTHUC",donhang.phuongthuc);
        String t=String.valueOf(donhang.madonhang);
        values.put("DIACHI",donhang.diachi);
        values.put("THANHTOAN",donhang.thanhtoan);
        values.put("TEN",donhang.ten);

        sqLiteDatabase.update("DONHANG",values,"maDH=?",new String[]{t});
    }

    public ArrayList<Donhang> getdata(String sql,String...AStrings){
        ArrayList<Donhang> donhangArrayList=new ArrayList<>();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        Cursor cursor=sqLiteDatabase.rawQuery(sql,AStrings);
        while (cursor.moveToNext()){
            Donhang donhang=new Donhang();
            donhang.madonhang=Integer.parseInt(cursor.getString(cursor.getColumnIndex("MADH")));
            donhang.magiohang=Integer.parseInt(cursor.getString(cursor.getColumnIndex("maGH")));
            donhang.gia=Integer.parseInt(cursor.getString(cursor.getColumnIndex("TONGGIA")));
            donhang.phuongthuc=Integer.parseInt(cursor.getString(cursor.getColumnIndex("PHUONGTHUC")));
            donhang.sdt=Integer.parseInt(cursor.getString(cursor.getColumnIndex("SDT")));
            donhang.diachi=cursor.getString(cursor.getColumnIndex("DIACHI"));
            donhang.ten=cursor.getString(cursor.getColumnIndex("TEN"));
            try {
                donhang.ngay=dateFormat.parse(cursor.getString(cursor.getColumnIndex("NGAY")));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            donhang.trangthai=Integer.parseInt(cursor.getString(cursor.getColumnIndex("TRANGTHAI")));
            donhang.thanhtoan=Integer.parseInt(cursor.getString(cursor.getColumnIndex("THANHTOAN")));
            donhangArrayList.add(donhang);
        }
        return  donhangArrayList;
    }

    public Donhang getid(String t){
        String tt="SELECT * FROM DONHANG WHERE maDH=?";
        ArrayList<Donhang> donhangArrayList=getdata(tt,t);
        return donhangArrayList.get(0);
    }

    public ArrayList<Donhang> tinhtong(){
        String tt="SELECT * FROM DONHANG WHERE TRANGTHAI=5";
        ArrayList<Donhang> donhangArrayList=getdata(tt);
        return donhangArrayList;
    }

    public ArrayList<Donhang> gettrangthai(String t,String tt){
        ArrayList<Donhang> donhangArrayList=new ArrayList<>();
        String sql="SELECT * FROM DONHANG WHERE maDH=? AND TRANGTHAI=?";
        Cursor cursor=sqLiteDatabase.rawQuery(sql,new String[]{t,tt});
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        while (cursor.moveToNext()){
            Donhang donhang=new Donhang();
            donhang.madonhang=Integer.parseInt(cursor.getString(cursor.getColumnIndex("MADH")));
            donhang.magiohang=Integer.parseInt(cursor.getString(cursor.getColumnIndex("maGH")));
            donhang.gia=Integer.parseInt(cursor.getString(cursor.getColumnIndex("TONGGIA")));
            donhang.phuongthuc=Integer.parseInt(cursor.getString(cursor.getColumnIndex("PHUONGTHUC")));
            donhang.sdt=Integer.parseInt(cursor.getString(cursor.getColumnIndex("SDT")));
            donhang.diachi=cursor.getString(cursor.getColumnIndex("DIACHI"));
            donhang.ten=cursor.getString(cursor.getColumnIndex("TEN"));
            try {
                donhang.ngay=dateFormat.parse(cursor.getString(cursor.getColumnIndex("NGAY")));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            donhang.trangthai=Integer.parseInt(cursor.getString(cursor.getColumnIndex("TRANGTHAI")));
            donhang.thanhtoan=Integer.parseInt(cursor.getString(cursor.getColumnIndex("THANHTOAN")));
            donhangArrayList.add(donhang);
        }

        return donhangArrayList;
    }

    public ArrayList<Donhang> getall(){
        String tt="SELECT * FROM DONHANG";
        ArrayList<Donhang> donhangArrayList=getdata(tt);
        return  donhangArrayList;
    }
}
