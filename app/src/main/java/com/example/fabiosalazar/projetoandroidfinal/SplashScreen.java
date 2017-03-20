package com.example.fabiosalazar.projetoandroidfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    ImageView android;
    Animation animacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        android = (ImageView) findViewById(R.id.android);
        animacao = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacao);
        animacao.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation){

            }

            @Override
            public void onAnimationEnd(Animation animation){
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation){

            }
        });

        android.startAnimation(animacao);
    }
}
