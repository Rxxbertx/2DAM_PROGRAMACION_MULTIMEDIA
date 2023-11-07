package com.roberto.minigame.appdata;

public class GameManager {


    public static int Nivel = 1;
    public static int Puntaje = 0;
    public static Usuario usuarioActual;
    public static Estado estado = Estado.NO_INICIADO;

    public static void reiniciar(){
        Nivel = 1;
        Puntaje = 0;
    }

    public static void aumentarNivel(){
        Nivel++;
        estado = Estado.NO_INICIADO;

    }


    public enum Estado{
        INICIADO,
        NO_INICIADO,
        FINALIZADO
    }


}


