package com.example.crud05.Repartidor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud05.R;

import java.util.List;

public class AdapatadorRepartidor extends RecyclerView.Adapter<AdapatadorRepartidor.RepartidorViewHolder> {
    Context context;
    List<Repartidor> listaRepartidor;

    @NonNull
    @Override
    public RepartidorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_repartidor, null, false);
        return new AdapatadorRepartidor.RepartidorViewHolder(vista);
    }

    public AdapatadorRepartidor(Context context, List<Repartidor> listaRepartidor) {
        this.context = context;
        this.listaRepartidor = listaRepartidor;
    }

    @Override
    public void onBindViewHolder(@NonNull RepartidorViewHolder holder, int position) {
        holder.tvIdLocal.setText(listaRepartidor.get(position).getIdlocal());
        holder.tvNombreRepartidor.setText(listaRepartidor.get(position).getNombreRepartidor());
        holder.tvCarnetRepartidor.setText(listaRepartidor.get(position).getCarneRepartidor());
    }

    @Override
    public int getItemCount() {
        return listaRepartidor.size();
    }

    public class RepartidorViewHolder extends RecyclerView.ViewHolder {

        TextView tvIdLocal, tvNombreRepartidor, tvCarnetRepartidor;

        public RepartidorViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIdLocal = itemView.findViewById(R.id.tvIdLocal);
            tvNombreRepartidor = itemView.findViewById(R.id.tvNombreRepartidor);
            tvCarnetRepartidor = itemView.findViewById(R.id.tvCarnetRepartidor);
        }
    }
}
