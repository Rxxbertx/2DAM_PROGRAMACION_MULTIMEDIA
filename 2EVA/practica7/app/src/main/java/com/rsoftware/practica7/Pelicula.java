package com.rsoftware.practica7;

import java.io.Serializable;

public class Pelicula implements Serializable {


    private final String titulo;
    private final String anio;
    private final String actor;
    private final String director;
    private final String sinopsis;
    private float valoracion;
    private final int idFoto;


    public int getIdFoto() {
        return idFoto;
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



    public Pelicula(int fotoId,String titulo, String anio, String actor, String director, String sinopsis,float valoracion){
        this.idFoto=fotoId;
        this.titulo = titulo;
        this.anio = anio;
        this.actor = actor;
        this.director = director;
        this.sinopsis = sinopsis;
        this.valoracion=valoracion;
    }
























}
