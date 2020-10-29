package com.example.valium;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class ListaPazienti extends AppCompatActivity {

    SearchView search;
    TextView noResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pazienti);
        search = findViewById(R.id.barraRicerca);
        search.setIconifiedByDefault(false);
        search.setSubmitButtonEnabled(false);
        search.setQueryHint("Cerca paziente...");
        search.setIconified(false);
        noResults = findViewById(R.id.noResults);

        final ListView lPaz = (ListView) findViewById(R.id.listaPazienti);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MappaUtenti.getListaUtenti(""));
        lPaz.setAdapter(adapter);
        lPaz.setTextFilterEnabled(true);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String summName) {
                lPaz.setFilterText(summName);
                lPaz.dispatchDisplayHint(View.INVISIBLE);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)) {
                    lPaz.clearTextFilter();
                    adapter.getFilter().filter("");
                } else {
                    lPaz.setFilterText(s);
                }
                lPaz.dispatchDisplayHint(View.INVISIBLE);
                return true;
            }
        });

        lPaz.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onItemClick(AdapterView<?> adattatore, final View componente, int pos, long id) {
                final String paziente = (String) adattatore.getItemAtPosition(pos);
                String[] paz = paziente.split(" ");
                MappaUtenti.setPazienteAttuale(MappaUtenti.recuperaUtente(paz[4]));
                paginaPaziente();
            }
        });
    }

    public void paginaPaziente() {
        Intent intent = new Intent(this, PaginaPaziente.class);
        startActivity(intent);
    }
}
