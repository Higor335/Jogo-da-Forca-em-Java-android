package com.example.jogodaforca;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DadosUsuario extends AppCompatActivity {

    private TextView nick;
    private ImageView avatar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_usuario);
        getSupportActionBar().hide();

        nick = (TextView) findViewById(R.id.tvNickResult);
        avatar = (ImageView) findViewById(R.id.ivAvatar);
        preencherCampos();
    }

    private void preencherCampos() {
        Intent intent = getIntent();
        if (intent.hasExtra("user")){
            User user;
            user = (User) intent.getSerializableExtra("user");
            nick.setText(user.getNick());

            int idImagem = getResources().getIdentifier(user.getFoto(), "drawable", getPackageName());
            avatar.setImageResource(idImagem);
        }
    }

    public void voltar(View v){
        finish();
    }

    public void continuar(View v){
        Intent intent = new Intent(this, MenuDificuldade.class);
        startActivity(intent);
    }
}