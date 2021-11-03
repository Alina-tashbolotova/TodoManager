package com.example.todomanager.ui.create;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.todomanager.databinding.ItemTaskBinding;
import com.example.todomanager.ui.create.model.TaskModel;
import com.example.todomanager.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    ArrayList<TaskModel> list = new ArrayList<>();
    OnItemClickListener onItemClickListener;


    public void setOnItemClickListener (OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }


    public void delete(int position) {
        list.remove(position);
        notifyDataSetChanged();
    }

    public void addAllList(ArrayList<TaskModel> taskModels) {
        this.list = taskModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskAdapter.TaskViewHolder(
                ItemTaskBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        private ItemTaskBinding binding;

        public TaskViewHolder(ItemTaskBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            randomColor();
        }

        public void onBind(TaskModel model) {
            binding.txtTitle.setText(model.title);
            binding.txtTime.setText(model.time);

            binding.viewLeftColor.setBackgroundColor(model.color);
            Glide.with(binding.imageItem)
                    .load(model.image)
                    .centerCrop()
                    .into(binding.imageItem);
            Random rand = new Random();
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            int randomColor = Color.rgb(r,g,b);
            binding.viewLeftColor.setBackgroundColor(randomColor);


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onClick(model,getAdapterPosition());
                    return false;
                }
            });

        }

        public void randomColor() {
            Random random = new Random();
            int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
            binding.viewLeftColor.setBackgroundColor(color);
        }
    }

}
