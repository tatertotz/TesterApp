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
    private static final int DB_VERSION = 3;

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

        //This will get called by the onCreate because the "oldVersion" is 0 because it doesn't exist.
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE SUSHI (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "TYPE TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER);");
            insertSushi(db, "Sake Nigiri", "Nigiri", "Salmon", R.drawable.sake_nigiri);
            insertSushi(db, "California Roll", "Uramaki",
                    "One of the rare types of Western sushi that's popular in Japan. " +
                            "A roll made of cucumber, imitation crab, and avocado.",
                    R.drawable.california_uramaki);
            insertSushi(db, "Kappa Maki", "Maki",
                    "Cucumber roll named after a Japanese Monster (Kappa) " +
                            "who eats cucumbers.", R.drawable.kappa_maki);
            insertSushi(db, "Anago", "Sashimi", "Conger eel (saltwater eel).",
                    R.drawable.anago_sashimi);
            insertSushi(db, "Temaki", "Temaki",
                    "A cone-shaped sushi wrapped in nori that's intended to be eaten " +
                            "with your hands.", R.drawable.sake_temaki);
            insertSushi(db, "Maguro Nigiri", "Nigiri",
                    "A lean cut of tuna. This is the inexpensive variety of tuna. " +
                            "When it comes to tuna, fatty cuts are more expensive.",
                    R.drawable.maguro_nigiri);
        }

        //What to do if you have version 1 of the database (it was lacking some spaces in some description entries).
        if (oldVersion == 1) {
            updateSushi(db, "Sake Nigiri", "Nigiri", "Salmon", R.drawable.sake_nigiri);
            updateSushi(db, "California Roll", "Uramaki",
                    "One of the rare types of Western sushi that's popular in Japan. " +
                            "A roll made of cucumber, imitation crab, and avocado.",
                    R.drawable.california_uramaki);
            updateSushi(db, "Kappa Maki", "Maki",
                    "Cucumber roll named after a Japanese Monster (Kappa) " +
                            "who eats cucumbers.", R.drawable.kappa_maki);
            updateSushi(db, "Anago", "Sashimi", "Conger eel (saltwater eel).",
                    R.drawable.anago_sashimi);
            updateSushi(db, "Temaki", "Temaki",
                    "A cone-shaped sushi wrapped in nori that's intended to be eaten " +
                            "with your hands.", R.drawable.sake_temaki);
            updateSushi(db, "Maguro Nigiri", "Nigiri",
                    "A lean cut of tuna. This is the inexpensive variety of tuna. " +
                            "When it comes to tuna, fatty cuts are more expensive.",
                    R.drawable.maguro_nigiri);
        }

        //What to do if you have an old version of the database.
        if (oldVersion < 3) {
            db.execSQL("ALTER TABLE SUSHI ADD COLUMN FAVORITE NUMERIC;");
        }
    }

    public static void insertSushi(SQLiteDatabase db, String name, String type, String description, int resourceId) {
        ContentValues sushiValues = new ContentValues();
        sushiValues.put("NAME", name);
        sushiValues.put("TYPE", type);
        sushiValues.put("DESCRIPTION", description);
        sushiValues.put("IMAGE_RESOURCE_ID", resourceId);
        db.insert("SUSHI", null, sushiValues);
    }

    public static void updateSushi(SQLiteDatabase db, String name, String type, String description, int resourceId) {
        ContentValues sushiValues = new ContentValues();
        sushiValues.put("NAME", name);
        sushiValues.put("TYPE", type);
        sushiValues.put("DESCRIPTION", description);
        sushiValues.put("IMAGE_RESOURCE_ID", resourceId);
        db.update("SUSHI", sushiValues, "NAME = ?", new String[] {name});
    }
}
