package com.rsoftware.practica6;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.rsoftware.practica6.interfaces.Pregunta;

import java.util.ArrayList;
import java.util.Collections;

public class PreguntasManager {

    public static final int TOTAL_PREGUNTAS = 5;
    private static final int PUNTUACION_PREGUNTA_1 = 2;
    private static final int PUNTUACION_PREGUNTA_2 = 2;
    private static final int PUNTUACION_PREGUNTA_3 = 3;
    private static final int PUNTUACION_PREGUNTA_4 = 2;
    private static final int PUNTUACION_PREGUNTA_5 = 1;

    private int puntuacionSeleccionada = 1;
    private int posicion = -1;
    private int progreso = 0;



    private static final PreguntasManager instancia = new PreguntasManager();

    private final ArrayList<Class<? extends Pregunta>> preguntas = new ArrayList<>();

    private PreguntasManager() {

    }

    public void modificarPuntuacion(int puntuacion){
        if (puntuacion==10)return;
        puntuacionSeleccionada = puntuacion;
    }

    // Método público para obtener la instancia única de la clase
    public static PreguntasManager obtenerInstancia() {
        return instancia;
    }

    public void creacionPreguntasAleatorio() {

        posicion=-1;

        preguntas.add(PreguntaUnoActivity.class);

        preguntas.add(PreguntaDosActivity.class);

        preguntas.add(PreguntaTresActivity.class);

        preguntas.add(PreguntaCuatroActivity.class);

        preguntas.add(PreguntaCincoActivity.class);

        Collections.shuffle(preguntas);

    }


    public int obtenerPuntuacionPregunta (Class<? extends Pregunta> pregunta){

        int puntuacion=0;

        if (pregunta.equals(PreguntaUnoActivity.class)){
            return PUNTUACION_PREGUNTA_1*puntuacionSeleccionada;
        }
        if (pregunta.equals(PreguntaDosActivity.class)){
            return PUNTUACION_PREGUNTA_2*puntuacionSeleccionada;
        }
        if (pregunta.equals(PreguntaTresActivity.class)){
            return PUNTUACION_PREGUNTA_3*puntuacionSeleccionada;
        }
        if (pregunta.equals(PreguntaCuatroActivity.class)){
            return PUNTUACION_PREGUNTA_4*puntuacionSeleccionada;
        }
        if (pregunta.equals(PreguntaCincoActivity.class)){
            return PUNTUACION_PREGUNTA_5*puntuacionSeleccionada;
        }
        return puntuacion;

    }

    public Class<?extends Pregunta> obtenerPreguntaSiguiente() {

        posicion++;
        progreso++;

        Class<?extends Pregunta> clase = preguntas.get(posicion);


        Log.println(Log.ERROR,"ERROR",String.valueOf(posicion));


        if (posicion>=preguntas.size()) return null;

        if (progreso > TOTAL_PREGUNTAS){
            progreso = TOTAL_PREGUNTAS;
        }

        return clase;
    }
    public Class<?extends Pregunta> obtenerPreguntaAnterior() {

        posicion--;
        progreso--;

        if (posicion<0){
            posicion = -1;
            return null;
        }

        Class<?extends Pregunta> clase = preguntas.get(posicion);


        if (progreso < 0){
            progreso = 0;
        }
        return clase;
    }

    public Bundle getBundle(Class<?extends Pregunta>clase) {

        Bundle b = new Bundle();
        b.putInt(ProgressFragment.PARAM_PROGRESO,progreso);
        b.putInt(ProgressFragment.PARAM_PUNTUACION,obtenerPuntuacionPregunta(clase));
        return b;
    }
}
