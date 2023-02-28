package com.example.beecar;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beecar.Database.MyDbHelper;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

public class SplashActivity extends AppCompatActivity {
    ImageView imageView;
    ShimmerTextView tv_title;
    Shimmer shimmer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


           tv_title = findViewById(R.id.tv_title_lg);




        ObjectAnimator animator = ObjectAnimator.ofFloat(tv_title,"translationY",-100f,0f);
        animator.setDuration(2000);
        animator.setRepeatCount(0);
        animator.start();
        Animation animationTitle = AnimationUtils.loadAnimation(this,R.anim.anim_title_splash_screen);
            tv_title.startAnimation(animationTitle);
            Typeface font = Typeface.createFromAsset(getAssets(),"fonts/FredokaOne-Regular.ttf");
            tv_title.setTypeface(font);


            shimmer = new Shimmer();
            shimmer.setDuration(2000);
            shimmer.start(tv_title);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        },4000);


    }
}