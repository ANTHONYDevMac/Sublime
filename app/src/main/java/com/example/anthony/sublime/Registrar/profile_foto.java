package com.example.anthony.sublime.Registrar;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.Toast;
import com.example.anthony.sublime.R;
import com.example.anthony.sublime.login;
import com.example.anthony.sublime.tela_principal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import de.hdodenhof.circleimageview.CircleImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class profile_foto extends AppCompatActivity {

    public final static int RESULT_SELECT_IMAGE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final String TAG = "GaleryUtil";
    private StorageReference mStorageRef;

    String mCurentPhotoPath;
    File photoFile = null;
    CircleImageView imageView;
    Button button_select_image;
    Animation interpolator;
    FloatingActionButton fab_send_photo;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();
    StorageReference riversRef;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("users").child(user.getUid()).child("url_foto");

    public profile_foto() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_foto);

        mStorageRef = FirebaseStorage.getInstance().getReference();
        riversRef = mStorageRef.child("images/" + user.getUid() + "/foto_de_perfil");

        button_select_image = findViewById(R.id.button_select_image);

        imageView = findViewById(R.id.image_profile_select);
        button_select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        fab_send_photo = findViewById(R.id.send_photo_btn);
        fab_send_photo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                logarUsuario("email", "password");
            }
        });

       // interpolator = AnimationUtils.loadAnimation(this, android.R.anim.accelerate_decelerate_interpolator);

    }

    private void selectImage() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_SELECT_IMAGE);
    }

    private void sendImage(Uri file) {
        riversRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        String url = downloadUrl.toString();
                        ref.setValue(url);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
    }

    private void logarUsuario(final String email, String password){

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Intent intent = new Intent(profile_foto.this, tela_principal.class);
                        startActivity(intent);
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imageView.setImageBitmap(selectedImage);
                button_select_image.setVisibility(View.INVISIBLE);
                imageView.setVisibility(View.VISIBLE);
                fab_send_photo.setVisibility(View.VISIBLE);
                sendImage(imageUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(profile_foto.this, "Houve um erro ao carregar está imagem", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(profile_foto.this, "Houve um erro", Toast.LENGTH_LONG).show();
        }

    }
}

