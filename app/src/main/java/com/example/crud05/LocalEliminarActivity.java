package com.example.crud05;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LocalEliminarActivity extends AppCompatActivity {

    ControlBD helper;
    EditText editIdLocal, editIdEncargado, editIdUbicacion;
    EditText editNombreLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_eliminar);
        helper = new ControlBD(this);
        editIdLocal = (EditText) findViewById(R.id.editTextIdLocal);
        editIdEncargado = (EditText) findViewById(R.id.editTextIdEncarLocal);
        editIdUbicacion = (EditText) findViewById(R.id.editTextIdUbicLocal);
        editNombreLocal = (EditText) findViewById(R.id.editTextNomLocal);
    }

    public void eliminarLocal(View v){
        String regEliminadas;
        Local local = new Local();
        if(!editIdLocal.getText().toString().isEmpty()){
            local.setIdlocal(Integer.valueOf(editIdLocal.getText().toString()));
            helper.abrir();
            regEliminadas=helper.eliminarLocal(local);
            helper.cerrar();
            Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Debe ingresar el ID", Toast.LENGTH_SHORT).show();
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