package com.example.thibaut.mysudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Generation du tableau d'objet
        int tab[][] = new int[300][4];
        int idItem = 0;
        for (int level = 1; level <= 3; level++) {
            for (int i = 0; i <= 99; i++) {
                for (int j = 0; j <= 3; j++) {
                    switch (j) {
                        case 0:
                            tab[idItem][j] = idItem;
                            break;
                        case 1:
                            tab[idItem][j] = level;
                            break;
                        case 2:
                            tab[idItem][j] = i;
                            break;
                        case 3:
                            tab[idItem][j] = (int) (Math.random() * (101));
                            break;
                        default:
                            break;
                    }
                }
                idItem++;
            }
        }

        //Recuperation du bundle
        Bundle objetbundle = this.getIntent().getExtras();
        int level = objetbundle.getInt("level");

        //Creation de la liste
        final ListView listview = (ListView) findViewById(R.id.listeId);
        String[] values = new String[100];
        int compteur = 0;
        for (int i = 0; i < tab.length; i++) {
            if (tab[i][1] == level) {
                values[compteur] = Integer.toString(tab[i][2]) + " - " + Integer.toString(tab[i][3]);
                compteur++;
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listview.setAdapter(adapter);

        //From LIST to GAME
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }
}
