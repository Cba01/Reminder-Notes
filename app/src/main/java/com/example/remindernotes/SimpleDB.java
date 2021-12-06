package com.example.remindernotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SimpleDB {
    private final String DATABASE_NAME = "SIMPLE_DB";
    private final String DATABASE_TABLE = "SIMPLE";
    private final int DATABASE_VERSION = 2;


    private static final String KEY_ID = "_id";
    private static final String KEY_TITULO = "_titulo";
    private static final String KEY_DESCRIP = "_descripcion";

    private SimpleDB.DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public SimpleDB(Context context) {

        this.ourContext = context;

    }

    public SimpleDB open() {

        this.ourHelper = new SimpleDB.DBHelper(ourContext);
        this.ourDatabase = this.ourHelper.getWritableDatabase();
        return this;
    }

    public void close() {

        this.ourHelper.close();

    }

    public ArrayList<Agenda> getAgenda(){

        String[] columnas = new String[] {KEY_ID, KEY_TITULO, KEY_DESCRIP};
        Cursor c = this.ourDatabase.query(DATABASE_TABLE, columnas, null, null, null, null, null);
        ArrayList<Agenda> result = new ArrayList<Agenda>();
        int iId = c.getColumnIndex(KEY_ID);
        int iTitulo = c.getColumnIndex(KEY_TITULO);
        int iDescripcion = c.getColumnIndex(KEY_DESCRIP);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            String id = c.getString(iId);
            String titulo = c.getString(iTitulo);
            String descripcion = c.getString(iDescripcion);

            Agenda agenda = new Agenda(id, titulo, descripcion);
            result.add(agenda);
        }
        c.close();
        return result;
    }

    public long guardarAgenda(String titulo, String descripcion ){

        ContentValues cv = new ContentValues();
        cv.put(KEY_TITULO, titulo);
        cv.put(KEY_DESCRIP, descripcion);

        return this.ourDatabase.insert(DATABASE_TABLE, null, cv);

    }

    public long borrarAgenda(String id){
        return this.ourDatabase.delete(DATABASE_TABLE, KEY_ID + "=?", new String[]{id});
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(@Nullable Context context){
            super(context, DATABASE_NAME,null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = "CREATE TABLE " + DATABASE_TABLE + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_TITULO + " TEXT NOT NULL, " +
                    KEY_DESCRIP + " TEXT NOT NULL); ";

            db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

            onCreate(db);
        }
    }
}
