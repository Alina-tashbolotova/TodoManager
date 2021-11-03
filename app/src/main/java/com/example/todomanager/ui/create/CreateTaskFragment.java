package com.example.todomanager.ui.create;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.todomanager.R;
import com.example.todomanager.databinding.FragmentCreateTaskBinding;
import com.example.todomanager.ui.create.model.TaskModel;
import com.example.todomanager.utils.App;
import com.example.todomanager.utils.Constants;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class CreateTaskFragment extends Fragment {

    private FragmentCreateTaskBinding binding;
    String userChooseDate;
    String time;
    String image;
    String userTask;
    List<TaskModel> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateTaskBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTime();
        setImage();
        sendButton();
    }

    private void sendButton() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        binding.btnSend.setOnClickListener(v -> {
            userTask = binding.edCreateTask.getText().toString();
            TaskModel model = new TaskModel(R.layout.item_task,userTask,userChooseDate + "/" + time,image);
            App.getInstance().getDatabase().taskDao().insert(model);
            navController.navigate(R.id.nav_home);
//            Log.e("tag", userTask);
        });

    }

    private void setTime() {
        binding.btnSetDate.setOnClickListener(v -> {
            showDateTime();
        });
   }

    private void setImage() {
        binding.btnCreateTask.setOnClickListener(v -> {
            mGetContent.launch("image/*");
        });
    }

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    image = uri.toString();
                    Glide.with(binding.imageCreate).load(image).centerCrop().into(binding.imageCreate);
                }
            });

    public void showDateTime() {
        final Calendar currentDate = Calendar.getInstance();

        Calendar date = Calendar.getInstance();
        new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(requireContext(), new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        date.set(Calendar.MINUTE, minute);
                        time = hourOfDay + " : " + minute;
                        userChooseDate = date.get(Calendar.MONTH) + "." + date.get(Calendar.DAY_OF_MONTH);

                        binding.txtTimeCreate.setText(time);
                        binding.txtDateCreate.setText(userChooseDate);
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}