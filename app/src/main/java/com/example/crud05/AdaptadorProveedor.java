package com.example.crud05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorProveedor extends RecyclerView.Adapter<AdaptadorProveedor.ProveedorViewHolder> {
    Context context;
    List<Proveedor> listaProveedores;

    public AdaptadorProveedor(Context context, List<Proveedor> listaProveedores) {
        this.context = context;
        this.listaProveedores = listaProveedores;
    }

    @NonNull
    @Override
    public AdaptadorProveedor.ProveedorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_proveedor, null, false);
        return new AdaptadorProveedor.ProveedorViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorProveedor.ProveedorViewHolder holder, int position) {
        holder.tvIdProveedor.setText(listaProveedores.get(position).getId());
        holder.tvNombreProveedor.setText(listaProveedores.get(position).getNombreProveedor());
        holder.tvDescripcionProveedor.setText(listaProveedores.get(position).getDescripcionProveedor());
        holder.tvTelefonoProveedor.setText(listaProveedores.get(position).getTelefonoProveedor());
    }

    @Override
    public int getItemCount() {
        return listaProveedores.size();
    }

    public class ProveedorViewHolder extends RecyclerView.ViewHolder {

        TextView tvIdProveedor,tvNombreProveedor, tvDescripcionProveedor,tvTelefonoProveedor;

        public ProveedorViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdProveedor = itemView.findViewById(R.id.tvIdProveedor);
            tvNombreProveedor=itemView.findViewById(R.id.tvNombreProveedor);
            tvDescripcionProveedor = itemView.findViewById(R.id.tvDescripcionProveedor);
            tvTelefonoProveedor = itemView.findViewById(R.id.tvTelefonoProveedor);
        }
    }

}
