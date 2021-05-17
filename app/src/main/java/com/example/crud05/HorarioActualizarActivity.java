package com.example.crud05;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class HorarioActualizarActivity extends AppCompatActivity {

    ControlBD helper;
    EditText editId, editIdlocal;
    EditText editDia;
    EditText editApertura;
    EditText editCierre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_actualizar);
        helper = new ControlBD(this);
        editId = (EditText) findViewById(R.id.editTextId);
        editIdlocal = (EditText) findViewById(R.id.editTextIdlocal);
        editDia = (EditText) findViewById(R.id.editTexDia);
        editApertura = (EditText) findViewById(R.id.editTextApertura);
        editCierre = (EditText) findViewById(R.id.editTextCierre);
    }

    public void actualizarHorario(View v) {
        Horario hora = new Horario();
        if(!editId.getText().toString().isEmpty() && !editIdlocal.getText().toString().isEmpty() && !editDia.getText().toString().isEmpty() && !editCierre.getText().toString().isEmpty() && !editApertura.getText().toString().isEmpty()) {
            hora.setIdhorario(Integer.valueOf(editId.getText().toString()));
            hora.setIdlocal(Integer.valueOf(editIdlocal.getText().toString()));
            hora.setDia(editDia.getText().toString());
            hora.setApertura(editApertura.getText().toString());
            hora.setCierre(editCierre.getText().toString());
            helper.abrir();
            String estado = helper.actualizarHorario(hora);
            helper.cerrar();
            Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void consultarHorario(View v) {
        if(!editId.getText().toString().isEmpty()){
            helper.abrir();
            Horario horario = helper.consultarHorario(editId.getText().toString());
            helper.cerrar();
            if(horario == null)
                Toast.makeText(this, "El horario con ID: " + editId.getText().toString() + " no encontrado", Toast.LENGTH_LONG).show();
            else{
                editIdlocal.setText(String.valueOf(horario.getIdlocal()));
                editDia.setText(horario.getDia());
                editApertura.setText(horario.getApertura());
                editCierre.setText(horario.getCierre());
            }
        }else {
            Toast.makeText(this, "Debe ingresar el ID del horario", Toast.LENGTH_LONG).show();
        }
    }

    public void limpiarTexto(View v) {
        editId.setText("");
        editIdlocal.setText("");
        editDia.setText("");
        editApertura.setText("");
        editCierre.setText("");
    }
}