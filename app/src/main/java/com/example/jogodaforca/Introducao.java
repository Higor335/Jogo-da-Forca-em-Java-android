package com.example.jogodaforca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.VideoView;

public class Introducao extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introducao);
        getSupportActionBar().hide();

        videoView = findViewById(R.id.videoView);

        // Defina o caminho do vídeo MP4
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video;

        // Configurar o vídeo no VideoView
        videoView.setVideoPath(videoPath);

        // Iniciar a reprodução do vídeo
        videoView.start();

        // Aguarde o término da reprodução do vídeo
        // Aguarde o término da reprodução do vídeo
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Intent intent = new Intent(Introducao.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}