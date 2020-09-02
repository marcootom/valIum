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
}
