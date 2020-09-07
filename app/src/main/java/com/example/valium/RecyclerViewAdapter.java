package com.example.valium;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    public ArrayList<String> myValues;
    public Context c;

    public RecyclerViewAdapter(ArrayList<String> myValues, Context c) {
        this.myValues = myValues;
        this.c = c;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem;
        listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.myTextView.setText(myValues.get(position));
        final String appuntamento = myValues.get(position);
        final String[] s = appuntamento.split(" ");
        DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateAsString = s[2];
        Date date = new Date(2,4,2020);
        try {
            date = sourceFormat.parse(dateAsString);
        }catch(ParseException e) {

        }
        final String[] orario = s[5].split(":");
        int ora = 0;
        int minuti = 2;
        try {
            ora = Integer.parseInt(orario[0]);
            minuti = Integer.parseInt(orario[1]);
        } catch(NumberFormatException e) {

        }


        final Appuntamento a = new Appuntamento(MappaUtenti.getUtenteAttuale(), date, ora,minuti );
        holder.button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(c);
                // Setting Dialog Title
                alertDialog1.setTitle("Cancellazione");

                // Setting Dialog Message
                alertDialog1.setMessage("Confermi la cancellazione della prenotazione?");

                alertDialog1.setPositiveButton("Conferma",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(c, "Prenotazione annullata!", Toast.LENGTH_LONG).show();
                                MappaAppuntamenti.rimuoviAppuntamento(a);
                                c.startActivity(new Intent(c, ListaPrenotazioni.class));
                            }
                        });

                alertDialog1.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                // Setting OK Button


                // Showing Alert Message
                alertDialog1.show();
            }
        });;
    }

    @Override
    public int getItemCount() {
        return myValues.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView myTextView;
        private Button button;

        public MyViewHolder(View itemView)  {
            super(itemView);
            myTextView = (TextView) itemView.findViewById(R.id.text_cardview);
            button = (Button) itemView.findViewById(R.id.buttonAnnulla);
            //final String [] s = appuntamento.split("");
            //DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
            //Date date = sourceFormat.parse(s[2]);
            //Appuntamento a = new Appuntamento(MappaUtenti.getUtenteAttuale(), date, )

        }
    }
}
