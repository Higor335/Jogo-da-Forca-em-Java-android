package com.example.jogodaforca;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Random;

public class TelaJogo extends AppCompatActivity {

    private TextView nick;
    private ImageView avatar;
    private String dificuldade;
    private String palavraAleatoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_jogo);
        getSupportActionBar().hide();

        nick = (TextView) findViewById(R.id.tvJogador);
        avatar = (ImageView) findViewById(R.id.ivJogador);

        Resources resources = getResources();
        String[] animaisArray = resources.getStringArray(R.array.animaisMedio);

        Random random = new Random();
        int indiceAleatorio = random.nextInt(animaisArray.length);

        String palavraAleatoria = animaisArray[indiceAleatorio];
        criarLinhas(palavraAleatoria);


        preencherCampos();
    }

    private void criarLinhas(String palavra) {
        TableLayout tableLayout = findViewById(R.id.tableLinhas);
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

        int editTextWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());

        for (int i = 0; i < palavra.length(); i++) {
            EditText editText = new EditText(this);
            editText.setText(" "); // Define um espaço em branco como texto inicial
            editText.setEnabled(false); // Desativa a interação do usuário
            editText.setLayoutParams(new TableRow.LayoutParams(editTextWidth, TableRow.LayoutParams.WRAP_CONTENT));
            editText.setGravity(Gravity.CENTER); // Define a gravidade como centralizada

            tableRow.addView(editText);
        }

        tableLayout.addView(tableRow);
    }

    private void preencherCampos() {
        BancoDeDados bd = new BancoDeDados(this);
        avatar.setImageResource(bd.obterFoto());
        nick.setText(bd.obterNick());

    }
}