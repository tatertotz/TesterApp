package com.example.lia.testerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lia on 5/21/17.
 */

public class SushiDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "sushi";
    private static final int DB_VERSION = 1;

    SushiDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SUSHI ("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "NAME TEXT, "
        + "TYPE TEXT, "
        + "DESCRIPTION TEXT);");

//        insertSushi(db, "Meow")
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static void insertSushi(SQLiteDatabase db, String name, String type, String description) {
        ContentValues sushiValues = new ContentValues();
        sushiValues.put("NAME", name);
        sushiValues.put("TYPE", type);
        sushiValues.put("DESCRIPTION", description);
        db.insert("DRINK", null, sushiValues);
    }
}
