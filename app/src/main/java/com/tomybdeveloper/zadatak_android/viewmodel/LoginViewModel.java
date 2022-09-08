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

public class LoginViewModel extends AndroidViewModel {

    private final MutableLiveData<DatabaseResult> loginUserResultMutable;

    public final LiveData<DatabaseResult> loginUserResult;

    public  UserRepository userRepository;



    public LoginViewModel(@NonNull Application application) {
        super(application);

        userRepository = new UserRepository(application);

        loginUserResultMutable = new MutableLiveData<>();

        loginUserResult = loginUserResultMutable;
    }

    public void resetState() {
        loginUserResultMutable.setValue(DatabaseResult.idle());
    }

    public void loginUser(String user , String password) {

        new LoginUserAsyncTask(user , password).execute();
    }

    private  class LoginUserAsyncTask extends AsyncTask<Void, Void, DatabaseResult> {

        private final String user;
        private final String password;

        public LoginUserAsyncTask(String user, String password) {
            this.user = user;
            this.password = password;
        }
        @Override
        protected DatabaseResult doInBackground(Void... voids) {

            Korisnik korisnik = userRepository.loginUser(user , password);
            if (korisnik == null) {

               return DatabaseResult.error("Pogresno korisnicko ime ili lozinka");
            }
               else {

               return DatabaseResult.success("Uspesna prijava");
            }
        }

        @Override
        protected void onPostExecute(DatabaseResult databaseResult) {
            super.onPostExecute(databaseResult);
            loginUserResultMutable.setValue(databaseResult);

        }
    }
}
