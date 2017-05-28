package com.example.lia.testerapp;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class SushiCategoryActivity extends ListActivity {

    public static final String EXTRA_SUSHISTYLE = "sushiStyle";
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listSushi = getListView();

        //Get the sushi type from the intent
        String sushiType = getIntent().getExtras().get(EXTRA_SUSHISTYLE).toString();

        try {
            SQLiteOpenHelper sushiDatabaseHelper = new SushiDatabaseHelper(this);
            db = sushiDatabaseHelper.getReadableDatabase();

            cursor = db.query("SUSHI",
                    new String[]{"_id", "NAME"},
                    "TYPE = ?", new String[] {sushiType}, null, null, null);

            CursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"NAME"},
                    new int[] {android.R.id.text1},
                    0);
            listSushi.setAdapter(listAdapter);

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }

    @Override
    public void onListItemClick(ListView listView, View itemView, int position, long id) {
        Intent intent = new Intent(SushiCategoryActivity.this, SushiActivity.class);
        intent.putExtra(SushiActivity.EXTRA_SUSHINO, (int)id);
        startActivity(intent);
    }
}
