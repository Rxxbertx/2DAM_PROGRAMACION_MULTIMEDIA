package com.jware.proyecto4_rjt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    private int vecesDado4, puntuacionDado4, vecesDado6, puntuacionDado6, vecesDado8, puntuacionDado8;
    private int dadoSelecccionado;

    private int puntuacion=0;
    private TextView puntuacionText;
    private RadioGroup radioGroup;
    private TextView infoTiradaDado;
    private TextView infoTirada;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        puntuacionText = findViewById(R.id.puntuacion);
        radioGroup = findViewById(R.id.rdGRoup);
        infoTiradaDado= findViewById(R.id.infoTiradaDado);
        infoTirada= findViewById(R.id.infoTirada);


    }

    public void Reinicio(View view) {
        vecesDado4 = 0;
        puntuacionDado4 = 0;
        vecesDado6 = 0;
        puntuacionDado6 = 0;
        vecesDado8 = 0;
        puntuacionDado8 = 0;

        puntuacionText.setText(0);
        radioGroup.clearCheck();

    }

    public void TirarDado(View view) {

        if (comprobarSeleccionDado()){

            if (comprobarPuntuacion()) {
                tirarDado();
            } else {
                nuevaActividad();
            }
        }

    }

    private void nuevaActividad() {

        Intent cambioActivity = new Intent(this, ResultadoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("vecesDado4", vecesDado4);
        bundle.putInt("puntuacionDado4", puntuacionDado4);
        bundle.putInt("vecesDado6", vecesDado6);
        bundle.putInt("puntuacionDado6", puntuacionDado6);
        bundle.putInt("vecesDado8", vecesDado8);
        bundle.putInt("puntuacionDado8", puntuacionDado8);
        bundle.putInt("totalDadosLanzados", vecesDado4+vecesDado6+vecesDado8);
        bundle.putInt("puntuacion", puntuacion);
        cambioActivity.putExtras(bundle);
        startActivity(cambioActivity);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
        


    }

    private boolean comprobarPuntuacion() {
        return (puntuacion <= 24);
    }


    private boolean comprobarSeleccionDado() {

        if (radioGroup.getCheckedRadioButtonId()==-1){
            showErrorDialog("DEBES SELECCIONAR UN DADO");
            return false;
        }
        return true;

    }

    private void tirarDado() {
        if (radioGroup.getCheckedRadioButtonId()==R.id.dado4caras) {

                vecesDado4++;
                dadoSelecccionado = 4;
                puntuacionDado4=getRandomNumber(1,dadoSelecccionado);
                puntuacion+=puntuacionDado4;


        }
        if (radioGroup.getCheckedRadioButtonId()==R.id.dado6caras) {

                vecesDado6++;
                dadoSelecccionado = 6;
                puntuacionDado6=getRandomNumber(1,dadoSelecccionado);
                puntuacion+=puntuacionDado6;

        }
        if (radioGroup.getCheckedRadioButtonId()==R.id.dado8caras) {

                vecesDado8++;
                dadoSelecccionado = 8;
                puntuacionDado8=getRandomNumber(1,dadoSelecccionado);
                puntuacion+=puntuacionDado8;

        }
        
        actualizarPuntuacion();
        
        
    }

    private void actualizarPuntuacion() {


        infoTiradaDado.setText(((String) infoTiradaDado.getText()).replaceAll("\\d",""+dadoSelecccionado));
        infoTirada.setText(String.valueOf(puntuacion-Integer.valueOf((String) puntuacionText.getText())));
        puntuacionText.setText(String.valueOf(puntuacion));

    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * max) + min);
    }
    private void showErrorDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.appName)
                .setMessage(message)
                .setIcon(R.drawable.dice)
                .setPositiveButton("ACEPTAR", null)
                .show();
    }
}
