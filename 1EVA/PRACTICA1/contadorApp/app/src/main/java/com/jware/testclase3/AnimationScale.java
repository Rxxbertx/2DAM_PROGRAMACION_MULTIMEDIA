package com.jware.testclase3;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import androidx.annotation.NonNull;

public class AnimationScale implements Animator.AnimatorListener{


    private  ObjectAnimator animacion;
    private  ObjectAnimator animacion2;
    private  ObjectAnimator animacionR;
    private  ObjectAnimator animacionR2;

    protected void crearAnimacion(View view, float toEscala){

        animacion = ObjectAnimator.ofFloat(view,"scaleX",toEscala);
        animacion2 = ObjectAnimator.ofFloat(view,"scaleY",toEscala);
        animacionR = ObjectAnimator.ofFloat(view,"scaleX",1);
        animacionR2 = ObjectAnimator.ofFloat(view,"scaleY",1);
        animacion.addListener(this);
        animacion2.addListener(this);
    }

    public void start(){
        animacion.start();
        animacion2.start();
    }


    @Override
    public void onAnimationStart(@NonNull Animator animator) {

    }

    @Override
    public void onAnimationEnd(@NonNull Animator animator) {



        animacionR2.start();
        animacionR.start();

    }

    @Override
    public void onAnimationCancel(@NonNull Animator animator) {

    }

    @Override
    public void onAnimationRepeat(@NonNull Animator animator) {

    }
}
