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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.tomybdeveloper.zadatak_android.R;
import com.tomybdeveloper.zadatak_android.model.Zaposleni;
import com.tomybdeveloper.zadatak_android.util.DatabaseResult;
import com.tomybdeveloper.zadatak_android.viewmodel.KupacViewModel;

import java.util.ArrayList;
import java.util.List;


public class KupacFragment extends Fragment {

    private EditText editTextNaziv;
    private EditText editTextPib;
    private EditText editTextSifra;
    private Button buttonDodaj;
    private KupacViewModel kupacViewModel;
    private Spinner spinner;
    private List<Zaposleni> spinnerZaposleniList;
    private ArrayAdapter<Zaposleni> zaposleniArrayAdapter;
    private Zaposleni selectedZaposleni;

    public KupacFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kupac, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextNaziv = view.findViewById(R.id.et_naziv_kupac);
        editTextPib = view.findViewById(R.id.et_PIB_kupac);
        editTextSifra = view.findViewById(R.id.et_sifra_kupac);
        buttonDodaj = view.findViewById(R.id.buttonDodajKupca);
        spinner = view.findViewById(R.id.spinnerOdabirZaposlenog);

        initiateViewModel();
        initSpinner();

        buttonDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nazivInput = editTextNaziv.getText().toString().trim();
                String pibInput = editTextPib.getText().toString().trim();
                String sifraInput = editTextSifra.getText().toString().trim();

                boolean isValid = validateInput(nazivInput , pibInput , sifraInput);

                if (isValid) {
                    kupacViewModel.insertKupac(nazivInput ,pibInput , sifraInput ,selectedZaposleni.getId());
                }

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                selectedZaposleni = (Zaposleni) adapterView.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        setObservers();
    }

    private void initSpinner() {

        spinnerZaposleniList = new ArrayList<>();

       zaposleniArrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, spinnerZaposleniList);
        zaposleniArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(zaposleniArrayAdapter);
    }

    private boolean validateInput(String nazivInput, String pibInput, String sifraInput) {

        if (nazivInput.isEmpty()) {

            editTextNaziv.setError("Naziv polje je obavezno!");
            return false;
        }
        if (pibInput.isEmpty()) {

            editTextPib.setError("PIB polje je obavezno!");
            return false;
        }
        if (sifraInput.isEmpty()) {

            editTextSifra.setError("Sifra polje je obavezno!");
            return false;
        }

        return true;
    }

    private void setObservers() {

        kupacViewModel.kupacLiveData.observe(getViewLifecycleOwner(), new Observer<DatabaseResult>() {
            @Override
            public void onChanged(DatabaseResult databaseResult) {

                switch (databaseResult.status) {
                    case SUCCESS: {

                        Toast.makeText(requireContext(), databaseResult.message, Toast.LENGTH_LONG).show();
                        kupacViewModel.resetState();
                        Navigation.findNavController(requireActivity(), R.id.fragmentContainerView).popBackStack();
                        break;
                    }
                    case ERROR: {

                        Toast.makeText(requireContext(), databaseResult.message, Toast.LENGTH_LONG).show();
                        kupacViewModel.resetState();
                        break;
                    }

                }
            }
        });

        //popunjavamo spinner listom tj podacima
        kupacViewModel.liveDataZaposleni.observe(getViewLifecycleOwner(), new Observer<List<Zaposleni>>() {
            @Override
            public void onChanged(List<Zaposleni> zaposlenis) {

                  spinnerZaposleniList.clear();
                  spinnerZaposleniList.addAll(zaposlenis);
                  zaposleniArrayAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initiateViewModel() {

        kupacViewModel = new ViewModelProvider(this).get(KupacViewModel.class);
    }
}