package com.example.lab2_20221203;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab2_20221203.databinding.ActivityListaBinding;
import com.example.lab2_20221203.databinding.ActivityMainBinding;

public class ListaActivity extends AppCompatActivity {

    ActivityListaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityListaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}