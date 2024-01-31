package com.rsoftware.practica9.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rsoftware.practica9.MainActivity;
import com.rsoftware.practica9.model.Pelicula;

public class PeliculasDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Peliculas.db";

    private static PeliculasDBHelper instance;

    private PeliculasDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static PeliculasDBHelper getInstance() {
        if (instance == null) {
            instance = new PeliculasDBHelper(MainActivity.context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("CREATE TABLE " + DBContract.DBPeliculaColumnas.TABLE_NAME + " (" +
                DBContract.DBPeliculaColumnas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DBContract.DBPeliculaColumnas.COLUMN_NAME_TITULO + " TEXT NOT NULL," +
                DBContract.DBPeliculaColumnas.COLUMN_NAME_ANIO + " TEXT NOT NULL," +
                DBContract.DBPeliculaColumnas.COLUMN_NAME_ACTOR + " TEXT NOT NULL," +
                DBContract.DBPeliculaColumnas.COLUMN_NAME_DIRECTOR + " TEXT NOT NULL," +
                DBContract.DBPeliculaColumnas.COLUMN_NAME_VISTA + " INTEGER NOT NULL," +
                DBContract.DBPeliculaColumnas.COLUMN_NAME_SINOPSIS + " TEXT NOT NULL," +
                DBContract.DBPeliculaColumnas.COLUMN_NAME_PUNTUACION + " REAL NOT NULL," +
                DBContract.DBPeliculaColumnas.COLUMN_NAME_FOTO + " TEXT NOT NULL)");




        PeliculasDAO.CreatePelicula(db,
                new Pelicula(
                        false,
                        "cars",
                        "CARS",
                        "2006",
                        "Owen Wilson, Bonnie Hunt, Paul Newman",
                        "John Lasseter, Joe Ranft",
                        "Rayo McQueen, una máquina de carreras novata y egoísta, descubre la importancia de la amistad y la humildad cuando se encuentra atrapado en Radiator Springs.",
                        5F)
        );


        PeliculasDAO.CreatePelicula(
                db, new Pelicula(
                        false,
                        "parasite",
                        "PARASITE",
                        "2019",
                        "Kang-ho Song, Sun-kyun Lee, Yeo-jeong Jo",
                        "Bong Joon-ho",
                        "Una familia desempleada astuta y sin escrúpulos, los Kim, se infiltra en la vida de la adinerada y glamorosa familia Park, entrelazando destinos que llevan a consecuencias inesperadas.",
                        5F)
        );

        PeliculasDAO.CreatePelicula(
                db, new Pelicula(
                        false,
                        "whiplash",
                        "WHIPLASH",
                        "2014",
                        "Miles Teller, J.K. Simmons, Paul Reiser",
                        "Damien Chazelle",
                        "Un joven baterista talentoso se encuentra bajo la tutela de un maestro exigente y perfeccionista que lo impulsa a los límites de sus habilidades y su cordura.",
                        0F)

        );

        PeliculasDAO.CreatePelicula(
                db, new Pelicula(
                        false,
                        "water",
                        "THE SHAPE OF WATER",
                        "2017",
                        "Sally Hawkins, Michael Shannon, Octavia Spencer",
                        "Guillermo del Toro",
                        "En la Guerra Fría, una mujer muda trabaja en un laboratorio de alta seguridad y forma un vínculo único con una criatura acuática cautiva.",
                        3F)
        );

        PeliculasDAO.CreatePelicula(
           db, new Pelicula(
                        false,
                        "anotherround",
                        "Otra Ronda",
                        "2020",
                        "Mads Mikkelsen, Thomas Bo Larsen, Magnus Millang",
                        "Thomas Vinterberg",
                        "Cuatro maestros deciden experimentar manteniendo un nivel constante de alcohol en sus sistemas durante el día para mejorar sus vidas. Sin embargo, pronto se dan cuenta de que puede salirse de control.",
                        4.3F)
        );

        PeliculasDAO.CreatePelicula(
               db, new Pelicula(
                        false,
                        "nomadland",
                        "Nomadland",
                        "2020",
                        "Frances McDormand, David Strathairn, Linda May",
                        "Chloé Zhao",
                        "Una mujer, después del colapso económico de una empresa en una ciudad rural, se convierte en nómada en el oeste americano, explorando la vida fuera de la sociedad convencional mientras viaja en una casa rodante.",
                        0F)

        );

        PeliculasDAO.CreatePelicula(
           db, new Pelicula(
                        false,
                        "dune",
                        "Duna",
                        "2021",
                        "Timothée Chalamet, Rebecca Ferguson, Oscar Isaac",
                        "Denis Villeneuve",
                        "En el futuro distante, Paul Atreides, joven noble de la familia Atreides, se ve destinado a gobernar el desierto planeta Arrakis, la única fuente conocida de la especia más valiosa en el universo.",
                        4.5F)
        );

        PeliculasDAO.CreatePelicula(
               db, new Pelicula(
                        false,
                        "soul",
                        "Alma",
                        "2020",
                        "Jamie Foxx, Tina Fey, Graham Norton",
                        "Pete Docter, Kemp Powers",
                        "Un músico que ha perdido su pasión por la vida es transportado fuera de su cuerpo y debe encontrar el camino de regreso con la ayuda de un alma infantil que aprende sobre sí misma.",
                        4.7f
                ));

        PeliculasDAO.CreatePelicula(

            db, new Pelicula(
                        false,
                        "lalaland",
                        "La La Land",
                        "2016",
                        "Ryan Gosling, Emma Stone, John Legend",
                        "Damien Chazelle",
                        "En Los Ángeles, un pianista de jazz y una aspirante a actriz se enamoran mientras persiguen sus sueños y enfrentan los desafíos de la vida artística.",
                        0f
                ));

        PeliculasDAO.CreatePelicula(

           db, new Pelicula(
                        false,
                        "inception",
                        "Origen",
                        "2010",
                        "Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page",
                        "Christopher Nolan",
                        "Dom Cobb, un ladrón hábil, se especializa en la extracción de secretos valiosos desde los sueños de las personas. Ahora, enfrenta la tarea opuesta: implantar una idea en la mente de alguien.",
                        0f
                ));


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
