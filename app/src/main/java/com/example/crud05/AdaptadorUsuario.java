package com.example.crud05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.crud05.Modelos.Usuario;
import java.util.List;


public class AdaptadorUsuario extends RecyclerView.Adapter<AdaptadorUsuario.UsuarioViewHolder>{

    Context context;
    List<Usuario> listaUsuarios;

    public AdaptadorUsuario(Context context, List<Usuario> listaUsuarios) {
        this.context = context;
        this.listaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_usuario, null,false);
        return new  AdaptadorUsuario.UsuarioViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        holder.tvIdusuario.setText(listaUsuarios.get(position).getIdUsuario());
        holder.tvNombre.setText(listaUsuarios.get(position).getNombreUsuario());
        //holder.tvApellido.setText(listaUsuarios.get(position).getApelUsuario());
        //holder.tvTelefono.setText(listaUsuarios.get(position).getTelUsuario());
        //holder.tvDireccion.setText(listaUsuarios.get(position).getDireccionUsuario());
        //holder.tvEstado.setText(listaUsuarios.get(position).getEstadoUsuario());
        //holder.tvEmail.setText(listaUsuarios.get(position).getEmailUsuario());
        //holder.tvClave.setText(listaUsuarios.get(position).getClaveUsuario());
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdusuario, tvNombre, tvApellido, tvTelefono, tvDireccion, tvEstado, tvEmail, tvClave;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIdusuario = itemView.findViewById(R.id.tvIdUsuariRS);
            tvNombre = itemView.findViewById(R.id.tvNombreUsuarioRS);

        }
    }
}
