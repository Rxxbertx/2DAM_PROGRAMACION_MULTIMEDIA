package com.jware.examen1ev1_rjt;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String DOLARES = "dólares";
    private static String EUROS = "euros";
    private static String BITCOIN = "bitcoins";

    private Conversor conversor = new Conversor();
    private static ArrayList<String> historial = new ArrayList<>();
    private EditText importe;

    private RadioGroup radioGroupOrigen;
    private RadioGroup radioGroupDestino;
    private float importeFloat;
    private TextView resultadoTxt;

    private TextView monedaSeleccionada;
    private TextView enlaceAMoneda;

    private boolean avanzado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        importe = findViewById(R.id.importe);
        radioGroupOrigen = findViewById(R.id.rdGroupOrigen);
        radioGroupDestino = findViewById(R.id.rdGroupDestino);
        resultadoTxt = findViewById(R.id.resultadoTxt);
        monedaSeleccionada = findViewById(R.id.monedaSeleccionada);

        botonAtras();

        listeners();

        recuperarDatos();
        comprobarVisibilidad();


    }

    private void botonAtras() {

        OnBackPressedCallback back = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                finish();

            }
        };

        getOnBackPressedDispatcher().addCallback(back);


    }

    private void listeners() {
        radioGroupOrigen.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                findViewById(R.id.enlaceMoneda).setVisibility(View.INVISIBLE);
                resultadoTxt.setText("");

                if ((R.id.radioButtonUSD) == checkedId) {
                    monedaSeleccionada.setText(constantes.DOLARES);
                }
                if ((R.id.radioButtonEUR) == checkedId) {
                    monedaSeleccionada.setText(constantes.EUROS);
                }
                if ((R.id.radioButtonBTC) == checkedId) {
                    monedaSeleccionada.setText(constantes.BITCOIN);
                }

            }
        });

        radioGroupDestino.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                findViewById(R.id.enlaceMoneda).setVisibility(View.INVISIBLE);
                resultadoTxt.setText("");

            }
        });


    }

    private void comprobarVisibilidad() {

        if (avanzado) {
            findViewById(R.id.radioButtonBTC).setVisibility(View.VISIBLE);
            findViewById(R.id.radioButtonBTC1).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.radioButtonBTC).setVisibility(View.INVISIBLE);
            findViewById(R.id.radioButtonBTC1).setVisibility(View.INVISIBLE);
        }

    }

    private void recuperarDatos() {


        Intent data = getIntent();

        avanzado=data.getBooleanExtra("modo",false);



    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence("resultado", resultadoTxt.getText());
        outState.putInt("visibilidadEnlace",findViewById(R.id.enlaceMoneda).getVisibility());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        resultadoTxt.setText(savedInstanceState.getCharSequence("resultado"));
        findViewById(R.id.enlaceMoneda).setVisibility(savedInstanceState.getInt("visibilidadEnlace"));
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

            return seleccionDestino(constantes.BITCOIN);

        }
        if (radioGroupOrigen.getCheckedRadioButtonId() == R.id.radioButtonEUR) {

            return seleccionDestino(constantes.EUROS);

        }
        if (radioGroupOrigen.getCheckedRadioButtonId() == R.id.radioButtonUSD) {

            return seleccionDestino(constantes.DOLARES);

        }

        Toast.makeText(this, "DEBES SELECCIONAR UN ORIGEN", Toast.LENGTH_LONG).show();

        return false;


    }

    private boolean seleccionDestino(String origen) {

        if (radioGroupDestino.getCheckedRadioButtonId() == R.id.radioButtonBTC1) {
            mostrarResultado(origen, constantes.BITCOIN);
            return true;
        }
        if (radioGroupDestino.getCheckedRadioButtonId() == R.id.radioButtonEUR1) {
            mostrarResultado(origen, constantes.EUROS);
            return true;
        }
        if (radioGroupDestino.getCheckedRadioButtonId() == R.id.radioButtonUSD1) {
            mostrarResultado(origen, constantes.DOLARES);
            return true;
        }

        Toast.makeText(this, "DEBES SELECCIONAR UN DESTINO", Toast.LENGTH_LONG).show();


        return false;


    }

    private void mostrarResultado(String origen, String destino) {

        mostrarEnlaceWeb(origen,destino);

        double temp = Conversor.obtenerConversion(origen, destino, importeFloat);
        DecimalFormat format = new DecimalFormat("#.########");

        resultadoTxt.setText(importeFloat + " " + origen + " son " + format.format(temp) + " " + destino);
        historial.add(String.valueOf(resultadoTxt.getText()));

    }

    private void mostrarEnlaceWeb(String origen,String destino) {

        if (origen.equals(constantes.BITCOIN)||destino.equals(constantes.BITCOIN)){
            findViewById(R.id.enlaceMoneda).setVisibility(View.VISIBLE);
        }else{
            findViewById(R.id.enlaceMoneda).setVisibility(View.INVISIBLE);
        }


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

    public void navegar(View view) {

        Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse("https://coinmarketcap.com/es/currencies/bitcoin/"));
        startActivity(web);


    }

    public void finalizarSesion(View view) {

        Intent finalAct = new Intent(this, FinalActivity.class);
        finalAct.putExtra("historial",historial);
        startActivity(finalAct);
        finish();

    }
}