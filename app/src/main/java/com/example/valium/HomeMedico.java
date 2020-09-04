package com.example.valium;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeMedico extends AppCompatActivity {
    TextView benvenuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_medico);
        benvenuto = findViewById(R.id.benvenutomedico);
        String s = "Benvenuto, Dottor " + MappaUtenti.recuperaUtente(MappaUtenti.getUtenteAttuale()).getCognome() + "!";
        benvenuto.setText(s);
    }
}
