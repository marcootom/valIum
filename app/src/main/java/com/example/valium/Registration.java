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
    User user;
    EditText data;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    DatePickerFragment datePickerFragment;
    Button registerButton;
    com.google.android.material.textfield.TextInputEditText username, password, confirmPassword, name, surname, phone;
    com.google.android.material.textfield.TextInputLayout pwHint, confirmPwHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //All'apertura della schermata vengono recuperati tutti i campi dell'activity tramite il loro id
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        datePickerFragment = new DatePickerFragment();
        data = findViewById(R.id.birthText);
        username = findViewById(R.id.codiceFiscale);
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        phone = findViewById(R.id.phone);
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
                    user = new User(username.getText().toString(), password.getText().toString(), name.getText().toString(), surname.getText().toString(), d, phone.getText().toString());
                    MappaUtenti.aggiungi(user.getUsername(),user);
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


        if (username.getText() == null || username.getText().length() < 16){
            username.setError("Inserire un Codice Fiscale valido");
            errors++;
        }

        if (validate(username.getText().toString()) != null){
            username.setError("Il Codice Fiscale inserito non è valido");
            errors++;
        }

        if(MappaUtenti.recuperaUtente(username.getText().toString())!= null){
            username.setError("Codice Fiscale già presente nel sistema");
            errors++;
        }

        if (data.getText() == null || data.getText().length() == 0){
            data.setError("Inserire la data di nascita");
            errors++;
        }else{
            data.setError(null);
        }

        if(password.getText().length() < 8){
            password.setError("La password deve essere di almeno 8 caratteri");
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

        if (name.getText() == null || name.getText().length() == 0) {
            name.setError("Inserire il nome");
            errors++;
        }
        if (surname.getText() == null || surname.getText().length() == 0) {
            surname.setError("Inserire il cognome");
            errors++;
        }

        if (phone.getText() == null || phone.getText().length() < 10) {
            phone.setError("Inserire un numero di telefono valido");
            errors++;
        }

        return (errors == 0);
    }

    public void Home(){
        //Funzione che viene chiamata a fine registrazione per tornare all'activity di Login
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    static String normalize(String cf)
    {
        cf = cf.replaceAll("[ \t\r\n]", "");
        cf = cf.toUpperCase();
        return cf;
    }

    static String format(String cf)
    {
        return normalize(cf);
    }

    /**
     * Validates a regular CF.
     * @param cf Normalized, 16 characters CF.
     * @return Null if valid, or string describing why this CF must be rejected.
     */
    private static String validate(String cf)
    {
        cf = format(cf);
        if( ! cf.matches("^[0-9A-Z]{16}$") )
            return "Invalid characters.";
        int s = 0;
        String even_map = "BAFHJNPRTVCESULDGIMOQKWZYX";
        for(int i = 0; i < 15; i++){
            int c = cf.charAt(i);
            int n;
            if( '0' <= c && c <= '9' )
                n = c - '0';
            else
                n = c - 'A';
            if( (i & 1) == 0 )
                n = even_map.charAt(n) - 'A';
            s += n;
        }
        if( s%26 + 'A' != cf.charAt(15) )
            return "Invalid checksum.";
        return null;
    }
}

