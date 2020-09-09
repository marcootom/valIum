package com.example.valium;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Contatti extends AppCompatActivity {

    TextView name, phone, mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contatti);
        name = findViewById(R.id.doctorname);
        phone = findViewById(R.id.doctorphone1);
        mail = findViewById(R.id.doctormail);
        ImageButton cell = findViewById(R.id.callCellulare);
        ImageButton fisso = findViewById(R.id.callFisso);
        User medico = MappaUtenti.recuperaMedico();
        String s = "Dottor " + MappaUtenti.recuperaMedico().getNome() + " " + MappaUtenti.recuperaMedico().getCognome();
        name.setText(s);
        phone.setText(MappaUtenti.recuperaMedico().getTelefono());
        mail.setText(MappaUtenti.recuperaMedico().getEmail());
        cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialContactPhone(MappaUtenti.recuperaMedico().getTelefono());
            }
        });
        fisso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialContactPhone("+39078245678");
            }
        });

    }

    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(this, HomeUtente.class));
        finish();
    }
}