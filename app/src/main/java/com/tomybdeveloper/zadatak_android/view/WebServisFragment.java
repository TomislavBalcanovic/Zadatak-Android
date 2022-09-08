package com.tomybdeveloper.zadatak_android.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tomybdeveloper.zadatak_android.R;
import com.tomybdeveloper.zadatak_android.adapter.KoktelAdapter;
import com.tomybdeveloper.zadatak_android.data.RetrofitInstance;
import com.tomybdeveloper.zadatak_android.model.Koktel;
import com.tomybdeveloper.zadatak_android.model.KoktelResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WebServisFragment extends Fragment {

    private RecyclerView recyclerView;
    private KoktelAdapter koktelAdapter;


    public WebServisFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web_servis, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerViewWebServis);


        koktelAdapter = new KoktelAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(koktelAdapter);

        fetchKoktel();
    }

    private void fetchKoktel() {

        Call<KoktelResponse> call = RetrofitInstance.getInstance().getMyApi().callDrinks();
        call.enqueue(new Callback<KoktelResponse>() {
            @Override
            public void onResponse(Call<KoktelResponse> call, Response<KoktelResponse> response) {

                if (response.isSuccessful() && response.body() != null ) {

                    KoktelResponse koktelResponses = response.body();
                    //uzimamo listu iz response drinka
                    List<Koktel> koktelLista = koktelResponses.getDrinks();
                    Toast.makeText(requireContext(), "Upesno fetchovanje!", Toast.LENGTH_LONG).show();

                    koktelAdapter.setKoktelLista(koktelLista);
                }
            }

            @Override
            public void onFailure(Call<KoktelResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "Neuspelo fetchovanje!", Toast.LENGTH_SHORT).show();

            }
        });

    }
}