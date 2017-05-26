package com.example.lia.testerapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SushiExpert expert = new SushiExpert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Call when the user clicks the button
    public void onClickFindSushi(View view){
        //Get a reference to the TextView
        TextView types = (TextView) findViewById(R.id.types);

        //Get a reference to the Spinner
        Spinner style = (Spinner) findViewById(R.id.style);

        //Get the selected item in the Spinner
        String sushiStyle = String.valueOf(style.getSelectedItem());


        //Get recommendations from SushiExpert class
        List<String> typesList = expert.getTypes(sushiStyle);
        StringBuilder typesFormatted = new StringBuilder();
        for (String type : typesList){
            typesFormatted.append(type).append('\n');
        }
        //Display the beers
        types.setText(typesFormatted);
    }
}
