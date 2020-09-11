package com.example.valium;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListaPrenotazioni extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView msg;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_prenotazioni);
        RecyclerView r = findViewById(R.id.recyclePrenotazioni);
        RecyclerViewAdapter adapter;
        ArrayList<String> prenotazioni = MappaAppuntamenti.appuntamentiUtenteAttuale(MappaUtenti.getUtenteAttuale());
        adapter = new RecyclerViewAdapter(prenotazioni,this);
        r.setHasFixedSize(true);
        r.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        r.setLayoutManager(llm);
        if(prenotazioni.size()==0){
            msg = findViewById(R.id.noResults);
            msg.setVisibility(TextView.VISIBLE);
        }

    }

    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(this, HomeUtente.class));
        finish();
    }


}
