package com.example.jogodaforca;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoPalavras extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PalavrasDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "palavras";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PALAVRA = "palavra";
    private static final String COLUMN_DICA = "dica";

    public BancoPalavras(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PALAVRA + " TEXT, "
                + COLUMN_DICA + " TEXT)";

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Lógica para atualizar o esquema do banco de dados, se necessário
    }

    public long inserirPalavra(String palavra) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PALAVRA, palavra);
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public Cursor buscarPalavras() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }

    public int obterQuantidadePalavras() {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT COUNT(*) FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        int quantidade = 0;
        if (cursor != null && cursor.moveToFirst()) {
            quantidade = cursor.getInt(0);
            cursor.close();
        }
        return quantidade;
    }

    public void apagarTodasPalavras() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }
}
