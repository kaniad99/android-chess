package com.mindorks.framework.chess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mindorks.framework.chess.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        initView();
    }

    public void initView() {
        binding.startButton.setOnClickListener(v -> showBoard(binding.getRoot()));
    }

    public void showBoard(View view){
        Intent intent = new Intent(this, GameActivity.class);
        Log.i(TAG, "Start button clicked");
        startActivity(intent);
    }
}