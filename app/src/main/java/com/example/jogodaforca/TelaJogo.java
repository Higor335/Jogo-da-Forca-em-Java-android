package com.example.jogodaforca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class TelaJogo extends AppCompatActivity {

    private TextView nick, timer, teste;
    private ImageView avatar;
    private String dificuldade;
    public String palavraAleatoria;

    private char[] letrasArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_jogo);
        getSupportActionBar().hide();

        nick = (TextView) findViewById(R.id.tvJogador);
        timer = (TextView) findViewById(R.id.tvTempo);
        avatar = (ImageView) findViewById(R.id.ivJogador);
        teste = (TextView) findViewById(R.id.tvTeste);

        Resources resources = getResources();
        String[] animaisArray = resources.getStringArray(R.array.animaisMedio);

        Random random = new Random();
        int indiceAleatorio = random.nextInt(animaisArray.length);

        palavraAleatoria = animaisArray[indiceAleatorio];
        letrasArray = palavraAleatoria.toCharArray();

        criarLinhas(palavraAleatoria);
        teste.setText(Arrays.toString(letrasArray));

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

    public void verificarLetra(View view) {
        Button botaoLetra = (Button) view;
        String letraClicada = botaoLetra.getText().toString();

        boolean letraEncontrada = false;

        for (char letra : letrasArray) {
            String letraAtual = String.valueOf(letra);
            if (letraClicada.contains(letraAtual)) {
                letraEncontrada = true;
                break;
            }
        }

        if (letraEncontrada) {
            // A letra está presente na palavra aleatória
            nick.setText(letraClicada);
            botaoLetra.setVisibility(View.INVISIBLE);
        } else {
            // A letra não está presente na palavra aleatória
            timer.setText(letraClicada);
            botaoLetra.setVisibility(View.INVISIBLE);
        }
    }

    private void preencherCampos() {
        BancoDeDados bd = new BancoDeDados(this);
        avatar.setImageResource(bd.obterFoto());
        nick.setText(bd.obterNick());

    }
}