package com.example.valium;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ListaAppuntamentiMedico extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        final AgendaPicker datePickerFragment;
        final EditText data;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_appuntamenti_medico);
        datePickerFragment = new AgendaPicker();
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

        datePickerFragment.setOnDatePickerFragmentChanged(new AgendaPicker.DatePickerFragmentListener() {
            @Override
            public void onAppointmentPickerFragmentOkButton(DialogFragment dialog, Calendar date) throws ParseException {
                //Associo il comportamento del bottone OK all'edit text della data, voglio che una
                // volta selezionata quindi ho premuto ok, l'edit text mostri la data selezionata
                // tramite il datepicker
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                data.setText(format.format(date.getTime()));
                data.setError(null);
                final String d = data.getText().toString();
                final Date date1 = format.parse(d);
                final ListView mylist = findViewById(R.id.listaOrariDisponibili);
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListaAppuntamentiMedico.this, android.R.layout.simple_list_item_1, MappaAppuntamenti.appuntamentiPerData(date1));
                mylist.setAdapter(adapter);
            }

            @Override
            public void onAppointmentPickerFragmentCancelButton(DialogFragment dialog) {

            }
        });
    }
}