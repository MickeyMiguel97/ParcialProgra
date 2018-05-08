package com.example.yanirayanes.parcialprogra;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class agregar_persona extends AppCompatActivity {
    private EditText nombre, telefono;
    private ImageView img;
    private Persona pipol;
    private Button btn;
    private Context MyContext;
    private ArrayList<Persona> MyData;
    public static final String EXTRA_CONTACT = "EXTRA_CONTACT";
    private static final int PICK_IMAGE = 100;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_persona);

        nombre = (EditText) findViewById(R.id.txt_name_add);
        telefono = (EditText) findViewById(R.id.txt_phone_add);
        img = (ImageView) findViewById(R.id.add_picture);
        btn = (Button) findViewById(R.id.botonadd);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });


        Intent intent = getIntent();
        pipol = intent.hasExtra(EXTRA_CONTACT)? (Persona) intent.getParcelableExtra(EXTRA_CONTACT): (new Persona());

        nombre.setText(pipol.getNombre());
        telefono.setText(pipol.getTelefono());



    }
    public void saveContact(View view) {

        pipol.setNombre(nombre.getText().toString());
        pipol.setTelefono(telefono.getText().toString());

        Intent returnIntent = new Intent();
        returnIntent.putExtra(EXTRA_CONTACT, (Parcelable) pipol);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            img.setImageURI(imageUri);
        }else {
            img.setImageResource(R.drawable.profile);
        }
    }
}
