package com.example.valium;

import java.util.Date;

public class Appuntamento {
    private String paziente;
    private Date data;
    private int hours, minutes;

    public Appuntamento(String p, Date d, int h, int m){
        this.paziente = p;
        this.data = d;
        this.hours = h;
        this.minutes = m;
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

}
