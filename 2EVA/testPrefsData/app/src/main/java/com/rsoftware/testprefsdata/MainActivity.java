package com.rsoftware.testprefsdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences config = getSharedPreferences("FicheroConfiguracion", MODE_PRIVATE);

        config.edit().putString("usuario","Alexis").apply();
        String usuario = config.getString("usuario", "invitado");
        Toast.makeText(this, "Usuario" + usuario, Toast.LENGTH_SHORT).show();


        AlumnosHelper helper = new AlumnosHelper(this);
        helper.getWritableDatabase();



    }
}