package com.example.valium;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class PaginaPaziente extends AppCompatActivity {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    TextView nomePaziente;
    static User u;
    com.google.android.material.textfield.TextInputEditText codFiscale, telefono, mail;
    EditText data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_paziente);

        u = MappaUtenti.getPazienteAttuale();
        String nomeCognome = u.getNome() + " " + u.getCognome();
        nomePaziente = findViewById(R.id.nomePaziente);
        nomePaziente.setText(nomeCognome);
        codFiscale = findViewById(R.id.codiceFiscale);
        codFiscale.setText(u.getUsername());
        codFiscale.setKeyListener(null);
        data = findViewById(R.id.birthText);
        data.setText(format.format(u.getDataNascita()));
        data.setKeyListener(null);
        telefono = findViewById(R.id.phone);
        telefono.setText(u.getTelefono());
        telefono.setKeyListener(null);
        mail = findViewById(R.id.email);
        mail.setText(u.getEmail());
        mail.setKeyListener(null);

    }
}
