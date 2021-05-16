package com.example.crud05;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TipoPagoInsertarActivity extends Activity {

    ControlBD helper;
   // EditText editidTipoPago;
    EditText edittipoPago;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_pago_insertar);
        helper = new ControlBD(this);
        //editidTipoPago = (EditText) findViewById(R.id.editidTipoPago);
        edittipoPago = (EditText) findViewById(R.id.edittipoPago);

    }
    public void insertarTipoPago(View v) {
        //Integer idTipoPago=editidTipoPago.getId();
        String tipoPago=edittipoPago.getText().toString();
        String regInsertados;
        TipoPago alumno=new TipoPago();
        //alumno.setIdTipoPago(idTipoPago);
        alumno.setTipoPago(tipoPago);
        helper.abrir();
        regInsertados=helper.insertar(alumno);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        //editidTipoPago.setText("");
        edittipoPago.setText("");

    }
}
