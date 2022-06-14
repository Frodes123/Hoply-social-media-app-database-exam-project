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

import dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.Database.User;
import dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.databinding.LoginBinding;

public class Login extends Fragment {

    private LoginBinding binding;
    private AppViewModel model;
    boolean matchesUser;
    String userid;
    String password;
    String hexaPassword;

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
                model = new ViewModelProvider(requireActivity()).get(AppViewModel.class);
                userid = binding.editTextTextPersonName2.getText().toString();
                password = binding.editTextTextPassword3.getText().toString();
                try{
                    // hash password before putting into database to prevent storing passwords openly.
                    MessageDigest digest = MessageDigest.getInstance("SHA-256");
                    byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
                    hexaPassword = bytesToHex(encodedhash);
                }catch(NoSuchAlgorithmException e)
                {
                    Log.d("error", "no such algorithm exception");
                }

                // get if user exists and the stored equals the one just entered and hashed
                matchesUser = model.repository.userExistsAndMatches(userid, hexaPassword);

                if (!matchesUser)
                {
                    binding.textViewLoginError.setVisibility(view.VISIBLE);
                }
                else{

                    //TODO find displayname og giv.
                    model.setName(userid);
                    NavHostFragment.findNavController(Login.this)
                            .navigate(R.id.action_LoginPage_to_loginSuccessful);
                }
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