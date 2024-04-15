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
import com.example.du_an_1.Fragment.Fragment_caidat;
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

public class User_MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Fragment fragmentt;
    NavigationView navigationView;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activyti_user);
        drawerLayout = findViewById(R.id.draweruser);
        navigationView = findViewById(R.id.naviuser);
        toolbar = findViewById(R.id.toolbaruser);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(User_MainActivity.this, drawerLayout, toolbar, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

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
               if (item.getItemId() == R.id.dangxuat) {
                    Intent intent=new Intent(User_MainActivity.this, login.class);
                    startActivity(intent);
                }if (item.getItemId() == R.id.canhan) {
                    fragmentt = new Fragment_caidat();
                    getfragment(fragmentt);
                    toolbar.setTitle("Cài đặt cá nhân");
                }
                return false;
            }
        });
    }

    public void getfragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fruser, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
