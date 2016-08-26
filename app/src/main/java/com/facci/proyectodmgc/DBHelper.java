package com.facci.proyectodmgc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tania on 24/08/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_Nombre = "CNE_DMGC.db";
    public static final String tabla_votantes_dmgc = "VOTANTES_DMGC";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "NOMBRE";
    public static final String COL_3 = "APELLIDO";
    public static final String COL_4 = "RECINTO ELECTORAL";
    public static final String COL_5 = "AÑO NACIMIENTO";


    public DBHelper(Context context) {
        super(context, DB_Nombre, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();


    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(String.format("create table %s (ID INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT, %s TEXT,%s TEXT,%s INTEGER)",tabla_votantes_dmgc,COL_2,COL_3,COL_4,COL_5));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(String.format("DROP TABLE IF EXISTS %s",tabla_votantes_dmgc));
        onCreate(db);

    }
    //Inserto un registro a la base de datos
    public boolean insertar(String nombre, String apellido,String recinto,int año){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,apellido);
        contentValues.put(COL_4,recinto);
        contentValues.put(COL_5,año);
        long resultado = db.insert(tabla_votantes_dmgc,null,contentValues);

        if(resultado == -1)
            return false;
        else
            return true;

    }

    public Cursor selectVerTodos(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(String.format("select * from %s",tabla_votantes_dmgc),null);
        return  res;
    }

    //Modifica un registro desde la base de datos
    public boolean modificarRegistro(String id,String nombre, String apellido,String recinto,int año){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,apellido);
        contentValues.put(COL_4,recinto);
        contentValues.put(COL_5,año);
        db.update(tabla_votantes_dmgc,contentValues,"id = ?",new String[]{id});
        return true;
    }

    //Para eliminar un registro en la base de datos
    public Integer eliminarRegistro(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(tabla_votantes_dmgc,"id = ?",new String[]{id});

    }

}


