package com.example.crud05;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EncargadoLocalEliminarActivity extends AppCompatActivity {

    ControlBD helper;
    EditText editIdEncargado, editIdUsuario;
    EditText editNombre, editApellido;
    EditText editTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargado_local_eliminar);
        helper = new ControlBD(this);
        editIdEncargado = (EditText) findViewById(R.id.editTextIDencargado);
        editIdUsuario = (EditText) findViewById(R.id.editTextIDusuario);
        editNombre = (EditText) findViewById(R.id.editTextNomEncargado);
        editApellido = (EditText) findViewById(R.id.editTextApelEncargado);
        editTel = (EditText) findViewById(R.id.editTextTelEncargado);
    }

    public void eliminarEncargado(View v){
        String regEliminadas;
        EncargadoLocal encargado = new EncargadoLocal();
        if(!editIdEncargado.getText().toString().isEmpty()){
            encargado.setIdencargadolocal(Integer.valueOf(editIdEncargado.getText().toString()));
            helper.abrir();
            regEliminadas=helper.eliminarEncargado(encargado);
            helper.cerrar();
            Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Debe ingresar el ID", Toast.LENGTH_SHORT).show();
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