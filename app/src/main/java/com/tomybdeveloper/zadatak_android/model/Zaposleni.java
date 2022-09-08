package com.tomybdeveloper.zadatak_android.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Zaposleni {

    @PrimaryKey(autoGenerate = true)
    private int Id;

    private String ime;
    private String prezime;
    private String sifra;
    private String grad;
    private String magacin;

    public Zaposleni(String ime, String prezime, String sifra, String grad, String magacin) {
        this.ime = ime;
        this.prezime = prezime;
        this.sifra = sifra;
        this.grad = grad;
        this.magacin = magacin;
    }

    public Zaposleni() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getMagacin() {
        return magacin;
    }

    public void setMagacin(String magacin) {
        this.magacin = magacin;
    }


    @Override
    public String toString() {

        return ime + " " + prezime;
    }
}
