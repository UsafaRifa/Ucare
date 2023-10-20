package com.example.ucare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class splash_screen extends AppCompatActivity {

    Animation anim;
    ImageView appName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        appName=findViewById(R.id.imageView2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        anim= AnimationUtils.loadAnimation(this,R.anim.zoom_in_anim);
        appName.setAnimation(anim);
        ConstraintLayout cl=findViewById(R.id.splash);
        AnimationDrawable animationDrawable=(AnimationDrawable) cl.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {

                Intent intent=new Intent(splash_screen.this,sign_in.class);
                startActivity(intent);
                finish();
            }
        },4000);

    }
}