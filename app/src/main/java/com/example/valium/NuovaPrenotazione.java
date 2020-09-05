package com.example.valium;

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
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
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
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(NuovaPrenotazione.this, android.R.layout.simple_list_item_1, MappaAppuntamenti.OrariLiberi(date1));
                mylist.setAdapter(adapter);
                if (!(adapter.getCount() == 1 && adapter.getItem(0).equals("Nessun orario disponibile per questa data"))) {
                    mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onItemClick(AdapterView<?> adattatore, final View componente, int pos, long id) {
                            final String time = (String) adattatore.getItemAtPosition(pos);
                            AlertDialog.Builder builder = new AlertDialog.Builder(NuovaPrenotazione.this);
                            builder.setCancelable(true);
                            builder.setTitle("Conferma prenotazione");
                            builder.setMessage("Confermi la prenotazione per il " + d + " alle " + time + "?");
                            builder.setPositiveButton("Conferma",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            final int h, m;
                                            String numbers[] = time.split(":");
                                            h = Integer.parseInt(numbers[0]);
                                            m = Integer.parseInt(numbers[1]);
                                            MappaAppuntamenti.aggiungiAppuntamento(new Appuntamento(MappaUtenti.getUtenteAttuale(), date1, h, m));
                                            Toast.makeText(getApplicationContext(), "Prenotazione confermata!", Toast.LENGTH_LONG).show();
                                            listaPrenotazioni();
                                        }
                                    });
                            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });

                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });
                }
            }

            @Override
            public void onAppointmentPickerFragmentCancelButton(DialogFragment dialog) {

            }
        });


    }

    public void listaPrenotazioni() {
        Intent intent = new Intent(this, ListaPrenotazioni.class);
        startActivity(intent);
    }
}
