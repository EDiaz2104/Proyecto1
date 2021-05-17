package com.example.crud05;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RepartidorConsultarActivity extends AppCompatActivity {

    ControlBD helper;
    EditText editIdRepartidor;
    EditText editidLocal;
    EditText editNombreRepartidor;
    EditText editCarnetRepartidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repartidor_consultar);
        helper = new ControlBD(this);
        editIdRepartidor = (EditText) findViewById(R.id.editIdRepartidor);
        editidLocal = (EditText) findViewById(R.id.editidLocal);
        editNombreRepartidor = (EditText) findViewById(R.id.editNombreRepartidor);
        editCarnetRepartidor = (EditText) findViewById(R.id.editCarnetRepartidor);

    }
    public void consultarRepartidor(View v) {
        helper.abrir();
        Repartidor repartidor = helper.consultarRepartidor(editIdRepartidor.getText().toString());
        helper.cerrar();
        if(repartidor == null)
            Toast.makeText(this, "Repartidor con Id Repartidor " +
                    editIdRepartidor.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editIdRepartidor.setText(repartidor.getIdRepartidor());
            editidLocal.setText(repartidor.getIdLocal());
            editNombreRepartidor.setText(repartidor.getNombreRepartidor());
            editCarnetRepartidor.setText(repartidor.getCarnetRepartidor());

        }
    }
    public void limpiarTexto(View v) {
        editIdRepartidor.setText("");
        editidLocal.setText("");
        editNombreRepartidor.setText("");
        editCarnetRepartidor.setText("");

    }
}