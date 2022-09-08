package com.tomybdeveloper.zadatak_android.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tomybdeveloper.zadatak_android.R;
import com.tomybdeveloper.zadatak_android.util.DatabaseResult;
import com.tomybdeveloper.zadatak_android.viewmodel.LoginViewModel;


public class LoginFragment extends Fragment {

    private EditText editTextUser;
    private EditText editTextPassword;
    private Button buttonLog;
    private Button buttonReg;
    private LoginViewModel loginViewModel;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextUser = view.findViewById(R.id.et_userLog);
        editTextPassword = view.findViewById(R.id.et_passwordLog);
        buttonLog = view.findViewById(R.id.buttonLog);
        buttonReg = view.findViewById(R.id.buttonLogReg);

        initiateViewModel();

        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userInput = editTextUser.getText().toString().trim();
                String passwordInput = editTextPassword.getText().toString().trim();

                boolean isValid = validateInput(userInput , passwordInput);

                if (isValid) {
                    loginViewModel.loginUser(userInput,passwordInput);
                }
            }
        });

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(requireActivity() , R.id.fragmentContainerView).navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });

        setObservers();
    }

    public void initiateViewModel() {
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

    public void setObservers() {

        loginViewModel.loginUserResult.observe(getViewLifecycleOwner(), new Observer<DatabaseResult>() {
            @Override
            public void onChanged(DatabaseResult databaseResult) {

                    switch (databaseResult.status) {

                        case SUCCESS: {
                            Toast.makeText(requireContext(), databaseResult.message, Toast.LENGTH_LONG).show();
                            loginViewModel.resetState();
                            Navigation.findNavController(requireActivity() , R.id.fragmentContainerView).navigate(R.id.action_loginFragment_to_mainFragment);
                            break;
                        }
                        case ERROR: {
                            Toast.makeText(requireContext(), databaseResult.message, Toast.LENGTH_LONG).show();
                             loginViewModel.resetState();
                             break;
                        }

                    }
            }
        });
    }
    private boolean validateInput(String userInput, String passwordInput) {

        if (userInput.isEmpty()) {

            editTextUser.setError("Polje user je obavezno!");
            return false;
        }
        if (passwordInput.isEmpty()) {
            editTextPassword.setError("Polje lozinka je obavezno!");
            return false;
        }
        return true;
    }

}