package com.rsoftware.practica9.model;

import java.util.ArrayList;
import java.util.List;

public class PeliculaCollection {

    private static List<Pelicula> peliculas = new ArrayList<Pelicula>();


    public PeliculaCollection(){



    }

    public List<Pelicula> getPeliculas(){
        return peliculas;
    }
    public List<Pelicula> getPeliculasVistas(){

        List<Pelicula>temp = new ArrayList<>();

        for (Pelicula pelicula:
             peliculas) {

            if (pelicula.getVista())
                temp.add(pelicula);

        }
        return temp;
    }

    public void setPeliculas(List<Pelicula> peliculas){
        PeliculaCollection.peliculas =peliculas;
    }

    public Pelicula getPelicula(int index){
        return peliculas.get(index);
    }

    public void setPelicula(int index, Pelicula pelicula){
        peliculas.set(index, pelicula);
    }

    public void addPelicula(Pelicula pelicula){
        peliculas.add(pelicula);
    }
    public void addPelicula(int index,Pelicula pelicula){
        peliculas.add(index,pelicula);
    }

    public void removePelicula(Pelicula pelicula){
        peliculas.remove(pelicula);
    }

    public int size(){
        return peliculas.size();
    }


}
