package com.tomybdeveloper.zadatak_android.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tomybdeveloper.zadatak_android.data.UserRepository;
import com.tomybdeveloper.zadatak_android.model.Kupac;
import com.tomybdeveloper.zadatak_android.model.Zaposleni;
import com.tomybdeveloper.zadatak_android.util.DatabaseResult;

import java.util.List;

public class KupacViewModel extends AndroidViewModel {

    public UserRepository userRepository;

    private final MutableLiveData<DatabaseResult> kupacMutableData;

    public final LiveData<DatabaseResult> kupacLiveData;

    public final  LiveData<List<Zaposleni>> liveDataZaposleni;

    public KupacViewModel(@NonNull Application application) {
        super(application);

        userRepository = new UserRepository(application);

        kupacMutableData = new MutableLiveData<>();
        kupacLiveData = kupacMutableData;

        liveDataZaposleni = userRepository.getAllZaposleni();
    }

    public void resetState() {
        kupacMutableData.setValue(DatabaseResult.idle());
    }

    public void insertKupac(String nazivString, String pibString, String sifraString , int zaposleniId) {

        new KupacAsyncTask(nazivString, pibString, sifraString , zaposleniId).execute();
    }

    private class KupacAsyncTask extends AsyncTask<Void, Void, DatabaseResult> {

        String naziv;
        String pib;
        String sifra;
        int zaposleniId;

        public KupacAsyncTask(String naziv, String pib, String sifra , int zaposleniId) {
            this.naziv = naziv;
            this.pib = pib;
            this.sifra = sifra;
            this.zaposleniId = zaposleniId;
        }

        @Override
        protected DatabaseResult doInBackground(Void... voids) {
            try {
                Kupac proveraKupca = userRepository.getByPib(pib);
                if (proveraKupca != null) {

                    return DatabaseResult.error("Kupac sa ovim PiB vec postoji!");
                }

                Kupac kupac = new Kupac(naziv , pib , sifra ,zaposleniId);

                Long dodatId = userRepository.insertKupac(kupac);

                if (dodatId > 0) {

                    return  DatabaseResult.success("Kupac uspesno dodat!");
                }
                else  {

                    return  DatabaseResult.error("Doslo je do greske prilikom unosa kupca!");
                }

            } catch (Exception e) {

                return  DatabaseResult.error(e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(DatabaseResult databaseResult) {
            super.onPostExecute(databaseResult);
            kupacMutableData.setValue(databaseResult);
        }
    }
}
