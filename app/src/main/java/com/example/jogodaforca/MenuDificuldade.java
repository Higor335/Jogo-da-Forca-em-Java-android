package com.example.jogodaforca;

import static android.widget.Button.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jogodaforca.R.id;

import java.util.ArrayList;
import java.util.List;

public class MenuDificuldade extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_dificuldade);
        getSupportActionBar().hide();

        Button button1 = findViewById(R.id.btFacil);
        Button button2 = findViewById(R.id.btMedio);
        Button button3 = findViewById(R.id.btDificil);
        Button pers = findViewById(R.id.btPers);
        TextView tv = (TextView)findViewById(R.id.tvPalavra);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);


        personalizado(pers);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuDificuldade.this, CriaPalavra.class);
                startActivity(intent);
            }
        });

    }

    private void personalizado(View v) {
        BancoPalavras bp = new BancoPalavras(this);
        int quantidade = bp.obterQuantidadePalavras();

        if(quantidade>0){
            v.setVisibility(v.VISIBLE);
        }else{
            v.setVisibility(v.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        String dificuldade = v.getTag().toString();
        setarDificuldade(dificuldade);
        Jogar();
    }

    private void setarDificuldade(String dificuldade) {
        Intent intent = getIntent();
        if (intent.hasExtra("user")) {
            User user = (User) intent.getSerializableExtra("user");
            user.setDificuldade(dificuldade);
        }
    }

    private void Jogar() {
        Intent intent = new Intent(this, TelaJogo.class);
        startActivity(intent);
    }
}