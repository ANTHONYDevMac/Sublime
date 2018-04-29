package com.example.anthony.sublime.Registrar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.anthony.sublime.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.ContentValues.TAG;

public class Reg_Password extends AppCompatActivity {

    String email_extra;
    EditText ed_senha, ed_confirma;
    private boolean permission = false;
    FloatingActionButton fab_pass;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg__password);

        ed_senha = findViewById(R.id.pass_1);
        ed_confirma = findViewById(R.id.pass_2);

        fab_pass = findViewById(R.id.fab_pass);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();

        if (bd != null) {
            //Se houver um email vindo de um intent
            email_extra = bd.get("email_extra").toString();


            fab_pass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String senha = ed_senha.getText().toString();
                    String confirmacao = ed_confirma.getText().toString();

                    TextView textView_erro = findViewById(R.id.sub_title);

                    if (senha.toString().equals(confirmacao.toString())) {
                        permission = true;
                        criarUsuario(email_extra.toString(), confirmacao.toString());
                    } else {
                        textView_erro.setText("As senhas não se coincidem");
                    }

                }
            });
        }
    }

    //========================================================================
    //Firebase Modules

    public void criarUsuario(String email_text, String password_text) {

        final ProgressBar progressBar = findViewById(R.id.progress_create);

        progressBar.setIndeterminate(true);
        mAuth.createUserWithEmailAndPassword(email_text, password_text)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            progressBar.setIndeterminate(false);

                            Intent intent = new Intent(Reg_Password.this, cadastro_user.class);
                            startActivity(intent);
                        } else {

                        }

                    }
                });
    }
}
