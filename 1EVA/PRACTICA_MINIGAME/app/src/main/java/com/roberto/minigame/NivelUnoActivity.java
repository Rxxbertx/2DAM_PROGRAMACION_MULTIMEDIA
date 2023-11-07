package com.roberto.minigame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.roberto.minigame.appdata.GameManager;

public class NivelUnoActivity extends AppCompatActivity {


    private int objetivo = 5;

    private CardView vistaInicio;
    private CardView vistaFinal;
    private CardView vistaPerdido;

    private CountDownTimer countDownTimer;




    private TextView txtTiempo;
    private long tiempoRestante = 10000; // 10 segundos en milisegundos (1000 ms = 1 segundo)



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



    private void actualizarTextViewTiempo() {

        int segundos = (int) tiempoRestante / 1000;
        txtTiempo.setText(String.valueOf(segundos));
    }

    private void comprobarFinal() {

         if (objetivo == 0){
                vistaFinal.setVisibility(View.VISIBLE);
                countDownTimer.cancel();
            } else {
                vistaPerdido.setVisibility(View.VISIBLE);
            }
    }


    public void reinciarNivel(View view) {

        GameManager.estado = GameManager.Estado.NO_INICIADO;
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
        countDownTimer = new CountDownTimer(tiempoRestante, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tiempoRestante = millisUntilFinished;
                actualizarTextViewTiempo();
                comprobarFinal();
            }

            @Override
            public void onFinish() {
                // Cuando el temporizador llega a 0, reinicia la actividad
                comprobarFinal();
            }
        }.start();

    }

}