package com.example.yanirayanes.parcialprogra;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class info_de_persona extends AppCompatActivity {

    private TextView nombre_persona, telefono_persona;
    private ImageView img;
    private ImageButton btncall;
    String num;
    private static final int PERMISSIONS_REQUEST_CALL = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_de_persona);

        nombre_persona = (TextView) findViewById(R.id.nombre_id);
        telefono_persona = (TextView) findViewById(R.id.telefono_id);
        btncall = (ImageButton) findViewById(R.id.btn_call);
        Intent intent = getIntent();
        final String nombre = intent.getExtras().getString("Nombre");
        final String telefono = intent.getExtras().getString("Telefono");

        nombre_persona.setText(nombre);
        telefono_persona.setText(telefono);

        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Llamar(telefono);
            }
        });
    }
    public void Llamar(String num){
        Intent call = new Intent(Intent.ACTION_CALL);
        call.setData(Uri.parse("tel:"+num));
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        startActivity(call);
    }
}
