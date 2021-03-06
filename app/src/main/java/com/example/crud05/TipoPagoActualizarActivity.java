package com.example.crud05;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class TipoPagoActualizarActivity extends Activity {

    ControlBD helper;
    EditText editidTipoPago;
    EditText edittipoPago;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_pago_actualizar);
        helper = new ControlBD(this);
        editidTipoPago = (EditText) findViewById(R.id.editidTipoPago);
        edittipoPago = (EditText) findViewById(R.id.edittipoPago);
    }
    public void actualizarTipoPago(View v) {
        TipoPago tipopago = new TipoPago();
        tipopago.setIdTipoPago(Integer.parseInt(editidTipoPago.getText().toString()));
        tipopago.setTipoPago(edittipoPago.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(tipopago);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editidTipoPago.setText("");
        edittipoPago.setText("");

    }
}