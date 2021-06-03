package com.example.crud05.CategoriaProducto;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.crud05.R;

import java.util.List;

public class AdaptadorCategoria extends RecyclerView.Adapter<AdaptadorCategoria.CategoriaViewHolder> {
    Context context;
    List<Categoria> listaCategorias;

    public AdaptadorCategoria(Context context, List<Categoria> listaCategorias) {
        this.context = context;
        this.listaCategorias = listaCategorias;
    }

    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_categoria, null, false);
        return new AdaptadorCategoria.CategoriaViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, int position) {
        holder.tvIdCategoria.setText(listaCategorias.get(position).getId());
        holder.tvNombreCategoria.setText(listaCategorias.get(position).getNombreCategoria());
        holder.tvDescripcionCategoria.setText(listaCategorias.get(position).getDescripcionCategoria());

    }

    @Override
    public int getItemCount() {
        return listaCategorias.size();
    }

    public class CategoriaViewHolder extends RecyclerView.ViewHolder {

        TextView tvIdCategoria, tvNombreCategoria,tvDescripcionCategoria;

        public CategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdCategoria = itemView.findViewById(R.id.tvIdCategoria);
            tvNombreCategoria = itemView.findViewById(R.id.tvNombreCategoria);
            tvDescripcionCategoria = itemView.findViewById(R.id.tvDescripcionCategoria);
        }
    }
}