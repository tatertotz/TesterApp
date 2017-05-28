package com.example.lia.testerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import static com.example.lia.testerapp.SushiCategoryActivity.EXTRA_SUSHISTYLE;

public class MainActivity extends AppCompatActivity {
    private SushiExpert expert = new SushiExpert();

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
    }

}
