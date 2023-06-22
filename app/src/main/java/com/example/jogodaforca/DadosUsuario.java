package com.example.jogodaforca;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DadosUsuario extends AppCompatActivity {

    private TextView nick;
    private ImageView avatar;

    String nome;
    int foto;

    User user;

    BancoDeDados bd = new BancoDeDados(this);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_usuario);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        nick = (TextView) findViewById(R.id.tvNickResult);
        avatar = (ImageView) findViewById(R.id.ivAvatar);
        preencherCampos();
    }

    private void preencherCampos() {
        Intent intent = getIntent();
        if (intent.hasExtra("user")){
            user = (User) intent.getSerializableExtra("user");
            nick.setText(user.getNick());
            nome = user.getNick();
            int idImagem = getResources().getIdentifier(user.getFoto(), "drawable", getPackageName());
            avatar.setImageResource(idImagem);
            foto = idImagem;
        }
    }

    public void passaValores(){
        User usuario = new User();
        usuario.setNick(user.getNick());
        usuario.setFoto(user.getFoto());

        Intent intent = new Intent(this, MenuDificuldade.class);
        intent.putExtra("usuario",usuario);
        startActivity(intent);
    }

    public void continuar(View v){
        bd.salvarVariaveis(user.getNick(),foto);
        bd.adicionarPontos("02:00");
        passaValores();
    }

    public void voltar(View v){
        finish();
    }

}