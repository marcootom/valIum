package com.example.valium;

import java.util.ArrayList;

public class ListaAppuntamenti {

    private static ArrayList<Appuntamento> listaAppuntamenti = new ArrayList<>();
    public static void aggiungiAppuntamento (Appuntamento a){listaAppuntamenti.add(a);}


    public static ArrayList<Appuntamento> appuntamentiUtente(String username){
        ArrayList<Appuntamento> aUtente = new ArrayList<>();

        for(int i=0; i<(listaAppuntamenti.size());i++)
            if(listaAppuntamenti.get(i).getPaziente().equals(username))
                aUtente.add(listaAppuntamenti.get(i));

        return aUtente;
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

    public static ArrayList<String> OrariLiberi() {
        String[] quarterHours = {"00", "30"};
        ArrayList<String> times = new ArrayList<String>(); // <-- List instead of array

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
        return times;
    }
}
