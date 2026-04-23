package com.example.lab2_20221203;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.lab2_20221203.databinding.ActivityListaBinding;
import com.example.lab2_20221203.entity.Equipo;

import java.util.ArrayList;
import java.util.List;

public class ListaActivity extends AppCompatActivity {

    ActivityListaBinding binding;
    private EquipoAdapter adapter;
    private List<Equipo> listaEquipos = new ArrayList<>();
    static final int REQUEST_FORMULARIO = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityListaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configurar RecyclerView
        adapter = new EquipoAdapter();
        adapter.setContext(this);
        binding.rvEquipos.setAdapter(adapter);
        binding.rvEquipos.setLayoutManager(new LinearLayoutManager(this));

        // Llenar filtros (opcional, pero solicitado que se llenen los dropdowns)
        String[] tipos = getResources().getStringArray(R.array.tipos_equipo_items);
        binding.autoTipo.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, tipos));

        String[] estados = getResources().getStringArray(R.array.estados_equipo_items);
        binding.autoEstado.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, estados));

        // Datos iniciales harcodeados
        if (listaEquipos.isEmpty()) {
            listaEquipos.add(new Equipo("EQ-001", "Multímetro Flake", "Multímetro", "Operativo"));
            listaEquipos.add(new Equipo("EQ-002", "Osciloscopio Tek", "Osciloscopio", "En mantenimiento"));
        }
        adapter.setListaEquipos(listaEquipos);

        binding.recargarBtn.setOnClickListener(view -> {
            adapter.notifyDataSetChanged();
        });

        binding.agregarBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, EquipoDetailActivity.class);
            intent.putExtra("modo", "crear");
            startActivityForResult(intent, REQUEST_FORMULARIO);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_FORMULARIO && resultCode == RESULT_OK && data != null) {
            String modo = data.getStringExtra("modo");
            String codigo = data.getStringExtra("codigo");
            String nombre = data.getStringExtra("nombre");
            String tipo   = data.getStringExtra("tipo");
            String estado = data.getStringExtra("estado");
            String observaciones = data.getStringExtra("observaciones");

            if ("crear".equals(modo)) {
                Equipo nuevo = new Equipo(codigo, nombre, tipo, estado);
                listaEquipos.add(nuevo);
            } else if ("editar".equals(modo)) {
                int pos = data.getIntExtra("posicion", -1);
                if (pos >= 0) {
                    listaEquipos.set(pos, new Equipo(codigo, nombre, tipo, estado));
                }
            }
            adapter.setListaEquipos(new ArrayList<>(listaEquipos));
        }
    }
}