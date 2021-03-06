package com.example.valium;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MappaAppuntamenti {

    private static final ArrayList<Appuntamento> listaAppuntamenti = new ArrayList<>();
    public static void aggiungiAppuntamento (Appuntamento a){listaAppuntamenti.add(a);}


    public static ArrayList<Appuntamento> appuntamentiUtente(String username){
        ArrayList<Appuntamento> aUtente = new ArrayList<>();

        for(int i=0; i<(listaAppuntamenti.size());i++)
            if(listaAppuntamenti.get(i).getPaziente().equals(username))
                aUtente.add(listaAppuntamenti.get(i));

        return aUtente;
    }

    public static ArrayList<String> appuntamentiUtenteAttuale(String username) {
        ArrayList<String> aUtente = new ArrayList<>();
        String msg = "";
        String data = "";
        Calendar c = Calendar.getInstance();

        for(int i=0; i<(listaAppuntamenti.size());i++) {
            msg = "";

            if (listaAppuntamenti.get(i).getPaziente().equals(username)) {
                c.setTime(listaAppuntamenti.get(i).getData());
                int anno = c.get(Calendar.YEAR);
                int minuti = listaAppuntamenti.get(i).getMinutes();
                String minuto = "" + minuti;
                if(minuti == 0) {
                    minuto += minuti;
                }
                int giorno = c.get(Calendar.DAY_OF_MONTH);
                String giorni = "" + giorno;
                if(giorno < 10) {
                    giorni = "0" + giorni;
                }
                int mese = c.get(Calendar.MONTH) + 1;
                String mesi = "" + mese;
                if(mese < 10) {
                    mesi = "0" + mesi;
                }
                msg += "Appuntamento del " + giorni + "/" + mesi + "/" + Integer.toString(anno) +" alle ore: " + listaAppuntamenti.get(i).getHours() +":"+
                        minuto;
                aUtente.add(msg);
            }

        }

        return aUtente;
    }

    public static ArrayList<String> appuntamentiPerData(Date data) {
        ArrayList<String> agenda = new ArrayList<>();
        String msg = "";
        int minuti = 0;
        int i;
        for(i=0; i<(listaAppuntamenti.size());i++) {
            msg = "";
            Appuntamento a = listaAppuntamenti.get(i);
            if (listaAppuntamenti.get(i).getData().getDay() == data.getDay() &&
                    listaAppuntamenti.get(i).getData().getMonth() == data.getMonth()) {
                minuti = listaAppuntamenti.get(i).getMinutes();
                String minuto = "" + minuti;
                if (minuti == 0)
                    minuto += minuti;
                String p = listaAppuntamenti.get(i).getPaziente();
                msg += "Appuntamento delle ore: " + listaAppuntamenti.get(i).getHours() + ":" + minuto + " - " +
                        MappaUtenti.recuperaUtente(p).getCognome() + " " + MappaUtenti.recuperaUtente(p).getNome();
                agenda.add(msg);

            }
        }
        return agenda;
    }

    public static void rimuoviAppuntamento (Appuntamento a){
        for(int i=0; i<(listaAppuntamenti.size()); i++){
            if((listaAppuntamenti.get(i).getPaziente().equals(a.getPaziente())) &&
                    (listaAppuntamenti.get(i).getData().equals((a.getData()))) &&
                    (listaAppuntamenti.get(i).getHours() == ((a.getHours()))) ){
                listaAppuntamenti.remove(i);
                break;
            }
        }
    }

    public static void rimuoviAppuntamento(Date d, String h){
        String[] numbers = h.split(" ");
        numbers = numbers[3].split(":");
        int hours = Integer.parseInt(numbers[0]);
        int minutes = Integer.parseInt(numbers[1]);
        for(int i=0; i<(listaAppuntamenti.size()); i++){
            if(listaAppuntamenti.get(i).getData().equals(d) && listaAppuntamenti.get(i).getHours() == hours && listaAppuntamenti.get(i).getMinutes() == minutes){
                rimuoviAppuntamento(listaAppuntamenti.get(i));
            }
        }
    }

    public static ArrayList<String> OrariOccupatiPerData(Date d) {
        int h, m;
        String s;
        ArrayList<String> busy = new ArrayList<String>(); // <-- List instead of array
        for (int i = 0; i < listaAppuntamenti.size(); i++) {
            if (listaAppuntamenti.get(i).getData().getDay() == d.getDay() && listaAppuntamenti.get(i).getData().getMonth() == d.getMonth()
                    && listaAppuntamenti.get(i).getData().getYear() == d.getYear()) {
                h = listaAppuntamenti.get(i).getHours();
                m = listaAppuntamenti.get(i).getMinutes();
                s = h + ":" + m;
                if (h < 10) s = "0" + s;
                if (m == 0) s += "0";
                busy.add(s);
            }
        }
        return busy;
    }

    public static ArrayList<String> OrariLiberi(Date d) {
        Calendar c = Calendar.getInstance();
        ArrayList<String> times = new ArrayList<String>();
        c.setTime(d);
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
            times.add("Nessun orario disponibile per questa data");
        else {
            String[] quarterHours = {"00", "30"};
            for (int i = 8; i < 19; i++) {
                if (i != 13 && i != 14 && i != 15) {
                    for (int j = 0; j < 2; j++) {
                        String time = i + ":" + quarterHours[j];
                        if (i < 10) {
                            time = "0" + time;
                        }
                        times.add("" + time); // <-- no need to care about indexes
                    }
                }
            }
            ArrayList<String> ap = OrariOccupatiPerData(d);
            for (int i = 0; i < ap.size(); i++) {
                times.remove(ap.get(i));
                if (times.size() == 0)
                    times.add("Nessun orario disponibile per questa data");

            }
        }
        return times;
    }


}
