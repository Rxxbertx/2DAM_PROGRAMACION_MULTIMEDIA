package com.jware.examen1ev1_rjt;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class FinalActivity extends AppCompatActivity {

    private  ArrayList<String> historial;

    private LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        layout=findViewById(R.id.layoutHistorial);

        cargarContenido();
        mostrarContenido();

    }


    private void mostrarContenido() {

        int c=1;

        for (String conversion: historial) {

            TextView temp = new TextView(this);
            temp.setText(String.valueOf(c)+" -- "+conversion);
            temp.setTextSize(20F);
            layout.addView(temp);
            c++;


        }




    }

    private void cargarContenido() {

        Intent datos = getIntent();
        historial = datos.getStringArrayListExtra("historial");

    }




}