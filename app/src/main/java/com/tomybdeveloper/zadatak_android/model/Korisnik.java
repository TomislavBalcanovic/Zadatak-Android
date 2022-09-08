package com.tomybdeveloper.zadatak_android.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Korisnik {

    @PrimaryKey(autoGenerate = true)
    private int Id;

    private String ime;
    private String user;
    private String password;

    public Korisnik(String ime, String user, String password) {
        this.ime = ime;
        this.user = user;
        this.password = password;
    }

    public Korisnik() {
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
