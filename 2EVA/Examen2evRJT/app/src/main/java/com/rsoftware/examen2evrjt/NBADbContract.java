package com.rsoftware.examen2evrjt;

import android.provider.BaseColumns;

public abstract  class NBADbContract {


    public static final class  NBAEntry implements BaseColumns{

        public final static String TABLA = "jugadores";
        public final static String NOMBRE = "nombre";
        public final static String EQUIPO = "equipo";
        public final static String DORSAL = "dorsal";
        public final static String CONFERENCIA = "conferencia";
        public final static String POSICION_1 = "posicion_1";
        public final static String POSICION_2 = "posicion_2";
        public final static String POSICION_3 = "posicion_3";
        public final static String FOTO = "foto";


    }



}
