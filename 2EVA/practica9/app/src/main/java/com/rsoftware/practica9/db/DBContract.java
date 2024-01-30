package com.rsoftware.practica9.db;

import android.provider.BaseColumns;


public final class DBContract {


    public static final class DBPeliculaColumnas implements BaseColumns {
        public static final String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_ANIO = "anio";
        public static final String COLUMN_NAME_ACTOR = "actor";
        public static final String COLUMN_NAME_DIRECTOR = "director";
        public static final String COLUMN_NAME_VISTA = "vista";
        public static final String COLUMN_NAME_SINOPSIS = "sinopsis";
        public static final String COLUMN_NAME_PUNTUACION = "puntuacion";
        public static final String COLUMN_NAME_FOTO = "foto";
        public static final String TABLE_NAME = "peliculas";

    }




}
