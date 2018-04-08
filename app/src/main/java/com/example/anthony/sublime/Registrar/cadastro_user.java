package com.example.anthony.sublime.Registrar;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.anthony.sublime.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class cadastro_user extends AppCompatActivity {

    EditText nome_ed, sobrenome_ed, descricao_ed;
    FloatingActionButton fab;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_user);

        nome_ed = findViewById(R.id.nome_ed);
        sobrenome_ed = findViewById(R.id.sobrenome_ed);
        descricao_ed = findViewById(R.id.descricao_ed);


        fab = findViewById(R.id.fab_user);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = nome_ed.getText().toString();
                if (user != null) {
                    UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                            .setDisplayName(nome).build();

                    user.updateProfile(profileUpdate);
                    userData(sobrenome_ed.getText().toString(), descricao_ed.getText().toString(), nome_ed.getText().toString());

                    
                    Intent intent = new Intent(cadastro_user.this, profile_foto.class);
                    startActivity(intent);
                }
            }
        });

    }

    //Define os dados do usuario
    private void userData(String sobrenome, String descricao, String nome){
        user_gs user_gs = new user_gs(sobrenome, descricao, nome);
        ref.child("users").child(user.getUid()).setValue(user_gs);
    }
}
