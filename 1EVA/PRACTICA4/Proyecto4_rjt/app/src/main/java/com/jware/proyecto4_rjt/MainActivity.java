package com.jware.proyecto4_rjt;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {


    private int vecesDado4, puntuacionDado4, vecesDado6, puntuacionDado6, vecesDado8, puntuacionDado8;
    private int dadoSelecccionado;

    private int puntuacion = 0;
    private TextView puntuacionText;
    private RadioGroup radioGroup;
    private TextView infoTiradaDado;
    private TextView infoTirada;
    private LinearLayout layoutResultado;
    private GifImageView dadosCarga;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        puntuacionText = findViewById(R.id.puntuacion);
        radioGroup = findViewById(R.id.rdGRoup);
        infoTiradaDado = findViewById(R.id.infoTiradaDado);
        infoTirada = findViewById(R.id.infoTirada);
        layoutResultado = findViewById(R.id.layoutPuntuacion);
        dadosCarga = findViewById(R.id.dadosCarga);
        puntuacionText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!comprobarPuntuacion()) nuevaActividad();
            }
        });

    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //textos
        puntuacionText.setText(savedInstanceState.getCharSequence(getString(R.string.C_puntuaciontext)));
        infoTirada.setText(savedInstanceState.getCharSequence(getString(R.string.C_infotirada)));
        infoTiradaDado.setText(savedInstanceState.getCharSequence(getString(R.string.C_infotiradadado)));
        //numeros
        puntuacion = savedInstanceState.getInt(getString(R.string.C_puntuacion));
        layoutResultado.setVisibility(savedInstanceState.getInt(getString(R.string.C_layoutresultadovisibility)));
        puntuacionDado4 = savedInstanceState.getInt(getString(R.string.C_puntuaciondado4));
        puntuacionDado6 = savedInstanceState.getInt(getString(R.string.C_puntuaciondado6));
        puntuacionDado8 = savedInstanceState.getInt(getString(R.string.C_puntuaciondado8));
        vecesDado4=savedInstanceState.getInt(getString(R.string.C_vecesdado4));
        vecesDado6=savedInstanceState.getInt(getString(R.string.C_vecesdado6));
        vecesDado8=savedInstanceState.getInt(getString(R.string.C_vecesdado8));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        //textos
        outState.putCharSequence(getString(R.string.C_puntuaciontext), puntuacionText.getText());
        outState.putCharSequence(getString(R.string.C_infotirada), infoTirada.getText());
        outState.putCharSequence(getString(R.string.C_infotiradadado), infoTiradaDado.getText());

        //numeros
        outState.putInt(getString(R.string.C_puntuacion), puntuacion);
        outState.putInt(getString(R.string.C_layoutresultadovisibility), layoutResultado.getVisibility());
        outState.putInt(getString(R.string.C_puntuaciondado4), puntuacionDado4);
        outState.putInt(getString(R.string.C_puntuaciondado6), puntuacionDado6);
        outState.putInt(getString(R.string.C_puntuaciondado8), puntuacionDado8);
        outState.putInt(getString(R.string.C_vecesdado4), vecesDado4);
        outState.putInt(getString(R.string.C_vecesdado6), vecesDado6);
        outState.putInt(getString(R.string.C_vecesdado8), vecesDado8);


    }

    public void Reinicio(View view) {
        vecesDado4 = 0;
        puntuacionDado4 = 0;
        vecesDado6 = 0;
        puntuacionDado6 = 0;
        vecesDado8 = 0;
        puntuacionDado8 = 0;
        puntuacion = 0;
        puntuacionText.setText(String.valueOf(0));
        radioGroup.clearCheck();
        layoutResultado.setVisibility(View.GONE);

    }

    public void TirarDado(View view) {

        if (comprobarSeleccionDado()) {

            dadosCarga.setVisibility(View.VISIBLE);
            dadosCarga.postDelayed(() -> {
                dadosCarga.setVisibility(View.GONE);
                tirarDado();
            }, 1000);


        }

    }

    private void nuevaActividad() {

        Intent cambioActivity = new Intent(this, ResultadoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(getString(R.string.C_vecesdado4), vecesDado4);
        bundle.putInt(getString(R.string.C_puntuaciondado4), puntuacionDado4);
        bundle.putInt(getString(R.string.C_vecesdado6), vecesDado6);
        bundle.putInt(getString(R.string.C_puntuaciondado6), puntuacionDado6);
        bundle.putInt(getString(R.string.C_vecesdado8), vecesDado8);
        bundle.putInt(getString(R.string.C_puntuaciondado8), puntuacionDado8);
        bundle.putInt(getString(R.string.C_vecesDadoTotal), vecesDado4 + vecesDado6 + vecesDado8);
        bundle.putInt(getString(R.string.C_puntuacion), puntuacion);
        bundle.putCharSequence(getString(R.string.C_userName), getIntent().getCharSequenceExtra(getString(R.string.C_userName)));
        cambioActivity.putExtras(bundle);
        startActivity(cambioActivity);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();


    }

    private boolean comprobarPuntuacion() {
        return (puntuacion < 24);
    }


    private boolean comprobarSeleccionDado() {

        if (radioGroup.getCheckedRadioButtonId() == -1) {
            showErrorDialog();
            return false;
        }
        return true;

    }

    private void tirarDado() {
        if (radioGroup.getCheckedRadioButtonId() == R.id.dado4caras) {

            vecesDado4++;
            dadoSelecccionado = 4;
            int temp = getRandomNumber(1, dadoSelecccionado);
            puntuacionDado4 += temp;
            puntuacion += temp;


        }
        if (radioGroup.getCheckedRadioButtonId() == R.id.dado6caras) {

            vecesDado6++;
            dadoSelecccionado = 6;
            int temp = getRandomNumber(1, dadoSelecccionado);
            puntuacionDado6 += temp;
            puntuacion += temp;

        }
        if (radioGroup.getCheckedRadioButtonId() == R.id.dado8caras) {

            vecesDado8++;
            dadoSelecccionado = 8;
            int temp = getRandomNumber(1, dadoSelecccionado);
            puntuacionDado8 += temp;
            puntuacion += temp;

        }

        actualizarPuntuacion();


    }

    private void actualizarPuntuacion() {


        layoutResultado.setVisibility(View.VISIBLE);
        infoTiradaDado.setText(infoTiradaDado.getText().toString().replaceAll("\\d", "" + dadoSelecccionado));
        infoTirada.setText(String.valueOf(puntuacion - Integer.parseInt(puntuacionText.getText().toString())));
        puntuacionText.setText(String.valueOf(puntuacion));

    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * max) + min);
    }

    private void showErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.appName)
                .setMessage("DEBES SELECCIONAR UN DADO")
                .setIcon(R.drawable.dice)
                .setPositiveButton("ACEPTAR", null)
                .show();
    }
}
