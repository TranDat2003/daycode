package com.example.du_an_1.Model;

import java.util.Date;

public class Donhang {
  public int madonhang;
  public int magiohang;
  public int gia;
  public int trangthai;
  public Date ngay;
  public String diachi;
  public int sdt;
  public int phuongthuc;
  public int thanhtoan;
  public String ten;


    public Donhang(String ten,int madonhang, int magiohang, int gia, int trangthai, Date ngay, String diachi, int sdt, int phuongthuc,int thanhtoan) {
        this.madonhang = madonhang;
        this.ten=ten;
        this.magiohang = magiohang;
        this.thanhtoan=thanhtoan;
        this.gia = gia;
        this.trangthai = trangthai;
        this.ngay = ngay;
        this.diachi = diachi;
        this.sdt = sdt;
        this.phuongthuc = phuongthuc;
    }

    public Donhang() {
    }
}
