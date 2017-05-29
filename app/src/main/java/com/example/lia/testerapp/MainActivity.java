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
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import static com.example.lia.testerapp.SushiCategoryActivity.EXTRA_SUSHISTYLE;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private Cursor favoritesCursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create an on click listener for the buttons
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

        //Set the on click listener to the list view.
        ListView listView = (ListView) findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);

        //Populate the favorites list from the database
        ListView listFavorites = (ListView) findViewById(R.id.favorites);

        try {
            SQLiteOpenHelper sushiDatabaseHelper =  new SushiDatabaseHelper(this);
            db = sushiDatabaseHelper.getReadableDatabase();
            favoritesCursor = db.query("SUSHI", new String[] {"NAME", "FAVORITE", "_id"},
                    "FAVORITE = 1", null, null, null, null);

            //Create a cursor adapter to eventually put the data from the database into the app
            CursorAdapter favoritesAdapter = new SimpleCursorAdapter(MainActivity.this,
                    android.R.layout.simple_list_item_1, favoritesCursor,
                    new String[] {"NAME"}, new int[] {android.R.id.text1}, 0);

            listFavorites.setAdapter(favoritesAdapter);

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    //Close the cursor and database in the onDestroy() method
    @Override
    public void onDestroy() {
        super.onDestroy();
        favoritesCursor.close();
        db.close();
    }

}
