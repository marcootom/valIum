package com.example.valium;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//Questa classe genera una HashMap contenente una lista di oggetti di classe User, identificati da una chiave Stringa composta dallo username
//dell'utente.
public class MappaUtenti {
    private static Map<String, User> listaUtenti = new HashMap<>();
    private static String utenteAttuale;

    //Funzione per recuperare i dati dell'utente attualmente loggato
    public static String getUtenteAttuale() {
        return utenteAttuale;
    }
    //Funzione per settare l'utente che si è appena loggato
    public static void setUtenteAttuale(String utenteAttuale) {
        MappaUtenti.utenteAttuale = utenteAttuale;
    }

    public static void aggiungi (String s, User u){
        listaUtenti.put(s,u);
    }
    //Attraverso lo user (che è la chiave dell'HashMap) vengono recuperati i dati dell'utente
    public static User recuperaUtente(String username) {
        return listaUtenti.get(username);
    }
    //Questa funzione aggiorna la password di un utente già presente nella mappa
    public static void modificaPassword(String newPw){
        User u = listaUtenti.get(utenteAttuale);
        if(u!=null) {
            u.setPassword(newPw);
        }
        listaUtenti.remove(utenteAttuale);
        aggiungi(utenteAttuale, u);
    }
}
