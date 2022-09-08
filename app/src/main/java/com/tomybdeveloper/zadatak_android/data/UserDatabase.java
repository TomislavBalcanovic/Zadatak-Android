package com.tomybdeveloper.zadatak_android.data;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.tomybdeveloper.zadatak_android.dao.KorisnikDao;
import com.tomybdeveloper.zadatak_android.dao.KupacDao;
import com.tomybdeveloper.zadatak_android.dao.ZaposleniDao;
import com.tomybdeveloper.zadatak_android.model.Korisnik;
import com.tomybdeveloper.zadatak_android.model.Kupac;
import com.tomybdeveloper.zadatak_android.model.Zaposleni;

@Database(entities = {Korisnik.class, Kupac.class, Zaposleni.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    public abstract KorisnikDao userDao();
    public  abstract KupacDao kupacDao();
    public abstract ZaposleniDao zaposleniDao();

    private static UserDatabase INSTANCE;

    public static synchronized UserDatabase getInstance(Context context) {
        if (INSTANCE == null) {

            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "user_database").build();
        }

        return INSTANCE;
    }

}
