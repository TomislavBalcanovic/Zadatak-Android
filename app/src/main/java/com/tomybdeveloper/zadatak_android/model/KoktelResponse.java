package com.tomybdeveloper.zadatak_android.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KoktelResponse {

     @SerializedName("drinks")
     private List<Koktel> drinks ;


     public KoktelResponse() {
     }

     public List<Koktel> getDrinks() {
          return drinks;
     }

     public void setDrinks(List<Koktel> drinks) {
          this.drinks = drinks;
     }
}
