package com.example.valium;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HomeMedico extends AppCompatActivity {
    TextView benvenuto;
    public CardView listaPazienti, listaPrenotazioni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_medico);
        listaPazienti = findViewById(R.id.bottoneListaPazienti);
        listaPrenotazioni = findViewById(R.id.bottoneListaPrenotazioni);

        benvenuto = findViewById(R.id.benvenutomedico);
        String s = "Benvenuto, Dottor " + MappaUtenti.recuperaUtente(MappaUtenti.getUtenteAttuale()).getCognome() + "!";
        benvenuto.setText(s);

        listaPazienti.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                goListaPazienti();
            }
        });
    }

    public void goListaPazienti(){
        Intent intent = new Intent(this, ListaPazienti.class);
        startActivity(intent);
    }
}
