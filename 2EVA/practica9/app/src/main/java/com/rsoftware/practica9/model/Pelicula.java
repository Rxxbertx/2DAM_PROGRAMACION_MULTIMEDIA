package com.rsoftware.practica9.model;

import android.content.ContentValues;

import com.rsoftware.practica9.db.DBContract;

import java.io.Serializable;
import java.util.Objects;

public class Pelicula implements Serializable {


    private final String titulo;
    private final String anio;
    private final String actor;
    private final String director;
    private final String sinopsis;
    private float valoracion;
    private final String foto;

    private boolean vista;

    private boolean valorada=false;

    private long id;


    public boolean isValorada() {
        return valorada;
    }

    public void setValorada(boolean valorada) {
        this.valorada = valorada;
    }
    public String getFoto() {
        return foto;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAnio() {
        return anio;
    }

    public String getActor() {
        return actor;
    }

    public String getDirector() {
        return director;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public float getValoracion() {
        return valoracion;
    }


    public boolean getVista() {
        return vista;
    }

    public void setVista(boolean isChecked) {

        this.vista=isChecked;
    }
    public void setValoracion(float valoracionNew) {
        valoracion=valoracionNew;
    }



    public Pelicula(boolean vista, String foto, String titulo, String anio, String actor, String director, String sinopsis, float valoracion){


        this.vista=vista;
        this.foto=foto;
        this.titulo = titulo;
        this.anio = anio;
        this.actor = actor;
        this.director = director;
        this.sinopsis = sinopsis;
        this.valoracion=valoracion;
    }



    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(DBContract.DBPeliculaColumnas.COLUMN_NAME_TITULO, titulo);
        values.put(DBContract.DBPeliculaColumnas.COLUMN_NAME_ANIO, anio);
        values.put(DBContract.DBPeliculaColumnas.COLUMN_NAME_ACTOR, actor);
        values.put(DBContract.DBPeliculaColumnas.COLUMN_NAME_DIRECTOR, director);
        values.put(DBContract.DBPeliculaColumnas.COLUMN_NAME_VISTA, vista);
        values.put(DBContract.DBPeliculaColumnas.COLUMN_NAME_SINOPSIS, sinopsis);
        values.put(DBContract.DBPeliculaColumnas.COLUMN_NAME_PUNTUACION, valoracion);
        values.put(DBContract.DBPeliculaColumnas.COLUMN_NAME_FOTO, foto);
        return values;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pelicula pelicula = (Pelicula) obj;
        return Objects.equals(titulo, pelicula.titulo);
    }

    @Override
    public int hashCode() {
        return titulo != null ? titulo.hashCode() : 0;
    }


    public void setId(long newRowId) {

        this.id = newRowId;
    }

    public long getId() {

        return id;
    }
}
