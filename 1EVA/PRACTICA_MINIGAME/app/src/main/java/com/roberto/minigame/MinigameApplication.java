package com.roberto.minigame;

import android.app.Application;

import com.roberto.minigame.sounds.Sonidos;

public class MinigameApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Sonidos.initMusic(this);
        Sonidos.playMusic();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Sonidos.releasePopWinLose();
        Sonidos.releaseMusic();

    }

}
