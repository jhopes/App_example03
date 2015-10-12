package com.example.docente05.app_example03;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by docente05 on 28/09/2015.
 */
public class Sqlite extends SQLiteOpenHelper{

    String table_usuario = "CREATE TABLE usuario(id_usuario INTEGER PRIMARY KEY AUTOINCREMENT, campo1 TEXT, campo2 TEXT) ";
    String table_categoria = "CREATE TABLE categoria(id_categoria INTEGER PRIMARY KEY AUTOINCREMENT, descripcion TEXT, estado TEXT) ";
    String table_producto = "CREATE TABLE producto(id_producto INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, estado TEXT)";
    public Sqlite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(table_usuario);
        db.execSQL(table_categoria);
        //Log.i("holaaaaaaa","");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS categoria");
        //
        this.onCreate(db);
    }
}
