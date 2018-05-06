package com.example.yanirayanes.parcialprogra;

public class Persona {
    private String nombre;
    private String telefono;
    private int thumbnail;
    private boolean fav;

    public Persona() {
    }

    public Persona(String nombre, String telefono, int thumbnail) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.thumbnail = thumbnail;
        fav = false;
    }

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
}
