package com.example.valium;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//Questa classe genera una HashMap contenente una lista di oggetti di classe User, identificati da una chiave Stringa composta dallo username
//dell'utente.
public class MappaUtenti {
    private static Map<String, User> listaUtenti = new HashMap<>();
    private static String utenteAttuale;
    private static User pazienteAttuale;


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

    public static User recuperaMedico() {
        String medico = "RSSMRA75S04B354T";
        return listaUtenti.get(medico);
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

    public static ArrayList<String> getListaUtenti(String s){
        String d, s1;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Collection<User> collection = listaUtenti.values();
        User[] array = collection.toArray(new User[0]);
        ArrayList<String> list = new ArrayList<>();
        for (User user : array) {
            if (!user.getMedico()) {
                d = format.format(user.getDataNascita());
                s1 = user.getCognome() + " " + user.getNome() + " - " + d + " " + user.getUsername();
                if (s1.contains(s)) {
                    list.add(s1);
                }
            }
        }
        Collections.sort(list);
        return list;
    }

    public static User getPazienteAttuale() {
        return pazienteAttuale;
    }

    public static void setPazienteAttuale(User pazienteAttuale) {
        MappaUtenti.pazienteAttuale = pazienteAttuale;
    }
}
