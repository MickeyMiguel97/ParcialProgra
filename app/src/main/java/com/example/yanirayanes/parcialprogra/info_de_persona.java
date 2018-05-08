package com.example.yanirayanes.parcialprogra;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class info_de_persona extends AppCompatActivity {

    private TextView nombre_persona, telefono_persona;
    private ImageView img;
    private ImageButton btncall, btnshare;
    String num;
    private static final int PERMISSIONS_REQUEST_CALL = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_de_persona);

        nombre_persona = (TextView) findViewById(R.id.nombre_id);
        telefono_persona = (TextView) findViewById(R.id.telefono_id);
        img = (ImageView) findViewById(R.id.img_contacto_id);
        btncall = (ImageButton) findViewById(R.id.btn_call);
        btnshare = (ImageButton) findViewById(R.id.btn_share);

        //Aqui se reciben los datos en teoria
        Intent intent = getIntent();
        final Persona persona = intent.getExtras().getParcelable(Persona.KEY_CONTACT);
        final String nombre = intent.getExtras().getString("Nombre");
        final String telefono = intent.getExtras().getString("Telefono");

        //Setteando valores
        nombre_persona.setText(nombre);
        telefono_persona.setText(telefono);
        img.setImageResource(persona.getThumbnail());
        num = persona.getTelefono();
        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Llamar(telefono);
            }
        });

        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                Persona persona = intent.getExtras().getParcelable(Persona.KEY_CONTACT);
                Intent enviar = new Intent();
                enviar.setAction(Intent.ACTION_SEND);
                enviar.putExtra(Intent.EXTRA_TEXT, "CONTACTO\nNombre: "+persona.getNombre()+"\nTelefono: "+persona.getTelefono());
                enviar.setType("Text/plain");
                Intent.createChooser(enviar,"Compartir");
                startActivity(enviar);
            }
        });


        //permiso para llamar para versiones superiores
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(info_de_persona.this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSIONS_REQUEST_CALL);
        }

    }

    public void Llamar(String num){
        Intent call = new Intent(Intent.ACTION_CALL);
        call.setData(Uri.parse("tel:"+num));
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        startActivity(call);
    }

    public void compartir (View view){
        Intent intent = getIntent();
        Persona persona = intent.getExtras().getParcelable(Persona.KEY_CONTACT);
        Intent enviar = new Intent();
        enviar.setAction(Intent.ACTION_SEND);
        enviar.putExtra(Intent.EXTRA_TEXT, "CONTACTO\nNombre: "+persona.getNombre()+"\nTelefono: "+persona.getTelefono());
        enviar.setType("Text/plain");
        Intent.createChooser(enviar,"Compartir");
        startActivity(enviar);
    }

}
