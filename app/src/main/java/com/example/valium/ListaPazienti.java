package com.example.valium;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

public class ListaPazienti extends AppCompatActivity {

    SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pazienti);
        search = findViewById(R.id.barraRicerca);
        search.setQueryHint("Cerca paziente...");
        search.setIconified(false);

        final ListView lPaz = (ListView) findViewById(R.id.listaPazienti);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MappaUtenti.getListaUtenti(""));
        lPaz.setAdapter(adapter);
        lPaz.setTextFilterEnabled(true);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String summName) {
                lPaz.setFilterText(summName);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
    });
    }
}
