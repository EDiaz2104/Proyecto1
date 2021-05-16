package com.example.crud05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UbicacionConsulElimiActivity extends AppCompatActivity {

    ControlBD helper;
    EditText editId;
    EditText editDirec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion_consul_elimi);
        helper = new ControlBD(this);
        editId = (EditText) findViewById(R.id.editTextIdU);
        editDirec = (EditText) findViewById(R.id.editMultDirec);
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

    public void eliminarHorario(View v){
        String regEliminadas;
        Ubicacion ubi=new Ubicacion();
        if(!editId.getText().toString().isEmpty()){
            ubi.setIdubicacion(Integer.valueOf(editId.getText().toString()));
            helper.abrir();
            regEliminadas=helper.eliminarUbicacion(ubi);
            helper.cerrar();
            Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Debe ingresar el ID", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v) {
        editId.setText("");
        editDirec.setText("");
    }
}