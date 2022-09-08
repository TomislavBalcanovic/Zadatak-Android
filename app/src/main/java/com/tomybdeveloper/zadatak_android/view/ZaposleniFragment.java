package com.tomybdeveloper.zadatak_android.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tomybdeveloper.zadatak_android.R;
import com.tomybdeveloper.zadatak_android.adapter.ZaposleniAdapter;

import com.tomybdeveloper.zadatak_android.util.DatabaseResult;
import com.tomybdeveloper.zadatak_android.viewmodel.ZaposleniViewModel;

import java.util.List;


public class ZaposleniFragment extends Fragment {

    private EditText editTextIme;
    private EditText editTextPrezime;
    private EditText editTextSifra;
    private EditText editTextGrad;
    private EditText editTextMagacin;
    private Button buttonDodaj;
    private ZaposleniViewModel zaposleniViewModel;


    public ZaposleniFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_zaposleni, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextIme = view.findViewById(R.id.et_ime_zaposleni);
        editTextPrezime = view.findViewById(R.id.et_prezime_zaposleni);
        editTextSifra = view.findViewById(R.id.et_sifra_zaposleni);
        editTextGrad = view.findViewById(R.id.et_grad_zaposleni);
        editTextMagacin = view.findViewById(R.id.et_magacin_zaposleni);
        buttonDodaj = view.findViewById(R.id.buttonDodajZaposlenog);


        initiateViewModel();


        buttonDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String imeInput = editTextIme.getText().toString().trim();
                String prezimeInput = editTextPrezime.getText().toString().trim();
                String sifraInput = editTextSifra.getText().toString().trim();
                String gradInput = editTextGrad.getText().toString().trim();
                String magacinInput = editTextMagacin.getText().toString().trim();

                boolean isValid = validateInput(imeInput, prezimeInput, sifraInput, gradInput, magacinInput);

                if (isValid) {

                    zaposleniViewModel.insertZaposleni(imeInput, prezimeInput, sifraInput, gradInput, magacinInput);
                }
            }
        });

        setObservers();
    }

    public void setObservers() {

        zaposleniViewModel.zaposleniLiveData.observe(getViewLifecycleOwner(), new Observer<DatabaseResult>() {
            @Override
            public void onChanged(DatabaseResult databaseResult) {
                switch (databaseResult.status) {
                    case SUCCESS: {
                        Toast.makeText(requireContext(), databaseResult.message, Toast.LENGTH_LONG).show();
                        zaposleniViewModel.resetState();
                        Navigation.findNavController(requireActivity(), R.id.fragmentContainerView).popBackStack();
                        break;
                    }
                    case ERROR: {
                        Toast.makeText(requireContext(), databaseResult.message, Toast.LENGTH_LONG).show();
                        zaposleniViewModel.resetState();
                        break;
                    }
                }
            }
        });
    }

    public void initiateViewModel() {

        zaposleniViewModel = new ViewModelProvider(this).get(ZaposleniViewModel.class);
    }

    private boolean validateInput(String imeInput, String prezimeInput, String sifraInput, String gradInput, String magacinInput) {

        if (imeInput.isEmpty()) {

            editTextIme.setError("Polje ime je obavezno!");
            return false;
        }
        if (prezimeInput.isEmpty()) {

            editTextPrezime.setError("Polje prezime je obavezno!");
            return false;
        }
        if (sifraInput.isEmpty()) {

            editTextSifra.setError("Polje sifra je obavezno!");
            return false;
        }
        if (gradInput.isEmpty()) {
            editTextGrad.setError("Polje grad je obavezno!");
        }
        if (magacinInput.isEmpty()) {
            editTextMagacin.setError("Polje magacin je obavezno!");
        }
        return true;
    }
}