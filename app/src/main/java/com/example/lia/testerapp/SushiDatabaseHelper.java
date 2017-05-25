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
    private static final int DB_VERSION = 2;

    SushiDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    public void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE SUSHI (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "NAME TEXT, "
            + "TYPE TEXT, "
            + "DESCRIPTION TEXT);");
            insertSushi(db, "Salmon Roll", "Maki", "Yummy");
            insertSushi(db, "Salmon Nigiri", "Nigiri", "Tasty");
            insertSushi(db, "California Roll", "Maki", "Mmmm");
        }
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE DRINK ADD COLUMN RATING NUMERIC;");
        }
    }

    public static void insertSushi(SQLiteDatabase db, String name, String type, String description) {
        ContentValues sushiValues = new ContentValues();
        sushiValues.put("NAME", name);
        sushiValues.put("TYPE", type);
        sushiValues.put("DESCRIPTION", description);
        db.insert("DRINK", null, sushiValues);
    }
}
