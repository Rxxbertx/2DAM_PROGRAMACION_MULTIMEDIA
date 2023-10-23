package com.jware.testclase3;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private static int contador = 0;
    private static int agregacion = 1;
    private static final AnimationScale aniBotonMenos = new AnimationScale();
    private static final AnimationScale aniBotonMas = new AnimationScale();
    private static final AnimationScale aniCongrats = new AnimationScale();

    private String[] colorsArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerEspeciales();
        crearAnimacionBotones();


    }


    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("contador", contador);
        outState.putInt("agregacion", agregacion);
        outState.putInt("colorContador", ((TextView) findViewById(R.id.contador)).getCurrentTextColor());

        outState.putInt("colorFondo", findViewById(R.id.mainView).getBackgroundTintList().getDefaultColor());

        outState.putInt("chipNegative", ((Chip) findViewById(R.id.chipNegative)).isChecked() ? 1 : 0);
        outState.putInt("chip2by2", ((Chip) findViewById(R.id.chip2by2)).isChecked() ? 1 : 0);
        outState.putInt("chipLimit", ((Chip) findViewById(R.id.chipLimit)).isChecked() ? 1 : 0);
        outState.putInt("chipColor", ((Chip) findViewById(R.id.chipColor)).isChecked() ? 1 : 0);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        contador = savedInstanceState.getInt("contador");
        agregacion = savedInstanceState.getInt("agregacion");
        ((TextView) findViewById(R.id.contador)).setTextColor(savedInstanceState.getInt("colorContador"));
        findViewById(R.id.mainView).getBackground().setTint(savedInstanceState.getInt("colorFondo"));
        ((Chip) findViewById(R.id.chipNegative)).setChecked(savedInstanceState.getInt("chipNegative") == 1);
        ((Chip) findViewById(R.id.chip2by2)).setChecked(savedInstanceState.getInt("chip2by2") == 1);
        ((Chip) findViewById(R.id.chipLimit)).setChecked(savedInstanceState.getInt("chipLimit") == 1);
        ((Chip) findViewById(R.id.chipColor)).setChecked(savedInstanceState.getInt("chipColor") == 1);
        ((TextView)findViewById(R.id.contador)).setText(String.valueOf(contador));

        if (((Chip) findViewById(R.id.chipLimit)).isChecked())
            findViewById(R.id.editTextMeta).setVisibility(EditText.VISIBLE);
        else
            findViewById(R.id.editTextMeta).setVisibility(EditText.GONE);
        if (((Chip) findViewById(R.id.chipColor)).isChecked()) {
            findViewById(R.id.spinnerFondo).setVisibility(View.VISIBLE);
            findViewById(R.id.spinnerContador).setVisibility(View.VISIBLE);
            findViewById(R.id.textViewContador).setVisibility(View.VISIBLE);
            findViewById(R.id.textViewFondo).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.spinnerFondo).setVisibility(View.GONE);
            findViewById(R.id.spinnerContador).setVisibility(View.GONE);
            findViewById(R.id.textViewContador).setVisibility(View.GONE);
            findViewById(R.id.textViewFondo).setVisibility(View.GONE);
        }

    }



    private void crearAnimacionBotones() {


        aniBotonMas.crearAnimacion(findViewById(R.id.btnMas), 0.75f);
        aniBotonMenos.crearAnimacion(findViewById(R.id.btnMenos), 0.75f);
        aniCongrats.crearAnimacion(findViewById(R.id.congratsImg), 1.2f);

    }

    private void spinnerEspeciales() {
        Spinner colorContadorSpinner = findViewById(R.id.spinnerContador);
        Spinner colorFondoSpinner = findViewById(R.id.spinnerFondo);

        colorsArray = getResources().getStringArray(R.array.colors_array);
        ColorAdapter colorAdapter = new ColorAdapter(this, colorsArray);
        colorFondoSpinner.setAdapter(colorAdapter);
        colorContadorSpinner.setAdapter(colorAdapter);
        colorFondoSpinner.setOnItemSelectedListener(this);
        colorContadorSpinner.setOnItemSelectedListener(this);
        colorContadorSpinner.setSelection(colorsArray.length - 1);
        colorFondoSpinner.setSelection(colorsArray.length - 2);
    }


    @Override
    public void onClick(View view) {


        if (view.getId() == R.id.btnContinuar){

            findViewById(R.id.congratsImg).setVisibility(View.GONE);
            findViewById(R.id.fondoDifuminado).setVisibility(View.GONE);
            findViewById(R.id.btnContinuar).setVisibility(View.GONE);
            activacionBotones(true);

            ((EditText)findViewById(R.id.editTextMeta)).setText("");
            contador=0;
            ((TextView) findViewById(R.id.contador)).setText(String.valueOf(contador));

        }


        if (view.getId() == R.id.btnMenos) {


            if (contador <= 0 && ((Chip) findViewById(R.id.chipNegative)).isChecked()) {
                Toast.makeText(this, "No puedes disminuir el contador, Activa Negativos", Toast.LENGTH_SHORT).show();
                return;
            }

            aniBotonMenos.start();
            contador = comprobarRestricciones(contador - agregacion);
            ((TextView) findViewById(R.id.contador)).setText(String.valueOf(contador));


        }
        if (view.getId() == R.id.btnMas) {


            aniBotonMas.start();
            contador = comprobarRestricciones(contador + agregacion);
            ((TextView) findViewById(R.id.contador)).setText(String.valueOf(contador));


        }

        if (view.getId() == R.id.chipLimit) {


            if (((Chip) findViewById(R.id.chipLimit)).isChecked())
                findViewById(R.id.editTextMeta).setVisibility(EditText.VISIBLE);
            else
                findViewById(R.id.editTextMeta).setVisibility(EditText.GONE);

        }


        if (view.getId() == R.id.chip2by2) {

            if (((Chip) findViewById(R.id.chip2by2)).isChecked()) {
                agregacion = 2;
            } else agregacion = 1;
        }


        if (view.getId() == R.id.chipColor) {

            if (((Chip) findViewById(R.id.chipColor)).isChecked()) {
                findViewById(R.id.spinnerFondo).setVisibility(View.VISIBLE);
                findViewById(R.id.spinnerContador).setVisibility(View.VISIBLE);
                findViewById(R.id.textViewContador).setVisibility(View.VISIBLE);
                findViewById(R.id.textViewFondo).setVisibility(View.VISIBLE);
            } else {
                findViewById(R.id.spinnerFondo).setVisibility(View.GONE);
                findViewById(R.id.spinnerContador).setVisibility(View.GONE);
                findViewById(R.id.textViewContador).setVisibility(View.GONE);
                findViewById(R.id.textViewFondo).setVisibility(View.GONE);
            }

        }


    }

    private int comprobarRestricciones(int i) {


        if (((Chip) findViewById(R.id.chipLimit)).isChecked()) {

            String limite = ((EditText) findViewById(R.id.editTextMeta)).getText().toString();

            if (limite.isEmpty()) {
                Toast.makeText(this, "Debe ingresar un limite", Toast.LENGTH_SHORT).show();

            } else if (Integer.parseInt(limite) == i) {
                Toast.makeText(this, "ENHORABUENA, CLIC EN EL BOTON PARA SEGUIR", Toast.LENGTH_SHORT).show();
                findViewById(R.id.congratsImg).setVisibility(View.VISIBLE);
                findViewById(R.id.fondoDifuminado).setVisibility(View.VISIBLE);
                findViewById(R.id.btnContinuar).setVisibility(View.VISIBLE);
                activacionBotones(false);


            }


        }


        return i;

    }

    private void activacionBotones(boolean estado) {


        findViewById(R.id.chipNegative).setEnabled(estado);
        findViewById(R.id.chip2by2).setEnabled(estado);
        findViewById(R.id.chipLimit).setEnabled(estado);
        findViewById(R.id.chipColor).setEnabled(estado);
        findViewById(R.id.btnMenos).setEnabled(estado);
        findViewById(R.id.btnMas).setEnabled(estado);



    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        int colorValue = Color.parseColor(colorsArray[position]);


        if (adapterView.getId() == R.id.spinnerContador) {


            // Cambia el backgroundTint del fondo del Spinner
            ((TextView) findViewById(R.id.contador)).setTextColor(colorValue);

        }
        if (adapterView.getId() == R.id.spinnerFondo) {


            // Cambia el backgroundTint del fondo del Spinner
            findViewById(R.id.mainView).getBackground().setTint(colorValue);

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}