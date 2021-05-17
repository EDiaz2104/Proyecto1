package com.example.crud05;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RepartidorActualizarActivity extends AppCompatActivity {

    ControlBD helper;
    EditText editIdRepartidor;
    EditText editidLocal;
    EditText editNombreRepartidor;
    EditText editCarnetRepartidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repartidor_actualizar);
        helper = new ControlBD(this);
        editIdRepartidor = (EditText) findViewById(R.id.editIdRepartidor);
        editidLocal = (EditText) findViewById(R.id.editidLocal);
        editNombreRepartidor = (EditText) findViewById(R.id.editNombreRepartidor);
        editCarnetRepartidor = (EditText) findViewById(R.id.editCarnetRepartidor);

    }
    public void actualizarRepartidor(View v) {
        String idrepartidor=editIdRepartidor.getText().toString();
        String idlocal=editidLocal.getText().toString();
        String nombrerepartidor=editNombreRepartidor.getText().toString();
        String carnetrepartidor=editCarnetRepartidor.getText().toString();

        String regInsertados;
        Repartidor repartidor=new Repartidor();
        repartidor.setIdRepartidor(Integer.parseInt(idrepartidor));
        repartidor.setIdLocal(Integer.parseInt(idlocal));
        repartidor.setNombreRepartidor(nombrerepartidor);
        repartidor.setCarnetRepartidor(carnetrepartidor);

        helper.abrir();
        regInsertados=helper.actualizar(repartidor);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editIdRepartidor.setText("");
        editidLocal.setText("");
        editNombreRepartidor.setText("");
        editCarnetRepartidor.setText("");

    }
}