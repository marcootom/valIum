package com.example.valium;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HomeMedico extends AppCompatActivity {
    TextView benvenuto;
    public CardView listaPazienti, listaPrenotazioni, listaRichiesteRicette;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_medico);
        listaPazienti = findViewById(R.id.bottoneListaPazienti);
        listaPrenotazioni = findViewById(R.id.bottoneListaPrenotazioni);
        listaRichiesteRicette = findViewById(R.id.bottoneListaRichiesteRicette);

        benvenuto = findViewById(R.id.benvenutomedico);
        String s = "Benvenuto, Dottor " + MappaUtenti.recuperaUtente(MappaUtenti.getUtenteAttuale()).getCognome() + "!";
        benvenuto.setText(s);

        listaRichiesteRicette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goListaRichiesteRicette();
            }
        });

        listaPazienti.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                goListaPazienti();
            }
        });
        listaPrenotazioni.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                goListaPrenotazioni();
            }
        });
    }

    private void goListaPrenotazioni() {
        Intent intent = new Intent(this, ListaAppuntamentiMedico.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {

            this.finishAffinity();
            return;
        }
        doubleBackToExitPressedOnce = true;
        Toast.makeText(this, R.string.msg_exit, Toast.LENGTH_SHORT).show();

        Timer t = new Timer();
        t.schedule(new TimerTask() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2500);

    }


    public void goListaPazienti(){
        Intent intent = new Intent(this, ListaPazienti.class);
        startActivity(intent);
    }
    public void goListaRichiesteRicette(){
        Intent intent = new Intent(this, ListaRichiesteRicette.class);
        startActivity(intent);
    }
}
