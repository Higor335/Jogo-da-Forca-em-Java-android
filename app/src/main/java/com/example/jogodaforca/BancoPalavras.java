package com.example.jogodaforca;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoPalavras extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PalavrasDB";
    private static final int DATABASE_VERSION = 2;

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
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + COLUMN_DICA + " TEXT DEFAULT 'N/A'");
        }
    }

    public long inserirPalavra(String palavra, String dica) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PALAVRA, palavra);
        values.put(COLUMN_DICA, dica);
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }

    @SuppressLint("Range")
    public String buscarDica() {
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT " + COLUMN_DICA + " FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);

        String dica = null;
        if (cursor.moveToFirst() && !cursor.isNull(cursor.getColumnIndex(COLUMN_DICA))) {
            dica = cursor.getString(cursor.getColumnIndex(COLUMN_DICA));
        }

        cursor.close();
        db.close();
        return dica;
    }

    public String[] buscarPalavras() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + COLUMN_PALAVRA + " FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);

        String[] palavras = new String[cursor.getCount()];

        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(COLUMN_PALAVRA);
            int i = 0;
            do {
                palavras[i] = cursor.getString(columnIndex);
                i++;
            } while (cursor.moveToNext());
        }

        cursor.close();
        return palavras;
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
        db.close();
        return quantidade;
    }

    public void apagarTodasPalavras() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }
}
