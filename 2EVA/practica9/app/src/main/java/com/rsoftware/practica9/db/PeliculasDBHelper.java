package com.rsoftware.practica9.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PeliculasDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Peliculas.db";


    public PeliculasDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("CREATE TABLE " + DBContract.DBPeliculaColumnas.TABLE_NAME + " (" +
                DBContract.DBPeliculaColumnas._ID + " INTEGER PRIMARY KEY," +
                DBContract.DBPeliculaColumnas.COLUMN_NAME_TITULO + " TEXT NOT NULL," +
                DBContract.DBPeliculaColumnas.COLUMN_NAME_ANIO + " TEXT NOT NULL," +
                DBContract.DBPeliculaColumnas.COLUMN_NAME_ACTOR + " TEXT NOT NULL," +
                DBContract.DBPeliculaColumnas.COLUMN_NAME_DIRECTOR + " TEXT NOT NULL," +
                DBContract.DBPeliculaColumnas.COLUMN_NAME_VISTA + " INTEGER NOT NULL," +
                DBContract.DBPeliculaColumnas.COLUMN_NAME_SINOPSIS + " TEXT NOT NULL," +
                DBContract.DBPeliculaColumnas.COLUMN_NAME_PUNTUACION + " REAL NOT NULL," +
                DBContract.DBPeliculaColumnas.COLUMN_NAME_FOTO + " TEXT NOT NULL)");


        db.insert(DBContract.DBPeliculaColumnas.TABLE_NAME, null, DBContract.DBPeliculaColumnas.crearPelicula("El Padrino", "1972", "Marlon Brando, Al Pacino, James Caan, Robert Duvall, Diane Keaton", "Francis Ford Coppola", 1, "La familia Corleone es una de las más importantes de la mafia de Nueva York. Sin embargo, Michael, el hijo menor de Don Vito Corleone, no quiere saber nada de los negocios de su padre.", 5, "el_padrino"));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
