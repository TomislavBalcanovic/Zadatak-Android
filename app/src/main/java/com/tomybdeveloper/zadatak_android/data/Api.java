package com.tomybdeveloper.zadatak_android.data;

import com.tomybdeveloper.zadatak_android.model.Koktel;
import com.tomybdeveloper.zadatak_android.model.KoktelResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://www.thecocktaildb.com/";

    @GET("api/json/v1/1/search.php?f=a&fbclid=IwAR1wx2AaIvONnB0YO2WQXYTWMu7582PJxYh5nkslUsIWrehPWlHMD_ucCno")
    Call<KoktelResponse> callDrinks();
}
