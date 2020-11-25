package com.example.valium;

import java.util.ArrayList;

public class MappaRichiesteRicette {
    private static ArrayList<RichiesteRicette> listaRichieste = new ArrayList<>();

    public static void aggiungiRichiesta(RichiesteRicette r){
        listaRichieste.add(r);
    }

    public static void rimuoviRichiesta(String user, String farmaco){
        for(int i=0; i<listaRichieste.size(); i++){
            if(listaRichieste.get(i).getUsername().equals(user) && listaRichieste.get(i).getFarmaco().equals(farmaco)) {
                listaRichieste.remove(i);
                break;
            }
        }
    }

    public static ArrayList<RichiesteRicette> getListaRichieste(){
        return listaRichieste;
    }

    public static ArrayList<String> stringheRichieste() {
        ArrayList<String> listaRic = new ArrayList<>();
        String farmaco, user;
        for (int i = 0; i < listaRichieste.size(); i++) {
            farmaco = listaRichieste.get(i).getFarmaco();
            user = listaRichieste.get(i).getUsername();
            listaRic.add("Farmaco: " + farmaco + "\nPaziente: "
                    + MappaUtenti.recuperaUtente(user).getCognome()
                    + " " + MappaUtenti.recuperaUtente(user).getNome());
        }
        return listaRic;
    }
}
