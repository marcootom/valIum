package com.example.valium;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

import androidx.fragment.app.DialogFragment;

public class AppointmentPicker extends DialogFragment {

    private Calendar date;
    private DatePickerFragmentListener listener;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        Calendar maxDate;
        //Questa porzione di codice vincola l'utente a inserire la data di nascita di una persona di almeno 18 anni e, soprattutto,
        //evita l'inserimento di date maggiori di quella attuale
        maxDate = Calendar.getInstance();
        maxDate.setTimeInMillis(Calendar.getInstance().getTimeInMillis());

        if (date == null) {
            date = Calendar.getInstance();
            //Se non c'è una data imposta la data odierna
        }
        //Costruisce il picker in base alla data di oggi impostando un vincolo sulla data massima
        final DatePicker datePicker = new DatePicker(getActivity());
        datePicker.setMinDate(System.currentTimeMillis() - 1000);
        datePicker.updateDate(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(datePicker);


        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                date.set(Calendar.YEAR, datePicker.getYear());
                date.set(Calendar.MONTH, datePicker.getMonth());
                date.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());

                if (listener != null) {
                    listener.onAppointmentPickerFragmentOkButton(AppointmentPicker.this, date);
                }
            }
        });

        builder.setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onAppointmentPickerFragmentCancelButton(AppointmentPicker.this);
                        }
                    }
                }
        );

        return builder.create();
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setOnDatePickerFragmentChanged(DatePickerFragmentListener l) {
        this.listener = l;
    }

    public interface DatePickerFragmentListener {
        public void onAppointmentPickerFragmentOkButton(DialogFragment dialog, Calendar date);

        public void onAppointmentPickerFragmentCancelButton(DialogFragment dialog);
    }
}