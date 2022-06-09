package dk.frodejohansen.hoplysocialmediaapp_databaseexamproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.databinding.LoginBinding;

public class Login extends Fragment {

    private LoginBinding binding;
    boolean matchesUser;

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