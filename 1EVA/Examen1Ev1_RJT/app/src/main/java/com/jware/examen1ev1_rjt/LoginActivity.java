package com.jware.examen1ev1_rjt;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.admin.SystemUpdatePolicy;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity {


    private ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        imagen=findViewById(R.id.imageLogo);

        animacion();



    }

    private void animacion() {

        ObjectAnimator ani1 = ObjectAnimator.ofFloat(imagen,"scaleX",1f,0.5f);
        ObjectAnimator ani2 = ObjectAnimator.ofFloat(imagen,"scaleY",1f,0.5f);
        ani1.setRepeatCount(ValueAnimator.INFINITE);
        ani2.setRepeatCount(ValueAnimator.INFINITE);
        ani1.setRepeatMode(ValueAnimator.REVERSE);
        ani2.setRepeatMode(ValueAnimator.REVERSE);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(ani1,ani2);
        set.setDuration(2000);
        set.start();




    }

    public void siguiente(View view) {

        Intent main = new Intent(this, MainActivity.class);


        if (view.getId()==R.id.buttonNormal){

            main.putExtra("modo",false);
            startActivity(main);

        }
        if (view.getId()==R.id.buttonAvanzado){

            main.putExtra("modo",true);
            startActivity(main);

        }



    }
}