package com.roberto.minigame.appdata;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class ModeloUsuario {

    // Crea un TreeSet para mantener las puntuaciones siempre ordenadas.
    private static final Set<Usuario> highScores = new TreeSet<>(Comparator.reverseOrder());
    public void addUsuario(Usuario usuario) {

        highScores.add(usuario);

    }

    public ArrayList<Usuario> getHighScores(){

        return new ArrayList<>(highScores);

    }
}
