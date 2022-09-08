package com.tomybdeveloper.zadatak_android.model;


//Pravimo klasu da bi room znao kako da mapira objekat

public class KupacWithZaposleni {

    private String naziv;
    private String pib;
    private String sifra;
    private String zaposleni;

    public KupacWithZaposleni() {
    }

    public KupacWithZaposleni(String naziv, String pib, String sifra, String zaposleni) {
        this.naziv = naziv;
        this.pib = pib;
        this.sifra = sifra;
        this.zaposleni = zaposleni;
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

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(String zaposleni) {
        this.zaposleni = zaposleni;
    }
}
