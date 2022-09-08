package com.tomybdeveloper.zadatak_android.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Kupac {

    @PrimaryKey(autoGenerate = true)
    private int Id;

    private String naziv;
    private String pib;
    private String sifra;
    private int zaposleniId;

    public Kupac(String naziv, String pib, String sifra , int zaposleniId ) {
        this.naziv = naziv;
        this.pib = pib;
        this.sifra = sifra;
        this.zaposleniId = zaposleniId;
    }

    public Kupac() {
    }

    public int getZaposleniId() {
        return zaposleniId;
    }

    public void setZaposleniId(int zaposleniId) {
        this.zaposleniId = zaposleniId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String Pib) {
        this.pib = pib;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }
}
