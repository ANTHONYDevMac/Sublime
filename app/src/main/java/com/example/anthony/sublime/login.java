package com.example.anthony.sublime;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity {

    private FirebaseAuth mAuth;

    Button logar;
    EditText email_ed, senha_ed;
    TextView text_logado;

    String email, senha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("message");


        email_ed = findViewById(R.id.logar_email);
        senha_ed = findViewById(R.id.logar_senha);

        logar = findViewById(R.id.logar_button);

        logar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                email = email_ed.getText().toString();
                senha = senha_ed.getText().toString();

                logarUsuario(email, senha);
            }
        });

    }

    private void logarUsuario(final String email, String password){

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(login.this, tela_principal.class);
                        startActivity(intent);
                        finish();
                    } else {

                    }
                }
            });

    }

}
