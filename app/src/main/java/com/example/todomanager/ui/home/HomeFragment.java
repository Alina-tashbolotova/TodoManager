package com.example.todomanager.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.todomanager.databinding.FragmentHomeBinding;
import com.example.todomanager.utils.Constants;

import org.jetbrains.annotations.NotNull;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        saveUser();

    }

    private void saveUser() {
        if (getArguments() != null) {
            String userTask = getArguments().getString(Constants.USER_TASK);
            binding.tvTitle.setText(userTask);
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}