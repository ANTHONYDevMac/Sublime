package com.example.anthony.sublime.Registrar;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;

import com.example.anthony.sublime.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Reg_Email extends AppCompatActivity {

    FloatingActionButton fab;
    String teste_extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_email);

        final CardView cardView = findViewById(R.id.card_input);

        final EditText editText = findViewById(R.id.ed_email);

        teste_extra = editText.getText().toString();

        fab = findViewById(R.id.fab_email);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();
                if (user == null){
                    finish();
                } else {
                    Intent intent = new Intent(Reg_Email.this, Reg_Password.class);
                    intent.putExtra("email_extra", editText.getText().toString());
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(Reg_Email.this).toBundle());
                }
            }
        });
    }
}
