package com.roberto.minigame;

import android.app.Application;
import android.content.Intent;

import com.roberto.minigame.sounds.Sonidos;

public class MinigameApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent = new Intent(this, AudioService.class);
        startService(intent);



    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Sonidos.releasePopWinLose();

    }

}
