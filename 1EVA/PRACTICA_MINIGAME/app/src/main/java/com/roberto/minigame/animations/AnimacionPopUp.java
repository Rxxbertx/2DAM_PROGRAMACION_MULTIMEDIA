package com.roberto.minigame.animations;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class AnimacionPopUp {

    public static void animarAparicion(View view, int duracion) {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX", 0, 1);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY", 0, 1);
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", 0, 1);


        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator, alphaAnimator);
        animatorSet.setDuration(duracion);
        animatorSet.start();
    }
}

