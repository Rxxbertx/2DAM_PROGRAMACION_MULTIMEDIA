package com.rsoftware.recyclerviewtest;

public class PilotoKart {


    private String nombre;
    private int posicion;


    public PilotoKart(String nombre, int posicion) {
        this.nombre = nombre;
        this.posicion = posicion;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}
