package com.rsoftware.practica6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rsoftware.practica6.interfaces.Pregunta;

public class PreguntaDosActivity extends AppCompatActivity implements Pregunta {
    private PreguntasManager manager = PreguntasManager.obtenerInstancia();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta_dos);
    }

    public void btnNext(View view) {

        startActivity(new Intent(this, manager.obtenerPregunta(PreguntasManager.PREGUNTA_3)));

    }

    public void btnAtras(View view) {
    }
}