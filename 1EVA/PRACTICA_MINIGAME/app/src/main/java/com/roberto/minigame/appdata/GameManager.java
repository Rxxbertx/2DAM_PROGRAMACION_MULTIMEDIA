package com.roberto.minigame.appdata;

public class GameManager {


    public static int Nivel = 1;
    public static int Puntaje = 0;
    public static int Objetivo = 5;
    public static int PuntuajeNivel1 = 0;
    public static int PuntuajeNivel2 = 0;
    public static int PuntuajeNivel3 = 0;

    public static Usuario usuarioActual;
    public static Estado estado = Estado.NO_INICIADO;

    public static void reiniciar(){

        switch (Nivel){
            case 1:
                PuntuajeNivel1 = 0;
                break;
            case 2:
                PuntuajeNivel2 = 0;
                break;
            case 3:
                PuntuajeNivel3 = 0;
                break;
        }
        estado = Estado.NO_INICIADO;

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


