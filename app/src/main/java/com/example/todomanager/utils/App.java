package com.example.todomanager.utils;

import android.app.Application;
import android.widget.Toast;

import androidx.room.Room;

import com.example.todomanager.room.DataBase;

public class App extends Application {
    public static App instance;
    private DataBase dataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        dataBase = Room.databaseBuilder(this, DataBase.class, "database").allowMainThreadQueries().build();
    }
    public void showToast(String text){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    public static App getInstance() {
        return instance;
    }

    public DataBase getDatabase() {
        return dataBase;
    }
}
