package br.org.catolicasc.gerenciamentofinanceiro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseDespesa extends SQLiteOpenHelper {

    private static final String TAG = "DataBaseDespesa";

    private static final String TABLE_NAME = "despesa";
    private static final String COL1 = "id";
    private static final String COL2 = "usuario";
    private static final String COL3 = "descricao";
    private static final String COL4 = "categoria";
    private static final String COL5 = "valor";
    private static final String COL6 = "data";

    public DataBaseDespesa(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            String createTable = "CREATE TABLE " + TABLE_NAME +
                    " (" + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COL2 + " TEXT," +
                    COL3 + " TEXT," +
                    COL4 + " TEXT," +
                    COL5 + " FLOAT," +
                    COL6 + " TEXT)";

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

    public boolean addData(String usuario, String descricao, String categoria, float valor, String data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, usuario);
        contentValues.put(COL3, descricao);
        contentValues.put(COL4, categoria);
        contentValues.put(COL5, valor);
        contentValues.put(COL6, data);

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

    public Cursor getDataUser(String usuario, boolean agruparCategoria){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL2 + "='" + usuario + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getDataUserByCategory(String usuario){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL4 + ", SUM(" + COL5 + ") FROM " + TABLE_NAME + " WHERE " + COL2 + "='" + usuario + "'" +  " GROUP BY " + COL4;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}
