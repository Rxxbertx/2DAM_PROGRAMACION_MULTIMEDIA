package com.rsoftware.practica6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rsoftware.practica6.interfaces.Pregunta;

public class PreguntaDosActivity extends AppCompatActivity implements Pregunta {
    private final PreguntasManager manager = PreguntasManager.obtenerInstancia();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta_dos);
        iniciarFragment();
    }

    public void btnNext(View view) {

        Class<?extends Pregunta> temp = manager.obtenerPreguntaSiguiente();
        if (temp!=null)
            startActivity(new Intent(this,temp));
        else startActivity(new Intent(this, FinalActivity.class));
        finish();;
    }

    public void btnAtras(View view) {

        Class<?extends Pregunta> temp = manager.obtenerPreguntaAnterior();
        if (temp!=null)
            startActivity(new Intent(this,temp));
        finish();
    }

    @Override
    public void iniciarFragment() {

        ProgressFragment progressFragment = new ProgressFragment();

        progressFragment.setArguments(manager.getBundle(this.getClass()));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView,progressFragment)
                .commit();


    }
}