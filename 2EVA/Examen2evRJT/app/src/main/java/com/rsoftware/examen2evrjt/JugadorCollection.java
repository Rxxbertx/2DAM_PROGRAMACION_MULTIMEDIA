package com.rsoftware.examen2evrjt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JugadorCollection {


    private static List<Jugador> jugadorList = new ArrayList<>();




    public void setJugadorList(List<Jugador> lista){

        jugadorList = lista;

    }

    public List<Jugador> getJugadorList() {
        return jugadorList;
    }




}
