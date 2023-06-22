package com.example.jogodaforca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.core.widget.ImageViewCompat;
import java.util.ArrayList;
import java.util.List;

public class CriacaoUsuario extends AppCompatActivity implements View.OnClickListener{

    private ImageButton btchar1, btchar2, btchar3, btchar4;
    private EditText nick;
    private List<ImageButton> imageButtons;
    private Button Entrar;
    public String imagemSelecionada="";


    public User user;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criacao_usuario);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        declaraChamada();
        listenerNick();
    }

    private void listenerNick() {
        nick.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
            }

            @Override
            public void afterTextChanged(Editable s) {
                validacaoBotao();
            }
        });
    }

    private void declaraChamada(){
        Entrar = (Button) findViewById(R.id.btEntrar);
        Entrar.setEnabled(false);

        nick = (EditText) findViewById(R.id.etNick);
        btchar1 = (ImageButton) findViewById(R.id.btChar1);
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
                imageButton.setSelected(true);
                ImageViewCompat.setImageTintList(imageButton, null); // Remover a cor
                imagemSelecionada = imageButton.getTag().toString();
                validacaoBotao();
            } else {
                imageButton.setSelected(false);
                ImageViewCompat.setImageTintList(imageButton, ContextCompat.getColorStateList(this, R.color.grey)); // Aplicar cor cinza
            }
        }
    }

    private void validacaoBotao() {
        boolean isNickEmpty = nick.getText().toString().isEmpty();
        boolean isAnyImageButtonSelected = btchar1.isSelected() || btchar2.isSelected() || btchar3.isSelected() || btchar4.isSelected();
        Entrar.setEnabled(!isNickEmpty && isAnyImageButtonSelected);
        if (Entrar.isEnabled()) {
            Entrar.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.green));
            Entrar.setTextColor(Color.WHITE);
        } else {
            Entrar.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.grey));
            Entrar.setTextColor(Color.BLACK);
        }
    }

    public void entrar(View v){
        user = new User();
        user.setNick(String.valueOf(nick.getText()));
        user.setFoto(pegaImagem(imagemSelecionada));


        Intent intent = new Intent(this, DadosUsuario.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    private String pegaImagem(String dado){
        String resultado="";

        System.out.println("Escolhido "+dado);
        if(dado.equals("branco1")){
            resultado = "@drawable/char1";
        }else if(dado.equals("cinza3")){
            resultado = "@drawable/char3";
        } if(dado.equals("azul2")){
            resultado = "@drawable/char2";
        }else if(dado.equals("dourado4")){
            resultado = "@drawable/char4";
        }
        return resultado;
    }
}