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
    private final int DATABASE_VERSION = 2;


    private static final String KEY_ID = "_id";
    private static final String KEY_TITULO = "_titulo";
    private static final String KEY_DESCRIP = "_descripcion";
    private static final String KEY_FECHAINICIO = "_fechainicio";
    private static final String KEY_FECHATERMINO = "_fechatermino";
    private static final String KEY_HORA = "_hora";
    private static final String KEY_TIPO = "_tipo";

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

        String[] columnas = new String[] {KEY_ID, KEY_TITULO, KEY_DESCRIP, KEY_FECHAINICIO,  KEY_FECHATERMINO, KEY_HORA, KEY_TIPO};
        Cursor c = this.ourDatabase.query(DATABASE_TABLE, columnas, null, null, null, null, null);
        ArrayList<Recordatorio> result = new ArrayList<Recordatorio>();
        int iId = c.getColumnIndex(KEY_ID);
        int iTitulo = c.getColumnIndex(KEY_TITULO);
        int iDescripcion = c.getColumnIndex(KEY_DESCRIP);
        int iFechaInicio = c.getColumnIndex(KEY_FECHAINICIO);
        int iFechaTermino = c.getColumnIndex(KEY_FECHATERMINO);
        int iHora = c.getColumnIndex(KEY_HORA);
        int iTipo = c.getColumnIndex(KEY_TIPO);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            String id = c.getString(iId);
            String titulo = c.getString(iTitulo);
            String descrip = c.getString(iDescripcion);
            String fechainicio = c.getString(iFechaInicio);
            String fechatermino = c.getString(iFechaTermino);
            String hora = c.getString(iHora);
            int tipo = c.getInt(iTipo);

            Recordatorio recordatorio = new Recordatorio(id, titulo, descrip, fechainicio, fechatermino, hora, tipo);
            result.add(recordatorio);
        }
        c.close();
        return result;
    }

    public long guardarRecordatorio(String titulo, String descripcion , String fechaInicio, String fechaTermino, String hora, int tipo){

        ContentValues cv = new ContentValues();
        cv.put(KEY_TITULO, titulo);
        cv.put(KEY_DESCRIP, descripcion);
        cv.put(KEY_FECHAINICIO, fechaInicio);
        cv.put(KEY_FECHATERMINO, fechaTermino);
        cv.put(KEY_HORA, hora);
        cv.put(KEY_TIPO, tipo);

        return this.ourDatabase.insert(DATABASE_TABLE, null, cv);

    }

    public long borrarRecordatorio(String id){
        return this.ourDatabase.delete(DATABASE_TABLE, KEY_ID + "=?", new String[]{id});
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
                    KEY_DESCRIP + " TEXT NOT NULL, " +
                    KEY_FECHAINICIO + " TEXT NOT NULL, " +
                    KEY_FECHATERMINO + " TEXT NOT NULL, " +
                    KEY_HORA + " TEXT NOT NULL, " +
                    KEY_TIPO + " INTEGER NOT NULL); ";

            db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

            onCreate(db);
        }
    }

}
