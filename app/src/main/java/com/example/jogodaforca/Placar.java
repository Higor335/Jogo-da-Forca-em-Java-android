package com.example.jogodaforca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class Placar extends AppCompatActivity {

    TextView tvnomes,tvpontos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placar);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tvnomes = (TextView) findViewById(R.id.tvNomes);
        tvpontos = (TextView) findViewById(R.id.tvPontos);

        tvnomes.setText("Jogadores");
        tvpontos.setText("Melhor Tempo");

        BancoDeDados bd = new BancoDeDados(this);
        if(!bd.estaVazio()) {
            String[] nicks = bd.obterNicks();
            String[] vetorPontos = bd.obterPontos();

            String textoNicks = "";
            String pontos = "";

            for (String nick : nicks) {
                textoNicks += nick + "\n";
            }
            for (String ponto : vetorPontos) {
                pontos += ponto + "\n";
            }
            tvnomes.setText(tvnomes.getText()+"\n"+textoNicks);
            tvpontos.setText(tvpontos.getText()+"\n"+pontos);
        }
    }

    public void sairPlacar(View v){
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
    }
}