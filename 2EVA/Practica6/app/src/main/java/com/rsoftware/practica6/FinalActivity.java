package com.rsoftware.practica6;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.rsoftware.practica6.interfaces.Pregunta;

import java.util.Locale;

public class FinalActivity extends AppCompatActivity {

    private TextView nombre;
    private PreguntasManager manager;

    private int aciertos,fallos;

    private TextView nota;
    private LinearLayout aciertoLayout,falloLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        nombre=findViewById(R.id.textViewNombre);
        nota=findViewById(R.id.textViewNota);
        aciertoLayout=findViewById(R.id.layoutAciertos);
        falloLayout=findViewById(R.id.layoutFallos);
        manager=PreguntasManager.obtenerInstancia();
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
        obtenerDatos();



    }

    private void obtenerDatos() {

        Intent intent = getIntent();
        Bundle data = intent.getExtras();


        if (data==null)return;

        mostarNota(manager.conseguirPuntuacionTotal(data,this));
        mostrarNombre(data.getCharSequence("nombre","RESUMEN"));
        comprobarFallosAciertos(data);



    }

    private void comprobarFallosAciertos(Bundle datos) {


        comprobarPregunta(datos,this,R.string.pregunta1id, R.string.pregunta1,PreguntaUnoActivity.class);
        comprobarPregunta(datos,this,R.string.pregunta2id,R.string.pregunta2,PreguntaDosActivity.class);
        comprobarPregunta(datos,this,R.string.pregunta3id,R.string.pregunta3,PreguntaTresActivity.class);
        comprobarPregunta(datos,this,R.string.pregunta4id,R.string.pregunta4,PreguntaCuatroActivity.class);
        comprobarPregunta(datos,this,R.string.pregunta5id,R.string.pregunta5,PreguntaCincoActivity.class);

        TextView textViewFallos = findViewById(R.id.textViewFallos);
        TextView textViewAciertos = findViewById(R.id.textViewAciertos);

        textViewFallos.setText(String.format(Locale.getDefault(),"%s%d",getString(R.string.fallos),fallos));
        textViewAciertos.setText(String.format(Locale.getDefault(),"%s%d",getString(R.string.aciertos),aciertos));





    }

    private void comprobarPregunta(Bundle datos, Context context, int id, int idEnunciado, Class<?extends Pregunta> clase) {

        float puntuacion;

        if (clase.equals(PreguntaCincoActivity.class))
            puntuacion = manager.calcularPuntuacionPreguntaMultiSeleccion(datos,context,id,clase);
        else puntuacion = manager.calcularPuntuacionPregunta(datos,context,id,clase);

        if (puntuacion<manager.obtenerPuntuacionPregunta(clase)) anadirFallo(datos,context,id,idEnunciado,puntuacion,clase);
        else anadirAcierto(datos,context,id,idEnunciado,puntuacion,clase);
    }

    private void anadirAcierto(Bundle datos, Context context ,int id,int idEnunciado,float puntuacion,Class<?extends Pregunta>clase) {

        CharSequence[]pregunta= manager.obtenerDatosPregunta(datos, context, id, idEnunciado);
        if (pregunta!=null){mostrarAcierto(pregunta,puntuacion,clase);aciertos++;}


    }

    private void mostrarAcierto(CharSequence[] pregunta, float puntuacion,Class<?extends Pregunta>clase) {

        // Obtener el Inflater del contexto
        LayoutInflater inflater = LayoutInflater.from(this);

        // Inflar el diseño
        CardView cardView1 = (CardView) inflater.inflate(R.layout.card_view_acierto, aciertoLayout, false);

        TextView textViewPregunta1 = cardView1.findViewById(R.id.textViewPreguntaAcierto);
        TextView textViewPuntuacion1 = cardView1.findViewById(R.id.textViewPuntuacionPreguntaAcierto);
        TextView textViewRespuestaCorrecta1 = cardView1.findViewById(R.id.textViewRespuestaCorrectaPreguntaAcierto);
        TextView textViewRespuestaUsuario1 = cardView1.findViewById(R.id.textViewRespuestaUsuarioPreguntaAcierto);


        textViewPregunta1.setText(pregunta[PreguntasManager.PREGUNTA_ENUNCIADO]);
        textViewPuntuacion1.setText(String.format(Locale.getDefault(),"%s%.2f/%d",getString(R.string.puntuacion),puntuacion,manager.obtenerPuntuacionPregunta(clase)));
        textViewRespuestaCorrecta1.setText(String.format("%s%n%s", getString(R.string.respuesta_correcta), pregunta[PreguntasManager.PREGUNTA_CORRECTA]));
        textViewRespuestaUsuario1.setText(String.format("%s%n%s", getString(R.string.respuesta_usuario), pregunta[PreguntasManager.PREGUNTA_USUARIO]));


        aciertoLayout.addView(cardView1);


    }

    private void anadirFallo(Bundle datos, Context context ,int id, int idEnunciado,float puntuacion,Class<?extends Pregunta>clase) {

        CharSequence[]pregunta= manager.obtenerDatosPregunta(datos, context, id,idEnunciado);
        if (pregunta!=null){mostrarFallo(pregunta,puntuacion,clase);fallos++;}


    }

    private void mostrarFallo(CharSequence[] pregunta, float puntuacion,Class<?extends Pregunta>clase) {


        // Obtener el Inflater del contexto
        LayoutInflater inflater = LayoutInflater.from(this);

        // Inflar el diseño
        CardView cardView1 = (CardView) inflater.inflate(R.layout.card_view_fallo, falloLayout, false);

        TextView textViewPregunta1 = cardView1.findViewById(R.id.textViewPreguntaFallo);
        TextView textViewPuntuacion1 = cardView1.findViewById(R.id.textViewPuntuacionPreguntaFallo);
        TextView textViewRespuestaCorrecta1 = cardView1.findViewById(R.id.textViewRespuestaCorrectaPreguntaFallo);
        TextView textViewRespuestaUsuario1 = cardView1.findViewById(R.id.textViewRespuestaUsuarioPreguntaFallo);


        textViewPregunta1.setText(pregunta[PreguntasManager.PREGUNTA_ENUNCIADO]);
        textViewPuntuacion1.setText(String.format(Locale.getDefault(),"%s%.2f/%d",getString(R.string.puntuacion),puntuacion,manager.obtenerPuntuacionPregunta(clase)));
        textViewRespuestaCorrecta1.setText(String.format("%s%n%s", getString(R.string.respuesta_correcta), pregunta[PreguntasManager.PREGUNTA_CORRECTA]));
        textViewRespuestaUsuario1.setText(String.format("%s%n%s", getString(R.string.respuesta_usuario), pregunta[PreguntasManager.PREGUNTA_USUARIO]));


        falloLayout.addView(cardView1);


    }

    private void mostarNota(float conseguirPuntuacionTotal) {

        nota.setText(String.format(Locale.getDefault(), "%.2f / %d", conseguirPuntuacionTotal,manager.obtenerPuntuacionSeleccionada()));
        ObjectAnimator animacion = ObjectAnimator.ofFloat(nota,"ScaleX",1.2f);
        ObjectAnimator animacion2 = ObjectAnimator.ofFloat(nota,"ScaleY",1.2f);
        animacion.setRepeatCount(ValueAnimator.INFINITE);
        animacion.setRepeatMode(ValueAnimator.REVERSE);
        animacion2.setRepeatCount(ValueAnimator.INFINITE);
        animacion2.setRepeatMode(ValueAnimator.REVERSE);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animacion,animacion2);
        animatorSet.setDuration(500);
        animatorSet.start();


    }

    private void mostrarNombre(CharSequence charSequence) {

        nombre.setText(charSequence);

    }

    public void btnBack(View view) {

        onBackPressed();

    }
}