package com.example.valium;

public class RichiesteRicette {
    private String farmaco;
    private String username;

    public RichiesteRicette(String farmaco, String username) {
        this.farmaco = farmaco;
        this.username = username;
    }

    public String getFarmaco() {
        return farmaco;
    }

    public void setFarmaco(String farmaco) {
        this.farmaco = farmaco;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
