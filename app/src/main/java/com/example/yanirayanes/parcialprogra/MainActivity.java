package com.example.yanirayanes.parcialprogra;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView myrv;
    RecyclerViewAdapter myAdapter;
    List<Persona> listContact, favos, buscar, list;
    Cursor c;
    Persona agregarpipol;
    public static final int ADD_CONTACT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listContact = new ArrayList<>();
        favos = new ArrayList<>();
        buscar = new ArrayList<>();

        listContact.add(new Persona("Tia Marce","7043-9792",R.drawable.profile));
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
        EditText filter = (EditText) findViewById(R.id.filter);
        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
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

    private void filter(String text){
        ArrayList<Persona> filteredList = new ArrayList<>();
        for(Persona item : listContact){
            if(item.getNombre().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        myAdapter.filterList(filteredList);
    }

    public void homebtn(View v){

        myAdapter = new RecyclerViewAdapter(v.getContext(), listContact);
        myrv.setAdapter(myAdapter);
    }
    public void favbtn(View v){

        myAdapter = new RecyclerViewAdapter(v.getContext(), favos);
        myrv.setAdapter(myAdapter);
    }

    public void addbtn(View v){
        Intent addIntent = new Intent(this, agregar_persona.class);
        agregarpipol = new Persona();
        startActivityForResult(addIntent, ADD_CONTACT);
    }

    public void buscarbtn(View v){
        EditText barra = (EditText) findViewById(R.id.filter);
        barra.setEnabled(true);
    }

    public void addContacts() {
        try {
            String var="";
            Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");
            while (phones.moveToNext()) {
                String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                if(!(var.equals(name))){
                    list.add(new Persona(name, phoneNumber,R.drawable.profile));
                }
                var=name;

            }
            phones.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}