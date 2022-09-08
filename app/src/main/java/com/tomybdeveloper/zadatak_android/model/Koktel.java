package com.tomybdeveloper.zadatak_android.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Koktel implements Serializable {

    @SerializedName("strDrink")
    private String ime;

    @SerializedName("strCategory")
    private String kategorija;

    @SerializedName("strDrinkThumb")
    private String slika;

    public Koktel() {
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getIme() {
        return ime;
    }

    public String getKategorija() {
        return kategorija;
    }

    public String getSlika() {
        return slika;
    }
}
