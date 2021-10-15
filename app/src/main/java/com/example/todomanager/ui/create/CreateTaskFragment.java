package com.example.todomanager.ui.create;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.todomanager.R;
import com.example.todomanager.databinding.FragmentCreateTaskBinding;
import com.example.todomanager.utils.Constants;

import org.jetbrains.annotations.NotNull;

public class CreateTaskFragment extends Fragment {
    NavController navController;
    private FragmentCreateTaskBinding binding;
    String userTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateTaskBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnApply.setOnClickListener(v -> {
            userTask = binding.edTask.getText().toString();
            Bundle bundle = new Bundle();
            bundle.putString(Constants.USER_TASK,userTask);
            navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.nav_home,bundle);
            Toast.makeText(requireContext(), " " + bundle.getString(Constants.USER_TASK,userTask), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}