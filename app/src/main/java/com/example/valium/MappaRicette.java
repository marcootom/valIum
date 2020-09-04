package com.example.valium;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        RicettaMedica r = new RicettaMedica("PRSDVD96C03E441A","https://upload.wikimedia.org/wikipedia/it/9/90/RICETTA_MEDICA.jpg",3,5,2010);
        RicettaMedica r2 = new RicettaMedica("PRSDVD96C03E441A","https://upload.wikimedia.org/wikipedia/it/9/90/RICETTA_MEDICA.jpg",3,5,2011);
        RicettaMedica r3 = new RicettaMedica("PRSDVD96C03E441A","https://upload.wikimedia.org/wikipedia/it/9/90/RICETTA_MEDICA.jpg",3,5,2012);
        RicettaMedica r4 = new RicettaMedica("TMSMRC94H14E281Z", "https://upload.wikimedia.org/wikipedia/it/9/90/RICETTA_MEDICA.jpg", 3, 5, 2012);

        aggiungiRicetta(r);
        aggiungiRicetta(r2);
        aggiungiRicetta(r3);
        aggiungiRicetta(r4);
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
