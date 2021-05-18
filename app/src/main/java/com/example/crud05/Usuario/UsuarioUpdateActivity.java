package com.example.crud05.Usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.crud05.ControlBD;
import com.example.crud05.Modelos.Usuario;
import com.example.crud05.R;

public class UsuarioUpdateActivity extends AppCompatActivity {
    EditText edit_idUsuario;
    EditText edit_nombreUsuario;
    EditText edit_apelUsuario;
    EditText edit_telUsuario;
    EditText edit_direccionUsuario;
    EditText edit_emailUsuario;
    EditText edit_claveUsuario;
    Spinner edit_estado;
    ControlBD helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_update);
        helper= new ControlBD(this);
        edit_idUsuario = (EditText) findViewById(R.id.edit_idUsuario);
        edit_nombreUsuario = (EditText) findViewById(R.id.edit_nombre);
        edit_apelUsuario = (EditText) findViewById(R.id.edit_apellido);
        edit_telUsuario = (EditText) findViewById(R.id.edit_telefono);
        edit_direccionUsuario = (EditText) findViewById(R.id.edit_direccion);
        edit_emailUsuario = (EditText) findViewById(R.id.edit_email);
        edit_claveUsuario = (EditText) findViewById(R.id.edit_clave);

        edit_estado = (Spinner) findViewById(R.id.edit_estado);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.estados_usuario, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edit_estado.setAdapter(adapter);

    }
    public void actualizarUsuario(View v) {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(Integer.parseInt(edit_idUsuario.getText().toString()));
        usuario.setNombreUsuario(edit_nombreUsuario.getText().toString());
        usuario.setApelUsuario(edit_apelUsuario.getText().toString());
        usuario.setTelUsuario(edit_telUsuario.getText().toString());
        usuario.setDireccionUsuario(edit_direccionUsuario.getText().toString());
        usuario.setEmailUsuario(edit_emailUsuario.getText().toString());
        usuario.setClaveUsuario(edit_claveUsuario.getText().toString());
        if (edit_estado.getSelectedItem().toString().equals("Activo")) usuario.setEstadoUsuario(1);
        else usuario.setEstadoUsuario(0);

        helper.abrir();
        String estado = helper.actualizar(usuario);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        edit_idUsuario.setText("");
        edit_nombreUsuario.setText("");
        edit_apelUsuario.setText("");
        edit_telUsuario.setText("");
        edit_direccionUsuario.setText("");
        edit_emailUsuario.setText("");
        edit_claveUsuario.setText("");
    }

}