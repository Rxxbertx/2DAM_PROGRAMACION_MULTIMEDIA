package com.rsoftware.practica8;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class DatosPeliculaActivity extends AppCompatActivity implements  PeliculaFragment.OnDatosEnviadosListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_pelicula);
        initFragment();

    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);


        finish();


    }

    private void initFragment() {
        // Crea una instancia del fragmento
        PeliculaFragment peliculaFragment = new PeliculaFragment();

        // Pasa los extras a través de un Bundle si es necesario
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            peliculaFragment.setArguments(extras);
        }

        // Reemplaza el fragmento en el contenedor
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, peliculaFragment)
                .commit();
    }


    @Override
    public void onPuntuacionEnviada(int positionAdapter) {

        if (MainActivity.context instanceof PeliculaFragment.OnDatosEnviadosListener)
            ((PeliculaFragment.OnDatosEnviadosListener)MainActivity.context).onPuntuacionEnviada(positionAdapter);

    }

    @Override
    public void onFinishCommunication() {

        if (MainActivity.context instanceof PeliculaFragment.OnDatosEnviadosListener)
            ((PeliculaFragment.OnDatosEnviadosListener)MainActivity.context).onFinishCommunication();

         finish();
    }

}