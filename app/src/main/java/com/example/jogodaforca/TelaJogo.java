package com.example.jogodaforca;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class TelaJogo extends AppCompatActivity {

    Resources resources = getResources();
    TypedArray animaisArray = resources.obtainTypedArray(R.array.animais);
    public User user;

    private TextView nick;

    String dificuldade;
    private ImageView avatar;

    // Gera um índice aleatório
    Random random = new Random();
    int indiceAleatorio = random.nextInt(animaisArray.length());

    // Obtém a palavra aleatória
    int resourceId = animaisArray.getResourceId(indiceAleatorio, 0);
    String palavraAleatoria = resources.getString(resourceId);


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_jogo);

        nick = (TextView) findViewById(R.id.tvJogador);
        avatar = (ImageView) findViewById(R.id.ivJogador);
        preencherCampos();
    }

    private void preencherCampos() {
        Intent intent = getIntent();
        if (intent.hasExtra("user")){
            User user;
            user = (User) intent.getSerializableExtra("user");
            nick.setText(user.getNick());
            dificuldade = user.getDificuldade();

            int idImagem = getResources().getIdentifier(user.getFoto(), "drawable", getPackageName());
            avatar.setImageResource(idImagem);
        }
    }
}