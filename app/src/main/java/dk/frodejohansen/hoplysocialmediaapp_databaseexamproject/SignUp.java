package dk.frodejohansen.hoplysocialmediaapp_databaseexamproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

import dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.Database.RoomDatabase;
import dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.Database.User;
import dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.databinding.LoginBinding;
import dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.databinding.SignUpBinding;

public class SignUp extends Fragment {
    private SignUpBinding binding;
    AppViewModel model;

    String userid;
    String displayName;
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
        model = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        // sign up button. needs to give values to app from text widgets.
        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get values from editTexts
                displayName = binding.editTextTextPersonName.getText().toString();
                userid = binding.editTextSignUpName.getText().toString();
                password1 = binding.editTextPassword1.getText().toString();
                password2 = binding.editTextPassword2.getText().toString();

                // check if password is empty. Only checks password1 as both passwords has to be the same
                passwordEmpty = password1.isEmpty() ? true : false;
                // check if passwords are the same
                passwordSame = password1.equals(password2) ? true : false;



                // check if password is empty
                if (passwordEmpty)
                {
                    binding.textViewSignUpError.setText("Password cannot be empty");
                    binding.textViewSignUpError.setVisibility(View.VISIBLE);
                }
                // check if name is taken
                else if(model.repository.usernameExists(userid))
                {
                    binding.textViewSignUpError.setText("Name is already taken, try another");
                    binding.textViewSignUpError.setVisibility(View.VISIBLE);
                }
                // check if passwords are the same
                else if (!passwordSame)
                {
                    binding.textViewSignUpError.setText("Passwords have to match");
                    binding.textViewSignUpError.setVisibility(View.VISIBLE);
                }
                // register user.
                else
                {
                    //Log.d("før usercreation", "ja");

                    try{
                        // hash password before putting into database to prevent storing passwords openly.
                        MessageDigest digest = MessageDigest.getInstance("SHA-256");
                        byte[] encodedhash = digest.digest(password1.getBytes(StandardCharsets.UTF_8));
                        String hexaPassword = bytesToHex(encodedhash);
                        model.repository.insert(new User(userid, displayName, hexaPassword, Instant.now().getEpochSecond()));
                    }catch(NoSuchAlgorithmException e)
                    {
                        Log.d("error", "no such algorithm exception");
                    }
                    //Log.d("efter usercreation", "ja");
                    // go to login page
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
    // method to make the byte[] to a string in hexadecimal. taken from https://www.baeldung.com/sha-256-hashing-java
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
