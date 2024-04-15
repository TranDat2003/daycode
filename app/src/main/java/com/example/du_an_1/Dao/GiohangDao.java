package com.example.du_an_1.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.du_an_1.Database.DPHelper;
import com.example.du_an_1.Model.Giohang;
import com.example.du_an_1.Model.Khachhang;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GiohangDao {
    Context context;
    DPHelper dpHelper;
    SQLiteDatabase sqLiteDatabase;

    public GiohangDao(Context context) {
        this.context = context;
        dpHelper=new DPHelper(context);
        sqLiteDatabase=dpHelper.getWritableDatabase();
    }

    public void insert(Giohang giohang){
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        ContentValues values=new ContentValues();
        values.put("maKH",giohang.makh+"");
        values.put("maSP",giohang.masp+"");
        values.put("SOLUONG",giohang.soluong);
        values.put("TRANGTHAI",0);
         sqLiteDatabase.insert("GIOHANG", null, values);
    }

    public void update(Giohang giohang){
        ContentValues values=new ContentValues();
        values.put("maKH",giohang.makh+"");
        values.put("maSP",giohang.masp+"");
        values.put("SOLUONG",giohang.soluong+"");
        String t=String.valueOf(giohang.magh);
        values.put("TRANGTHAI",giohang.trangthai);
        sqLiteDatabase.update("GIOHANG",  values,"maGH=?",new String[]{t});
    }

    public ArrayList<Giohang> check(String t,String tt){
        ArrayList<Giohang> giohangArrayList=new ArrayList<>();
        String sql="SELECT *FROM GIOHANG WHERE maSP=? AND TRANGTHAI=?";
        Cursor cursor=sqLiteDatabase.rawQuery(sql,new String[]{t,tt});
        while (cursor.moveToNext()){
            Giohang giohang=new Giohang();
            giohang.masp=Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSP")));
            giohang.magh=Integer.parseInt(cursor.getString(cursor.getColumnIndex("maGH")));
            giohang.makh=Integer.parseInt(cursor.getString(cursor.getColumnIndex("maKH")));
            giohang.trangthai=Integer.parseInt(cursor.getString(cursor.getColumnIndex("TRANGTHAI")));
            giohang.soluong=Integer.parseInt(cursor.getString(cursor.getColumnIndex("SOLUONG")));
            giohangArrayList.add(giohang);
        }
        return giohangArrayList;
    }

    public void updatee(Giohang giohang){
        ContentValues values=new ContentValues();

        String t=String.valueOf(giohang.magh);
        values.put("TRANGTHAI",giohang.trangthai);
        sqLiteDatabase.update("GIOHANG",  values,"maGH=?",new String[]{t});
    }

    public void delete(String t){
        sqLiteDatabase.delete("GIOHANG","maGH=?",new String[]{t});
    }

    public ArrayList<Giohang> getall(){
        String t="select * from GIOHANG";
        return getdata(t);
    }

    public Giohang getid(String tt){
        String t="select * from GIOHANG where maGH=?";
        ArrayList<Giohang> giohangArrayList=getdata(t,tt);
        return giohangArrayList.get(0);
    }

    public Giohang getidd(String tt){
        String t="select * from GIOHANG where maSP=?";
        ArrayList<Giohang> giohangArrayList=getdata(t,tt);
        return giohangArrayList.get(0);
    }

    public ArrayList<Giohang> getdata(String sql, String...AStrings){
        ArrayList<Giohang> giohangArrayList=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.rawQuery(sql,AStrings);
        while (cursor.moveToNext()){
            Giohang giohang=new Giohang();
            giohang.makh=Integer.parseInt(cursor.getString(cursor.getColumnIndex("maKH")));
            giohang.magh=Integer.parseInt(cursor.getString(cursor.getColumnIndex("maGH")));
            giohang.masp=Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSP")));
            giohang.soluong=Integer.parseInt(cursor.getString(cursor.getColumnIndex("SOLUONG")));
            giohang.trangthai=Integer.parseInt(cursor.getString(cursor.getColumnIndex("TRANGTHAI")));
            giohangArrayList.add(giohang);
        }
        return giohangArrayList;
    }
}
