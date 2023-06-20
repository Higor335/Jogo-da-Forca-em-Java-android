package com.example.jogodaforca;

import static android.widget.Button.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
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