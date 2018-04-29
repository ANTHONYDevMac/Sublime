package com.example.anthony.sublime;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.anthony.sublime.Registrar.Reg_Email;
import com.example.anthony.sublime.Registrar.profile_foto;

public class intro extends AppCompatActivity {

    Button btn_logar, btn_registrar;
    Animation fade_in, scale, fade_out;
    ImageView image_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        btn_logar = findViewById(R.id.btn_logar);
        btn_registrar = findViewById(R.id.btn_registrar);

        fade_in = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fade_out = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        image_logo = findViewById(R.id.image_logo);

        scale = AnimationUtils.loadAnimation(this, R.anim.scale);
        image_logo.setAnimation(scale);

        CountDownTimer countDownTimer = new CountDownTimer(2610, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                image_logo.setAnimation(fade_out);
                image_logo.setVisibility(View.INVISIBLE);
                btn_logar.setVisibility(View.VISIBLE);
                btn_logar.setAnimation(fade_in);
                btn_registrar.setVisibility(View.VISIBLE);
                btn_registrar.setAnimation(fade_in);
            }
        }.start();

        btn_logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(intro.this, login.class);
                startActivity(intent);
            }
        });

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(intro.this, Reg_Email.class);
                startActivity(intent);
            }
        });
    }
}
