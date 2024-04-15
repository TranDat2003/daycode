package com.example.du_an_1.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1.Database.DPHelper;
import com.example.du_an_1.Model.Admin;
import com.example.du_an_1.Model.Khachhang;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AdminDao {
    Context context;
    DPHelper dpHelper;
    SQLiteDatabase sqLiteDatabase;

    public AdminDao(Context context) {
        this.context = context;
        dpHelper=new DPHelper(context);
        sqLiteDatabase=dpHelper.getWritableDatabase();
    }

    public void insert(Admin admin){
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        ContentValues values=new ContentValues();
        values.put("hoTen",admin.name);
        values.put("USERNAME",admin.username);
        values.put("soDT",admin.sdt);
        values.put("diaChi",admin.diachi);
        values.put("matKhau",admin.mk);
        values.put("Email",admin.email);
        sqLiteDatabase.insert("KHACHHANG",null,values);
    }

    public ArrayList<Admin> getid(String t, String tt){
        String sql="SELECT * FROM ADMIN WHERE USERNAME=? AND matKhau=?";
        ArrayList<Admin> adminArrayList=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.rawQuery(sql,new String[]{t,tt});
        while (cursor.moveToNext()){

            Admin admin=new Admin();
            admin.ma=Integer.parseInt(cursor.getString(cursor.getColumnIndex("maAD")));
            admin.name=cursor.getString(cursor.getColumnIndex("hoTen"));
            admin.diachi=cursor.getString(cursor.getColumnIndex("diaChi"));
            admin.sdt=cursor.getString(cursor.getColumnIndex("soDT"));
            admin.mk=cursor.getString(cursor.getColumnIndex("matKhau"));
            admin.email=cursor.getString(cursor.getColumnIndex("EMAIL"));
            admin.username=cursor.getString(cursor.getColumnIndex("USERNAME"));

            adminArrayList.add(admin);
        }
        return adminArrayList;
    }
}
