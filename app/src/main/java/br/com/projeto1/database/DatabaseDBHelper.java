package br.com.projeto1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import br.com.projeto1.database.contratc.CategoriaContract;
import br.com.projeto1.database.contratc.ProdutoContract;
import br.com.projeto1.database.entity.CategoriaEntity;

public class DatabaseDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db.produto";
    private static final int DATABASE_VERSION = 7;

    public DatabaseDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CategoriaContract.criarTabela());
        db.execSQL(ProdutoContract.criarTabela());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ProdutoContract.removerTabela());
        db.execSQL(CategoriaContract.removerTabela());
        db.execSQL(CategoriaContract.criarTabela());
        db.execSQL(ProdutoContract.criarTabela());
    }
}
