package com.example.todomanager.utils;

import com.example.todomanager.ui.create.model.TaskModel;

public interface OnItemClickListener {
    void onClick(TaskModel model,int position);
}
