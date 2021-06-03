package com.example.crud05;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorUbicacion extends RecyclerView.Adapter<AdaptadorUbicacion.UbicacionViewHolder>{
    Context context;
    List<Ubicacion> listaUbicacion;

    @NonNull
    @Override
    public UbicacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_ubicacion, null, false);
        return new AdaptadorUbicacion.UbicacionViewHolder(vista);
    }

    public AdaptadorUbicacion(Context context, List<Ubicacion> listaUbicacion) {
        this.context = context;
        this.listaUbicacion = listaUbicacion;
    }

    @Override
    public void onBindViewHolder(@NonNull UbicacionViewHolder holder, int position) {
        holder.tvIdUbicacion.setText(listaUbicacion.get(position).getId());
        holder.tvDescripcionUbicacion.setText(listaUbicacion.get(position).getDescripcionubicacion());
    }

    @Override
    public int getItemCount() {
        return listaUbicacion.size();
    }

    public class UbicacionViewHolder extends RecyclerView.ViewHolder {

        TextView tvIdUbicacion, tvDescripcionUbicacion;

        public UbicacionViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIdUbicacion = itemView.findViewById(R.id.tvId);
            tvDescripcionUbicacion = itemView.findViewById(R.id.tvDescripcionUbicacion);

        }
    }

}