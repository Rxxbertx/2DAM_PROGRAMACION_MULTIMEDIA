package com.roberto.minigame.sounds;

import android.content.Context;
import android.media.MediaPlayer;

import com.roberto.minigame.R;

public class Sonidos {


    public static MediaPlayer mediaMusic;
    public static MediaPlayer mediaPop;
    public static MediaPlayer mediaWin;
    public static MediaPlayer mediaLose;


    public static void initMusic(Context context) {

        if (mediaMusic == null) {
            mediaMusic = MediaPlayer.create(context, R.raw.music);


        }
    }

    public static void initPopWinLose(Context context) {


        if (mediaPop == null) {
            mediaPop = MediaPlayer.create(context, R.raw.pop);
        }
        if (mediaWin == null) {
            mediaWin = MediaPlayer.create(context, R.raw.win);
        }if (mediaLose == null) {
            mediaLose = MediaPlayer.create(context, R.raw.lose);
        }
    }



    public static void playPoP(){

        if (mediaPop != null) {
            mediaPop.start();
        }
    }

    public static void playMusic(){

        mediaMusic.setLooping(true);
        mediaMusic.setVolume(0.5f, 0.5f);

        if (mediaMusic != null) {
            mediaMusic.start();
        }
    }

    public static void playWin(){

        if (mediaWin != null) {
            mediaWin.start();
        }


    }
    public static void playLose(){

        if (mediaLose != null) {
            mediaLose.start();
        }

    }



    public static void releasePopWinLose(){


        if (mediaPop != null) {
            mediaPop.release();
            mediaPop = null;
        }
        if (mediaWin != null) {
            mediaWin.release();
            mediaWin = null;
        }
        if (mediaLose != null) {
            mediaLose.release();
            mediaLose = null;
        }
    }

    public static void releaseMusic(){

        if (mediaMusic != null) {
            mediaMusic.release();
            mediaMusic = null;
        }
    }


    public static void pauseMusic() {
        if (mediaMusic != null) {
            mediaMusic.pause();

        }
    }


}
