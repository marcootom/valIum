package com.example.valium;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
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
import java.util.ArrayList;
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
                final ArrayList<String> appuntamentiPerData = MappaAppuntamenti.appuntamentiPerData(date1);
                final ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(ListaAppuntamentiMedico.this, android.R.layout.simple_list_item_1, appuntamentiPerData);
                mylist.setAdapter(adapter);
                mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onItemClick(AdapterView<?> adattatore, final View componente, int pos, long id) {
                        final String timeString = (String) adattatore.getItemAtPosition(pos);
                        AlertDialog.Builder builder = new AlertDialog.Builder(ListaAppuntamentiMedico.this);
                        builder.setCancelable(true);
                        builder.setTitle("Rimuovi appuntamento");
                        builder.setMessage("L'utente si è presentato?");
                        builder.setPositiveButton("Si",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        MappaAppuntamenti.rimuoviAppuntamento(date1, timeString);
                                        Toast.makeText(getApplicationContext(), "Appuntamento Rimosso!", Toast.LENGTH_LONG).show();
                                        listaAppuntamentiMedico();
                                    }
                                });
                        builder.setNeutralButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MappaAppuntamenti.rimuoviAppuntamento(date1, timeString);
                                String[] str = timeString.split(" ");
                                MappaUtenti.rimuoviPuntoPrenotazione(str[6], str[5]);
                                Toast.makeText(getApplicationContext(), "Appuntamento Rimosso!Al paziente è stato rimosso un punto prenotazione.", Toast.LENGTH_LONG).show();
                                listaAppuntamentiMedico();
                            }
                        });

                        builder.setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });
            }

            @Override
            public void onAppointmentPickerFragmentCancelButton(DialogFragment dialog) {

            }
        });
    }

    public void listaAppuntamentiMedico() {
        Intent intent = new Intent(this, ListaAppuntamentiMedico.class);
        startActivity(intent);
    }
}