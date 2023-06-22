package com.example.jogodaforca;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class musica extends Service {
    private MediaPlayer mediaPlayer;
    private Intent intent;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.musica);
        float volume = 0.1f; // Volume máximo (1.0f)
        mediaPlayer.setVolume(volume, volume);
        mediaPlayer.setLooping(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
