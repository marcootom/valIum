package com.example.valium;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Registration extends AppCompatActivity {
    //User user;
    EditText data;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    DatePickerFragment datePickerFragment;
    Button registerButton;
    com.google.android.material.textfield.TextInputEditText username, password, confirmPassword, city;
    com.google.android.material.textfield.TextInputLayout pwHint, confirmPwHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //All'apertura della schermata vengono recuperati tutti i campi dell'activity tramite il loro id
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        datePickerFragment = new DatePickerFragment();
        data = findViewById(R.id.birthText);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        confirmPwHint = findViewById(R.id.confirmPasswordHint);
        pwHint = findViewById(R.id.passwordHint);
        registerButton = (Button) findViewById(R.id.registrati);
        //Configurazione Eventi Dialog Calendar, data è ancora l'edit text che esiste, stiamo
        // infatti settando il listener dell'edit text, vogliamo che quando si clicchi compaia il
        // fragment
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //il fragment manager è colui che dirige tutti i fragment
                datePickerFragment.show(getSupportFragmentManager(), "date picker");
            }
        });

        //questa funzione permette di controllare che l'utente non scriva nella textview
        data.setOnFocusChangeListener(new View.OnFocusChangeListener() { //funzione di view
            @Override
            public void onFocusChange(View v, boolean hasFocus) { //metodo chiamato quando lo stato della view cambia
                if (hasFocus) {
                    datePickerFragment.show(getSupportFragmentManager(), "datePicker");
                }
            }
        });

        datePickerFragment.setOnDatePickerFragmentChanged(new DatePickerFragment.DatePickerFragmentListener() {
            @Override
            public void onDatePickerFragmentOkButton(DialogFragment dialog, Calendar date) {
                //Associo il comportamento del bottone OK all'edit text della data, voglio che una
                // volta selezionata quindi ho premuto ok, l'edit text mostri la data selezionata
                // tramite il datepicker
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                data.setText(format.format(date.getTime()));
                data.setError(null);
            }

            @Override
            public void onDatePickerFragmentCancelButton(DialogFragment dialog) {

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //All'interno di questa funzione vengono recuperati i dati inseriti dall'utente e vengono associati ad un nuovo utente
                //nell'HashMap
                if(checkInputField()) {
                    Date d = null;
                    try {
                        d = format.parse(data.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    //user = new User(username.getText().toString(), password.getText().toString(), city.getText().toString(), d);
                    //MappaUtenti.aggiungi(user.getUsername(),user);
                    Home();
                }
            }
        });
    }

    private boolean checkInputField() {
        //Funzione che verifica che tutti i campi siano corretti e se non lo sono segnala all'utente
        //l'errore attraverso dei popup con scritto il tipo di errore
        int errors = 0;
        String passwordString = password.getText().toString();
        String confirmPasswordString = confirmPassword.getText().toString();

        if(username.getText().length() <= 4){
            username.setError("Lo username deve avere almeno 5 caratteri");
            errors++;
        }

        /*if(MappaUtenti.recuperaUtente(username.getText().toString())!= null){
            username.setError("Esiste già un utente con questo username");
            errors++;
        }*/

        if (username.getText() == null || username.getText().length() == 0){
            username.setError("Inserire un username");
            errors++;
        }

        if (city.getText() == null || city.getText().length() == 0){
            city.setError("Inserire la città di origine");
            errors++;
        }

        if (data.getText() == null || data.getText().length() == 0){
            data.setError("Inserire la data di nascita");
            errors++;
        }else{
            data.setError(null);
        }

        if(password.getText().length() <= 4){
            password.setError("La password deve essere di almeno 5 caratteri");
            errors++;
        }

        if (password.getText() == null || password.getText().length() == 0){
            password.setError("Inserire password");
            errors++;
        }

        if (confirmPassword.getText() == null || confirmPassword.getText().length() == 0){
            confirmPassword.setError("Inserire conferma password");
            errors++;
        }

        if (!passwordString.equals(confirmPasswordString)){
            confirmPassword.setError("Password non corrispondente");
            errors++;
        }
        return (errors == 0);
    }

    public void Home(){
        //Funzione che viene chiamata a fine registrazione per tornare all'activity di Login
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
