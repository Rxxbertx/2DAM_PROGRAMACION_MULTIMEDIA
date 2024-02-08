package com.rsoftware.examen2evrjt;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class NBADao {


    private Context context;


    public NBADao(Context context) {
        this.context = context;
    }

    public void ReadAll() {

        NBADbHelper nbaDbHelper = new NBADbHelper(context);

        JugadorCollection jugadorCollection = new JugadorCollection();

        List<Jugador> jugadorList = new ArrayList<>();

        String[] columns = {NBADbContract.NBAEntry._ID, NBADbContract.NBAEntry.NOMBRE, NBADbContract.NBAEntry.EQUIPO, NBADbContract.NBAEntry.DORSAL, NBADbContract.NBAEntry.CONFERENCIA,

                NBADbContract.NBAEntry.POSICION_1, NBADbContract.NBAEntry.POSICION_2, NBADbContract.NBAEntry.POSICION_3, NBADbContract.NBAEntry.FOTO


        };

        try (Cursor cursor = nbaDbHelper.getReadableDatabase().query(


                NBADbContract.NBAEntry.TABLA,
                columns,
                null, null, null, null, null)) {


            while (cursor.moveToNext()) {

                Jugador jugador = new Jugador(


                        cursor.getString(cursor.getColumnIndexOrThrow(NBADbContract.NBAEntry.NOMBRE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(NBADbContract.NBAEntry.EQUIPO)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(NBADbContract.NBAEntry.DORSAL)),
                        cursor.getString(cursor.getColumnIndexOrThrow(NBADbContract.NBAEntry.CONFERENCIA)),
                        cursor.getString(cursor.getColumnIndexOrThrow(NBADbContract.NBAEntry.POSICION_1)),
                        cursor.getString(cursor.getColumnIndexOrThrow(NBADbContract.NBAEntry.POSICION_2)),
                        cursor.getString(cursor.getColumnIndexOrThrow(NBADbContract.NBAEntry.POSICION_3)),
                        cursor.getString(cursor.getColumnIndexOrThrow(NBADbContract.NBAEntry.FOTO))


                );

                jugador.setId(cursor.getInt(cursor.getColumnIndexOrThrow(NBADbContract.NBAEntry._ID)));

                jugadorList.add(jugador);


            }

            jugadorCollection.setJugadorList(jugadorList);


        }

       nbaDbHelper.close();
    }


}
