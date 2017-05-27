package com.example.lia.testerapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SushiActivity extends AppCompatActivity {

    public static final String EXTRA_SUSHINO = "drinkNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sushi);

        //Get the drink from the intent
        int sushiNo = (Integer)getIntent().getExtras().get(EXTRA_SUSHINO);

        //Create a cursor
        SQLiteOpenHelper sushiDatabaseHelper = new SushiDatabaseHelper(this);
        SQLiteDatabase db = sushiDatabaseHelper.getReadableDatabase();
        Cursor cursor = db.query("SUSHI",
                new String[] {"NAME", "TYPE", "IMAGE_RESOURCE_ID"},
                "id = ?",
                new String[] {Integer.toString(sushiNo)},
                null, null, null);
    }
}
