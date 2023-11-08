package com.roberto.minigame;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.io.IOException;

public class AudioService extends Service {


    private MediaPlayer mediaPlayer;


    @Override
    public void onCreate() {
        super.onCreate();

        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(this, Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.music));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            mediaPlayer.setLooping(true);
            mediaPlayer.setVolume(0.5f, 0.5f);

            mediaPlayer.setOnPreparedListener(mp -> mediaPlayer.start());

            mediaPlayer.prepareAsync(); // Ahora llamamos a prepareAsync después de configurar el data source
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
        return START_STICKY;

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
