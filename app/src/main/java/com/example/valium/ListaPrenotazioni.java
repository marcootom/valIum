package com.example.valium;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class ListaPrenotazioni extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_prenotazioni);

        ArrayList<String> AC = new ArrayList<>();
        AC.add("fgte");
        AC.add("darw");
        ListView sw = findViewById(R.id.listaAppuntamenti);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.card_appuntamento, AC);
        sw.setAdapter(adapter);

    }
}
