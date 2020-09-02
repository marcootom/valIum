package com.example.valium;

import java.util.Date;

public class RicettaMedica {
    private String username, filePath;
    private int giorno,mese,anno,id;
    private static int idRicette=0;

    public RicettaMedica(String u, String f,int giorno, int mese, int anno){
        this.username = u;
        this.filePath = f;
        this.giorno = giorno;
        this.mese = mese;
        this.anno = anno;
        idRicette++;
        id = idRicette;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getGiorno(){
        return giorno;
    }
    public int getMese(){
        return mese;
    }
    public int getAnno(){
        return anno;
    }
    public int getId() { return this.id;};


}
