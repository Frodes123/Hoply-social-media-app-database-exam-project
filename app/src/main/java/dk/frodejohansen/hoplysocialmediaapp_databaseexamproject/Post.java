package dk.frodejohansen.hoplysocialmediaapp_databaseexamproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.databinding.PostBinding;
import dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.databinding.SignUpBinding;

public class Post extends Fragment {

    private PostBinding binding;
    AppViewModel model;



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = PostBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        //TODO post button
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO get post text and image. Put into database together with username.
                // TODO return to loginSuccesful?
            }
        });
        // cancel button
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(Post.this)
                        .navigate(R.id.action_post_to_loginSuccessful);
            }
        });

        //TODO choose picture button
        binding.button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO open gallery to choose picture.
                // TODO set preview image to chosen image.
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}