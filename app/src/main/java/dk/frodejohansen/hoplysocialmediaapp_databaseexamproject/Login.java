package dk.frodejohansen.hoplysocialmediaapp_databaseexamproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.databinding.LoginBinding;

public class Login extends Fragment {

    private LoginBinding binding;
    private AppViewModel model;
    boolean matchesUser;
    String username;
    String password;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = LoginBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //login button
        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = binding.editTextTextPersonName2.getText().toString();
                password = binding.editTextTextPassword3.getText().toString();
                /*
                if (!matchesUser)
                {
                    binding.textViewLoginError.setVisibility(view.VISIBLE)
                }
                else{
                    NavHostFragment.findNavController(Login.this)
                            .navigate(R.id.action_LoginPage_to_loginSuccessful);
                }
                */
                model = new ViewModelProvider(requireActivity()).get(AppViewModel.class);
                model.setName(username);
                NavHostFragment.findNavController(Login.this)
                        .navigate(R.id.action_LoginPage_to_loginSuccessful);
            }
        });

        // go to signup button
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(Login.this)
                        .navigate(R.id.action_LoginPage_to_SignUpPage);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}