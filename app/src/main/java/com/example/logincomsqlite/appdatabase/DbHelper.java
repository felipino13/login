package com.example.logincomsqlite.appdatabase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper( Context context) {
        super(context, "login.db", null, 1);
    }


    public boolean inserirUsuario(String username, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);

        long result = myDB.insert("users", null, values);

        return result != -1;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (id integer primary key autoincrement, username text, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
    }
    public boolean checkUserPassword(String username, String password){
        int teste = 0;

        String n = username;
        String p = password;

        SQLiteDatabase database = this.getReadableDatabase();

        @SuppressLint("Recycle") Cursor cursor = database.rawQuery("SELECT *FROM users WHERE username = ?" +
                " and password = ?", new String[]{username, password});

        if (cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkUserName(String username){
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT *FROM users WHERE username = ?", new String[]{username});

        return cursor.getCount() > 0;
    }

     public boolean teste(String username, String password){
        boolean retorno = true;

        String n = username;
        String p = password;

      return retorno;
    }
}
