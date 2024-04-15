package com.example.du_an_1.sigin_sigup;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1.Dao.AdminDao;
import com.example.du_an_1.Fragment.Fragment_caidatcanhan;
import com.example.du_an_1.Model.Admin;
import com.example.du_an_1.Model.Khachhang;
import com.example.du_an_1.Quenmatkhau;
import com.example.du_an_1.R;
import com.example.du_an_1.Dao.KhachhangDao;
import com.example.du_an_1.MainActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class login extends AppCompatActivity {

    Button btdn,btdk;
    TextInputEditText eduser,edpass;
    KhachhangDao khachhangdao;
    CheckBox checkBox;
    String user,pass;
    ArrayList<Khachhang> khachhangArrayList;
    Boolean check;
    ArrayList<Khachhang> arrayList;
    ArrayList<Admin> adminArrayList;
    AdminDao adminDao;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap);
        btdn=findViewById(R.id.button);
        checkBox=findViewById(R.id.checkBox);
        btdk=findViewById(R.id.button2);
        eduser=findViewById(R.id.user);
        edpass=findViewById(R.id.pass);
        textView=findViewById(R.id.textView183);
        adminDao=new AdminDao(login.this);
        khachhangdao=new KhachhangDao(login.this);
        arrayList=khachhangdao.getall();

        SharedPreferences preferences=getSharedPreferences("userfile",MODE_PRIVATE);
        user=preferences.getString("username","");
        pass=preferences.getString("password","");
        check=preferences.getBoolean("remember",false);

        eduser.setText(user);
        edpass.setText(pass);
        checkBox.setChecked(check);
        if(arrayList.isEmpty()){
            eduser.setText("");
            edpass.setText("");
        }

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this, Quenmatkhau.class);
                startActivity(intent);
            }
        });
        btdk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this, dangky.class);
                startActivity(intent);
                finish();
            }
        });

        btdn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t=eduser.getText().toString();
                String tt=edpass.getText().toString();
                khachhangArrayList =khachhangdao.chechlogin(t,tt);
                adminArrayList=adminDao.getid(t,tt);

                if(eduser.getText().length()==0||edpass.getText().length()==0){
                    Toast.makeText(login.this, "Bạn ko đc để trống ", Toast.LENGTH_SHORT).show();
                }else{
                    if (khachhangArrayList.isEmpty() && !adminArrayList.isEmpty()) {
                        if (adminArrayList.get(0).username.equals(t) && adminArrayList.get(0).mk.equals(tt)) {
                            Intent intent1 = new Intent(login.this, Admin_MainActivity.class);
                            startActivity(intent1);
                            rememberuser(t, tt, checkBox.isChecked());
                        } else {
                            Toast.makeText(login.this, "mật khẩu hoặc tên đăng nhập sai ", Toast.LENGTH_SHORT).show();
                        }
                    } else if (!khachhangArrayList.isEmpty()) {
                        if(khachhangArrayList.get(0).trangthai==2){
                            AlertDialog.Builder builder=new AlertDialog.Builder(login.this);
                            builder.setIcon(R.drawable.caution);
                            builder.setTitle("Thông báo");
                            builder.setMessage("tài khoản của bạn đã bị khóa hãy liên hệ với chúng tôi để khôi phục lại tài khoản \n"+"SDT:0923457321");
                            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            builder.show();
                        }else if(khachhangArrayList.get(0).trangthai==1){
                            AlertDialog.Builder builder=new AlertDialog.Builder(login.this);
                            builder.setIcon(R.drawable.caution);
                            builder.setTitle("Thông báo");
                            builder.setMessage("Tài khoản đã bị người dùng xóa");
                            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                     dialogInterface.dismiss();
                                }
                            });
                            builder.show();
                        }else{
                        if (khachhangArrayList.get(0).phone.equals(t) || khachhangArrayList.get(0).mk.equals(tt)) {
                            Intent intent1 = new Intent(login.this, User_MainActivity.class);
                            int u = khachhangArrayList.get(0).makh;
                            intent1.putExtra("user", u);
                            startActivity(intent1);
                            Toast.makeText(login.this, ""+khachhangArrayList.get(0).makh+"", Toast.LENGTH_SHORT).show();
                            rememberuser(t, tt, checkBox.isChecked());
                        } else {
                            Toast.makeText(login.this, "mật khẩu hoặc tên đăng nhập sai ", Toast.LENGTH_SHORT).show();
                        }}
                    } else {
                        Toast.makeText(login.this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    }}
                }

        });
    }
    public void rememberuser(String u, String p, Boolean r){
        SharedPreferences sharedPreferences=getSharedPreferences("userfile",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        if(!r){
            editor.clear();
            if(khachhangArrayList.isEmpty()){

            }else{
                editor.putInt("userr",khachhangArrayList.get(0).makh);
            }

        }else{
            editor.putString("username",u);
            editor.putString("password",p);
            editor.putBoolean("check",r);
            if(khachhangArrayList.isEmpty()){

            }else{
                editor.putInt("userr",khachhangArrayList.get(0).makh);
            }
        }
        editor.commit();
    }
}