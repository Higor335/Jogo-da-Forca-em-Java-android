package com.example.jogodaforca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        startService(new Intent(this, musica.class));

    }

    public void jogar(View v){
        Intent intent = new Intent(this, CriacaoUsuario.class);
        startActivity(intent);
    }

    public void placar(View v){
        Intent intent = new Intent(this, Placar.class);
        startActivity(intent);
    }

    public void creditos(View v){
        Intent intent = new Intent(this, Creditos.class);
        startActivity(intent);
    }

    public void sair(View v){
        finishAffinity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, musica.class));
    }
}