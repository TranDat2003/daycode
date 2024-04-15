package com.example.du_an_1.manhinhchao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1.R;
import com.example.du_an_1.sigin_sigup.login;

public class manhinhcha extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhchao);
        Handler handler=new Handler();
        textView=findViewById(R.id.anim);
        Animation animation= AnimationUtils.loadAnimation(manhinhcha.this,R.anim.anim);
        textView.startAnimation(animation);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(manhinhcha.this, login.class);
                startActivity(intent);

            }
        },3000);
    }
}


