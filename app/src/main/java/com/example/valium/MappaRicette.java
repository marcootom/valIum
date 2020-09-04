package com.example.valium;

import java.util.ArrayList;

public class MappaRicette {
    private static ArrayList<RicettaMedica> listaRicette = new ArrayList<>();

    public static void aggiungiRicetta(RicettaMedica r) {
        RicettaMedica x;
        for (int i = 0; i < listaRicette.size(); i++) {
            x = listaRicette.get(i);
            if (x.getUsername().equals(r.getUsername()) && x.getAnno() == r.getAnno() && x.getGiorno() == r.getGiorno() &&
                    x.getMese() == r.getMese())
                return;
        }
        listaRicette.add(r);
    }


    public static ArrayList<String> ricetteUtente(String username){
        ArrayList<String> rUtente = new ArrayList<>();

        for(int i=0; i<(listaRicette.size());i++)
            if(listaRicette.get(i).getUsername().equals(username))
                rUtente.add("#" + listaRicette.get(i).getId() + "      Ricetta del " + listaRicette.get(i).getGiorno() + "/" + listaRicette.get(i).getMese() + "/" + listaRicette.get(i).getAnno());

        return rUtente;
    }

    public static RicettaMedica ricettaPerId(int id) {
        for (int i = 0; i < listaRicette.size(); i++) {
            if (listaRicette.get(i).getId() == id) {
                return listaRicette.get(i);
            }
        }
        return null;
    }
}
