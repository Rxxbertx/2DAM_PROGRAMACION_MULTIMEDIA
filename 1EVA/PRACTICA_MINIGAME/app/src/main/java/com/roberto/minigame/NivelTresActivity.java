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
import com.roberto.minigame.appdata.Usuario;
import com.roberto.minigame.interfaces.Nivel;
import com.roberto.minigame.sounds.Sonidos;

public class NivelTresActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener, Nivel {

    private CardView vistaInicio;
    private CardView vistaFinal;
    private CardView vistaPerdido;
    private TextView txtTiempo;
    private String figuraAleatoriaNombre;
    private String figuraAleatoriaNombre1;

    private ImageView figuraAleatoria;
    private ImageView figuraAleatoria1;

    private TextView contador;
    private TextView contador1;
    private long tiempoRestante;

    private CountDownTimer countDownTimer;
    private ImageView animacion;
    private ImageView animacion1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel_tres);
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

        figuraAleatoria = findViewById(R.id.figura);
        figuraAleatoriaNombre = GameManager.figuraAleatoria();
        contador = findViewById(R.id.contador);
        figuraAleatoria.setImageDrawable(GameManager.getFiguraAleatoria(this, figuraAleatoriaNombre));

        contador1 = findViewById(R.id.contador1);
        figuraAleatoria1 = findViewById(R.id.figura1);
        figuraAleatoriaNombre1 = GameManager.figuraAleatoria();
        figuraAleatoria1.setImageDrawable(GameManager.getFiguraAleatoria(this, figuraAleatoriaNombre1));



        // Configura los elementos de la vista
        ImageView caja = findViewById(R.id.box);
        ImageView caja1 = findViewById(R.id.box1);
        ImageView circulo = findViewById(R.id.circulo);
        ImageView triangulo = findViewById(R.id.triangulo);
        ImageView cuadrado = findViewById(R.id.cuadrado);
        ImageView estrella = findViewById(R.id.estrella);
        ImageView corazon = findViewById(R.id.corazon);
        ImageView rombo = findViewById(R.id.rombo);

        contador.setText(String.valueOf(GameManager.Objetivo/2));
        contador1.setText(String.valueOf(GameManager.Objetivo/2));

        vistaInicio = findViewById(R.id.viewInicio);
        vistaFinal = findViewById(R.id.viewGanar);
        vistaPerdido = findViewById(R.id.viewPerder);
        txtTiempo = findViewById(R.id.textViewTiempo);

        animacion = findViewById(R.id.popImg);
        animacion1 = findViewById(R.id.popImg1);

        // Configura los listeners de arrastre y eventos táctiles
        circulo.setOnTouchListener(this);
        triangulo.setOnTouchListener(this);
        cuadrado.setOnTouchListener(this);
        estrella.setOnTouchListener(this);
        corazon.setOnTouchListener(this);
        rombo.setOnTouchListener(this);
        caja.setOnDragListener(this);
        caja1.setOnDragListener(this);

        tiempoRestante = GameManager.tiempoRestanteNivel3;
        comprobarEstado();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putLong("tiempoRestante", tiempoRestante);

        outState.putString("figuraAleatoria", figuraAleatoriaNombre);
        outState.putString("figuraAleatoria1", figuraAleatoriaNombre1);

        outState.putString("contador", contador.getText().toString());
        outState.putString("contador1", contador1.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        tiempoRestante = savedInstanceState.getLong("tiempoRestante");
        txtTiempo.setText(String.valueOf(tiempoRestante / 1000));

        figuraAleatoriaNombre = savedInstanceState.getString("figuraAleatoria");
        figuraAleatoria.setImageDrawable(GameManager.getFiguraAleatoria(this, figuraAleatoriaNombre != null ? figuraAleatoriaNombre : "circulo"));

        figuraAleatoriaNombre1 = savedInstanceState.getString("figuraAleatoria1");
        figuraAleatoria1.setImageDrawable(GameManager.getFiguraAleatoria(this, figuraAleatoriaNombre1 != null ? figuraAleatoriaNombre1 : "circulo"));

        contador.setText(savedInstanceState.getString("contador"));
        contador1.setText(savedInstanceState.getString("contador1"));

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

    public void generarFiguraAleatoria(int i) {

        if (i == 0) {
            figuraAleatoriaNombre = GameManager.figuraAleatoria();
            figuraAleatoria.setImageDrawable(GameManager.getFiguraAleatoria(this, figuraAleatoriaNombre));
        } else {
            figuraAleatoriaNombre1 = GameManager.figuraAleatoria();
            figuraAleatoria1.setImageDrawable(GameManager.getFiguraAleatoria(this, figuraAleatoriaNombre1));
        }

    }

    private void actualizarTextViewTiempo() {
        int segundos = (int) tiempoRestante / 1000;
        txtTiempo.setText(String.valueOf(segundos));
    }

    // Restar objetivo
    public void restarObjetivo(TextView contador) {

        int objetivoActual = Integer.parseInt(contador.getText().toString());

        if (objetivoActual == 0) {
            return;
        }

        objetivoActual--;
        contador.setText(String.valueOf(objetivoActual));
        GameManager.Objetivo--;
        comprobarFinal(); // Comprobar si se ha alcanzado el objetivo
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
        tiempoRestante = GameManager.tiempoRestanteNivel3;
        comprobarEstado();

    }

    public void siguienteNivel(View view) {


        Usuario usuario = GameManager.usuarioActual;
        usuario.setPuntuacion(GameManager.getPuntuacionTotal());

        Intent puntuacion = new Intent(this, PuntuacionActivity.class);
        startActivity(puntuacion);
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

    // Animación
    public void realizarAnimacion(ImageView animacion) {
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

            if (view.getId() == R.id.box) {
                if (figuraAleatoriaNombre.equals(figuraArrastrada)) {
                    if (!contador.getText().toString().equals("0")) {
                    restarObjetivo(contador);
                    realizarAnimacion(animacion);

                        generarFiguraAleatoria(0);
                    }

                }
            } else if (view.getId() == R.id.box1) {
                if (figuraAleatoriaNombre1.equals(figuraArrastrada)) {

                    if (!contador1.getText().toString().equals("0")) {
                    restarObjetivo(contador1);
                    realizarAnimacion(animacion1);

                        generarFiguraAleatoria(1);
                    }
                }
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
