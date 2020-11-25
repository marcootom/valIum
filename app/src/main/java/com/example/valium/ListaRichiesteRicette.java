package com.example.valium;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaRichiesteRicette extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_richieste_ricette);
        final ListView lric = (ListView) findViewById(R.id.listaRichieste);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, MappaRichiesteRicette.stringheRichieste());
        lric.setAdapter(adapter);
        lric.setTextFilterEnabled(true);
        lric.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onItemClick(AdapterView<?> adattatore, final View componente, int pos, long id) {
                RichiesteRicette r = MappaRichiesteRicette.getListaRichieste().get(pos);
                MappaUtenti.setPazienteAttuale(MappaUtenti.recuperaUtente(r.getUsername()));
                PaginaPaziente();
            }
        });
    }

    public void PaginaPaziente(){
        Intent intent = new Intent(this, PaginaPaziente.class);
        startActivity(intent);
    }
}