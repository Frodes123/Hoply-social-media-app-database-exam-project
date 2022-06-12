package dk.frodejohansen.hoplysocialmediaapp_databaseexamproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.databinding.LoginSuccessfulBinding;

public class LoginSuccessful extends Fragment {
    private LoginSuccessfulBinding binding;
    AppViewModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = LoginSuccessfulBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        model = new ViewModelProvider(requireActivity()).get(AppViewModel.class);
        model.getName().observe(getViewLifecycleOwner(), name ->{
            binding.textView6.setText("Welcome " + name);
        });
        // TODO set text and picture when logged in. get reactions

        // log out button
        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LoginSuccessful.this)
                        .navigate(R.id.action_loginSuccessful_to_LoginPage);
            }
        });
        // next post button
        binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO set picture
                // TODO set text
                // TODO set reactions
            }
        });

        // create post button. goes to create post fragment
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LoginSuccessful.this)
                        .navigate(R.id.action_loginSuccessful_to_post);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
