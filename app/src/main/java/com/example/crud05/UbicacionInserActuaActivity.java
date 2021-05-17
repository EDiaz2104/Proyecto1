package com.example.crud05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UbicacionInserActuaActivity extends AppCompatActivity {

    ControlBD helper;
    EditText editId;
    EditText editDirec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion_inser_actua);
        helper = new ControlBD(this);
        editId = (EditText) findViewById(R.id.editTextIdU);
        editDirec = (EditText) findViewById(R.id.editMultDirec);
    }

    public void insertarUbicacion(View v) {
        if(!editId.getText().toString().isEmpty() && !editDirec.getText().toString().isEmpty()){
            Integer id=Integer.valueOf(editId.getText().toString());
            String direccion=editDirec.getText().toString();
            String regInsertados;
            Ubicacion ubi=new Ubicacion();
            ubi.setIdubicacion(id);
            ubi.setDescripcionubicacion(direccion);
            helper.abrir();
            regInsertados=helper.insertarUbicacion(ubi);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Debe ingresar datos", Toast.LENGTH_SHORT).show();
        }
    }

    public void actualizarUbicacion(View v) {
        Ubicacion ubi=new Ubicacion();
        if(!editId.getText().toString().isEmpty() && !editDirec.getText().toString().isEmpty()) {
            ubi.setIdubicacion(Integer.valueOf(editId.getText().toString()));
            ubi.setDescripcionubicacion(editDirec.getText().toString());
            helper.abrir();
            String estado = helper.actualizarUbicacion(ubi);
            helper.cerrar();
            Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void consultarHorario(View v) {
        if(!editId.getText().toString().isEmpty()){
            helper.abrir();
            Ubicacion ubi = helper.consultarUbicacion(editId.getText().toString());
            helper.cerrar();
            if(ubi == null)
                Toast.makeText(this, "El horario con ID: " + editId.getText().toString() + " no encontrado", Toast.LENGTH_LONG).show();
            else{
                editDirec.setText(ubi.getDescripcionubicacion());
            }
        }else {
            Toast.makeText(this, "Debe ingresar el ID del horario", Toast.LENGTH_LONG).show();
        }
    }

    public void limpiarTexto(View v) {
        editId.setText("");
        editDirec.setText("");
    }
}