package com.tomybdeveloper.zadatak_android.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tomybdeveloper.zadatak_android.R;


public class GlavniFragment extends Fragment {

    private Button buttonDzaposleni;
    private Button buttonDkupac;
    private Button buttonPzaposleni;
    private Button buttonPkupac;
    private Button buttonWeb;

    public GlavniFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_glavni, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonDzaposleni = view.findViewById(R.id.buttonZaposleni);
        buttonDkupac = view.findViewById(R.id.buttonKupac);
        buttonPzaposleni = view.findViewById(R.id.buttonPregledZaposlenog);
        buttonPkupac = view.findViewById(R.id.buttonPregledKupca);
        buttonWeb = view.findViewById(R.id.buttonWeb);

        buttonDzaposleni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity() , R.id.fragmentContainerView).navigate(R.id.action_mainFragment_to_zaposleniFragment);
            }
        });
        buttonDkupac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity() , R.id.fragmentContainerView).navigate(R.id.action_mainFragment_to_kupacFragment);
            }
        });
        buttonPzaposleni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity() , R.id.fragmentContainerView).navigate(R.id.action_mainFragment_to_pregledZaposlenogFragment);
            }
        });

        buttonPkupac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity() , R.id.fragmentContainerView).navigate(R.id.action_mainFragment_to_pregledKupcaFragment);
            }
        });

        buttonWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Navigation.findNavController(requireActivity() , R.id.fragmentContainerView).navigate(R.id.action_mainFragment_to_webServisFragment);
            }
        });


    }
}