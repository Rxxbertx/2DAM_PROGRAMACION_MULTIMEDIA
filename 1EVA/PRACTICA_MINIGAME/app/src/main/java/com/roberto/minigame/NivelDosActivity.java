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

public class NivelDosActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener, Nivel {

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
        setContentView(R.layout.activity_nivel_dos);
        cargarContenido();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Sonidos.pauseMusic();
        if (countDownTimer != null)
            countDownTimer.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Sonidos.playMusic();
        if (GameManager.estado == GameManager.Estado.INICIADO) {
            iniciarTemporizador();
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    public void cargarContenido() {

        Sonidos.playMusic();
        figuraAleatoria = findViewById(R.id.figura);
        figuraAleatoriaNombre = GameManager.figuraAleatoria();
        contador = findViewById(R.id.contador);
        figuraAleatoria.setImageDrawable(GameManager.getFiguraAleatoria(this, figuraAleatoriaNombre));

        // Configura los elementos de la vista
        ImageView caja = findViewById(R.id.box);
        ImageView circulo = findViewById(R.id.circulo);
        ImageView triangulo = findViewById(R.id.triangulo);
        ImageView cuadrado = findViewById(R.id.cuadrado);
        ImageView estrella = findViewById(R.id.estrella);
        ImageView corazon = findViewById(R.id.corazon);
        ImageView rombo = findViewById(R.id.rombo);

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
        estrella.setOnTouchListener(this);
        corazon.setOnTouchListener(this);
        rombo.setOnTouchListener(this);
        caja.setOnDragListener(this);

        tiempoRestante = 15000;
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
        finish();
        startActivity(getIntent());
    }

    public void siguienteNivel(View view) {
        GameManager.aumentarNivel();
        Intent nivel3 = new Intent(this, NivelTresActivity.class);
        startActivity(nivel3);
        finish();
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

    }

    @Override
    public boolean onDrag(View view, DragEvent event) {
        int action = event.getAction();
        if (action == DragEvent.ACTION_DROP) {
            String figuraArrastrada = event.getClipData().getItemAt(0).getText().toString();
            if (figuraAleatoriaNombre.equals(figuraArrastrada)) {
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
            ClipData data = ClipData.newPlainText("figura", view.getTag().toString());
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            view.startDragAndDrop(data, shadowBuilder, view, 0);
            return true;
        } else {
            return false;
        }
    }
}
