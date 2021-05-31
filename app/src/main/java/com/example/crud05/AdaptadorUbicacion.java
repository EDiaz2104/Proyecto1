package com.example.crud05;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdaptadorUbicacion extends RecyclerView.Adapter<AdaptadorUbicacion.UbicacionViewHolder> {
    Context context;
    List<Ubicacion> listaUbicacion;

    public AdaptadorUbicacion(Context context, List<Ubicacion> listaUbicacion) {
        this.context = context;
        this.listaUbicacion = listaUbicacion;
    }

    @NonNull
    @Override
    public UbicacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_ubicacion, null, false);
        return new AdaptadorUbicacion.UbicacionViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull UbicacionViewHolder holder, int position) {
        holder.tvIdUbicacion.setText(listaUbicacion.get(position).getId());
        holder.tvDescripcionUbicacion.setText(listaUbicacion.get(position).getDescripcionUbicacion());

    }

    @Override
    public int getItemCount() {
        return listaUbicacion.size();
    }

    public class UbicacionViewHolder extends RecyclerView.ViewHolder {

        TextView tvIdUbicacion,tvDescripcionUbicacion;

        public UbicacionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdUbicacion = itemView.findViewById(R.id.tvIdUbicacion);
            tvDescripcionUbicacion = itemView.findViewById(R.id.tvDescripcionUbicacion);
        }
    }
}