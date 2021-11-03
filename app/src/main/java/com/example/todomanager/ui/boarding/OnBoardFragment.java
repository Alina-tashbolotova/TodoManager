package com.example.todomanager.ui.boarding;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.todomanager.MainActivity;
import com.example.todomanager.R;
import com.example.todomanager.databinding.FragmentOnBoardBinding;
import com.example.todomanager.utils.Constants;

import org.jetbrains.annotations.NotNull;

public class OnBoardFragment extends Fragment {
    private FragmentOnBoardBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPositionFromViewPagerAdapter();
    }

    private void getPositionFromViewPagerAdapter() {
        if (getArguments() != null) {
            int position = getArguments().getInt(Constants.POSITION_FRAGMENT);

            switch (position) {
                case 0:
                    binding.txtTitleBoard.setText("Manage your Task");
                    binding.txtDescriptionBoard.setText("    Organaize all your to-do's in list");
                    binding.imageBoard.setAnimation("task.json");
                    break;
                case 1:
                    binding.txtDescriptionBoard.setText("Color teg them to set priorities");
                    binding.imageBoard.setAnimation("like.json");
                    break;
                case 2:
                    binding.txtDescriptionBoard.setText("Set notification for tasks");
//                    binding.startBtn.setVisibility(View.VISIBLE);
                    binding.imageBoard.setAnimation("books.json");
//                    binding.startBtn.setOnClickListener(v -> {
//                        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(Constants.BOARD_FILE, Context.MODE_PRIVATE);
//                        sharedPreferences.edit().putBoolean(Constants.IS_SHOW,false).apply();
//                        startActivity(new Intent(requireContext(), MainActivity.class));
//
//                    });
                    break;
            }
        }
    }
}