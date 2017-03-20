package com.example.fabiosalazar.projetoandroidfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabio on 20/03/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME="student";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "nomeTabela";
    private static final String STU_TABLE = "create table " + TABLE_NAME + "(name TEXT)";

    Context context;
    SQLiteDatabase db;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(STU_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    public DatabaseHelper openDB(){
        try {
            db = getWritableDatabase();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return this;
    }


    public void insertIntoDB(String name){
        Log.d("insert", "before insert");

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);

        db.insert(TABLE_NAME, null, values);

        db.close();
        Toast.makeText(context, "insert value", Toast.LENGTH_LONG);
        Log.i("insert into DB", "After insert");
    }

    public List<DatabaseModel> getDataFromDB(){
        List<DatabaseModel> modelList = new ArrayList<DatabaseModel>();
        String query = "select * from " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                DatabaseModel model = new DatabaseModel();
                model.setName(cursor.getString(0));

                modelList.add(model);

            } while (cursor.moveToNext());
        }

        return modelList;
    }

    public void Delete(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "name" + " = ?", new String[]{ name });
        db.close();
    }

    public long Editar(String name){

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        return db.update(TABLE_NAME, contentValues, "name" + " = ?", new String[]{name});

    }
}








