package com.tomybdeveloper.zadatak_android.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.tomybdeveloper.zadatak_android.R;
import com.tomybdeveloper.zadatak_android.adapter.KupacAdapter;


import com.tomybdeveloper.zadatak_android.model.KupacWithZaposleni;
import com.tomybdeveloper.zadatak_android.viewmodel.PregledKupacViewModel;

import java.util.List;


public class PregledKupcaFragment extends Fragment {

    private RecyclerView recyclerView;
    private KupacAdapter kupacAdapter;
    private PregledKupacViewModel pregledKupacViewModel;



    public PregledKupcaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pregled_kupca, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerViewPregledKupca);


        initiateViewModel();

        kupacAdapter = new KupacAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(kupacAdapter);

        setObservers();


        kupacAdapter.setOnItemClickListener(new KupacAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(KupacWithZaposleni kupac) {

                pregledKupacViewModel.deleteKupac(kupac.getNaziv());
            }
        });
    }

    private void setObservers() {

        pregledKupacViewModel.liveDataKupac.observe(getViewLifecycleOwner(), new Observer<List<KupacWithZaposleni>>() {
            @Override
            public void onChanged(List<KupacWithZaposleni> kupacs) {

                kupacAdapter.setKupacList(kupacs);
            }
        });
    }

    private void initiateViewModel() {

        pregledKupacViewModel = new ViewModelProvider(this).get(PregledKupacViewModel.class);
    }
}