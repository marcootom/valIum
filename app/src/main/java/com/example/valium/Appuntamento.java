package com.example.valium;

import java.util.Date;

public class Appuntamento {
    private String paziente;
    private Date data;
    private int hours, minutes;
    private Boolean presentato;

    public Appuntamento(String p, Date d, int h, int m, Boolean presentato){
        this.paziente = p;
        this.data = d;
        this.hours = h;
        this.minutes = m;
        this.presentato = presentato;
    }

    public String getPaziente() {
        return paziente;
    }
    public void setPaziente(String paziente) {
        this.paziente = paziente;
    }
    public int getHours() {
        return hours;
    }
    public void setHours(int hours) {
        this.hours = hours;
    }
    public int getMinutes() {
        return minutes;
    }
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }

    public Boolean getPresentato() {
        return presentato;
    }

    public void setPresentato(Boolean presentato) {
        this.presentato = presentato;
    }
}
