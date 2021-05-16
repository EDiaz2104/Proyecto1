package com.example.crud05;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetallePedidoInsertarActivity extends Activity {

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
    public void insertarDetallePedido(View v) {
        String regInsertados;
        String idDetallepedido=editidDetallePedido.getText().toString();
        String idTipoPago=editidTipoPago.getText().toString();
        String idProducto=editidProducto.getText().toString();
        String cantidad=editcantidad.getText().toString();
        String EstadoPedido = editEstadoPedido.getText().toString();
        DetallePedido detalle= new DetallePedido();
        detalle.setIdTipoPago(idTipoPago);
        detalle.setIdProducto(idProducto);
        detalle.setIdDetallePedido(idDetallepedido);
        detalle.setCantidad(cantidad);
        detalle.setEstadoPedido(EstadoPedido);
        helper.abrir();
        regInsertados=helper.insertar(detalle);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editidTipoPago.setText("");
        editidProducto.setText("");
        editidDetallePedido.setText("");
        editcantidad.setText("");
        editEstadoPedido.setText("");
    }
}