package com.example.lab2_20221203;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2_20221203.databinding.ItemEquipoBinding;
import com.example.lab2_20221203.entity.Equipo;

import java.util.ArrayList;
import java.util.List;

public class EquipoAdapter extends RecyclerView.Adapter<EquipoAdapter.EquipoViewHolder> {

    private List<Equipo> listaEquipos = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public EquipoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemEquipoBinding binding = ItemEquipoBinding.inflate(inflater, parent, false);
        return new EquipoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EquipoViewHolder holder, int position) {
        Equipo equipo = listaEquipos.get(position);

        // Color de fondo alternado
        if (position % 2 == 1) {
            holder.binding.getRoot().setBackgroundColor(Color.parseColor("#F3E5F5"));
        } else {
            holder.binding.getRoot().setBackgroundColor(Color.WHITE);
        }

        holder.binding.tvCodigo.setText(equipo.getCodigo());
        holder.binding.tvNombre.setText(equipo.getNombre());
        holder.binding.tvTipo.setText(equipo.getTipo());
        holder.binding.tvEstado.setText(equipo.getEstado());

        // Color del texto de estado
        String estado = equipo.getEstado();
        if ("Operativo".equalsIgnoreCase(estado)) {
            holder.binding.tvEstado.setTextColor(Color.GREEN);
        } else if ("En mantenimiento".equalsIgnoreCase(estado)) {
            holder.binding.tvEstado.setTextColor(Color.parseColor("#F9A825")); // Amarillo/Naranja visible
        } else if ("Fuera de servicio".equalsIgnoreCase(estado)) {
            holder.binding.tvEstado.setTextColor(Color.RED);
        } else {
            holder.binding.tvEstado.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return listaEquipos != null ? listaEquipos.size() : 0;
    }

    public void setListaEquipos(List<Equipo> listaEquipos) {
        this.listaEquipos = listaEquipos;
        notifyDataSetChanged();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static class EquipoViewHolder extends RecyclerView.ViewHolder {
        ItemEquipoBinding binding;
        public EquipoViewHolder(ItemEquipoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}