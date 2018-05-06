package com.example.yanirayanes.parcialprogra;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView myrv;
    RecyclerViewAdapter myAdapter;
    List<Persona> listContact, favos;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listContact = new ArrayList<>();
        favos = new ArrayList<>();

        listContact.add(new Persona("Mami","7854-8475",R.drawable.profile));
        listContact.add(new Persona("Papi","7792-7529",R.drawable.profile));
        listContact.add(new Persona("Casa","2284-9566",R.drawable.profile));
        listContact.add(new Persona("Yo Movistar","6420-9082", R.drawable.profile));
        listContact.add(new Persona("Yo Claro","7209-2609", R.drawable.profile));
        listContact.add(new Persona("Gaby","7183-9267",R.drawable.profile));
        listContact.add(new Persona("Marina","7810-6420",R.drawable.profile));
        listContact.add(new Persona("Abu Gloria","7689-7867",R.drawable.profile));
        listContact.add(new Persona("Abu Angelica","7827-0937", R.drawable.profile));

        myrv = (RecyclerView) findViewById(R.id.Recyclerview_id);
        myAdapter = new RecyclerViewAdapter(this,listContact);
        myrv.setLayoutManager(new GridLayoutManager(this,3));
        myrv.setAdapter(myAdapter);
    }

    public void addFavs(Persona favo){
        favos.add(favo);
    }

    public void eraseFavourite(String name) {
        int contador = 0;
        for (Persona contacts : favos){
            if (contacts.getNombre()== name){
                break;
            }
            contador++;
        }

        favos.remove(contador);

        if (myAdapter.VerificarFav()){
            myAdapter = new RecyclerViewAdapter((Context) favos, listContact);
            myrv.setAdapter(myAdapter);

        }
    }
    public void homebtn(View v){

        myAdapter = new RecyclerViewAdapter(v.getContext(), listContact);
        myrv.setAdapter(myAdapter);
    }
    public void favbtn(View v){

        myAdapter = new RecyclerViewAdapter(v.getContext(), favos);
        myrv.setAdapter(myAdapter);
    }




}