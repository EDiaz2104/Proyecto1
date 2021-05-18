package com.example.crud05.Usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud05.ControlBD;
import com.example.crud05.Modelos.Usuario;
import com.example.crud05.R;

public class UsuarioReadActivity extends AppCompatActivity {
    ControlBD helper;
    EditText edit_idUsuario;
    EditText edit_nombreUsuario;
    EditText edit_apelUsuario;
    EditText edit_telUsuario;
    EditText edit_direccionUsuario;
    EditText edit_estadoUsuario;
    EditText edit_emailUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_read);
        helper= new ControlBD(this);
        edit_idUsuario = (EditText) findViewById(R.id.edit_idUsuario);
        edit_nombreUsuario = (EditText) findViewById(R.id.edit_nombre);
        edit_apelUsuario = (EditText) findViewById(R.id.edit_apellido);
        edit_telUsuario = (EditText) findViewById(R.id.edit_telefono);
        edit_direccionUsuario = (EditText) findViewById(R.id.edit_direccion);
        edit_estadoUsuario = (EditText) findViewById(R.id.edit_estado);
        edit_emailUsuario = (EditText) findViewById(R.id.edit_email);
    }
    public void consultarUsuario(View v) {
        helper.abrir();
        Usuario usuario = helper.consultarUsuario(edit_idUsuario.getText().toString());
        helper.cerrar();
        if(usuario == null) Toast.makeText(
                this,
                "Usuario con id "+ edit_idUsuario.getText().toString() + " no encontrado",
                Toast.LENGTH_LONG).show();
        else{
            edit_nombreUsuario.setText(usuario.getNombreUsuario());
            edit_apelUsuario.setText(usuario.getApelUsuario());
            edit_telUsuario.setText(usuario.getTelUsuario());
            edit_direccionUsuario.setText(usuario.getDireccionUsuario());
            if (usuario.getEstadoUsuario()==1) edit_estadoUsuario.setText("Activo");
            else edit_estadoUsuario.setText("Inactivo");
            edit_emailUsuario.setText(usuario.getEmailUsuario());
        }
    }

    public void limpiarTexto(View v){
        edit_idUsuario.setText("");
        edit_nombreUsuario.setText("");
        edit_apelUsuario.setText("");
        edit_telUsuario.setText("");
        edit_direccionUsuario.setText("");
        edit_estadoUsuario.setText("");
        edit_emailUsuario.setText("");

    }
}