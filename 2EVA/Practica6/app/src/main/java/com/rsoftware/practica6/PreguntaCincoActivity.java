package com.rsoftware.practica6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.ChipGroup;
import com.rsoftware.practica6.interfaces.Pregunta;

import java.util.List;

public class PreguntaCincoActivity extends AppCompatActivity implements Pregunta {
    private final PreguntasManager manager = PreguntasManager.obtenerInstancia();
    private Intent data;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta_cinco);
        iniciarFragment();
        manager.botonBackPregunta(this);
        data=getIntent();
    }
    @Override
    public void btnNext(View view) {

        Class<? extends Pregunta> temp = manager.obtenerPreguntaSiguiente();

        if (temp != null)
            intent = new Intent(this,temp);
        else
            intent = new Intent(this, FinalActivity.class);


        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtras(actualizarBundle());
        startActivity(intent);
    }


    @Override
    public Bundle actualizarBundle() {
        Bundle bundle = data.getExtras();

        if (bundle==null)
            bundle=new Bundle();


        bundle.putCharSequenceArray(getString(R.string.pregunta5id),new CharSequence[]{respuestaCorrecta(),respuestaUsuario()});

        return bundle;
    }

    @Override
    public String respuestaCorrecta() {

        return findViewById(R.id.chipCorrecto).getTag().toString()+";"+findViewById(R.id.chipCorrecto1).getTag().toString()+";";
    }

    @Override
    public String respuestaUsuario() {

        List<Integer> seleccion = ((ChipGroup)findViewById(R.id.chipGroup)).getCheckedChipIds();

        if (seleccion.isEmpty())return null;

        StringBuilder data = new StringBuilder();
        for (int temp:seleccion) {
            data.append(findViewById(temp).getTag().toString());
            data.append(";");
        }
        return data.toString();

    }

    @Override
    public void btnAtras(View view) {

        Class<? extends Pregunta> temp = manager.obtenerPreguntaAnterior();

        if (temp != null) {
            intent = new Intent(this, temp);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            intent.putExtras(actualizarBundle());
            startActivity(intent);
        } else startActivity(new Intent(this, MainActivity.class));

    }

    @Override
    public void iniciarFragment() {

        ProgressFragment progressFragment = new ProgressFragment();

        progressFragment.setArguments(manager.getBundle(this.getClass()));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView,progressFragment)
                .commit();


    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.data=intent;
    }
}