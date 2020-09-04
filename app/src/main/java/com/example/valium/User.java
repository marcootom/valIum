package com.example.valium;

import java.util.Date;

public class User {
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private Date dataNascita;
    private String telefono;
    private String email;
    private int puntiAppuntamento;
    private Boolean medico;


    public User(String username, String password, String nome, String cognome, Date dataNascita, String telefono, String email, Boolean medico) {
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.dataNascita = dataNascita;
        this.email = email;
        this.puntiAppuntamento = 3;
        this.medico = medico;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPuntiAppuntamento() {
        return puntiAppuntamento;
    }

    public void setPuntiAppuntamento(int puntiAppuntamento) {
        this.puntiAppuntamento = puntiAppuntamento;
    }

    public Boolean getMedico() {
        return medico;
    }

    public void setMedico(Boolean medico) {
        this.medico = medico;
    }
}
