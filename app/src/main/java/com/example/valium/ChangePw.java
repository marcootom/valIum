package com.example.valium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ChangePw extends AppCompatActivity {

    com.google.android.material.textfield.TextInputEditText oldpw, newpw1, newpw2;
    Button changePw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pw);

        oldpw = findViewById(R.id.oldPasswordText);
        newpw1 = findViewById(R.id.newPasswordText);
        newpw2 = findViewById(R.id.confirmNewPwText);
        changePw = findViewById(R.id.changePwButton);

        changePw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInputField()) {
                    //MappaUtenti.modificaPassword(newpw1.getText().toString());
                    String pass = newpw1.getText().toString();
                    MappaUtenti.recuperaUtente(MappaUtenti.getUtenteAttuale()).setPassword(pass);
                    MappaUtenti.setUtenteAttuale(null);
                    Toast.makeText(getApplicationContext(), "Password modificata!", Toast.LENGTH_LONG).show();
                    home();
                }
            }
        });

    }
    public void home() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    private boolean checkInputField() {
        //Questa funzione verifica la correttezza dei campi inseriti e segnala all'utente eventuali errori con messaggi a video
        String old = oldpw.getText().toString();
        String new1 = newpw1.getText().toString();
        String new2 = newpw2.getText().toString();
        int errors = 0;

        if (!old.equals(MappaUtenti.recuperaUtente(MappaUtenti.getUtenteAttuale()).getPassword())) {
            oldpw.setError("La vecchia password inserita non è corretta");
            errors++;
        }

        if(new1.equals((MappaUtenti.recuperaUtente(MappaUtenti.getUtenteAttuale()).getPassword()))) {
            newpw1.setError("La password nuova non può essere uguale alla vecchia");
            errors++;
        }

        if(newpw1.getText().length() < 8){
            newpw1.setError("La password deve essere di almeno 8 caratteri");
            errors++;
        }

        if (newpw1.getText() == null || newpw1.getText().length() == 0){
            newpw1.setError("Inserire password");
            errors++;
        }

        if (newpw2.getText() == null || newpw2.getText().length() == 0){
            newpw2.setError("Inserire password");
            errors++;
        }

        if(!new1.equals(new2)){
            newpw1.setError("La password non corrisponde");
            newpw2.setError("La password non corrisponde");
            errors++;
        }

        return (errors == 0);
    }
}
