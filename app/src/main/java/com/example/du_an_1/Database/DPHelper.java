package com.example.du_an_1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DPHelper extends SQLiteOpenHelper {
    public DPHelper(@Nullable Context context) {

        super(context, "bang", null, 46);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String t="CREATE TABLE KHACHHANG(maKH INTEGER PRIMARY KEY AUTOINCREMENT,hoTen TEXT NOT NULL,diaChi TEXT NOT NULL,soDT TEXT NOT NULL, matKhau TEXT NOT NULL,TRANGTHAI INTEGER NOT NULL,NAMSINH DATE NOT NULL)";
        sqLiteDatabase.execSQL(t);

        String tt="CREATE TABLE ADMIN(maAD INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT NOT NULL,hoTen TEXT NOT NULL,diaChi TEXT NOT NULL,soDT TEXT NOT NULL,matKhau TEXT NOT NULL ,EMAIL TEXT NOT NULL)";
        sqLiteDatabase.execSQL(tt);
        String tt1="INSERT INTO ADMIN(maAD,USERNAME,hoTen,diaChi,soDT,matKhau,EMAIL) VALUES (1,'admin01','NGUYEN VAN ADMIN','VIETNAM','0912345678','admin1','admin@gmail.com')";
        sqLiteDatabase.execSQL(tt1);

        String ttt="CREATE TABLE LOAISANPHAM(maLoai INTEGER PRIMARY KEY AUTOINCREMENT,tenLoai TEXT NOT NULL,TRANGTHAI INTEGER NOT NULL)";
        sqLiteDatabase.execSQL(ttt);
        String ttt1="INSERT INTO LOAISANPHAM(maLoai,tenLoai,TRANGTHAI) VALUES (1,'nước trái cây',0),(2,'nước rau củ',0),(3,'trà trái cây',0)";
        sqLiteDatabase.execSQL(ttt1);

        String tttt="CREATE TABLE SANPHAM(maSP INTEGER PRIMARY KEY AUTOINCREMENT,tenSP TEXT NOT NULL,giaSP INTEGER NOT NULL,thongTin TEXT NOT NULL,maLoai INTEGER REFERENCES LOAISANPHAM(maLoai),SOLUONG INTEGER NOT NULL,TRANGTHAI INTEGER NOT NULL,IMG TEXT NOT NULL)";
        sqLiteDatabase.execSQL(tttt);
        String insertDataQuery1 = "INSERT INTO SANPHAM (tenSP, giaSP, thongTin, maLoai, SOLUONG, TRANGTHAI, IMG) VALUES ('nước ép dâu', 15000, 'thơm ngon đẹp da tốt cho sức khỏe', 1, 20, 0, 'https://dautaydalat.vn/wp-content/uploads/2015/06/nuoc-cot-dau-tay-1.jpg')";
        sqLiteDatabase.execSQL(insertDataQuery1);

        String insertDataQuery2 = "INSERT INTO SANPHAM (tenSP, giaSP, thongTin, maLoai, SOLUONG, TRANGTHAI, IMG) VALUES ('nước cam ép', 10000, 'giàu vitamin C cải thiện làn da tăng cường hệ miễn dịch', 1, 30, 0, 'https://hutiuthanhdat.vn/timthumb.php?src=data/Product/nuoc-ep-cam-ca-rot.jpg&h=600&w=600&zc=1')";
        sqLiteDatabase.execSQL(insertDataQuery2);

        String insertDataQuery3 = "INSERT INTO SANPHAM (tenSP, giaSP, thongTin, maLoai, SOLUONG, TRANGTHAI, IMG) VALUES ('nước cần tây', 5000, 'trẻ hóa cơ thể giúp cải thiện giấc ngủ tăng cường hệ tim mạch là sản phẩm rất tốt cho sức khỏe', 2, 20, 0, 'https://bizweb.dktcdn.net/100/004/714/articles/nuoc-ep-can-tay.png?v=1650604627680')";
        sqLiteDatabase.execSQL(insertDataQuery3);

        String insertDataQuery4 = "INSERT INTO SANPHAM (tenSP, giaSP, thongTin, maLoai, SOLUONG, TRANGTHAI, IMG) VALUES ('trà đào cam sả', 12000, 'hỗ trợ giảm stress thư giãn tinh thần vị ngọt thanh mát mang lại cảm giác hứng khởi', 3, 20, 0, 'https://blog.onelife.vn/wp-content/uploads/2023/09/9e447449-2.png')";
        sqLiteDatabase.execSQL(insertDataQuery4);

        String insertDataQuery5 = "INSERT INTO SANPHAM (tenSP, giaSP, thongTin, maLoai, SOLUONG, TRANGTHAI, IMG) VALUES ('trà chanh', 10000, 'thức uống giải khát ngày hè cực kỳ hiệu quả', 3, 20, 0, 'https://taiducfood.com/wp-content/uploads/2022/05/19303-Tra-chanh-Lemon-ice-tea-taiducfood.com_.jpeg')";
        sqLiteDatabase.execSQL(insertDataQuery5);

        String ttttt="CREATE TABLE GIOHANG(maGH INTEGER PRIMARY KEY AUTOINCREMENT ,maKH INTEGER REFERENCES KHACHHANG(maKH),maSP INTEGER REFERENCES SANPHAM(maSP),SOLUONG INTEGER NOT NULL,TRANGTHAI INTEGER NOT NULL)";
        sqLiteDatabase.execSQL(ttttt);

        String tttttt="CREATE TABLE DONHANG(MADH INTEGER PRIMARY KEY AUTOINCREMENT,maGH INTEGER REFERENCES GIOHANG(maGH),TONGGIA INTEGER NOT NULL,NGAY DATE NOT NULL,TRANGTHAI INTEGER NOT NULL,SDT TEXT NOT  NULL,PHUONGTHUC INTEGER NOT NULL,DIACHI TEXT NOT NULL,THANHTOAN INTEGER NOT NULL,TEN TEXT NOT NULL)";
        sqLiteDatabase.execSQL(tttttt);

        String ttttttt="CREATE TABLE DANHGIA(MADG INTEGER PRIMARY KEY AUTOINCREMENT,maSP INTEGER REFERENCES SANPHAM(maSP),maKH INTEGER REFERENCES KHACHHANG(maKH),NOIDUNG TEXT NOT NULL,NGAYTAO DATE NOT NULL)";
        sqLiteDatabase.execSQL(ttttttt);

//        String tttttttt="CREATE TABLE VOCHER(MAVOCHER INTEGER PRIMARY KEY AUTOINCREMENT,NOIDUNG TEXT NOT NULL,NGAYTAO DATE NOT NULL)";
//        sqLiteDatabase.execSQL(tttttttt);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
           if(i!=i1){
              sqLiteDatabase.execSQL("DROP TABLE IF EXISTS KHACHHANG");
               sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ADMIN");
               sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LOAISANPHAM");
               sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SANPHAM");
               sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DONHANG");
               sqLiteDatabase.execSQL("DROP TABLE IF EXISTS GIOHANG");
               sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DANHGIA");
               onCreate(sqLiteDatabase);
           }
    }
}
