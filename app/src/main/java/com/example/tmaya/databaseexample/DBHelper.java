package com.example.tmaya.databaseexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "test.db";
    private static final String CREATE_TABLE_USER = "CREATE TABLE user (username VARCHAR(20) PRIMARY KEY,password VARCHAR(20));";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_USER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean chekUser(String username, String password) {
        boolean success = false;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        if (sqLiteDatabase != null) {
            String sql = "SELECT * FROM user WHERE username = '" + username + "' AND password = '" + password + "';";

            final Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
            if (cursor != null && cursor.getCount() == 1) {
                success = true;
                cursor.close();

            }
            sqLiteDatabase.close();

        }

        return success;
    }

    public boolean saveUser(String username, String password) {
        boolean success = false;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        if (sqLiteDatabase != null) {

            ContentValues contentValues = new ContentValues();
            contentValues.put("username", username);
            contentValues.put("password", password);

            try {
                sqLiteDatabase.insertOrThrow(
                        "user",
                        null,
                        contentValues
                );
                success=true;


            } catch (Exception e) {
                //Ignored
            } finally {
                sqLiteDatabase.close();
            }

        }

        return success;

    }
}
