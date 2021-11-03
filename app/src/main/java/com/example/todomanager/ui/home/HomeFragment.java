package com.example.todomanager.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.todomanager.R;
import com.example.todomanager.databinding.FragmentHomeBinding;
import com.example.todomanager.ui.create.TaskAdapter;
import com.example.todomanager.ui.create.model.TaskModel;
import com.example.todomanager.utils.App;
import com.example.todomanager.utils.Constants;
import com.example.todomanager.utils.OnItemClickListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    List<TaskModel> models;
    private FragmentHomeBinding binding;
    public static boolean isChange = true;
    private TaskAdapter adapter = new TaskAdapter();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDataFromDatabase();
        initAdapter();
        onLongDelete();
    }

    private void onLongDelete(){
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(TaskModel model, int position) {
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setTitle("Внимание");
                alertDialog.setMessage("Точно хотите удалить?");
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.notifyDataSetChanged();
                    }
                });
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        App.getInstance().getDatabase().taskDao().delete(model);
                        adapter.delete(position);
                        App.getInstance().showToast("Удалено");
                    }
                });
                alertDialog.show();
            }
        });
    }



    private void getDataFromDatabase() {
        App.getInstance().getDatabase().taskDao().getAll().observe(getViewLifecycleOwner(), new Observer<List<TaskModel>>() {
            @Override
            public void onChanged(List<TaskModel> taskModels) {
                adapter.addAllList((ArrayList<TaskModel>) taskModels);
                models = taskModels;
            }
        });
     }
    private void initAdapter() {
        binding.recyclerTask.setAdapter(adapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}