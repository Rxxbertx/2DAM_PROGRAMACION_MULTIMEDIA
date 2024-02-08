package com.rsoftware.examen2evrjt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class NBADbHelper extends SQLiteOpenHelper {

    private static  final int DB_VERSION = 1;
    private static final String DB_NAME = "examen2evRJT.db";

    public NBADbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String dbInit = "CREATE TABLE "+NBADbContract.NBAEntry.TABLA+" ( "+
                NBADbContract.NBAEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                NBADbContract.NBAEntry.NOMBRE+" TEXT NOT NULL UNIQUE, "+
                NBADbContract.NBAEntry.EQUIPO+" TEXT, "+
                NBADbContract.NBAEntry.DORSAL+" INTEGER, "+
                NBADbContract.NBAEntry.CONFERENCIA+" TEXT, "+
                NBADbContract.NBAEntry.POSICION_1+" TEXT NOT NULL, "+
                NBADbContract.NBAEntry.POSICION_2+" TEXT, "+
                NBADbContract.NBAEntry.POSICION_3+" TEXT, "+
                NBADbContract.NBAEntry.FOTO+" TEXT );";



        db.execSQL(dbInit);


        Jugador lebron = new Jugador(

                "Lebron James","Los Angeles Lakers",6,"Oeste","SF",null,null,"l_james"


        );

        lebron.setId((int) db.insert(NBADbContract.NBAEntry.TABLA,null,lebron.toContentValues()));



        Jugador james = new Jugador(

                "James Harden","Philadelphia 76ers",1,"este","PG","SG","SF","j_harden"


        );

        james.setId((int) db.insert(NBADbContract.NBAEntry.TABLA,null,james.toContentValues()));


        Jugador nikola = new Jugador(

                "Nikola Jokic","Denver Nuggets",15,"oeste","C",null,null,"n_jokic"


        );

        nikola.setId((int) db.insert(NBADbContract.NBAEntry.TABLA,null,nikola.toContentValues()));



        JugadorCollection jugadorCollection = new JugadorCollection();

        List<Jugador> jugadorList = new ArrayList<>();

        jugadorList.add(lebron);
        jugadorList.add(james);
        jugadorList.add(nikola);

        jugadorCollection.setJugadorList(jugadorList);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
