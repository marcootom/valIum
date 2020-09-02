package com.example.valium;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Ricette extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ricette);
        String username = MappaUtenti.getUtenteAttuale();
        final ListView mylist = (ListView) findViewById(R.id.listView1);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1, MappaRicette.ricetteUtente(username));
        mylist.setAdapter(adapter);
        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adattatore, final View componente, int pos, long id){
                // recupero il titolo memorizzato nella riga tramite l'ArrayAdapter
                final String titoloriga = (String) adattatore.getItemAtPosition(pos);
                final char a = ' ';
                String dio = "";
                for(int i = 1; i < titoloriga.length() && titoloriga.charAt(i) != a; i++) {
                    dio = dio + titoloriga.charAt(i);
                }
                Log.d("List", "Ho cliccato sull'elemento con id" + dio);
            }
        });
    }
}


