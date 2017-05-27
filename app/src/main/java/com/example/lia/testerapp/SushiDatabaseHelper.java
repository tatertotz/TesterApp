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
        //updateMyDatabase(db, 0, DB_VERSION);
        db.execSQL("CREATE TABLE SUSHI (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "TYPE TEXT, "
                + "IMAGE_RESOURCE_ID INTEGER);");
        insertSushi(db, "Salmon Nigiri", "Nigiri", R.drawable.sake_nigiri);
        insertSushi(db, "California Roll", "Uramaki", R.drawable.california_uramaki);
        insertSushi(db, "Cucumber Roll", "Maki", R.drawable.kappa_maki);
        insertSushi(db, "Saltwater Eel", "Sashimi", R.drawable.anago_sashimi);
        insertSushi(db, "Temaki", "Temaki", R.drawable.sake_temaki);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        updateMyDatabase(db, oldVersion, newVersion);
    }

//    public void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if (oldVersion < 1) {
//            db.execSQL("CREATE TABLE SUSHI (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
//                    + "NAME TEXT, "
//                    + "TYPE TEXT, "
//                    + "IMAGE_RESOURCE_ID INTEGER);");
//            insertSushi(db, "Salmon Nigiri", "Nigiri", R.drawable.sake_nigiri);
//            insertSushi(db, "California Roll", "Uramaki", R.drawable.california_uramaki);
//            insertSushi(db, "Cucumber Roll", "Maki", R.drawable.kappa_maki);
//            insertSushi(db, "Saltwater Eel", "Sashimi", R.drawable.anago_sashimi);
//            insertSushi(db, "Temaki", "Temaki", R.drawable.sake_temaki);
//        }
//        if (oldVersion < 2) {
//            db.execSQL("ALTER TABLE SUSHI ADD COLUMN RATING NUMERIC;");
//        }
//    }

    public static void insertSushi(SQLiteDatabase db, String name, String type, int resourceId) {
        ContentValues sushiValues = new ContentValues();
        sushiValues.put("NAME", name);
        sushiValues.put("TYPE", type);
        sushiValues.put("IMAGE_RESOURCE_ID", resourceId);
        db.insert("SUSHI", null, sushiValues);
    }
}
