package com.example.jogodaforca;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDeDados extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Jogador.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NOME = "dados_jogador";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NICK = "nick";
    private static final String COLUMN_FOTO = "foto";

    public BancoDeDados(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NOME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NICK + " TEXT, " +
                COLUMN_FOTO + " INTEGER" +
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

    public String obterNick() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT " + COLUMN_NICK + " FROM " + TABLE_NOME + " ORDER BY " + COLUMN_ID + " DESC LIMIT 1";
        Cursor cursor = db.rawQuery(query, null);
        String nick = null;

        if (cursor.moveToFirst()) {
            nick = cursor.getString(cursor.getColumnIndex(COLUMN_NICK));
        }

        cursor.close();
        db.close();

        return nick;
    }

    public int obterFoto() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT " + COLUMN_FOTO + " FROM " + TABLE_NOME + " ORDER BY " + COLUMN_ID + " DESC LIMIT 1";
        Cursor cursor = db.rawQuery(query, null);
        int foto = 0;

        if (cursor.moveToFirst()) {
            foto = cursor.getInt(cursor.getColumnIndex(COLUMN_FOTO));
        }

        cursor.close();
        db.close();

        return foto;
    }

}