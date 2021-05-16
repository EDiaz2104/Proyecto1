package com.example.crud05;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class TipoPagoConsultarActivity extends Activity {
    ControlBD helper;
    EditText editidTipoPago;
    EditText edittipoPago;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_pago_consultar);
        helper = new ControlBD(this);
        editidTipoPago = (EditText) findViewById(R.id.editidTipoPago);
        edittipoPago = (EditText) findViewById(R.id.edittipoPago);

    }
    public void consultarTipoPago(View v) {
        helper.abrir();
        TipoPago tipoPago = helper.consultarTipoPago(editidTipoPago.getText().toString());
        helper.cerrar();
        if(tipoPago == null)
            Toast.makeText(this, "TipoPago con id " +
                    editidTipoPago.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            edittipoPago.setText(tipoPago.getTipoPago());
        }
    }
    public void limpiarTexto(View v){
        editidTipoPago.setText("");
        edittipoPago.setText("");

    }
}
