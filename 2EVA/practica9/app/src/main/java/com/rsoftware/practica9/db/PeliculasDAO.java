package com.rsoftware.practica9.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rsoftware.practica9.model.Pelicula;
import com.rsoftware.practica9.model.PeliculaCollection;

import java.util.ArrayList;
import java.util.List;

public final class PeliculasDAO {

    static PeliculaCollection peliculaCollection = new PeliculaCollection();

    public static void CreatePelicula(SQLiteDatabase database, Pelicula pelicula){

        long newRowId = database.insert(DBContract.DBPeliculaColumnas.TABLE_NAME, null, pelicula.toContentValues());

        pelicula.setId(newRowId);

        peliculaCollection.addPelicula(pelicula);



    }
    public static void CreatePelicula(Pelicula pelicula){


        SQLiteDatabase database = PeliculasDBHelper.getInstance().getWritableDatabase();

        long newRowId = database.insert(DBContract.DBPeliculaColumnas.TABLE_NAME, null, pelicula.toContentValues());

        pelicula.setId(newRowId);

        peliculaCollection.addPelicula(pelicula);

        database.close();


    }
    public static void UpdatePelicula(Pelicula pelicula){

        SQLiteDatabase db = PeliculasDBHelper.getInstance().getWritableDatabase();

        db.update(DBContract.DBPeliculaColumnas.TABLE_NAME, pelicula.toContentValues(), DBContract.DBPeliculaColumnas._ID + " = ?", new String[]{String.valueOf(pelicula.getId())});

        db.close();

    }
    public static void DeletePelicula(Pelicula pelicula){

        SQLiteDatabase db = PeliculasDBHelper.getInstance().getWritableDatabase();

        db.delete(DBContract.DBPeliculaColumnas.TABLE_NAME, DBContract.DBPeliculaColumnas._ID + " = ?", new String[]{String.valueOf(pelicula.getId())});

        peliculaCollection.removePelicula(pelicula);

        db.close();

    }
    public static void ReadPeliculas() {
        SQLiteDatabase db = PeliculasDBHelper.getInstance().getReadableDatabase();

        String[] projection = {
                DBContract.DBPeliculaColumnas._ID,
                DBContract.DBPeliculaColumnas.COLUMN_NAME_TITULO,
                DBContract.DBPeliculaColumnas.COLUMN_NAME_ANIO,
                DBContract.DBPeliculaColumnas.COLUMN_NAME_ACTOR,
                DBContract.DBPeliculaColumnas.COLUMN_NAME_DIRECTOR,
                DBContract.DBPeliculaColumnas.COLUMN_NAME_VISTA,
                DBContract.DBPeliculaColumnas.COLUMN_NAME_SINOPSIS,
                DBContract.DBPeliculaColumnas.COLUMN_NAME_PUNTUACION,
                DBContract.DBPeliculaColumnas.COLUMN_NAME_FOTO
        };

        Cursor cursor = db.query(
                DBContract.DBPeliculaColumnas.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        List<Pelicula> peliculas = new ArrayList<>();
        while (cursor.moveToNext()) {
            Pelicula pelicula = new Pelicula(
                    cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.DBPeliculaColumnas.COLUMN_NAME_VISTA)) == 1,
                    cursor.getString(cursor.getColumnIndexOrThrow(DBContract.DBPeliculaColumnas.COLUMN_NAME_FOTO)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DBContract.DBPeliculaColumnas.COLUMN_NAME_TITULO)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DBContract.DBPeliculaColumnas.COLUMN_NAME_ANIO)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DBContract.DBPeliculaColumnas.COLUMN_NAME_ACTOR)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DBContract.DBPeliculaColumnas.COLUMN_NAME_DIRECTOR)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DBContract.DBPeliculaColumnas.COLUMN_NAME_SINOPSIS)),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(DBContract.DBPeliculaColumnas.COLUMN_NAME_PUNTUACION))
            );
            pelicula.setId(cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.DBPeliculaColumnas._ID)));
            peliculas.add(pelicula);
        }
        cursor.close();
        db.close();

        peliculaCollection.setPeliculas(peliculas);
    }

}
