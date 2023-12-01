package com.rsoftware.practica6;


import android.app.Activity;

import com.rsoftware.practica6.interfaces.Pregunta;

import java.util.ArrayList;
import java.util.Collections;

public class PreguntasManager {

    public static final int TOTAL_PREGUNTAS = 5;
    public static final int PREGUNTA_1 = 0;
    public static final int PREGUNTA_2 = 1;
    public static final int PREGUNTA_3 = 2;
    public static final int PREGUNTA_4 = 3;
    public static final int PREGUNTA_5 = 4;

    private static final int PUNTUACION_PREGUNTA_1 = 2;
    private static final int PUNTUACION_PREGUNTA_2 = 2;
    private static final int PUNTUACION_PREGUNTA_3 = 3;
    private static final int PUNTUACION_PREGUNTA_4 = 2;
    private static final int PUNTUACION_PREGUNTA_5 = 1;

    private int puntuacionSeleccionada = 0;




    // La instancia única de la clase, inicializada estáticamente
    private static final PreguntasManager instancia = new PreguntasManager();

    private ArrayList<Class<? extends Pregunta>> preguntas = new ArrayList<>();

    // Constructor privado para evitar la creación de instancias desde fuera de la clase
    private PreguntasManager() {
        // Inicialización de la instancia (si es necesario)
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


        preguntas.add(PreguntaUnoActivity.class);

        preguntas.add(PreguntaDosActivity.class);

        preguntas.add(PreguntaTresActivity.class);

        preguntas.add(PreguntaCuatroActivity.class);

        preguntas.add(PreguntaCincoActivity.class);

        Collections.shuffle(preguntas);

    }

    public Class<? extends Pregunta> obtenerPregunta(int pregunta) {

        return preguntas.get(pregunta);

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

}
