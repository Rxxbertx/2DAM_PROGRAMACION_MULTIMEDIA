package com.jware.examen1ev1_rjt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static String DOLARES = "dólares";
    private static String EUROS = "euros";
    private static String BITCOIN = "bitcoins";

    private EditText importe;

    private RadioGroup radioGroupOrigen;
    private RadioGroup radioGroupDestino;


    private float importeFloat;

    private TextView resultadoTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        importe = findViewById(R.id.importe);
        radioGroupOrigen = findViewById(R.id.rdGroupOrigen);
        radioGroupDestino = findViewById(R.id.rdGroupDestino);
        resultadoTxt = findViewById(R.id.resultadoTxt);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence("resultado",resultadoTxt.getText());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        resultadoTxt.setText(savedInstanceState.getCharSequence("resultado"));
    }

    public void convertir(View view) {

        comprobarSeleccionImporte();
    }

    private void comprobarSeleccionImporte() {


        if (comprobarImporte()) seleccionOrigen();
        //se iban a implementar con booleanos pero al final no hace falta

    }

    private boolean seleccionOrigen() {

        if (radioGroupOrigen.getCheckedRadioButtonId() == R.id.radioButtonBTC) {
            return seleccionDestino(BITCOIN);

        }
        if (radioGroupOrigen.getCheckedRadioButtonId() == R.id.radioButtonEUR) {
            return seleccionDestino(EUROS);

        }
        if (radioGroupOrigen.getCheckedRadioButtonId() == R.id.radioButtonUSD) {
            return seleccionDestino(DOLARES);

        }

        Toast.makeText(this, "DEBES SELECCIONAR UN ORIGEN", Toast.LENGTH_LONG).show();

        return false;


    }

    private boolean seleccionDestino(String origen) {

        if (radioGroupDestino.getCheckedRadioButtonId() == R.id.radioButtonBTC1) {
            mostrarResultado(origen, BITCOIN);
            return true;
        }
        if (radioGroupDestino.getCheckedRadioButtonId() == R.id.radioButtonEUR1) {
            mostrarResultado(origen, EUROS);
            return true;
        }
        if (radioGroupDestino.getCheckedRadioButtonId() == R.id.radioButtonUSD1) {
            mostrarResultado(origen, DOLARES);
            return true;
        }

        Toast.makeText(this, "DEBES SELECCIONAR UN DESTINO", Toast.LENGTH_LONG).show();


        return false;


    }

    private void mostrarResultado(String origen, String destino) {

        resultadoTxt.setText(importeFloat + " " + origen + " son " + importeFloat + " " + destino);


    }

    private boolean comprobarImporte() {
        if (importe.getText().length() != 0) {

            try {
                importeFloat = Float.valueOf(String.valueOf(importe.getText()));
            } catch (Exception e) {

                Toast.makeText(this, "INTRODUCE UN IMPORTE CORRECTO", Toast.LENGTH_LONG).show();

            }

        } else {
            Toast.makeText(this, "INTRODUCE UN IMPORTE", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}