package com.example.todomanager.ui.boarding;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todomanager.MainActivity;
import com.example.todomanager.R;
import com.example.todomanager.databinding.ActivityBoardBinding;
import com.example.todomanager.utils.App;
import com.example.todomanager.utils.Constants;

public class BoardActivity extends AppCompatActivity {

    boolean isShow = false;
    private ActivityBoardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViewPager();
        onClickSkip();
        onClickArrow();
        onClickNext();
        onClickBack();
        checkViewPager();
    }

    private void onClickBack() {
        binding.txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.viewPagerBoard.setCurrentItem(binding.viewPagerBoard.getCurrentItem() - 1);
                binding.txtNext.setVisibility(View.VISIBLE);
                if (binding.viewPagerBoard.getCurrentItem() == 0 || binding.viewPagerBoard.getCurrentItem() == 1) {
//                    binding.imageArrow.setImageResource(R.drawable.ic_baseline_arrow_forward_24);
                }
            }
        });
    }

    private void onClickNext() {
        binding.txtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.viewPagerBoard.setCurrentItem(binding.viewPagerBoard.getCurrentItem() + 1);
                if (binding.viewPagerBoard.getCurrentItem() == 2) {
//                    binding.txtNext.setVisibility(View.GONE);
//                    binding.imageBoard.setImageResource(R.drawable.ic_baseline_check_24);
                }
            }
        });
    }

    private void onClickArrow() {
        binding.imageBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.viewPagerBoard.getCurrentItem() == 2) {
                    SharedPreferences sharedPreferences = BoardActivity.this.getSharedPreferences(Constants.BOARD_FILE, Context.MODE_PRIVATE);
                    sharedPreferences.edit().putBoolean(Constants.IS_SHOW, true).apply();
                    Intent intent = new Intent(BoardActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void onClickSkip() {
        binding.btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoardActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void checkViewPager() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.BOARD_FILE, MODE_PRIVATE);
        isShow = sharedPreferences.getBoolean(Constants.IS_SHOW, false);
        if (isShow) {
            startActivity(new Intent(this, MainActivity.class));
        }

    }

    private void initViewPager() {
        binding.viewPagerBoard.setAdapter(new BoardAdapter(getSupportFragmentManager()));
        binding.dotsIndicator.setViewPager(binding.viewPagerBoard);
        binding.txtBack.setAlpha(0f);
        binding.txtBack.animate().alpha(1f).setDuration(1500);
        binding.txtNext.setAlpha(0f);
        binding.txtNext.animate().alpha(1f).setDuration(1500);
        binding.btnSkip.setAlpha(0f);
        binding.btnSkip.animate().alpha(1f).setDuration(1500);
        binding.imageBoard.setAlpha(0f);
        binding.imageBoard.animate().alpha(1f).setDuration(1500);
        binding.imageBoard.animate().rotation(360).setDuration(1000);
        binding.dotsIndicator.setAlpha(0f);
        binding.dotsIndicator.animate().alpha(1f).setDuration(1500);
    }
}