package com.baladika.bencoolen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    ImageView logo;
    TextView text;
    private static int TimeoutSPlash = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        changeColor(R.color.hijauSplash);

        logo = findViewById(R.id.logo);
        text = findViewById(R.id.textSPlash);

        Animation animLogo = AnimationUtils.loadAnimation(this, R.anim.smalltobig);
        logo.startAnimation(animLogo);
        Animation animText = AnimationUtils.loadAnimation(this, R.anim.bottom);
        text.startAnimation(animText);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(home);
                finish();
            }
        },TimeoutSPlash);
    }

    //merubah warna status bar
    public void changeColor(int resourseColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,resourseColor));
        }
    }
}