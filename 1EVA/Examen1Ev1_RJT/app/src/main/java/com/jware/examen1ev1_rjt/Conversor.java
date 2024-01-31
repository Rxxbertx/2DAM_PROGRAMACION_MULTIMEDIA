package com.jware.examen1ev1_rjt;

import java.util.HashMap;
import com.jware.examen1ev1_rjt.*;

public class Conversor {

    public static HashMap<String,HashMap<String,Float>> conversorMonedas = new HashMap<>();


    public Conversor(){

        añadirDivisas(constantes.DOLARES,constantes.EUROS,0.92f);
        añadirDivisas(constantes.DOLARES,constantes.BITCOIN,0.000027F);
        añadirDivisas(constantes.EUROS,constantes.DOLARES,1.1F);
        añadirDivisas(constantes.EUROS,constantes.BITCOIN,0.000029F);
        añadirDivisas(constantes.BITCOIN,constantes.EUROS,34500F);
        añadirDivisas(constantes.BITCOIN,constantes.DOLARES,37500F);

    }




    public static void añadirDivisas(String origen, String destino, float valor){

        if (conversorMonedas.containsKey(origen)){
            conversorMonedas.get(origen).put(destino,valor);

        }else{
            HashMap<String,Float> temp = new HashMap<String,Float>();
            temp.put(destino,valor);
            conversorMonedas.put(origen,temp);
        }
    }

    public static float obtenerConversion(String origen, String destino, float cantidad){

        if (origen.equals(destino))return cantidad;

        if (conversorMonedas.containsKey(origen)){

            return conversorMonedas.get(origen).get(destino)*cantidad;

        }else{
            return -1f;
        }
    }


}
