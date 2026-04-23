package com.example.lab2_20221203;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab2_20221203.databinding.ActivityEquipoDetailBinding;

public class EquipoDetailActivity extends AppCompatActivity {

    ActivityEquipoDetailBinding binding;
    private String modo;
    private int posicion = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityEquipoDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        modo = getIntent().getStringExtra("modo");
        posicion = getIntent().getIntExtra("posicion", -1);

        // Llenar los dropdowns
        String[] tipos = getResources().getStringArray(R.array.tipos_equipo_items);
        ArrayAdapter<String> adapterTipos = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, tipos);
        binding.etTipo.setAdapter(adapterTipos);

        String[] estados = getResources().getStringArray(R.array.estados_equipo_items);
        ArrayAdapter<String> adapterEstados = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, estados);
        binding.etEstado.setAdapter(adapterEstados);

        if ("editar".equals(modo)){
            binding.tvTitulo.setText("Editar Equipo");
            binding.etCodigo.setText(getIntent().getStringExtra("codigo"));
            binding.etNombre.setText(getIntent().getStringExtra("nombre"));
            binding.etTipo.setText(getIntent().getStringExtra("tipo"), false);
            binding.etEstado.setText(getIntent().getStringExtra("estado"), false);
        }

        binding.btnGuardar.setOnClickListener(view -> {
            guardarEquipo();
        });

        binding.btnBack.setOnClickListener(view -> {
            finish();
        });
    }

    private void guardarEquipo() {
        String codigo = binding.etCodigo.getText().toString();
        String nombre = binding.etNombre.getText().toString();
        String tipo = binding.etTipo.getText().toString();
        String estado = binding.etEstado.getText().toString();

        if (codigo.isEmpty() || nombre.isEmpty() || tipo.isEmpty() || estado.isEmpty()) {
            // Podrías mostrar un error aquí
            return;
        }

        Intent result = new Intent();
        result.putExtra("codigo", codigo);
        result.putExtra("nombre", nombre);
        result.putExtra("tipo", tipo);
        result.putExtra("estado", estado);
        result.putExtra("modo", modo);
        result.putExtra("posicion", posicion);
        setResult(RESULT_OK, result);
        finish();
    }
}