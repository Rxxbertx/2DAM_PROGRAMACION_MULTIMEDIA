package com.rsoftware.examen2evrjt;

import android.content.ContentValues;

import java.io.Serializable;
import java.util.Objects;

public class Jugador implements Serializable {


    private int id;
    private String nombre;
    private String equipo;
    private int dorsal;
    private String conferencia;
    private String posicion_1;
    private String posicion_2;
    private String posicion_3;
    private String foto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jugador jugador = (Jugador) o;

        return Objects.equals(nombre, jugador.nombre);
    }

    @Override
    public int hashCode() {
        return nombre != null ? nombre.hashCode() : 0;
    }

    public Jugador(String nombre, String equipo, int dorsal, String conferencia, String posicion_1, String posicion_2, String posicion_3, String foto) {

        this.nombre = nombre;
        this.equipo = equipo;
        this.dorsal = dorsal;
        this.conferencia = conferencia;
        this.posicion_1 = posicion_1;
        this.posicion_2 = posicion_2;
        this.posicion_3 = posicion_3;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getConferencia() {
        return conferencia;
    }

    public void setConferencia(String conferencia) {
        this.conferencia = conferencia;
    }

    public String getPosicion_1() {
        return posicion_1;
    }

    public void setPosicion_1(String posicion_1) {
        this.posicion_1 = posicion_1;
    }

    public String getPosicion_2() {
        return posicion_2;
    }

    public void setPosicion_2(String posicion_2) {
        this.posicion_2 = posicion_2;
    }

    public String getPosicion_3() {
        return posicion_3;
    }

    public void setPosicion_3(String posicion_3) {
        this.posicion_3 = posicion_3;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }



    public ContentValues toContentValues(){

        ContentValues contentValues = new ContentValues();

        contentValues.put(NBADbContract.NBAEntry.NOMBRE,nombre);
        contentValues.put(NBADbContract.NBAEntry.EQUIPO,equipo);
        contentValues.put(NBADbContract.NBAEntry.DORSAL,dorsal);
        contentValues.put(NBADbContract.NBAEntry.CONFERENCIA,conferencia);
        contentValues.put(NBADbContract.NBAEntry.POSICION_1,posicion_1);
        contentValues.put(NBADbContract.NBAEntry.POSICION_2,posicion_2);
        contentValues.put(NBADbContract.NBAEntry.POSICION_3,posicion_3);
        contentValues.put(NBADbContract.NBAEntry.FOTO,foto);

        return contentValues;


    }




}
