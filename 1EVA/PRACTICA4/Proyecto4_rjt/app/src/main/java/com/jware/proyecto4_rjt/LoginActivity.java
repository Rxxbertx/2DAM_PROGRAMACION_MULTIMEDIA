package com.jware.proyecto4_rjt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText userText;
    private CardView cardViewuser;
    private CardView cardViewsiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cardViewsiguiente = findViewById(R.id.cardViewSiguiente);
        cardViewuser = findViewById(R.id.cardViewUser);
        userText = findViewById(R.id.userEdit);



        animacionesEntrada();



    }
    public void comprobarNombre(View view) {

        if (userText.getText().length()<2)
            showErrorDialog("DEBES INTRODUCIR UN NOMBR DE USUARIO QUE CONTEGA AL MENOS 2 CARACTERES");
        else animacionesSalida();
    }
    private void showErrorDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.appName)
                .setMessage(message)
                .setIcon(R.drawable.dice)
                .setPositiveButton("ACEPTAR", null)
                .show();
    }
    private void animacionesEntrada() {

        // Obtener el ancho de la pantalla
        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        // Calcular la cantidad de desplazamiento basada en el ancho de la pantalla


        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(cardViewsiguiente, "translationX", screenWidth, 20f,1f,0f);
        ObjectAnimator translationAnimator2 = ObjectAnimator.ofFloat(cardViewuser, "translationX", -screenWidth, 20f,1f,0f);

        AnimatorSet animacionesEntrada = new AnimatorSet();
        animacionesEntrada.playTogether(translationAnimator,translationAnimator2);
        animacionesEntrada.setDuration(1500);
        animacionesEntrada.start();
    }



    private void animacionesSalida() {

        // Obtener el ancho de la pantalla
        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        // Calcular la cantidad de desplazamiento basada en el ancho de la pantalla


        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(cardViewsiguiente, "translationX", screenWidth);
        ObjectAnimator translationAnimator2 = ObjectAnimator.ofFloat(cardViewuser, "translationX", -screenWidth);

        AnimatorSet animacionesSalida = new AnimatorSet();
        animacionesSalida.playTogether(translationAnimator,translationAnimator2);
        animacionesSalida.setDuration(500);
        animacionesSalida.start();
        animacionesSalida.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animator) {


            }

            @Override
            public void onAnimationEnd(@NonNull Animator animator) {

                Intent segundaAct = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(segundaAct);
                segundaAct.putExtra("nombreUsuario",userText.getText().toString());
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();


            }

            @Override
            public void onAnimationCancel(@NonNull Animator animator) {

            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animator) {

            }
        });

    }


}