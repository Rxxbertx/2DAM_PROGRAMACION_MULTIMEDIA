package com.rsoftware.practica8.model;

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

    private boolean valorada=false;


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
    public void setValoracion(float valoracionNew) {
        valoracion=valoracionNew;
    }



    public Pelicula(String foto, String titulo, String anio, String actor, String director, String sinopsis, float valoracion){
        this.foto=foto;
        this.titulo = titulo;
        this.anio = anio;
        this.actor = actor;
        this.director = director;
        this.sinopsis = sinopsis;
        this.valoracion=valoracion;
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



















}
