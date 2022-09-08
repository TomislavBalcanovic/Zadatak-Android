package com.tomybdeveloper.zadatak_android.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.tomybdeveloper.zadatak_android.model.Korisnik;

@Dao
public interface KorisnikDao {

    @Insert
    Long registerUser(Korisnik korisnik);

    @Query("SELECT * FROM Korisnik WHERE user= :user  AND password= :password")
    Korisnik loginUser(String user , String password);

    @Query("SELECT * FROM Korisnik WHERE ime= :ime")
    Korisnik getUserbyName(String ime);
}
