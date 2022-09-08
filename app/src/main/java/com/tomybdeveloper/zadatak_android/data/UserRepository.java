package com.tomybdeveloper.zadatak_android.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.tomybdeveloper.zadatak_android.dao.KorisnikDao;
import com.tomybdeveloper.zadatak_android.dao.KupacDao;
import com.tomybdeveloper.zadatak_android.dao.ZaposleniDao;
import com.tomybdeveloper.zadatak_android.data.UserDatabase;
import com.tomybdeveloper.zadatak_android.model.Korisnik;
import com.tomybdeveloper.zadatak_android.model.Kupac;
import com.tomybdeveloper.zadatak_android.model.KupacWithZaposleni;
import com.tomybdeveloper.zadatak_android.model.Zaposleni;

import java.util.List;

public class UserRepository {

    private final KorisnikDao korisnikDao;
    private final KupacDao kupacDao;
    private final ZaposleniDao zaposleniDao;

    public UserRepository(Application application) {

        UserDatabase userDatabase = UserDatabase.getInstance(application);
        korisnikDao = userDatabase.userDao();
        kupacDao = userDatabase.kupacDao();
        zaposleniDao = userDatabase.zaposleniDao();
    }


    public void deleteKupac(String ime) {

         kupacDao.deleteKupac(ime);
    }

    public Kupac getByPib (String pib) {
        return kupacDao.getByPib(pib);
    }

    public Zaposleni getByPassword(String sifra) {

        return zaposleniDao.getByPassword(sifra);
    }

    public Long insertZaposleni(Zaposleni zaposleni) {

       return zaposleniDao.insertZaposleni(zaposleni);

    }

    public Long insertKupac(Kupac kupac) {

       return kupacDao.insertKupac(kupac);

    }

    public LiveData<List<Zaposleni>> getAllZaposleni() {
        return zaposleniDao.getAllZaposleni();
    }

    public LiveData<List<KupacWithZaposleni>> getAllKupac() {
        return kupacDao.getAllKupac();
    }

    public Long registerUser(Korisnik korisnik) {

        return korisnikDao.registerUser(korisnik);

    }

    public Korisnik loginUser(String user, String password) {

        return korisnikDao.loginUser(user, password);

    }

    public Korisnik getByUsername(String ime) {

        return korisnikDao.getUserbyName(ime);

    }

}
