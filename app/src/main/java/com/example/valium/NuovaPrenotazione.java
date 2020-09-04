package com.example.valium;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class NuovaPrenotazione extends AppCompatActivity {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    AppointmentPicker datePickerFragment;
    EditText data;
    Appuntamento a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuova_prenotazione);
        final Context c = this;
        final ListView mylist = (ListView) findViewById(R.id.listaOrariDisponibili);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ListaAppuntamenti.OrariLiberi());
        mylist.setAdapter(adapter);
        datePickerFragment = new AppointmentPicker();
        data = findViewById(R.id.appointmentDate);
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

        datePickerFragment.setOnDatePickerFragmentChanged(new AppointmentPicker.DatePickerFragmentListener() {
            @Override
            public void onAppointmentPickerFragmentOkButton(DialogFragment dialog, Calendar date) {
                //Associo il comportamento del bottone OK all'edit text della data, voglio che una
                // volta selezionata quindi ho premuto ok, l'edit text mostri la data selezionata
                // tramite il datepicker
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                data.setText(format.format(date.getTime()));
                data.setError(null);

            }

            @Override
            public void onAppointmentPickerFragmentCancelButton(DialogFragment dialog) {

            }
        });

    }
}
