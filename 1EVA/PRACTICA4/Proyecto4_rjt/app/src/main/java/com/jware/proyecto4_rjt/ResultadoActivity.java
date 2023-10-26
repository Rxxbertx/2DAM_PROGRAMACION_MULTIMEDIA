package com.jware.proyecto4_rjt;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity {

    private TextView userResult, userResult4, userResult6, userResult8;
    OnBackPressedCallback callback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        userResult = findViewById(R.id.userResult);
        userResult4 = findViewById(R.id.userResult4);
        userResult6 = findViewById(R.id.userResult6);
        userResult8 = findViewById(R.id.userResult8);

        showIntentData();

        controlBtnSalir();

    }

    private void controlBtnSalir() {
        callback = new OnBackPressedCallback(true) {
           @Override
           public void handleOnBackPressed() {
               // Agrega aquí la lógica para redirigir al usuario a la pantalla deseada
               Intent intent = new Intent(ResultadoActivity.this, LoginActivity.class);
               startActivity(intent);
               finish(); // Opcional, para cerrar la actividad actual
           }
       };

        getOnBackPressedDispatcher().addCallback(callback);
    }

    private void showIntentData() {

        Bundle datos = getIntent().getExtras();

//usuario

        assert datos != null;
        userResult.setText(userResult.getText().toString().replace("Usuario", datos.getString(getString(R.string.C_userName)))
                .replaceAll("XX", String.valueOf(datos.getInt(getString(R.string.C_puntuacion))))
                .replace("X", String.valueOf(datos.getInt(getString(R.string.C_vecesDadoTotal)))));

        //dddo4
        userResult4.setText(userResult4.getText().toString().replaceAll("XX", String.valueOf(datos.getInt(getString(R.string.C_puntuaciondado4))))
                .replaceAll("X", String.valueOf(datos.getInt(getString(R.string.C_vecesdado4)))));

//dddo6
        userResult6.setText(userResult6.getText().toString().replaceAll("XX", String.valueOf(datos.getInt(getString(R.string.C_puntuaciondado6))))
                .replaceAll("X", String.valueOf(datos.getInt(getString(R.string.C_vecesdado6)))));
        //dddo8
        userResult8.setText(userResult8.getText().toString().replaceAll("XX", String.valueOf(datos.getInt(getString(R.string.C_puntuaciondado8))))
                .replaceAll("X", String.valueOf(datos.getInt(getString(R.string.C_vecesdado8)))));


    }

    public void onClickSiguiente(View view) {

        Intent pantallaLogin = new Intent(this, LoginActivity.class);
        startActivity(pantallaLogin);
        finish();

    }
    // Agrega un callback para el botón "Atrás"


}