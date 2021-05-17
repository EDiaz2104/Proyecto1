package com.example.crud05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EncargadoLocalIncerActualActivity extends AppCompatActivity {

    ControlBD helper;
    EditText editIdEncargado, editIdUsuario;
    EditText editNombre, editApellido;
    EditText editTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargado_local_incer_actual);
        helper = new ControlBD(this);
        editIdEncargado = (EditText) findViewById(R.id.editTextIDencargado);
        editIdUsuario = (EditText) findViewById(R.id.editTextIDusuario);
        editNombre = (EditText) findViewById(R.id.editTextNomEncargado);
        editApellido = (EditText) findViewById(R.id.editTextApelEncargado);
        editTel = (EditText) findViewById(R.id.editTextTelEncargado);
    }

    public void insertarEncargado(View v) {
        if(!editIdEncargado.getText().toString().isEmpty() && !editIdUsuario.getText().toString().isEmpty() && !editNombre.getText().toString().isEmpty() && !editApellido.getText().toString().isEmpty() && !editTel.getText().toString().isEmpty()){

            Integer idEncargado=Integer.valueOf(editIdEncargado.getText().toString());
            Integer idUsuario=Integer.valueOf(editIdUsuario.getText().toString());
            String nombre=editNombre.getText().toString();
            String apellido=editApellido.getText().toString();
            String telefono=editTel.getText().toString();
            String regInsertados;
            EncargadoLocal encargado=new EncargadoLocal();

            encargado.setIdencargadolocal(idEncargado);
            encargado.setIdusuario(idUsuario);
            encargado.setNomencargado(nombre);
            encargado.setApelencargado(apellido);
            encargado.setTelencargado(telefono);
            helper.abrir();
            regInsertados=helper.insertarEncargado(encargado);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Debe ingresar datos", Toast.LENGTH_SHORT).show();
        }
    }

    public void actualizarEncargado(View v) {
        EncargadoLocal encargado=new EncargadoLocal();
        if(!editIdEncargado.getText().toString().isEmpty() && !editIdUsuario.getText().toString().isEmpty() && !editNombre.getText().toString().isEmpty() && !editApellido.getText().toString().isEmpty() && !editTel.getText().toString().isEmpty()) {

            encargado.setIdencargadolocal(Integer.valueOf(editIdEncargado.getText().toString()));
            encargado.setIdusuario(Integer.valueOf(editIdUsuario.getText().toString()));
            encargado.setNomencargado(editNombre.getText().toString());
            encargado.setApelencargado(editApellido.getText().toString());
            encargado.setTelencargado(editTel.getText().toString());

            helper.abrir();
            String estado = helper.actualizarEncargado(encargado);
            helper.cerrar();

            Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Debe llenar los campos obligatorios", Toast.LENGTH_SHORT).show();
        }
    }

    public void consultarEncargado(View v) {
        if(!editIdEncargado.getText().toString().isEmpty()){
            helper.abrir();
            EncargadoLocal encargado = helper.consultarEncargado(editIdEncargado.getText().toString());
            helper.cerrar();
            if(encargado == null)
                Toast.makeText(this, "El Encargado de local con ID: " + editIdEncargado.getText().toString() + " no encontrado", Toast.LENGTH_LONG).show();
            else{
                editIdUsuario.setText(String.valueOf(encargado.getIdusuario()));
                editNombre.setText(encargado.getNomencargado());
                editApellido.setText(encargado.getApelencargado());
                editTel.setText(encargado.getTelencargado());
            }
        }else {
            Toast.makeText(this, "Debe ingresar el ID del horario", Toast.LENGTH_LONG).show();
        }
    }

    public void limpiarTexto(View v) {
        editIdEncargado.setText("");
        editIdUsuario.setText("");
        editNombre.setText("");
        editApellido.setText("");
        editTel.setText("");
    }
}