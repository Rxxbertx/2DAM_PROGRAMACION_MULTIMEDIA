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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rsoftware.practica7.model.Pelicula;
import com.rsoftware.practica7.model.PeliculaCollection;
import com.rsoftware.practica7.recycler.RecyclerViewPeliculas;

import org.xmlpull.v1.XmlPullParser;

public class MainActivity extends AppCompatActivity implements PeliculaFragment.OnDatosEnviadosListener {


    public static Context context;

    private PeliculaCollection peliculaCollection;

    View fragment;
    private boolean salidaFragment = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        Log.d("BOOLEAN SALIDA CREATE", String.valueOf(salidaFragment));
        peliculaCollection = new PeliculaCollection();
        cargarPeliculas();
        mostrarPeliculas();

    }

    private void mostrarPeliculas() {


        RecyclerView recycler =  findViewById(R.id.recycler);

        RecyclerViewPeliculas adapter = new RecyclerViewPeliculas(peliculaCollection);

        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verDetallesPelicula(peliculaCollection.getPelicula(recycler.getChildAdapterPosition(v)));

            }
        });




    }

    private void cargarPeliculas() {

        try {
            XmlResourceParser parser = getResources().getXml(R.xml.peliculas);

            while (parser.next() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() == XmlPullParser.START_TAG && "pelicula".equals(parser.getName())) {
                    String id = parser.getAttributeValue(null, "id");

                    String titulo = null, actor = null, sinopsis = null, anio = null, director = null, foto = null;
                    float rating = 0;

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
                            } else if ("img".equals(nombreTag)) {
                                parser.next();
                                foto = parser.getAttributeValue(null, "src");
                            } else if ("rating".equals(nombreTag)) {

                                parser.next();
                                rating = Float.parseFloat(parser.getText());
                            }


                        }
                    }
                    peliculaCollection.addPelicula(new Pelicula(foto, titulo, anio, actor, director, sinopsis, rating));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        //rating = findViewById(savedInstanceState.getInt("idRating"));

        salidaFragment = savedInstanceState.getBoolean("salidaFragment");

        Log.d("BOOLEAN SALIDA RESTORE", String.valueOf(salidaFragment));

        if (!salidaFragment) {

            verDetallesPelicula((Pelicula) getIntent().getSerializableExtra("pelicula"));
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);


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

        if (fragment != null) findViewById(R.id.fragmentContainerView).setVisibility(View.GONE);

        salidaFragment = true;


    }

}