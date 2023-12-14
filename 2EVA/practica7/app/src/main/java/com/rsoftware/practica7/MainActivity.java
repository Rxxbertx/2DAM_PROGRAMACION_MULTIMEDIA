package com.rsoftware.practica7;

import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
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
                                    }
                                }
                            }

                            Toast.makeText(this, anio+sinopsis+titulo, Toast.LENGTH_SHORT).show();

                            //crearActividad(actor,sinopsis,anio,director);


                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if (!ENCONTRADO) Toast.makeText(this, "Error al cargar información de la película", Toast.LENGTH_SHORT).show();


    }

    private void crearActividad(String actor, String sinopsis, String anio, String director) {

        Intent intent = new Intent(this,DatosPeliculaActivity.class);
        intent.putExtra("actor",actor);
        intent.putExtra("director",director);
        intent.putExtra("sinopsis",sinopsis);
        intent.putExtra("anio",anio);

        startActivity(intent);



    }
}