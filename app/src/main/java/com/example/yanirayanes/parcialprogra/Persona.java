package com.example.yanirayanes.parcialprogra;

import android.os.Parcel;
import android.os.Parcelable;

public class Persona implements Parcelable{
    private String nombre;
    private String telefono;
    private int thumbnail;
    private boolean fav;
    public static String KEY_CONTACT = "KEY_CONTACT";

    public Persona() {
    }

    public Persona(String nombre, String telefono, int thumbnail) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.thumbnail = thumbnail;
        fav = false;
    }

    protected Persona(Parcel in) {
        nombre = in.readString();
        telefono = in.readString();
        thumbnail = in.readInt();
        fav = in.readByte() != 0;
    }

    public static final Creator<Persona> CREATOR = new Creator<Persona>() {
        @Override
        public Persona createFromParcel(Parcel in) {
            return new Persona(in);
        }

        @Override
        public Persona[] newArray(int size) {
            return new Persona[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void set(boolean fav ){
        this.fav = fav;
    }

    public void setTrue(){ fav = true;}

    public void setFalse(){ fav = false;}

    public boolean verificarFav(){
        return fav;
    }

    public boolean yesorno(){
        return fav;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeString(telefono);
        parcel.writeInt(thumbnail);
        parcel.writeByte((byte) (fav?1:0));
    }
}
