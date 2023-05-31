package com.example.jogodaforca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.widget.ImageViewCompat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton btchar1, btchar2, btchar3, btchar4;
    private TextView tvNome;
    private List<ImageButton> imageButtons;
    private boolean isClicked = false;
    private Button Entrar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        Entrar = (Button) findViewById(R.id.btEntrar);
        Entrar.setEnabled(false);

        tvNome = (TextView) findViewById(R.id.tvName);
        btchar1 = (ImageButton) findViewById(R.id.btchar1);
        btchar2 = (ImageButton) findViewById(R.id.btChar2);
        btchar3 = (ImageButton) findViewById(R.id.btChar3);
        btchar4 = (ImageButton) findViewById(R.id.btChar4);

        imageButtons = new ArrayList<>();
        imageButtons.add(btchar1);
        imageButtons.add(btchar2);
        imageButtons.add(btchar3);
        imageButtons.add(btchar4);

        for (ImageButton imageButton : imageButtons) {
            imageButton.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        for (ImageButton imageButton : imageButtons) {
            if (imageButton.getId() == v.getId()) {
                // Botão clicado
                isClicked=true;
                imageButton.setSelected(true);
                ImageViewCompat.setImageTintList(imageButton, null); // Remover a cor

            } else {
                // Outros botões
                imageButton.setSelected(false);
                ImageViewCompat.setImageTintList(imageButton, ContextCompat.getColorStateList(this, R.color.grey)); // Aplicar cor azul
            }
        }

        validar();
    }

    private void validar(){
        if(tvNome.getText().toString().equals("a")){
            Entrar.setEnabled(true);
        }
    }

    public void entrar(View v){
        System.out.println("faz alguma coisa");
    }






}