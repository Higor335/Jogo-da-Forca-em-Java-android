package com.example.jogodaforca;

import static com.example.jogodaforca.R.id.tvPalavra;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class CriaPalavra extends AppCompatActivity {


    EditText pala,tema;
    TextView qtd;
    TableRow tb;
    int quantidade = 0;
    BancoPalavras bp;
    String temaPalavras;
    long existePalavra = -1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cria_palavra);

        pala = (EditText) findViewById(R.id.etPala);
        tema = (EditText)findViewById(R.id.etDica);
        qtd = (TextView) findViewById(R.id.tvQuantidade);
        tb = (TableRow) findViewById(R.id.trDica);

        mostraQTD();
        verificaDica(tb);

    }

    public void verificaDica(View v){
        bp = new BancoPalavras(this);
        quantidade = bp.obterQuantidadePalavras();
        if(quantidade>0){
            v.setVisibility(View.GONE);
        }else{
            v.setVisibility(View.VISIBLE);
        }
    }

    public void salvar(View v){
        if (!pala.getText().toString().isEmpty()) {
            bp = new BancoPalavras(this);
            existePalavra = bp.inserirPalavra(pala.getText().toString(), tema.getText().toString());
            System.out.println("A DICA É: " + bp.buscarDica());
            if (existePalavra != -1) {
                Toast.makeText(getApplicationContext(), "Palavra salva com sucesso", Toast.LENGTH_SHORT).show();
            }
            pala.setText("");
            temaPalavras = tema.getText().toString();
            mostraQTD();
            verificaDica(tb);
        } else {
            Toast.makeText(getApplicationContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
        }
    }

    private void mostraQTD() {
        BancoPalavras bp = new BancoPalavras(this);
        quantidade = bp.obterQuantidadePalavras();
        qtd.setText(String.valueOf(quantidade));
    }


    public void volta(View v){
        Intent intent = new Intent(this,MenuDificuldade.class);
        startActivity(intent);
    }

    public void apagar(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmação de Exclusão");
        builder.setMessage("Tem certeza de que deseja excluir todas as palavras?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(quantidade>0) {
                    BancoPalavras bancoPalavras = new BancoPalavras(CriaPalavra.this);
                    bancoPalavras.apagarTodasPalavras();
                    mostraQTD();
                    verificaDica(tb);
                }
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.show();
    }
}