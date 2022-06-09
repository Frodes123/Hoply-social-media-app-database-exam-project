package dk.frodejohansen.hoplysocialmediaapp_databaseexamproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.databinding.LoginBinding;
import dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.databinding.SignUpBinding;

public class SignUp extends Fragment {
    private SignUpBinding binding;


    String username;
    String password1;
    String password2;
    boolean passwordEmpty;
    boolean passwordSame;
    boolean nameTaken;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = SignUpBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // sign up button. needs to give values to app from text widgets.
        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get values from editTexts
                username = binding.editTextSignUpName.getText().toString();
                password1 = binding.editTextPassword1.getText().toString();
                password2 = binding.editTextPassword2.getText().toString();

                // check if password is empty. Only checks password1 as both passwords has to be the same
                passwordEmpty = password1.isEmpty() ? true : false;
                // check if passwords are the same
                passwordSame = password1.equals(password2) ? true : false;


                //nameTaken = repository.nameTaken(username);
                // TODO check if name already taken.
                /*
                if(nameTaken)
                {
                    binding.textViewSignUpError.setText("Name is already taken, try another");
                    binding.textViewSignUpError.setVisibility(View.VISIBLE);
                }
                 */
                // check if password is empty
                if (passwordEmpty)
                {
                    binding.textViewSignUpError.setText("Password cannot be empty");
                    binding.textViewSignUpError.setVisibility(View.VISIBLE);
                }
                // check if passwords are the same
                else if (!passwordSame)
                {
                    binding.textViewSignUpError.setText("Passwords have to match");
                    binding.textViewSignUpError.setVisibility(View.VISIBLE);
                }
                else
                {
                    NavHostFragment.findNavController(SignUp.this)
                            .navigate(R.id.action_SignUpPage_to_LoginPage);
                }
            }
        });
        // go to login button
        binding.button100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SignUp.this)
                        .navigate(R.id.action_SignUpPage_to_LoginPage);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
