package com.example.jogodaforca;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class TelaJogo extends AppCompatActivity {

    private TextView nick, timer, teste;
    private ImageView avatar, forca;
    private String dificuldade;
    public String palavraAleatoria;

    private char[] letrasArray;
    private EditText[] editTextArray;

    private static final int MAX_TENTATIVAS = 7;
    private int tentativas = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_jogo);
        getSupportActionBar().hide();

        nick = findViewById(R.id.tvJogador);
        timer = findViewById(R.id.tvTempo);
        avatar = findViewById(R.id.ivJogador);
        teste = findViewById(R.id.tvTeste);
        forca = findViewById(R.id.ivForca);

        Intent intent = getIntent();
        if (intent.hasExtra("usuario")) {
            User user = (User) intent.getSerializableExtra("usuario");
            dificuldade = user.getDificuldade();
        }


        String dica = "";
        Resources resources = getResources();

        String[] palavras = resources.getStringArray(R.array.animaisFacil);
        dica="Animal";
        if(dificuldade.equals("medio")){
            palavras = resources.getStringArray(R.array.objetosMedio);
            dica="Objeto";
        }if(dificuldade.equals("dificil")){
            palavras = resources.getStringArray(R.array.planetasDificil);
            dica="Planeta";
        }



        Random random = new Random();
        int indiceAleatorio = random.nextInt(palavras.length);

        palavraAleatoria = palavras[indiceAleatorio];
        letrasArray = palavraAleatoria.toCharArray();

        criarLinhas(palavraAleatoria);
        teste.setText(dica);

        preencherCampos();
    }

    private void criarLinhas(String palavra) {
        TableLayout tableLayout = findViewById(R.id.tableLinhas);
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

        int editTextWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());

        editTextArray = new EditText[palavra.length()]; // Inicializa o array com o tamanho correto

        for (int i = 0; i < palavra.length(); i++) {
            EditText editText = new EditText(this);
            editText.setText(" "); // Define um espaço em branco como texto inicial
            editText.setEnabled(false); // Desativa a interação do usuário
            editText.setLayoutParams(new TableRow.LayoutParams(editTextWidth, TableRow.LayoutParams.WRAP_CONTENT));
            editText.setGravity(Gravity.CENTER); // Define a gravidade como centralizada

            tableRow.addView(editText);

            editTextArray[i] = editText; // Adiciona o EditText ao array
        }

        tableLayout.addView(tableRow);
    }

    public void verificarLetra(View view) {
        Button botaoLetra = (Button) view;
        String letraClicada = botaoLetra.getText().toString();

        boolean letraEncontrada = false;

        for (char letra : letrasArray) {
            char caracter = Character.toUpperCase(letra);
            String letraAtual = String.valueOf(caracter);

            if (letraClicada.contains(letraAtual)) {
                letraEncontrada = true;
                break;
            }
        }

        if (letraEncontrada) {
            // A letra está presente na palavra aleatória
            botaoLetra.setVisibility(View.INVISIBLE);

            // Percorra o array de EditText e atualize o texto no índice correto
            for (int i = 0; i < letrasArray.length; i++) {
                char caracter = letrasArray[i];
                caracter = Character.toUpperCase(caracter);
                String letraAtual = String.valueOf(caracter);

                if (letraClicada.equals(letraAtual)) {
                    editTextArray[i].setText(letraAtual); // Atualiza o texto do EditText no índice correspondente
                }
            }

            verificarCamposPreenchidos(); // Verifica se todos os campos foram preenchidos
        } else {
            // A letra não está presente na palavra aleatória
            botaoLetra.setVisibility(View.INVISIBLE);
            tentativas++;

            if (tentativas == MAX_TENTATIVAS) {
                // Você perdeu
                Toast.makeText(this, "Você perdeu!", Toast.LENGTH_SHORT).show();
                exibirMensagemConfirmacao();
            } else {
                // Atualizar a imagem da forca
                int idImagem = getResources().getIdentifier("forca" + (tentativas + 1), "drawable", getPackageName());
                forca.setImageResource(idImagem);
            }
        }
    }

    private void preencherCampos() {
        BancoDeDados bd = new BancoDeDados(this);
        avatar.setImageResource(bd.obterFoto());
        nick.setText(bd.obterNick());
    }

    private void verificarCamposPreenchidos() {
        boolean todosPreenchidos = true;

        for (EditText editText : editTextArray) {
            if (editText.getText().toString().trim().isEmpty()) {
                todosPreenchidos = false;
                break;
            }
        }

        if (todosPreenchidos) {
            // Tela de jogo concluído
            Toast.makeText(this, "Parabéns !!!", Toast.LENGTH_SHORT).show();
            //mandar para a tela de score
        }
    }

    private void exibirMensagemConfirmacao() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Jogo da Forca");
        builder.setMessage("Você Perdeu! Deseja reiniciar o jogo ou sair?");
        builder.setPositiveButton("Reiniciar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                reiniciarJogo();
            }
        });
        builder.setNegativeButton("Sair", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setCancelable(false); // Impede o cancelamento do diálogo ao tocar fora dele

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void reiniciarJogo() {
        recreate();
    }
}