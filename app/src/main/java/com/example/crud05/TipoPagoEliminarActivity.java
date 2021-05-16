package com.example.crud05;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TipoPagoEliminarActivity extends Activity {

    EditText editidTipoPago;
    ControlBD controlhelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_pago_eliminar);
        controlhelper=new ControlBD (this);
        editidTipoPago=(EditText)findViewById(R.id.editidTipoPago);
    }

    public void eliminarTipoPago(View v){
        String regEliminadas;
        TipoPago tipopago=new TipoPago();
        tipopago.setIdTipoPago(editidTipoPago.getId());
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(tipopago);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editidTipoPago.setText("");

    }
}