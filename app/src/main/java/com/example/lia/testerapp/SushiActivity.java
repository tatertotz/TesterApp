package com.example.lia.testerapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SushiActivity extends AppCompatActivity {

    public static final String EXTRA_SUSHINO = "sushiNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sushi);

        //Get the sushi from the intent
        int sushiNo = (Integer)getIntent().getExtras().get(EXTRA_SUSHINO);

        //Create a cursor
        try {
            SQLiteOpenHelper sushiDatabaseHelper = new SushiDatabaseHelper(this);
            SQLiteDatabase db = sushiDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("SUSHI",
                    new String[] {"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"},
                    "_id = ?",
                    new String[] {Integer.toString(sushiNo)},
                    null, null, null);

            if (cursor.moveToFirst()) {
                //Get the sushi details from the cursor
                String nameText = cursor.getString(0);
                String typeText = cursor.getString(1);
                int photoId = cursor.getInt(2);

                //Populate the sushi name
                TextView name = (TextView)findViewById(R.id.name);
                name.setText(nameText);

                //Populate the sushi type
                TextView type = (TextView) findViewById(R.id.type);
                type.setText(typeText);

                //Populate the sushi picture
                ImageView photo = (ImageView) findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);
            }
            cursor.close();
            db.close();
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
