package com.example.du_an_1.sigin_sigup;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.du_an_1.Fragment.Fragment_Doimk;
import com.example.du_an_1.Fragment.Fragment_Lichsumuahang;
import com.example.du_an_1.Fragment.Fragment_Top10;
import com.example.du_an_1.Fragment.Fragment_caidatcanhan;
import com.example.du_an_1.Fragment.Fragment_giohang;
import com.example.du_an_1.Fragment.Fragment_quanlydoanhthu;
import com.example.du_an_1.Fragment.Fragment_quanlydonhang_Admin;
import com.example.du_an_1.Fragment.Fragment_quanlyloaisanpham;
import com.example.du_an_1.Fragment.Fragment_quanlynguoidung;
import com.example.du_an_1.Fragment.Fragment_quanlysanpham;
import com.example.du_an_1.Fragment.Fragment_trangchu;
import com.example.du_an_1.MainActivity;
import com.example.du_an_1.R;
import com.google.android.material.navigation.NavigationView;

public class Admin_MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Fragment fragmentt;
    NavigationView navigationView;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activyti_admin);
        drawerLayout = findViewById(R.id.draweradmin);
        navigationView = findViewById(R.id.naviadmin);
        toolbar = findViewById(R.id.toolbaradmin);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(Admin_MainActivity.this, drawerLayout, toolbar, R.string.Open, R.string.Close);
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

                if (item.getItemId() == R.id.doanhth) {
                    fragmentt = new Fragment_quanlydoanhthu();
                    getfragment(fragmentt);
                    toolbar.setTitle("Doanh thu bán hàng");
                }if (item.getItemId() == R.id.donhangadmin) {
                    fragmentt = new Fragment_quanlydonhang_Admin();
                    getfragment(fragmentt);
                    toolbar.setTitle("Quản lý đơn hàng");
                }if (item.getItemId() == R.id.dangxuat) {
                    Intent intent=new Intent(Admin_MainActivity.this, login.class);
                    startActivity(intent);
                }if (item.getItemId() == R.id.topbanchay) {
                    fragmentt = new Fragment_Top10();
                    getfragment(fragmentt);
                    toolbar.setTitle("Top bán chạy");
                }
                return false;
            }
        });
    }
    public void getfragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fradmin, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
