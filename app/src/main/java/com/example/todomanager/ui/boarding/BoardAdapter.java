package com.example.todomanager.ui.boarding;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.todomanager.utils.Constants;

public class BoardAdapter extends FragmentPagerAdapter {
    public BoardAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new OnBoardFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.POSITION_FRAGMENT,position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
