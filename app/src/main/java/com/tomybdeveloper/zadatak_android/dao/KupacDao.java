package com.tomybdeveloper.zadatak_android.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.tomybdeveloper.zadatak_android.model.Kupac;
import com.tomybdeveloper.zadatak_android.model.KupacWithZaposleni;

import java.util.List;

@Dao
public interface KupacDao {

   @Insert
   Long insertKupac(Kupac kupac);

    @Query("SELECT Kupac.naziv, Kupac.pib , Kupac.sifra , Zaposleni.ime AS zaposleni FROM Kupac INNER JOIN Zaposleni ON Kupac.zaposleniId = Zaposleni.Id ")
    LiveData<List<KupacWithZaposleni>> getAllKupac();

    @Query("SELECT * FROM Kupac WHERE pib= :pib")
    Kupac getByPib(String pib);

    @Query("DELETE FROM Kupac WHERE Kupac.naziv= :ime")
    void deleteKupac(String ime);
}
