package com.example.valium;

import java.util.Date;

//Questa classe genera automaticamente alla prima esecuzione dell'app dei dati di esempio per popolare le altre classi
public class DataSet {

    public static void caricaDati() {
        caricaMedico();
        caricaUtenti();
        caricaRicette();
        caricaAppuntamenti();
    }

    public static void caricaMedico() {
        Date date = new Date(75, 10, 4);
        User medico = new User("RSSMRA75S04B354T", "medico75", "Mario", "Rossi",
                date, "0704544896", "dotrossimario@gmail.com", true);
        MappaUtenti.aggiungi(medico.getUsername(), medico);
    }

    public static void caricaUtenti() {
        Date date;
        date = new Date(52, 8, 9);
        User u1 = new User("BNCLGU52P09I734K", "siliqua33", "Luigi", "Bianchi",
                date, "3365588978", "whiteluigi@gmail.com", false);
        date = new Date(67, 6, 6);
        User u2 = new User("VRDGNN67L46I765G", "tartaruga22", "Giovanna", "Verdi",
                date, "3452525225", "gioverdi@gmail.com", false);
        date = new Date(96, 2, 3);
        User u3 = new User("PRSDVD96C03E441A", "davide96", "Davide", "Piras",
                date, "3886240344", "davidino96@gmail.com", false);
        MappaUtenti.aggiungi(u1.getUsername(), u1);
        MappaUtenti.aggiungi(u2.getUsername(), u2);
        MappaUtenti.aggiungi(u3.getUsername(), u3);
    }

    public static void caricaRicette() {
        RicettaMedica r = new RicettaMedica("PRSDVD96C03E441A", "https://upload.wikimedia.org/wikipedia/it/9/90/RICETTA_MEDICA.jpg", 3, 5, 2020);
        RicettaMedica r2 = new RicettaMedica("PRSDVD96C03E441A", "https://upload.wikimedia.org/wikipedia/it/9/90/RICETTA_MEDICA.jpg", 4, 7, 2020);
        RicettaMedica r3 = new RicettaMedica("PRSDVD96C03E441A", "https://upload.wikimedia.org/wikipedia/it/9/90/RICETTA_MEDICA.jpg", 7, 8, 2020);
        RicettaMedica r4 = new RicettaMedica("VRDGNN67L46I765G", "https://upload.wikimedia.org/wikipedia/it/9/90/RICETTA_MEDICA.jpg", 15, 5, 2020);
        RicettaMedica r5 = new RicettaMedica("VRDGNN67L46I765G", "https://upload.wikimedia.org/wikipedia/it/9/90/RICETTA_MEDICA.jpg", 23, 8, 2020);
        RicettaMedica r6 = new RicettaMedica("BNCLGU52P09I734K", "https://upload.wikimedia.org/wikipedia/it/9/90/RICETTA_MEDICA.jpg", 3, 9, 2020);
        MappaRicette.aggiungiRicetta(r);
        MappaRicette.aggiungiRicetta(r2);
        MappaRicette.aggiungiRicetta(r3);
        MappaRicette.aggiungiRicetta(r4);
        MappaRicette.aggiungiRicetta(r5);
        MappaRicette.aggiungiRicetta(r6);
    }

    public static void caricaAppuntamenti() {
        Appuntamento a;
        Date date = new Date(120, 8, 23);
        a = new Appuntamento("BNCLGU52P09I734K", date, 9, 30, false);
        MappaAppuntamenti.aggiungiAppuntamento(a);
        date = new Date(120, 8, 24);
        a = new Appuntamento("BNCLGU52P09I734K", date, 9, 30, false);
        MappaAppuntamenti.aggiungiAppuntamento(a);
        date = new Date(120, 8, 28);
        a = new Appuntamento("VRDGNN67L46I765G", date, 10, 30, false);
        MappaAppuntamenti.aggiungiAppuntamento(a);
        date = new Date(120, 8, 24);
        a = new Appuntamento("VRDGNN67L46I765G", date, 10, 30, false);
        MappaAppuntamenti.aggiungiAppuntamento(a);
    }
}
