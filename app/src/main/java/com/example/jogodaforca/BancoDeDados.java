package com.example.jogodaforca;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BancoDeDados extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Jogador.db";
    private static final int DATABASE_VERSION = 3; // Aumentado para a versão 3

    private static final String TABLE_NOME = "dados_jogador";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NICK = "nick";
    private static final String COLUMN_FOTO = "foto";
    private static final String COLUMN_PONTOS = "pontos";

    public BancoDeDados(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NOME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NICK + " TEXT, " +
                COLUMN_FOTO + " INTEGER, " +
                COLUMN_PONTOS + " TEXT" + // Alterado para TEXT
                ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOME);
        onCreate(db);
    }

    public void salvarVariaveis(String nick, int foto) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NICK, nick);
        values.put(COLUMN_FOTO, foto);
        db.insert(TABLE_NOME, null, values);
        db.close();
    }

    public void adicionarPontos(String pontos) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PONTOS, pontos);

        db.insert(TABLE_NOME, null, values);
        db.close();
    }


    public String[] obterNicks() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT " + COLUMN_NICK + " FROM " + TABLE_NOME + " WHERE " + COLUMN_NICK + " IS NOT NULL AND TRIM(" + COLUMN_NICK + ") <> ''";
        Cursor cursor = db.rawQuery(query, null);
        List<String> nickList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String nick = cursor.getString(cursor.getColumnIndex(COLUMN_NICK));
                nickList.add(nick);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        String[] nicks = new String[nickList.size()];
        nicks = nickList.toArray(nicks);

        return nicks;
    }



    public int[] obterFotos() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT " + COLUMN_FOTO + " FROM " + TABLE_NOME + " WHERE " + COLUMN_FOTO + " IS NOT NULL";
        Cursor cursor = db.rawQuery(query, null);
        List<Integer> fotoList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int foto = cursor.getInt(cursor.getColumnIndex(COLUMN_FOTO));
                fotoList.add(foto);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        int[] fotos = new int[fotoList.size()];
        for (int i = 0; i < fotoList.size(); i++) {
            fotos[i] = fotoList.get(i);
        }

        return fotos;
    }


    @SuppressLint("Range")
    public String[] obterPontos() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT " + COLUMN_PONTOS + " FROM " + TABLE_NOME + " WHERE " + COLUMN_PONTOS + " IS NOT NULL AND (" + COLUMN_PONTOS + " <> '5' OR " + COLUMN_PONTOS + " IS NULL)";
        Cursor cursor = db.rawQuery(query, null);
        List<String> pontosList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String pontos = cursor.getString(cursor.getColumnIndex(COLUMN_PONTOS));
                if (pontos != null && pontos.equals("5")) {
                    pontos = "02:00";
                }
                pontosList.add(pontos);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        String[] pontosArray = new String[pontosList.size()];
        pontosArray = pontosList.toArray(pontosArray);

        return pontosArray;
    }



    public boolean estaVazio() {
        SQLiteDatabase db = getReadableDatabase();
        String countQuery = "SELECT COUNT(*) FROM " + TABLE_NOME;
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        db.close();
        return count == 0;
    }

    public void excluirUltimoPonto() {
        SQLiteDatabase db = getWritableDatabase();

        // Obtém o último registro de pontos
        String query = "SELECT " + COLUMN_ID + " FROM " + TABLE_NOME + " WHERE " + COLUMN_PONTOS + " IS NOT NULL ORDER BY " + COLUMN_ID + " DESC LIMIT 1";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));

            // Exclui o registro do último ponto
            db.delete(TABLE_NOME, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        }

        cursor.close();
        db.close();
    }



    public void apagarTudo() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NOME, null, null);
        db.close();
    }
}
