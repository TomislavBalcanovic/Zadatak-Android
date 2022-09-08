package com.tomybdeveloper.zadatak_android.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tomybdeveloper.zadatak_android.data.UserRepository;
import com.tomybdeveloper.zadatak_android.model.Kupac;
import com.tomybdeveloper.zadatak_android.model.KupacWithZaposleni;

import java.util.List;

public class PregledKupacViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    public final LiveData<List<KupacWithZaposleni>> liveDataKupac;

    public PregledKupacViewModel(@NonNull Application application) {
        super(application);

        userRepository = new UserRepository(application);

        liveDataKupac = userRepository.getAllKupac();
    }

    public void deleteKupac(String ime) {

        new DeleteAsyncTask(ime).execute();
    }

    private class DeleteAsyncTask extends AsyncTask<Void, Void, Void> {

        private final String ime;

        private DeleteAsyncTask(String ime) {
            this.ime = ime;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userRepository.deleteKupac(ime);
            return null;
        }
    }
}
