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

public class AdaptadorHorario extends RecyclerView.Adapter<AdaptadorHorario.HorarioViewHolder> {
    Context context;
    List<Horario> listaHorarios;

    public AdaptadorHorario(Context context, List<Horario> listaHorarioss) {
        this.context = context;
        this.listaHorarios = listaHorarioss;
    }

    @NonNull
    @Override
    public HorarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_horario, null, false);
        return new AdaptadorHorario.HorarioViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull HorarioViewHolder holder, int position) {
        holder.tvIdHorario.setText(listaHorarios.get(position).getId());
        holder.tvIdLocal.setText(listaHorarios.get(position).getIdlocal());
        holder.tvDia.setText(listaHorarios.get(position).getDia());
        holder.tvApertura.setText(listaHorarios.get(position).getApertura());
        holder.tvCierre.setText(listaHorarios.get(position).getCierre());

    }

    @Override
    public int getItemCount() { return listaHorarios.size();
    }

    public class HorarioViewHolder extends RecyclerView.ViewHolder {

        TextView tvIdHorario, tvIdLocal, tvDia, tvApertura,tvCierre;

        public HorarioViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdHorario = itemView.findViewById(R.id.tvIdHorario);
            tvIdLocal = itemView.findViewById(R.id.tvIdLocal);
            tvDia = itemView.findViewById(R.id.tvDia);
            tvApertura = itemView.findViewById(R.id.tvApertura);
            tvCierre = itemView.findViewById(R.id.tvCierre);
        }
    }
}