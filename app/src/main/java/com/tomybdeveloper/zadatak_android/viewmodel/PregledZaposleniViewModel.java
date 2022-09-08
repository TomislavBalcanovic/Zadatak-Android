package com.tomybdeveloper.zadatak_android.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tomybdeveloper.zadatak_android.data.UserRepository;
import com.tomybdeveloper.zadatak_android.model.Zaposleni;

import java.util.List;

public class PregledZaposleniViewModel extends AndroidViewModel {

        private UserRepository userRepository;

       public final  LiveData<List<Zaposleni>> liveDataZaposleni;

    public PregledZaposleniViewModel(@NonNull Application application) {

        super(application);

        userRepository = new UserRepository(application);

        liveDataZaposleni = userRepository.getAllZaposleni();
    }
}
