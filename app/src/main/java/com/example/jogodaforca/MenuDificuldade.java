package com.example.jogodaforca;

import static android.widget.Button.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.jogodaforca.R.id;

import java.util.ArrayList;
import java.util.List;

public class MenuDificuldade extends AppCompatActivity implements View.OnClickListener {

    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_dificuldade);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button button1 = findViewById(R.id.btFacil);
        Button button2 = findViewById(R.id.btMedio);
        Button button3 = findViewById(R.id.btDificil);
        Button pers = findViewById(R.id.btPers);
        TextView tv = (TextView)findViewById(R.id.tvPalavra);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        pers.setOnClickListener(this);




        personalizado(pers,pers);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuDificuldade.this, CriaPalavra.class);
                startActivity(intent);
            }
        });


    }

    private void personalizado(View v, Button button) {
        Intent intent = getIntent();
        BancoPalavras bp = new BancoPalavras(this);
        int quantidade = bp.obterQuantidadePalavras();

        if(quantidade>0){
            v.setVisibility(v.VISIBLE);
            user = (User) intent.getSerializableExtra("personalizado");
            button.setText(String.valueOf(bp.buscarDica()));
        }else{
            v.setVisibility(v.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        String dificuldade = v.getTag().toString();
        setarDificuldade(dificuldade);
    }

    private void setarDificuldade(String dificuldade) {
        Intent intent = getIntent();
        if (intent.hasExtra("usuario")) {
            user = (User) intent.getSerializableExtra("usuario");
            user.setDificuldade(dificuldade);
            System.out.println(dificuldade);
            Jogar();
        }
    }

    private void Jogar() {
        Intent intent = new Intent(this, TelaJogo.class);
        intent.putExtra("usuario",user);
        startActivity(intent);
    }
}