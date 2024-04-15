package com.example.du_an_1.Dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import com.example.du_an_1.Database.DPHelper;
import com.example.du_an_1.Model.Sanpham;
import com.example.du_an_1.Model.Top;

import java.util.ArrayList;

public class ThongkeDao {

DPHelper dpHelper;
Context context;

    public ThongkeDao(Context context) {
        this.context = context;
        dpHelper=new DPHelper(context);
    }

    public ArrayList<Top> getTop(){
        String sqlTop="SELECT SANPHAM.maSP, SANPHAM.tenSP, SUM(GIOHANG.SOLUONG) AS soLuongBanRa\n" +
                "FROM SANPHAM\n" +
                "JOIN GIOHANG ON SANPHAM.maSP = GIOHANG.maSP\n" +
                "JOIN DONHANG ON GIOHANG.maGH = DONHANG.maGH\n" +
                "WHERE DONHANG.TRANGTHAI = 4\n" +
                "GROUP BY SANPHAM.maSP, SANPHAM.tenSP\n" +
                "ORDER BY soLuongBanRa DESC\n" +
                "LIMIT 10;\n";
        ArrayList<Top> arrayList=new ArrayList();
        SanphamDao sanphamDao=new SanphamDao(context);
        Cursor cursor=dpHelper.getWritableDatabase().rawQuery(sqlTop,null);
        if(cursor != null && cursor.moveToFirst()) { // Kiểm tra cursor không null và có dữ liệu
            do {
                Top top=new Top();
                Sanpham sanpham=sanphamDao.getid(cursor.getString(cursor.getColumnIndex("maSP")));
                top.tensp=sanpham.tensp;
                top.soluong = cursor.getInt(cursor.getColumnIndex("soLuongBanRa")); // Sử dụng tên đúng của cột
                arrayList.add(top);
            } while (cursor.moveToNext());
            cursor.close(); // Đóng cursor sau khi sử dụng
        }
        return arrayList;
    }

    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay){
        String sqlDoanhThu="SELECT SUM(TONGGIA) as DOANHTHU FROM DONHANG WHERE NGAY BETWEEN ? AND ? AND TRANGTHAI = 4 ";
        ArrayList<Integer> arrayList=new ArrayList<>();
        Cursor cursor=dpHelper.getWritableDatabase().rawQuery(sqlDoanhThu,new String[]{tuNgay,denNgay});
        while (cursor.moveToNext()){
            try{
                arrayList.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("DOANHTHU"))));
            }catch (Exception e){
                arrayList.add(0);
            }
        }
        return arrayList.get(0);
    }
}
