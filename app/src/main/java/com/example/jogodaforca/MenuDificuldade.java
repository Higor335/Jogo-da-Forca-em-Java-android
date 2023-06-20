package com.example.jogodaforca;

import static android.widget.Button.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MenuDificuldade extends AppCompatActivity {

    private List<Button> Buttons;
    TextView tv;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_dificuldade);
        getSupportActionBar().hide();

        tv = findViewById(R.id.tvTI);
        Button button1 = findViewById(R.id.btFacil); // Substitua "button1" pelo ID do primeiro botão
        Button button2 = findViewById(R.id.btMedio); // Substitua "button2" pelo ID do segundo botão
        Button button3 = findViewById(R.id.btDificil); // Substitua "button3" pelo ID do terceiro botão

        // Adicione o listener de clique aos botões
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String x = button.getTag().toString();
                Jogar(x);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String x = button.getTag().toString();
                Jogar(x);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String x = button.getTag().toString();
                Jogar(x);
            }
        });
    }

    public void Jogar(String x) {
        tv.setText("Botão selecionado: " + x);
    }
}