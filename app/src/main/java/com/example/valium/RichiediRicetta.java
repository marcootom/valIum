package com.example.valium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RichiediRicetta extends AppCompatActivity {

    ArrayList<String> listaFarmaci = MappaUtenti.recuperaUtente(MappaUtenti.getUtenteAttuale()).getListaFarmaci();
    String medicinaScelta;
    Button richiediRicetta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_richiedi_ricetta);
        richiediRicetta = findViewById(R.id.confermaRichiesta);
        // preparazione della ListView per l'elenco delle città
        ListView lv = (ListView) findViewById(R.id.farmaco);
        ArrayAdapter<String> listviewAdapter = new ArrayAdapter<String>(this, R.layout.row);
        lv.setAdapter(listviewAdapter);
        // preparazione dello Spinner per mostrare l'elenco dei Paesi
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, R.layout.row);
        spinnerAdapter.addAll(listaFarmaci);
        final Spinner sp = (Spinner) findViewById(R.id.farmaci);
        sp.setAdapter(spinnerAdapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                TextView txt = (TextView) arg1.findViewById(R.id.rowtext);
                medicinaScelta = txt.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                medicinaScelta = "";
            }
        });
        richiediRicetta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RichiesteRicette r;
                if (!medicinaScelta.equals("")) {
                    r = new RichiesteRicette(medicinaScelta, MappaUtenti.getUtenteAttuale());
                    MappaRichiesteRicette.aggiungiRichiesta(r);
                    Toast.makeText(getApplicationContext(), "La richiesta di ricetta per il " +
                            "farmaco selezionato è stata inviata!", Toast.LENGTH_LONG).show();
                    Home();
                } else
                    Toast.makeText(getApplicationContext(), "Seleziona un farmaco", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void Home(){
        Intent intent = new Intent(this, HomeUtente.class);
        startActivity(intent);
    }
}
