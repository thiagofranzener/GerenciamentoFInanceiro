package br.org.catolicasc.gerenciamentofinanceiro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseReceita extends SQLiteOpenHelper {

    private static final String TAG = "DataBaseReceita";

    private static final String TABLE_NAME = "receita";
    private static final String COL1 = "usuario";
    private static final String COL2 = "descricao";
    private static final String COL3 = "valor";
    private static final String COL4 = "data";

    public DataBaseReceita(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            String createTable = "CREATE TABLE " + TABLE_NAME +
                    " (" + COL1 + " TEXT PRIMARY KEY," +
                    COL2 + " TEXT," +
                    COL3 + " FLOAT," +
                    COL4 + " TEXT)";

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

    public boolean addData(String usuario, String descricao, Double valor, String data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, usuario);
        contentValues.put(COL2, descricao);
        contentValues.put(COL3, valor);
        contentValues.put(COL4, data);

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
}
