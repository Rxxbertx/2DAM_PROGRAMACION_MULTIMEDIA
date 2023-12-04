package com.rsoftware.practica6;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class FinalActivity extends AppCompatActivity {

    private TextView nombre;
    private PreguntasManager manager;

    private TextView nota;
    private float puntuacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        nombre=findViewById(R.id.textViewNombre);
        nota=findViewById(R.id.textViewNota);
        manager=PreguntasManager.obtenerInstancia();
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
        obtenerDatos();



    }

    private void obtenerDatos() {

        Intent intent = getIntent();
        Bundle data = intent.getExtras();


        if (data==null)return;

        mostarNota(manager.conseguirPuntuacionTotal(data,this));
        mostrarNombre(data.getCharSequence("nombre","NOMBRE"));
        comprobarFallosAciertos(data);



    }

    private void comprobarFallosAciertos(Bundle datos) {

        CharSequence[]pregunta1 = manager.obtenerDatosPregunta(datos,this,R.string.pregunta1id,PreguntaUnoActivity.class);
        CharSequence[]pregunta2 =manager.obtenerDatosPregunta(datos,this,R.string.pregunta2id,PreguntaDosActivity.class);
        CharSequence[]pregunta3 =manager.obtenerDatosPregunta(datos,this,R.string.pregunta3id,PreguntaTresActivity.class);
        CharSequence[]pregunta4 =manager.obtenerDatosPregunta(datos,this,R.string.pregunta4id,PreguntaCuatroActivity.class);
        CharSequence[]pregunta5 = manager.obtenerDatosPregunta(datos,this,R.string.pregunta5id,PreguntaCincoActivity.class);
        


    }

    private void mostarNota(float conseguirPuntuacionTotal) {

        puntuacion=conseguirPuntuacionTotal;
        nota.setText(String.format(Locale.getDefault(), "%.2f",puntuacion));

    }

    private void mostrarNombre(CharSequence charSequence) {

        nombre.setText(charSequence);

    }

}