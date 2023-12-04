package com.rsoftware.practica6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.ChipGroup;

public class MainActivity extends AppCompatActivity {


    private final PreguntasManager manager = PreguntasManager.obtenerInstancia();

    private EditText nombre;
    private ChipGroup chipGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = findViewById(R.id.editTextName);
        chipGroup = findViewById(R.id.chipGroup);
    }



    public void btnNext(View view) {

        if(!comprobarNombre()){
            Toast.makeText(this, R.string.ingresa_un_nombre,Toast.LENGTH_LONG).show();
            return;
        }
        if (!comprobarPuntuacion()){
            Toast.makeText(this, R.string.selecciona_la_puntuacion,Toast.LENGTH_LONG).show();
            return;
        }
        manager.creacionPreguntasAleatorio();
        Intent intent = new Intent(this, manager.obtenerPreguntaSiguiente());
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT|Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        Bundle bundle = new Bundle();
        bundle.putString("nombre",nombre.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    private boolean comprobarNombre() {

        return !nombre.getText().toString().isBlank();
    }
    private boolean comprobarPuntuacion(){

        int chip100 = findViewById(R.id.chip100).getId();
        int chip10 = findViewById(R.id.chip10).getId();
        int checkedChip =chipGroup.getCheckedChipId();
        if (checkedChip==chip100){
            manager.modificarPuntuacion(10);
            return true;
        } else if (checkedChip==chip10) {
            manager.modificarPuntuacion(1);
            return true;
        }
        return false;

    }
}