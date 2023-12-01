package com.rsoftware.practica6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rsoftware.practica6.interfaces.Pregunta;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {


    private final PreguntasManager manager = PreguntasManager.obtenerInstancia();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void btnNext(View view) {

       // Intent int = new Intent(this, PreguntaUnoActivity.class);
        manager.creacionPreguntasAleatorio();
        startActivity(new Intent(this, manager.obtenerPreguntaSiguiente()));

    }
}