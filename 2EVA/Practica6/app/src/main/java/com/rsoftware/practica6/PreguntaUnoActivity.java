package com.rsoftware.practica6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rsoftware.practica6.interfaces.Pregunta;

public class PreguntaUnoActivity extends AppCompatActivity implements Pregunta {

    private PreguntasManager manager = PreguntasManager.obtenerInstancia();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta_uno);
    }

    public void btnNext(View view) {

        startActivity(new Intent(this, manager.obtenerPregunta(PreguntasManager.PREGUNTA_2)));

    }

    public void btnAtras(View view) {
    }
}