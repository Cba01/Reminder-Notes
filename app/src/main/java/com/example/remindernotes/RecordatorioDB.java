package com.example.remindernotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class RecordatorioDB {
    private final String DATABASE_NAME = "RECORDATORIO_DB";
    private final String DATABASE_TABLE = "RECORDATORIO";
    private final int DATABASE_VERSION = 1;


    private static final String KEY_ID = "_id";
    private static final String KEY_TITULO = "_titulo";
    private static final String KEY_DESCRIP = "_descripcion";

    private DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public RecordatorioDB(Context context) {

        this.ourContext = context;

    }

    public RecordatorioDB open() {

        this.ourHelper = new DBHelper(ourContext);
        this.ourDatabase = this.ourHelper.getWritableDatabase();
        return this;
    }

    public void close() {

        this.ourHelper.close();

    }

    public ArrayList<Recordatorio> getRecordatorio(){

        String[] columnas = new String[] {KEY_ID, KEY_TITULO, KEY_DESCRIP};
        Cursor c = this.ourDatabase.query(DATABASE_TABLE, columnas, null, null, null, null, null);
        ArrayList<Recordatorio> result = new ArrayList<Recordatorio>();
        int iId = c.getColumnIndex(KEY_ID);
        int iTitulo = c.getColumnIndex(KEY_TITULO);
        int iDescripcion = c.getColumnIndex(KEY_DESCRIP);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            String id = c.getString(iId);
            String titulo = c.getString(iTitulo);
            String descrip = c.getString(iDescripcion);

            Recordatorio recordatorio = new Recordatorio(id, titulo, descrip);
            result.add(recordatorio);
        }
        c.close();
        return result;
    }

    public long guardarRecordatorio(String titulo, String descripcion){

        ContentValues cv = new ContentValues();
        cv.put(KEY_TITULO, titulo);
        cv.put(KEY_DESCRIP, descripcion);

        return this.ourDatabase.insert(DATABASE_TABLE, null, cv);

    }

    private class DBHelper extends SQLiteOpenHelper{

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
