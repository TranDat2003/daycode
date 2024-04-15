package com.example.du_an_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.du_an_1.Fragment.Fragment_Doimk;
import com.example.du_an_1.Fragment.Fragment_Top10;
import com.example.du_an_1.Fragment.Fragment_caidatcanhan;
import com.example.du_an_1.Fragment.Fragment_giohang;
import com.example.du_an_1.Fragment.Fragment_quanlydoanhthu;
import com.example.du_an_1.Fragment.Fragment_Lichsumuahang;
import com.example.du_an_1.Fragment.Fragment_quanlydonhang_Admin;
import com.example.du_an_1.Fragment.Fragment_quanlyloaisanpham;
import com.example.du_an_1.Fragment.Fragment_quanlynguoidung;
import com.example.du_an_1.Fragment.Fragment_quanlysanpham;
import com.example.du_an_1.Fragment.Fragment_trangchu;
import com.example.du_an_1.sigin_sigup.login;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    FrameLayout fragment;
    NavigationView navigationView;
    Toolbar toolbar;
    Fragment fragmentt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.draw);
        fragment = findViewById(R.id.fr);
        navigationView = findViewById(R.id.navi);
        toolbar = findViewById(R.id.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(toggle);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.sanpham) {
                    fragmentt = new Fragment_quanlysanpham();
                    getfragment(fragmentt);
                    toolbar.setTitle("Quản lý sản phẩm");
                }
                if (item.getItemId() == R.id.loaisp) {
                    fragmentt = new Fragment_quanlyloaisanpham();
                    getfragment(fragmentt);
                    toolbar.setTitle("Quản lý loại sản phẩm");
                }
                if (item.getItemId() == R.id.nguoidung) {
                    fragmentt = new Fragment_quanlynguoidung();
                    getfragment(fragmentt);
                    toolbar.setTitle("Quản lý người dùng");
                }
                if (item.getItemId() == R.id.trangchu) {
                    fragmentt = new Fragment_trangchu();
                    getfragment(fragmentt);
                    toolbar.setTitle("Trang chủ");
                } if (item.getItemId() == R.id.giohang) {
                    fragmentt = new Fragment_giohang();
                    getfragment(fragmentt);
                    toolbar.setTitle("Giỏ hàng");
                }if (item.getItemId() == R.id.donhang) {
                    fragmentt = new Fragment_Lichsumuahang();
                    getfragment(fragmentt);
                    toolbar.setTitle("Lịch sử mua hàng");
                }
               if (item.getItemId() == R.id.doanhth) {
                    fragmentt = new Fragment_quanlydoanhthu();
                    getfragment(fragmentt);
                    toolbar.setTitle("Doanh thu bán hàng");
                }if (item.getItemId() == R.id.donhangadmin) {
                    fragmentt = new Fragment_quanlydonhang_Admin();
                    getfragment(fragmentt);
                    toolbar.setTitle("Quản lý đơn hàng");
                }if (item.getItemId() == R.id.dangxuat) {
                    Intent intent=new Intent(MainActivity.this, login.class);
                    startActivity(intent);
                }if (item.getItemId() == R.id.topbanchay) {
                    fragmentt = new Fragment_Top10();
                    getfragment(fragmentt);
                    toolbar.setTitle("top 10 sản phẩm bán chạy nhất");
                }if (item.getItemId() == R.id.canhan) {
                    fragmentt = new Fragment_caidatcanhan();
                    getfragment(fragmentt);
                    toolbar.setTitle("Thông tin cá nhân");
                }if (item.getItemId() == R.id.doimk) {
                    fragmentt = new Fragment_Doimk();
                    getfragment(fragmentt);
                    toolbar.setTitle("Đổi mật khẩu");
                }
                return false;
            }
        });
    }

    public void getfragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fr, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
