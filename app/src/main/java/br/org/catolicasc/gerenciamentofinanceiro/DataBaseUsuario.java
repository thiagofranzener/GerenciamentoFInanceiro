package br.org.catolicasc.gerenciamentofinanceiro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;


public class DataBaseUsuario extends SQLiteOpenHelper {

    private static final String TAG = "DataBaseUsuario";

    private static final String TABLE_NAME = "usuario";
    private static final String COL1 = "nome";
    private static final String COL2 = "dataNasc";
    private static final String COL3 = "cpf";
    private static final String COL4 = "email";
    private static final String COL5 = "senha";
    private static final String COL6 = "logado";

    public DataBaseUsuario(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            String createTable = "CREATE TABLE " + TABLE_NAME +
                " (" + COL1 + " TEXT PRIMARY KEY," +
                COL2 + " TEXT," +
                COL3 + " TEXT," +
                COL4 + " TEXT," +
                COL5 + " TEXT," +
                COL6 + " INT)";

            db.execSQL(createTable);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String nome, String dataNasc, String cpf, String email, String senha, Boolean logado) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, nome);
        contentValues.put(COL2, dataNasc);
        contentValues.put(COL3, cpf);
        contentValues.put(COL4, email);
        contentValues.put(COL5, senha);
        if (logado){
            contentValues.put(COL6, 1);
        }else {
            contentValues.put(COL6, 0);
        }

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public String getLoggedUser(){
        Cursor data = getData();
        String usuario = "";
        while(data.moveToNext()){
            if (data.getString(5).equals(1)){
                usuario = data.getString(0);
            }
        }
        return usuario;
    }

    public void setLoggedUser(String usuario){
        SQLiteDatabase db = this.getWritableDatabase();
        String logIn = "UPDATE " + TABLE_NAME + " SET " + COL6 + "=1 WHERE " + COL1 + "='" + usuario + "'";
        db.execSQL(logIn);
    }

    public void logOut(){
        SQLiteDatabase db = this.getWritableDatabase();
        String logOut = "UPDATE " + TABLE_NAME + " SET " + COL6 + "=0";
        db.execSQL(logOut);
    }

}