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
import com.tomybdeveloper.zadatak_android.util.DatabaseResult;
import com.tomybdeveloper.zadatak_android.viewmodel.RegisterViewModel;


public class RegisterFragment extends Fragment {

    private EditText editTextIme;
    private EditText editTextUser;
    private EditText editTextPassword;
    private Button buttonReg;
    private RegisterViewModel registerViewModel;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextIme = view.findViewById(R.id.et_name);
        editTextUser = view.findViewById(R.id.et_user);
        editTextPassword = view.findViewById(R.id.et_password);
        buttonReg = view.findViewById(R.id.buttonReg);

       initiateViewModel();

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imeInput = editTextIme.getText().toString().trim();
                String userInput = editTextUser.getText().toString().trim();
                String passwordInput = editTextPassword.getText().toString().trim();


                boolean isValid = validateInput(imeInput , userInput ,passwordInput);

                if (isValid) {
                    registerViewModel.registerUser(imeInput , userInput , passwordInput);
                }
            }
        });

        setObservers();
    }

    private void initiateViewModel() {
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
    }
  private void setObservers() {

        registerViewModel.registerUserResult.observe(getViewLifecycleOwner(), new Observer<DatabaseResult>() {
            @Override
            public void onChanged(DatabaseResult databaseResult) {

                switch (databaseResult.status) {
                    case SUCCESS: {
                        Toast.makeText(requireContext(), databaseResult.message, Toast.LENGTH_LONG).show();
                        registerViewModel.resetState();
                        Navigation.findNavController(requireActivity(), R.id.fragmentContainerView).popBackStack();
                        break;
                    }
                    case ERROR: {
                        Toast.makeText(requireContext(), databaseResult.message, Toast.LENGTH_LONG).show();
                        registerViewModel.resetState();
                        break;
                    }
                }
            }
        });
  }

    private boolean validateInput(String imeInput , String userInput , String passwordInput) {

        if (imeInput.isEmpty()) {

            editTextIme.setError("Polje ime je obavezno!");
            return false;
        }
        if (userInput.isEmpty()) {

            editTextUser.setError("Polje user je obavezno!");
            return false;
        }
       if (passwordInput.isEmpty()) {

           editTextPassword.setError("Polje password je obavezno!");
           return false;
       }
       if (passwordInput.length() < 6) {

           editTextPassword.setError("Sifra mora imati minimum 6 karaktera!");
       }
         return true;
    }
}