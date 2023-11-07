package com.roberto.minigame.appdata;

import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ModeloUsuario {

    // Crea un TreeSet para mantener las puntuaciones siempre ordenadas.
    private static Set<Usuario> highScores = new TreeSet<>();

    public void addUsuario(Usuario usuario) {

        highScores.add(usuario);

    };
    public ArrayList<Usuario> getHighScores(){

        return new ArrayList<Usuario>(highScores);

    };
}
