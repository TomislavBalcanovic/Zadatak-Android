package com.tomybdeveloper.zadatak_android.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tomybdeveloper.zadatak_android.model.Korisnik;
import com.tomybdeveloper.zadatak_android.data.UserRepository;
import com.tomybdeveloper.zadatak_android.util.DatabaseResult;

public class RegisterViewModel extends AndroidViewModel {

    private final MutableLiveData<DatabaseResult> registerUserResultMutable;

    public final LiveData<DatabaseResult> registerUserResult;

    public UserRepository userRepository;

    public RegisterViewModel(@NonNull Application application) {
        super(application);

        userRepository = new UserRepository(application);
        registerUserResultMutable = new MutableLiveData<>();
        registerUserResult = registerUserResultMutable;
    }

    public void resetState() {
        registerUserResultMutable.setValue(DatabaseResult.idle());
    }

    public void registerUser(String imeString, String userString, String passwordString) {

        new RegisterUserAsyncTask(imeString, userString, passwordString).execute();

    }

    private class RegisterUserAsyncTask extends AsyncTask<Void, Void, DatabaseResult> {
        private final String ime;
        private final String user;
        private final String password;

        public RegisterUserAsyncTask(String ime, String user, String password) {
            this.ime = ime;
            this.user = user;
            this.password = password;
        }

        @Override
        protected DatabaseResult doInBackground(Void... voids) {

            try {
                Korisnik proveraKorisnika = userRepository.getByUsername(ime);
                if (proveraKorisnika != null)
                    return DatabaseResult.error(
                            "Korisnik sa ovim imenom vec postoji u bazi"
                    );
                Korisnik korisnik = new Korisnik(ime, user, password);

                Long dodatId = userRepository.registerUser(korisnik);
                if (dodatId > 0) {

                    return DatabaseResult.success("Registracija je uspesna");
                } else {

                    return DatabaseResult.error("Doslo je do greske prilikom registracije , pokusajte kasnije ponovo");
                }
            } catch (Exception e) {

                return DatabaseResult.error(e.getMessage());
            }

        }
        @Override
        protected void onPostExecute(DatabaseResult databaseResult) {
            super.onPostExecute(databaseResult);
            registerUserResultMutable.setValue(databaseResult);
        }
    }
}
