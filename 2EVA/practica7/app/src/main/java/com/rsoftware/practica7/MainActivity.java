package com.rsoftware.practica7;

import android.content.Context;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;

public class MainActivity extends AppCompatActivity implements PeliculaFragment.OnDatosEnviadosListener {

    RatingBar rating;
    protected static Context context;

    View fragment;
    private boolean salidaFragment = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        Log.d("BOOLEAN SALIDA CREATE", String.valueOf(salidaFragment));

    }

    public void peliculasClickListener(View view) {


        boolean ENCONTRADO = false;

        String tag = (String) view.getTag();

        if (tag != null) {

            try {
                XmlResourceParser parser = getResources().getXml(R.xml.peliculas);

                while (parser.next() != XmlPullParser.END_DOCUMENT) {
                    if (parser.getEventType() == XmlPullParser.START_TAG && "pelicula".equals(parser.getName())) {
                        String id = parser.getAttributeValue(null, "id");

                        // Verificar si el id es el que estás buscando
                        if (tag.equals(id)) {

                            ENCONTRADO = true;

                            String titulo = null, actor = null, sinopsis = null, anio = null, director = null;

                            while (parser.next() != XmlPullParser.END_DOCUMENT && !(parser.getEventType() == XmlPullParser.END_TAG && "pelicula".equals(parser.getName()))) {
                                if (parser.getEventType() == XmlPullParser.START_TAG) {

                                    String nombreTag = parser.getName();

                                    if ("anio".equals(nombreTag)) {
                                        parser.next();
                                        anio = parser.getText();
                                    } else if ("actor".equals(nombreTag)) {
                                        parser.next();
                                        actor = parser.getText();
                                    } else if ("sinopsis".equals(nombreTag)) {
                                        parser.next();
                                        sinopsis = parser.getText();
                                    } else if ("titulo".equals(nombreTag)) {
                                        parser.next();
                                        titulo = parser.getText();
                                    } else if ("director".equals(nombreTag)) {
                                        parser.next();
                                        director = parser.getText();
                                    }
                                }
                            }


                            int rb = getResources().getIdentifier("rb" + tag, "id", getPackageName());
                            if (rb != View.NO_ID) {

                                rating = findViewById(rb);


                                Pelicula temp = new Pelicula(view.getId(), titulo, anio, actor, director, sinopsis, rating.getRating());

                                verDetallesPelicula(temp);

                            }

                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if (!ENCONTRADO)
            Toast.makeText(this, R.string.error_al_cargar_pelicula, Toast.LENGTH_SHORT).show();


    }

    private void verDetallesPelicula(Pelicula temp) {

        getIntent().putExtra("pelicula", temp);

        salidaFragment = false;

        if (comprobarFragment()) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, PeliculaFragment.class, getIntent().getExtras()).commit();
            fragment.setVisibility(View.VISIBLE);

        } else {
            crearActividad((Pelicula) getIntent().getSerializableExtra("pelicula"));
        }

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        rating = findViewById(savedInstanceState.getInt("idRating"));

        salidaFragment = savedInstanceState.getBoolean("salidaFragment");

        Log.d("BOOLEAN SALIDA RESTORE", String.valueOf(salidaFragment));

        if (!salidaFragment) {

            verDetallesPelicula((Pelicula) getIntent().getSerializableExtra("pelicula"));
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (rating != null) {

            outState.putInt("idRating", rating.getId());


        }

        outState.putBoolean("salidaFragment", salidaFragment);
        Log.d("BOOLEAN SALIDA SAVE", String.valueOf(salidaFragment));
    }

    private boolean comprobarFragment() {

        return (fragment = findViewById(R.id.fragmentContainerView)) != null;
    }

    private void crearActividad(Pelicula temp) {


        Intent intent = new Intent(this, DatosPeliculaActivity.class);

        intent.putExtra("pelicula", temp);

        startActivity(intent);


    }


    @Override
    public void onPuntuacionEnviada(float puntuacion) {

        if (rating != null) rating.setRating(puntuacion);

        if (fragment != null) findViewById(R.id.fragmentContainerView).setVisibility(View.GONE);

        salidaFragment = true;


    }

}