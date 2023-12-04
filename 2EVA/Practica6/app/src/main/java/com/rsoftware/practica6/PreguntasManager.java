package com.rsoftware.practica6;


import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.rsoftware.practica6.interfaces.Pregunta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PreguntasManager {

    public static final int TOTAL_PREGUNTAS = 5;
    private static final int PUNTUACION_PREGUNTA_1 = 2;
    private static final int PUNTUACION_PREGUNTA_2 = 2;
    private static final int PUNTUACION_PREGUNTA_3 = 3;
    private static final int PUNTUACION_PREGUNTA_4 = 2;
    private static final int PUNTUACION_PREGUNTA_5 = 1;

    private static final int PREGUNTA_CORRECTA=0;
    private static final int PREGUNTA_USUARIO =1;
    private static final int PREGUNTA_ENUNCIADO =2;

    private int puntuacionSeleccionada = 1;
    private int posicion = -1;
    private int progreso = 0;



    private static final PreguntasManager instancia = new PreguntasManager();

    private final ArrayList<Class<? extends Pregunta>> preguntas = new ArrayList<>();

    private PreguntasManager() {

    }

    public void modificarPuntuacion(int puntuacion){
        puntuacionSeleccionada = puntuacion;
    }

    // Método público para obtener la instancia única de la clase
    public static PreguntasManager obtenerInstancia() {
        return instancia;
    }

    public void creacionPreguntasAleatorio() {

        posicion=-1;
        progreso=0;

        preguntas.clear();

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

        ++posicion;
        ++progreso;

        if (posicion>=preguntas.size()) return null;


        Class<?extends Pregunta> clase = preguntas.get(posicion);


        Log.println(Log.ERROR,"ERROR",String.valueOf(posicion));



        if (progreso > TOTAL_PREGUNTAS){
            progreso = TOTAL_PREGUNTAS;
        }

        return clase;
    }
    public Class<?extends Pregunta> obtenerPreguntaAnterior() {

        --posicion;
        --progreso;
        Log.println(Log.ERROR,"ERROR",String.valueOf(posicion));



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

    public void botonBackPregunta(AppCompatActivity act){

        Pregunta pregunta = (Pregunta) act;
        act.getOnBackPressedDispatcher().addCallback(act, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                pregunta.btnAtras(new View(act.getApplicationContext()));


            }
        });

    }


    public float conseguirPuntuacionTotal(Bundle datos, Context context) {
        if (datos == null) return 0f;

        float puntuacion = 0f;

        Log.d("ERROR", "conseguirPuntuacionTotal: "+puntuacion);
        puntuacion += calcularPuntuacionPregunta(datos, context, R.string.pregunta1id, PreguntaUnoActivity.class);
        Log.d("ERROR", "conseguirPuntuacionTotal: "+puntuacion);
        puntuacion += calcularPuntuacionPregunta(datos, context, R.string.pregunta2id, PreguntaDosActivity.class);
        Log.d("ERROR", "conseguirPuntuacionTotal: "+puntuacion);
        puntuacion += calcularPuntuacionPregunta(datos, context, R.string.pregunta3id, PreguntaTresActivity.class);
        Log.d("ERROR", "conseguirPuntuacionTotal: "+puntuacion);
        puntuacion += calcularPuntuacionPregunta(datos, context, R.string.pregunta4id, PreguntaCuatroActivity.class);
        Log.d("ERROR", "conseguirPuntuacionTotal: "+puntuacion);
        puntuacion += calcularPuntuacionPreguntaMultiSeleccion(datos, context, R.string.pregunta5id, PreguntaCincoActivity.class);
        Log.d("ERROR", "conseguirPuntuacionTotal: "+puntuacion);
        return puntuacion;
    }

    private float calcularPuntuacionPregunta(Bundle datos, Context context, int preguntaId , Class<? extends Pregunta> preguntaClass) {
        CharSequence[] respuestas = datos.getCharSequenceArray(obtenerValorDeString(context, preguntaId));

        if (respuestas != null && Arrays.stream(respuestas).allMatch(elemento -> elemento.equals(respuestas[PREGUNTA_CORRECTA]))) {
            return obtenerPuntuacionPregunta(preguntaClass);
        }

        return 0f;
    }

    private float calcularPuntuacionPreguntaMultiSeleccion(Bundle datos, Context context, int preguntaId, Class<?extends Pregunta> preguntaClass) {

        CharSequence[] respuestas = datos.getCharSequenceArray(obtenerValorDeString(context, preguntaId));

        if (respuestas != null) {
            float puntuacionPregunta = obtenerPuntuacionPregunta(preguntaClass);
            float puntuacionACalcular = 0;

            if (Arrays.stream(respuestas).allMatch(elemento -> elemento.equals(respuestas[PREGUNTA_CORRECTA]))) {
                puntuacionACalcular += puntuacionPregunta;
            } else {
                puntuacionACalcular += calcularPuntuacionRespuestasIncorrectas(respuestas, puntuacionPregunta);
            }

            return puntuacionACalcular;
        }

        return 0f;
    }

    private float calcularPuntuacionRespuestasIncorrectas(CharSequence[] respuestas, float puntuacionPregunta) {
        float puntuacionACalcular = 0;

        if (respuestas[PREGUNTA_USUARIO] != null) {
            String[] respuestasUsuario = respuestas[PREGUNTA_USUARIO].toString().split(";");
            String[] respuestasCorrectas = respuestas[PREGUNTA_CORRECTA].toString().split(";");

            for (String usuario : respuestasUsuario) {
                int correcto = 0;

                for (String correcta : respuestasCorrectas) {
                    if (usuario.equals(correcta)) {
                        correcto++;
                    }
                }

                if (correcto == 0) {
                    puntuacionACalcular -= puntuacionPregunta / respuestasCorrectas.length;
                } else {
                    puntuacionACalcular += puntuacionPregunta / respuestasCorrectas.length;
                }
            }

            if (puntuacionACalcular < 0) {
                puntuacionACalcular = -puntuacionPregunta / 2;
            }
        }

        return puntuacionACalcular;
    }
    public static String obtenerValorDeString(Context contexto, int resourceId) {
        try {
            Resources resources = contexto.getResources();
            return resources.getString(resourceId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public CharSequence[] obtenerDatosPregunta(Bundle datos,Context context,int preguntaId,Class<?extends Pregunta> preguntaClass){

        if (datos==null)return null;

        CharSequence[]temp = datos.getCharSequenceArray(obtenerValorDeString(context,preguntaId));

        if (temp==null)return null;

        CharSequence[]dataPregunta=Arrays.copyOf(temp,3);

        String enunciado = obtenerValorDeString(context,preguntaId);

        if (enunciado==null)
            enunciado="PREGUNTA";

        dataPregunta[PREGUNTA_ENUNCIADO]=enunciado;

        return dataPregunta;


    }








}
