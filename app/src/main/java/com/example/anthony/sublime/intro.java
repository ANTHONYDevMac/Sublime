package com.example.anthony.sublime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.anthony.sublime.Registrar.Reg_Email;
import com.example.anthony.sublime.Registrar.profile_foto;

public class intro extends AppCompatActivity {

    Button btn_logar, btn_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intro);
        btn_logar = findViewById(R.id.btn_logar);
        btn_registrar = findViewById(R.id.btn_registrar);

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
