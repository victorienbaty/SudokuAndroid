package com.example.thibaut.mysudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        Button ButtonLevelEasy = (Button) findViewById(R.id.ButtonLevelEasy);
        Button ButtonLevelMedium = (Button) findViewById(R.id.ButtonLevelMedium);
        Button ButtonLevelHard = (Button) findViewById(R.id.ButtonLevelHard);

        ButtonLevelEasy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View button11) {
                Intent intentEasy = new Intent(LevelActivity.this, ListActivity.class);
                Bundle objetBundle = new Bundle();
                objetBundle.putInt("level", 1);
                intentEasy.putExtras(objetBundle);
                startActivity(intentEasy);
            }
        });

        ButtonLevelMedium.setOnClickListener(new View.OnClickListener() {
            public void onClick(View button11) {
                Intent intentMedium = new Intent(LevelActivity.this, ListActivity.class);
                Bundle objetBundle = new Bundle();
                objetBundle.putInt("level", 2);
                intentMedium.putExtras(objetBundle);
                startActivity(intentMedium);
            }
        });

        ButtonLevelHard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View button11) {
                Intent intentHard = new Intent(LevelActivity.this, ListActivity.class);
                Bundle objetBundle = new Bundle();
                objetBundle.putInt("level", 3);
                intentHard.putExtras(objetBundle);
                startActivity(intentHard);
            }
        });
    }
}
