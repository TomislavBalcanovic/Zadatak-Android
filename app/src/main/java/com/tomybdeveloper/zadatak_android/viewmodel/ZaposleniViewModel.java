package com.tomybdeveloper.zadatak_android.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.tomybdeveloper.zadatak_android.data.UserRepository;
import com.tomybdeveloper.zadatak_android.model.Zaposleni;
import com.tomybdeveloper.zadatak_android.util.DatabaseResult;

import java.util.List;

public class ZaposleniViewModel extends AndroidViewModel {

    public UserRepository userRepository;

    private final MutableLiveData<DatabaseResult> zaposleniMutableData;

    public final LiveData<DatabaseResult> zaposleniLiveData;

    public ZaposleniViewModel(@NonNull Application application) {
        super(application);

        userRepository = new UserRepository(application);
        zaposleniMutableData = new MutableLiveData<>();
        zaposleniLiveData = zaposleniMutableData;

    }

    public void resetState() {
        zaposleniMutableData.setValue(DatabaseResult.idle());
    }

    public void insertZaposleni(String imeString, String prezimeString, String sifraString, String gradString, String magacinString) {

        new ZaposleniAsyncTask(imeString, prezimeString, sifraString, gradString, magacinString).execute();
    }

    private class ZaposleniAsyncTask extends AsyncTask<Void, Void, DatabaseResult> {

        private final String ime;
        private final String prezime;
        private final String sifra;
        private final String grad;
        private final String magacin;

        public ZaposleniAsyncTask(String ime, String prezime, String sifra, String grad, String magacin) {
            this.ime = ime;
            this.prezime = prezime;
            this.sifra = sifra;
            this.grad = grad;
            this.magacin = magacin;
        }

        @Override
        protected DatabaseResult doInBackground(Void... voids) {

            try {
                Zaposleni proveraZaposlenog = userRepository.getByPassword(sifra);
                if (proveraZaposlenog != null) {

                    return DatabaseResult.error("Zaposleni sa ovom sifrom vec postoji!");
                }
                Zaposleni zaposleni = new Zaposleni(ime, prezime, sifra, grad, magacin);

                Long dodatId = userRepository.insertZaposleni(zaposleni);

                if (dodatId > 0) {
                    return DatabaseResult.success("Zaposleni uspesno dodat!");
                } else {

                    return DatabaseResult.error("Doslo je do greske prilikom unosa zaposlenog!");
                }
            } catch (Exception e) {

                return DatabaseResult.error(e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(DatabaseResult databaseResult) {
            super.onPostExecute(databaseResult);
            zaposleniMutableData.setValue(databaseResult);
        }
    }

}
