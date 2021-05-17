package com.example.crud05;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LocalCrearActualizarActivity extends AppCompatActivity {

    ControlBD helper;
    EditText editIdLocal, editIdEncargado, editIdUbicacion;
    EditText editNombreLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_crear_actualizar);
        helper = new ControlBD(this);
        editIdLocal = (EditText) findViewById(R.id.editTextIdLocal);
        editIdEncargado = (EditText) findViewById(R.id.editTextIdEncarLocal);
        editIdUbicacion = (EditText) findViewById(R.id.editTextIdUbicLocal);
        editNombreLocal = (EditText) findViewById(R.id.editTextNomLocal);
    }

    public void insertarLocal(View v) {
        if(!editIdLocal.getText().toString().isEmpty() && !editIdEncargado.getText().toString().isEmpty()
                && !editIdUbicacion.getText().toString().isEmpty() && !editNombreLocal.getText().toString().isEmpty()){

            Integer idLocal=Integer.valueOf(editIdLocal.getText().toString());
            Integer idEncargado=Integer.valueOf(editIdEncargado.getText().toString());
            Integer idUbicacion=Integer.valueOf(editIdUbicacion.getText().toString());
            String nombre=editNombreLocal.getText().toString();

            String regInsertados;
            Local local=new Local();

            local.setIdlocal(idLocal);
            local.setIdencargadolocal(idEncargado);
            local.setIdubicacion(idUbicacion);
            local.setNomlocal(nombre);
            helper.abrir();
            regInsertados=helper.insertarLocal(local);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Debe ingresar datos", Toast.LENGTH_SHORT).show();
        }
    }

    public void actualizarLocal(View v) {
        Local local=new Local();
        if(!editIdLocal.getText().toString().isEmpty() && !editIdEncargado.getText().toString().isEmpty()
                && !editIdUbicacion.getText().toString().isEmpty() && !editNombreLocal.getText().toString().isEmpty()) {

            local.setIdlocal(Integer.valueOf(editIdLocal.getText().toString()));
            local.setIdencargadolocal(Integer.valueOf(editIdEncargado.getText().toString()));
            local.setIdubicacion(Integer.valueOf(editIdUbicacion.getText().toString()));
            local.setNomlocal(editNombreLocal.getText().toString());

            helper.abrir();
            String estado = helper.actualizarLocal(local);
            helper.cerrar();

            Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Debe llenar los campos obligatorios", Toast.LENGTH_SHORT).show();
        }
    }

    public void consultarLocal(View v) {
        if(!editIdLocal.getText().toString().isEmpty()){
            helper.abrir();
            Local local = helper.consultarLocal(editIdLocal.getText().toString());
            helper.cerrar();
            if(local == null)
                Toast.makeText(this, "El Local con ID: " + editIdLocal.getText().toString() + " no encontrado", Toast.LENGTH_LONG).show();
            else{
                editIdLocal.setText(String.valueOf(local.getIdlocal()));
                editIdEncargado.setText(String.valueOf(local.getIdencargadolocal()));
                editIdUbicacion.setText(String.valueOf(local.getIdubicacion()));
                editNombreLocal.setText(local.getNomlocal());
            }
        }else {
            Toast.makeText(this, "Debe ingresar el ID del horario", Toast.LENGTH_LONG).show();
        }
    }

    public void limpiarTexto(View v) {
        editIdLocal.setText("");
        editIdEncargado.setText("");
        editIdUbicacion.setText("");
        editNombreLocal.setText("");
    }
}