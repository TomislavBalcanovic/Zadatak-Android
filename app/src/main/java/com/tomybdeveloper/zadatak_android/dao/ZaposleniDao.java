package com.tomybdeveloper.zadatak_android.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.tomybdeveloper.zadatak_android.model.Zaposleni;

import java.util.List;

@Dao
public interface ZaposleniDao {

    @Insert
   Long insertZaposleni(Zaposleni zaposleni);

    @Query("SELECT * FROM zaposleni")
    LiveData<List<Zaposleni>> getAllZaposleni();

    @Query("SELECT * FROM Zaposleni WHERE sifra= :sifra ")
    Zaposleni getByPassword(String sifra);

}
