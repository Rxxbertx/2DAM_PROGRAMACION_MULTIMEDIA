package com.rsoftware.practica7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Objects;

public class DatosPeliculaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_pelicula);
        initFragment();

    }

    private void initFragment() {


        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainerView, PeliculaFragment.class,getIntent().getExtras()).commit();

    }


}