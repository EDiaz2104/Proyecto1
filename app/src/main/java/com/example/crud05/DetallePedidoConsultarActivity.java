package com.example.crud05;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetallePedidoConsultarActivity extends Activity {

    ControlBD helper;
    EditText editidTipoPago;
    EditText editidProducto;
    EditText editidDetallePedido;
    EditText editcantidad;
    EditText editEstadoPedido;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido_consultar);
        helper = new ControlBD(this);
        editidTipoPago = (EditText) findViewById(R.id.editidTipoPago);
        editidProducto = (EditText) findViewById(R.id.editidProducto);
        editidDetallePedido = (EditText) findViewById(R.id.editidDetallePedido);
        editcantidad = (EditText) findViewById(R.id.editcantidad);
        editEstadoPedido = (EditText) findViewById(R.id.editEstadoPedido);
    }
    public void consultarDetallePedido(View v) {
        helper.abrir();
        DetallePedido detalle = helper.consultarDetallepedido(editidTipoPago.getText().toString(),
                editidProducto.getText().toString(), editidProducto.getText().toString());
        helper.cerrar();
        if(detalle == null)
            Toast.makeText(this, "Detalle de pedido no registrada",
                    Toast.LENGTH_LONG).show();
        else{
            editcantidad.setText(String.valueOf(detalle.getCantidad()));
            if (detalle.getEstadoPedido()==1) editEstadoPedido.setText("Activo");
            else editEstadoPedido.setText("Inactivo");

        }
    }
    public void limpiarTexto(View v) {
        editidTipoPago.setText("");
        editidProducto.setText("");
        editidDetallePedido.setText("");
        editcantidad.setText("");
        editEstadoPedido.setText("");
    }
}