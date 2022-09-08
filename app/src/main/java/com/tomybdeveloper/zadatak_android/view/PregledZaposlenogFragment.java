package com.tomybdeveloper.zadatak_android.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tomybdeveloper.zadatak_android.R;
import com.tomybdeveloper.zadatak_android.adapter.ZaposleniAdapter;

import com.tomybdeveloper.zadatak_android.model.Zaposleni;
import com.tomybdeveloper.zadatak_android.viewmodel.PregledZaposleniViewModel;

import java.util.List;


public class PregledZaposlenogFragment extends Fragment {

      private RecyclerView recyclerView;
      private ZaposleniAdapter zaposleniAdapter;
      private PregledZaposleniViewModel pregledZaposleniViewModel;


    public PregledZaposlenogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pregled_zaposlenog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

          recyclerView = view.findViewById(R.id.recyclerViewPregledZaposlenog);

         initiateViewModel();

          zaposleniAdapter = new ZaposleniAdapter();
          recyclerView.setHasFixedSize(true);
          recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
          recyclerView.setAdapter(zaposleniAdapter);

          setObservers();
    }

    private void setObservers() {

        pregledZaposleniViewModel.liveDataZaposleni.observe(getViewLifecycleOwner(), new Observer<List<Zaposleni>>() {
            @Override
            public void onChanged(List<Zaposleni> zaposlenis) {

                zaposleniAdapter.setZaposleniList(zaposlenis);
            }
        });
    }

    private void initiateViewModel() {

       pregledZaposleniViewModel = new ViewModelProvider(this).get(PregledZaposleniViewModel.class);
    }
}