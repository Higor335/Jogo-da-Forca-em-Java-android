package com.example.jogodaforca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Placar extends AppCompatActivity {

    TextView tvnomes, tvpontos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placar);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        float shadowRadius = 30.0f;
        float shadowDx = 3.0f;
        float shadowDy = 3.0f;
        int shadowColor = Color.BLACK;


        tvnomes = findViewById(R.id.tvNomes);
        tvpontos = findViewById(R.id.tvPontos);
        tvnomes.setShadowLayer(shadowRadius, shadowDx, shadowDy, shadowColor);
        tvpontos.setShadowLayer(shadowRadius, shadowDx, shadowDy, shadowColor);


        tvnomes.setText("Jogadores");
        tvpontos.setText("Melhor Tempo");

        BancoDeDados bd = new BancoDeDados(this);
        if (!bd.estaVazio()) {
            String[] nicks = bd.obterNicks();
            String[] vetorPontos = bd.obterPontos();

            List<Jogador> jogadores = new ArrayList<>();

            for (int i = 0; i < nicks.length; i++) {
                Jogador jogador = new Jogador(nicks[i], vetorPontos[i]);
                jogadores.add(jogador);
            }

            Collections.sort(jogadores, new Comparator<Jogador>() {
                @Override
                public int compare(Jogador jogador1, Jogador jogador2) {
                    int pontos1 = convertToSeconds(jogador1.getPontos());
                    int pontos2 = convertToSeconds(jogador2.getPontos());
                    return Integer.compare(pontos1, pontos2);
                }
            });

            StringBuilder textoNicks = new StringBuilder();
            StringBuilder pontos = new StringBuilder();

            int limite = Math.min(10, jogadores.size());

            for (int i = 0; i < limite; i++) {
                Jogador jogador = jogadores.get(i);
                textoNicks.append(jogador.getNick()).append("\n");
                pontos.append(jogador.getPontos()).append("\n");
            }

            tvnomes.setText(tvnomes.getText() + "\n" + textoNicks.toString());
            tvpontos.setText(tvpontos.getText() + "\n" + pontos.toString());
        }
    }

    public void sairPlacar(View v) {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public class Jogador {
        private String nick;
        private String pontos;

        public Jogador(String nick, String pontos) {
            this.nick = nick;
            this.pontos = pontos;
        }

        public String getNick() {
            return nick;
        }

        public String getPontos() {
            return pontos;
        }
    }

    private int convertToSeconds(String time) {
        String[] parts = time.split(":");
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);
        return minutes * 60 + seconds;
    }
}