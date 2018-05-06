package com.example.yanirayanes.parcialprogra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class informacion_persona extends AppCompatActivity {

    TextView nombre_persona, telefono_persona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        nombre_persona = (TextView) findViewById(R.id.nombre_id);
        telefono_persona = (TextView) findViewById(R.id.telefono_id);
        Intent intent = getIntent();
        String nombre = intent.getExtras().getString("Nombre");
        String telefono = intent.getExtras().getString("Telefono");

        nombre_persona.setText(nombre);
        telefono_persona.setText(telefono);
    }

}
