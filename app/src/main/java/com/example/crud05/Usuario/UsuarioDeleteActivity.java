package com.example.crud05.Usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud05.ControlBD;
import com.example.crud05.Modelos.Usuario;
import com.example.crud05.R;

public class UsuarioDeleteActivity extends AppCompatActivity {
    EditText edit_idUsuario;
    ControlBD helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_delete);
        helper = new ControlBD(this);
        edit_idUsuario = (EditText) findViewById(R.id.edit_idUsuario);
    }

    public void eliminarUsuario(View v){
        String regEliminadas;
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(Integer.parseInt(edit_idUsuario.getText().toString()));
        helper.abrir();
        regEliminadas=helper.eliminar(usuario);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        edit_idUsuario.setText("");
    }
}