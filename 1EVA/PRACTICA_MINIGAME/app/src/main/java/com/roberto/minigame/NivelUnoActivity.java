package com.roberto.minigame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.roberto.minigame.appdata.GameManager;

public class NivelUnoActivity extends AppCompatActivity {


    private CardView vistaInicio;
    private CardView vistaFinal;
    private CardView vistaPerdido;
    private TextView txtTiempo;

    private long tiempoRestante; // 10 segundos en milisegundos (1000 ms = 1 segundo)
    private int puntuacion = 0;
    private CountDownTimer countDownTimer;

    private static ImageView[] imageViews = new ImageView[3];

    private static ImageView animacion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel_uno);
        cargarContenido();




    }

    private void cargarContenido(){

        vistaInicio = findViewById(R.id.viewInicio);
        vistaFinal = findViewById(R.id.viewGanar);
        vistaPerdido = findViewById(R.id.viewPerder);
        txtTiempo = findViewById(R.id.textViewTiempo);

        animacion = findViewById(R.id.popImg);

        imageViews[0] = findViewById(R.id.circulo);
        imageViews[1] = findViewById(R.id.triangulo);
        imageViews[2] = findViewById(R.id.cuadrado);
        

        tiempoRestante = 10000;

         comprobarEstado();

    }


    private void comprobarEstado() {

        switch (GameManager.estado){
            case INICIADO:
                iniciarTemporizador();
                break;
            case NO_INICIADO:
                vistaInicio.setVisibility(View.VISIBLE);
                break;
            case FINALIZADO:
                comprobarFinal();
                break;
        }
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putLong("tiempoRestante", tiempoRestante);
        outState.putInt("puntuacion", puntuacion);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        tiempoRestante = savedInstanceState.getLong("tiempoRestante");
        puntuacion = savedInstanceState.getInt("puntuacion");
        txtTiempo.setText(String.valueOf(tiempoRestante / 1000));

        if (GameManager.estado == GameManager.Estado.INICIADO) {
            iniciarTemporizador();
        } else if (GameManager.estado == GameManager.Estado.FINALIZADO) {
            comprobarFinal();
        }

    }

    private void actualizarTextViewTiempo() {

        int segundos = (int) tiempoRestante / 1000;
        if (segundos==0){
            countDownTimer.cancel();
        }
        txtTiempo.setText(String.valueOf(segundos));

    }


    private void restarObjetivo() {

        GameManager.Objetivo--;
        comprobarFinal();
    }
    private void comprobarFinal() {

        if (GameManager.Objetivo == 0 && GameManager.estado == GameManager.Estado.FINALIZADO) {
            vistaFinal.setVisibility(View.VISIBLE);
            if (countDownTimer != null) {
                countDownTimer.cancel(); // Detiene el temporizador si el objetivo se ha alcanzado
            }
        }
        if(GameManager.Objetivo!=0 && tiempoRestante==0){
            vistaPerdido.setVisibility(View.VISIBLE);
        }
    }

    public void reinciarNivel(View view) {

        GameManager.reiniciar();
        recreate();


    }

    public void siguienteNivel(View view) {

        //Intent nivel2 = new Intent(this);

    }

    public void comenzarNivel(View view) {

            GameManager.estado = GameManager.Estado.INICIADO;
            vistaInicio.setVisibility(View.GONE);
            iniciarTemporizador();

    }

    private void iniciarTemporizador() {

        if (countDownTimer != null) {
            countDownTimer.cancel(); // Detiene el temporizador si se ha iniciado previamente
        }

       countDownTimer = new CountDownTimer(this.tiempoRestante, 1000) {
           @Override
           public void onTick(long millisUntilFinished) {
               tiempoRestante = millisUntilFinished;

               if (GameManager.Objetivo > 0) { // Verifica si el objetivo aún no se ha alcanzado
                   puntuacion++;
                   actualizarTextViewTiempo();
                   comprobarFinal();
               }
           }

                   @Override
                   public void onFinish() {
                       GameManager.estado = GameManager.Estado.FINALIZADO;
                       comprobarFinal();
                       countDownTimer.cancel(); // Detiene el temporizador cuando llega a 0
                   }
               }.start();

    }

}