package com.example.valium;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    com.google.android.material.textfield.TextInputEditText username, password;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DataSet.caricaDati();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        //Funzione eseguita quando viene premuto il tasto login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInputField()) {
                    //Se il login è andato a buon fine, viene settato l'utente attuale con lo user usato per fare login
                    MappaUtenti.setUtenteAttuale(username.getText().toString());
                    if (!MappaUtenti.recuperaUtente(MappaUtenti.getUtenteAttuale()).getMedico()) {
                        home();
                    } else homeMedico();
                }
            }
        });
    }

    public void home() {
        Intent intent = new Intent(this, HomeUtente.class);
        startActivity(intent);
    }

    public void homeMedico() {
        Intent intent = new Intent(this, HomeMedico.class);
        startActivity(intent);
    }

    public void registration(View view) {
        Intent intent = new Intent(this, HomeUtente.class);
        startActivity(intent);
    }

    private boolean checkInputField() {
        //Questa funzione verifica la correttezza dei campi inseriti e segnala all'utente eventuali errori con messaggi a video
        String user = username.getText().toString();
        String pw = password.getText().toString();
        User u = MappaUtenti.recuperaUtente(user);
        int errors = 0;
        if (pw.equals("")) {
            password.setError("La password non può essere vuota");
            errors++;
        }
        if (user.equals("")) {
            username.setError("Il Codice Fiscale non può essere vuoto");
            errors++;
        }
        if (u != null) {
            if (!pw.equals(u.getPassword())) {
                password.setError("Password errata");
                errors++;
            }
        } else {
            username.setError("Codice Fiscale non presente nel sistema");
            errors++;
        }
        return (errors == 0);
    }
}
