package com.example.crud05;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetallePedidoActualizarActivity extends Activity {
    ControlBD helper;
    EditText editidTipoPago;
    EditText editidProducto;
    EditText editidDetallePedido;
    EditText editcantidad;
    EditText editEstadoPedido;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido_insertar);
        helper = new ControlBD(this);
        editidTipoPago = (EditText) findViewById(R.id.editidTipoPago);
        editidProducto = (EditText) findViewById(R.id.editidProducto);
        editidDetallePedido = (EditText) findViewById(R.id.editidDetallePedido);
        editcantidad = (EditText) findViewById(R.id.editcantidad);
        editEstadoPedido = (EditText) findViewById(R.id.editEstadoPedido);
    }
    public void actualizarDetallePedido(View v) {
        DetallePedido detalle = new DetallePedido();
        detalle.setIdTipoPago(editidTipoPago.getText().toString());
        detalle.setIdProducto(editidProducto.getText().toString());
        detalle.setIdDetallePedido(editidDetallePedido.getText().toString());
        detalle.setCantidad(editcantidad.getText().toString());
        detalle.setEstadoPedido(editEstadoPedido.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(detalle);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editidTipoPago.setText("");
        editidProducto.setText("");
        editidDetallePedido.setText("");
        editcantidad.setText("");
        editEstadoPedido.setText("");
    }
}