package com.example.crud05;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class HorarioInsertarActivity extends AppCompatActivity {

    ControlBD helper;
    EditText editId, editIdlocal;
    EditText editDia;
    EditText editApertura, editCierre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_insertar);
        helper = new ControlBD(this);
        editId = (EditText) findViewById(R.id.editTextId);
        editIdlocal = (EditText) findViewById(R.id.editTextIdlocal);
        editDia = (EditText) findViewById(R.id.editTexDia);
        editApertura = (EditText) findViewById(R.id.editTextApertura);
        editCierre = (EditText) findViewById(R.id.editTextCierre);
    }

    public void insertarHorario(View v) {
        if(!editId.getText().toString().isEmpty() && !editIdlocal.getText().toString().isEmpty() && !editDia.getText().toString().isEmpty() && !editCierre.getText().toString().isEmpty() && !editApertura.getText().toString().isEmpty()){

            Integer id=Integer.valueOf(editId.getText().toString());
            Integer idlocal=Integer.valueOf(editIdlocal.getText().toString());
            String dia=editDia.getText().toString();
            String apertura=editApertura.getText().toString();
            String cierre=editCierre.getText().toString();
            String regInsertados;
            Horario hora=new Horario();
            hora.setIdhorario(id);
            hora.setIdlocal(idlocal);
            hora.setDia(dia);
            hora.setApertura(apertura);
            hora.setCierre(cierre);
            helper.abrir();
            regInsertados=helper.insertarHorario(hora);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Debe ingresar datos", Toast.LENGTH_SHORT).show();
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