package com.rsoftware.practica7;

import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

                            ENCONTRADO=true;

                            String titulo=null,actor = null, sinopsis = null,anio=null,director=null;

                            while (parser.next() != XmlPullParser.END_DOCUMENT && !(parser.getEventType() == XmlPullParser.END_TAG && "pelicula".equals(parser.getName()))) {
                                if (parser.getEventType() == XmlPullParser.START_TAG) {

                                    String nombreTag = parser.getName();

                                    if ("anio".equals(nombreTag)) {
                                        parser.next();
                                        anio = parser.getText();
                                    } else if ("actores".equals(nombreTag)) {
                                        parser.next();
                                        actor = parser.getText();
                                    } else if ("sinopsis".equals(nombreTag)) {
                                        parser.next();
                                        sinopsis = parser.getText();
                                    }else if ("titulo".equals(nombreTag)) {
                                        parser.next();
                                        titulo = parser.getText();
                                    }else if ("director".equals(nombreTag)) {
                                        parser.next();
                                        director = parser.getText();
                                    }
                                }
                            }


                            int rb = getResources().getIdentifier("rb"+tag, "id", getPackageName());
                            if (rb!= View.NO_ID) {


                                RatingBar rating = findViewById(rb);
                                rating.getRating();

                                Pelicula temp = new Pelicula(view.getId(),titulo, anio, actor, director, sinopsis, rating.getRating());

                                crearActividad(temp);
                            }

                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if (!ENCONTRADO) Toast.makeText(this, R.string.error_al_cargar_pelicula, Toast.LENGTH_SHORT).show();


    }

    private void crearActividad(Pelicula temp) {


        Intent intent = new Intent(this, DatosPeliculaActivity.class);

        intent.putExtra("pelicula",temp);

        startActivity(intent);


    }
}