package com.roberto.minigame.appdata;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.roberto.minigame.R;

public class GameManager {
    public static int Nivel = 1;
    public static int Objetivo = 5;
    public static int[] PuntuajeNiveles = new int[3];
    public static Usuario usuarioActual;
    public static Estado estado = Estado.NO_INICIADO;

    public static int getPuntuacionTotal() {
        int total = 0;
        for (int puntuajeNivele : PuntuajeNiveles) {
            total += puntuajeNivele;
        }
        return total;
    }

    public enum Estado {
        NO_INICIADO, INICIADO, FINALIZADO
    }

    private static final String[] FIGURAS = {"circulo", "triangulo", "cuadrado", "estrella", "corazon", "rombo"};

    public static String figuraAleatoria() {
        int maxFiguras = Nivel == 1 ? 3 : 6;
        return generarFigura(maxFiguras);

    }

    private static String generarFigura(int max) {
        int i = (int) (Math.random() * max);
        return FIGURAS[i];
    }

    public static void reiniciarNivel() {

        PuntuajeNiveles[Nivel - 1] = 0;
        Objetivo = (Nivel == 1) ? 5 : (Nivel == 2) ? 10 : 20;
        estado = Estado.NO_INICIADO;

    }
    public static void reiniciarJuego() {
        Nivel = 1;
        PuntuajeNiveles = new int[3];
        Objetivo = (Nivel == 1) ? 5 : (Nivel == 2) ? 10 : 20;
        estado = Estado.NO_INICIADO;
    }

    public static void aumentarNivel() {
        Nivel++;
        Objetivo = (Nivel == 1) ? 5: (Nivel == 2) ? 10 : 20;
        estado = Estado.NO_INICIADO;
    }

    public static Drawable getFiguraAleatoria(Context context, String figuraAleatoriaNombre) {
        int drawableId;

        switch (figuraAleatoriaNombre) {
            case "circulo":
                drawableId = R.drawable.circle;
                break;
            case "triangulo":
                drawableId = R.drawable.triangle;
                break;
            case "cuadrado":
                drawableId = R.drawable.rectangle;
                break;
            case "estrella":
                drawableId = R.drawable.star;
                break;
            case "corazon":
                drawableId = R.drawable.heart;
                break;
            case "rombo":
                drawableId = R.drawable.rombo;
                break;
            default:
                drawableId = R.drawable.circle;
                break;
        }

        return ContextCompat.getDrawable(context, drawableId);
    }
}



