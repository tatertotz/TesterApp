package com.example.lia.testerapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import static com.example.lia.testerapp.SushiCategoryActivity.EXTRA_SUSHISTYLE;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private Cursor favoritesCursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {
                //Get the chosen type form the listView
                String type = listView.getItemAtPosition(position).toString();
                Intent intent = new Intent(MainActivity.this, SushiCategoryActivity.class);
                intent.putExtra(EXTRA_SUSHISTYLE, type);
                startActivity(intent);
//                if (position == 0) {
//                    Intent intent = new Intent(MainActivity.this, SushiCategoryActivity.class);
//                    startActivity(intent);
//                }
            }
        };

        ListView listView = (ListView) findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);

        //Populate the favorites list from the database
        ListView listFavorites = (ListView) findViewById(R.id.favorites);

        try {
            SQLiteOpenHelper sushiDatabaseHelper =  new SushiDatabaseHelper(this);
            db = sushiDatabaseHelper.getReadableDatabase();
            favoritesCursor = db.query("SUSHI", new String[] {"NAME", "FAVORITES", "_id"},
                    "FAVORITE = 1", null, null, null, null);   
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

}
