package com.example.crud05.Usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud05.ControlBD;
import com.example.crud05.Modelos.Usuario;
import com.example.crud05.R;

public class UsuarioCreateActivity extends AppCompatActivity {
    ControlBD helper;
    EditText edit_nombre;
    EditText edit_apellido;
    EditText edit_telefono;
    EditText edit_direccion;
    EditText edit_email;
    EditText edit_clave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_create);
        helper= new ControlBD(this);
        edit_nombre = (EditText) findViewById(R.id.edit_nombre);
        edit_apellido = (EditText) findViewById(R.id.edit_apellido);
        edit_telefono = (EditText) findViewById(R.id.edit_telefono);
        edit_direccion = (EditText) findViewById(R.id.edit_direccion);
        edit_email = (EditText) findViewById(R.id.edit_email);
        edit_clave = (EditText) findViewById(R.id.edit_clave);
    }

    public void insertarUsuario(View v){
        String nombre = edit_nombre.getText().toString();
        String apellido = edit_apellido.getText().toString();
        String telefono = edit_telefono.getText().toString();
        String direccion = edit_direccion.getText().toString();
        String email = edit_email.getText().toString();
        String clave = edit_clave.getText().toString();
        String regInsertados;

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nombre);
        usuario.setApelUsuario(apellido);
        usuario.setTelUsuario(telefono);
        usuario.setDireccionUsuario(direccion);
        usuario.setEmailUsuario(email);
        usuario.setClaveUsuario(clave);
        usuario.setEstadoUsuario(1);
        helper.abrir();
        regInsertados=helper.insertar(usuario);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarUsuario(View v){
        edit_nombre.setText("");
        edit_apellido.setText("");
        edit_telefono.setText("");
        edit_direccion.setText("");
        edit_email.setText("");
        edit_clave.setText("");
    }
}