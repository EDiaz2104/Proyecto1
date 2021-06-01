package com.example.crud05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AdaptadorTipopago extends RecyclerView.Adapter<AdaptadorTipopago.TipopagoViewHolder>{
    Context context;
    List<TipoPagoAPI> listaTipopago;

    public AdaptadorTipopago(Context context, List<TipoPagoAPI> listaTipopago) {
        this.context = context;
        this.listaTipopago = listaTipopago;
    }

    @NonNull
    @Override
    public AdaptadorTipopago.TipopagoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_tipopago, null,false);
        return new  AdaptadorTipopago.TipopagoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorTipopago.TipopagoViewHolder holder, int position) {
        holder.tvIdtipo.setText(listaTipopago.get(position).getIdTipoPago());
        holder.tvTipopago.setText(listaTipopago.get(position).getTipoPago());
    }

    @Override
    public int getItemCount() {
        return listaTipopago.size();
    }

    public class TipopagoViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdtipo, tvTipopago;

        public TipopagoViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIdtipo = itemView.findViewById(R.id.tvIdtipoRS);
            tvTipopago = itemView.findViewById(R.id.tvTipopagoRS);

        }
    }
}