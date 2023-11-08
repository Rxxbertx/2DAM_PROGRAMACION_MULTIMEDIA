package com.roberto.minigame;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.roberto.minigame.animations.AnimacionPopUp;
import com.roberto.minigame.appdata.GameManager;
import com.roberto.minigame.interfaces.Nivel;
import com.roberto.minigame.sounds.Sonidos;

public class NivelUnoActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener, Nivel {

    private CardView vistaInicio;
    private CardView vistaFinal;
    private CardView vistaPerdido;
    private TextView txtTiempo;
    private String figuraAleatoriaNombre;
    private ImageView figuraAleatoria;
    private TextView contador;
    private long tiempoRestante;
    private CountDownTimer countDownTimer;
    private ImageView animacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel_uno);
        cargarContenido();
    }

    @Override
    protected void onPause() {
            super.onPause();

            if (countDownTimer != null)
                countDownTimer.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (GameManager.estado == GameManager.Estado.INICIADO) {
            iniciarTemporizador();
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    public void cargarContenido() {
        // Inicializa los sonidos
        Sonidos.initPopWinLose(this);

        figuraAleatoria = findViewById(R.id.figura);
        figuraAleatoriaNombre = GameManager.figuraAleatoria();
        contador = findViewById(R.id.contador);
        figuraAleatoria.setImageDrawable(GameManager.getFiguraAleatoria(this, figuraAleatoriaNombre));

        // Configura los elementos de la vista
        ImageView caja = findViewById(R.id.box);
        ImageView circulo = findViewById(R.id.corazon);
        ImageView triangulo = findViewById(R.id.triangulo);
        ImageView cuadrado = findViewById(R.id.cuadrado);
        contador.setText(String.valueOf(GameManager.Objetivo));

        vistaInicio = findViewById(R.id.viewInicio);
        vistaFinal = findViewById(R.id.viewGanar);
        vistaPerdido = findViewById(R.id.viewPerder);
        txtTiempo = findViewById(R.id.textViewTiempo);
        animacion = findViewById(R.id.popImg);

        // Configura los listeners de arrastre y eventos táctiles
        circulo.setOnTouchListener(this);
        triangulo.setOnTouchListener(this);
        cuadrado.setOnTouchListener(this);
        caja.setOnDragListener(this);

        tiempoRestante = GameManager.tiempoRestanteNivel1;
        comprobarEstado();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("tiempoRestante", tiempoRestante);
        outState.putString("figuraAleatoria", figuraAleatoriaNombre);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tiempoRestante = savedInstanceState.getLong("tiempoRestante");
        txtTiempo.setText(String.valueOf(tiempoRestante / 1000));
        figuraAleatoriaNombre = savedInstanceState.getString("figuraAleatoria");
        figuraAleatoria.setImageDrawable(GameManager.getFiguraAleatoria(this, figuraAleatoriaNombre != null ? figuraAleatoriaNombre : "circulo"));
        contador.setText(String.valueOf(GameManager.Objetivo));
        comprobarEstado();
    }

    public void comprobarEstado() {

        switch (GameManager.estado) {
            case INICIADO:
                iniciarTemporizador();
                break;
            case NO_INICIADO:
                vistaInicio.setVisibility(View.VISIBLE);
                AnimacionPopUp.animarAparicion(vistaInicio, 600);
                break;
            case FINALIZADO:
                comprobarFinal();
                break;
        }
    }

    public void generarFiguraAleatoria() {
        figuraAleatoriaNombre = GameManager.figuraAleatoria();
        figuraAleatoria.setImageDrawable(GameManager.getFiguraAleatoria(this, figuraAleatoriaNombre));
    }

    private void actualizarTextViewTiempo() {
        int segundos = (int) tiempoRestante / 1000;
        txtTiempo.setText(String.valueOf(segundos));
    }

    public void restarObjetivo() {
        GameManager.Objetivo--;
        contador.setText(String.valueOf(GameManager.Objetivo));
        comprobarFinal();
    }

    public void comprobarFinal() {
        if (GameManager.Objetivo <= 0) {
            GameManager.estado = GameManager.Estado.FINALIZADO;
            vistaFinal.setVisibility(View.VISIBLE);
            Sonidos.playWin();
            AnimacionPopUp.animarAparicion(vistaFinal, 500);
            if (countDownTimer != null) {
                countDownTimer.cancel(); // Detiene el temporizador si el objetivo se ha alcanzado
            }
        }
        if (GameManager.Objetivo != 0 && tiempoRestante / 1000 <= 0) {
            GameManager.estado = GameManager.Estado.FINALIZADO;
            Sonidos.playLose();
            vistaPerdido.setVisibility(View.VISIBLE);
            AnimacionPopUp.animarAparicion(vistaPerdido, 500);
        }
    }

    public void reinciarNivel(View view) {

        GameManager.reiniciarNivel();
        vistaFinal.setVisibility(View.GONE);
        vistaPerdido.setVisibility(View.GONE);
        contador.setText(String.valueOf(GameManager.Objetivo));
        tiempoRestante = GameManager.tiempoRestanteNivel1;
        comprobarEstado();

    }

    public void siguienteNivel(View view) {
        GameManager.aumentarNivel();
        Intent nivel2 = new Intent(this, NivelDosActivity.class);
        finish();startActivity(nivel2);

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
                if (GameManager.Objetivo > 0) {
                    GameManager.PuntuajeNiveles[GameManager.Nivel - 1]++;
                    actualizarTextViewTiempo();
                }
            }

            @Override
            public void onFinish() {
                GameManager.estado = GameManager.Estado.FINALIZADO;
                comprobarFinal();
                countDownTimer.cancel();
            }
        }.start();
    }

    private void realizarAnimacion() {
        Sonidos.playPoP();
        animacion.setVisibility(View.VISIBLE);
        AnimacionPopUp.animarAparicion(animacion, 400);
        animacion.postDelayed(() -> animacion.setVisibility(View.GONE), 500);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        //Sonidos.releasePopWinLose();
    }

    @Override
    public boolean onDrag(View view, DragEvent event) {
        int action = event.getAction();
        if (action == DragEvent.ACTION_DROP) {
            // Obtiene el texto de la figura arrastrada desde los datos del evento de arrastre.
            String figuraArrastrada = event.getClipData().getItemAt(0).getText().toString();

            // Comprueba si la figura arrastrada es la misma que la figura aleatoria.
            if (figuraAleatoriaNombre.equals(figuraArrastrada)) {
                // Resta un objetivo y realiza una animación si la figura es correcta.
                restarObjetivo();
                realizarAnimacion();
                generarFiguraAleatoria();
            }
            return true;
        }
        return true;
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // Crea un objeto ClipData con el texto de la figura que se toca.
            ClipData data = ClipData.newPlainText("figura", view.getTag().toString());
            // Crea un objeto DragShadowBuilder para representar la vista durante el arrastre.
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            // Inicia la operación de arrastre y soltar con los datos y el constructor de sombras.
            view.startDragAndDrop(data, shadowBuilder, view, 0);
            return true;
        } else {
            return false;
        }
    }
}
