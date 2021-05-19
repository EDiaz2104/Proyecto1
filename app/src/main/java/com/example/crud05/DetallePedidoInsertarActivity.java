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


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido_insertar);
        helper = new ControlBD(this);
        editidTipoPago = (EditText) findViewById(R.id.editidTipoPago);
        editidProducto = (EditText) findViewById(R.id.editidProducto);
        editidDetallePedido = (EditText) findViewById(R.id.editidDetallePedido);
        editcantidad = (EditText) findViewById(R.id.editcantidad);

    }
    public void insertarDetallePedido(View v) {
        String regInsertados;
        int idDetallepedido= Integer.valueOf(editidDetallePedido.getText().toString());
        int idTipoPago= Integer.valueOf(editidTipoPago.getText().toString());
        int idProducto= Integer.valueOf(editidProducto.getText().toString());
        int cantidad= Integer.parseInt(editcantidad.getText().toString());

        DetallePedido detalle= new DetallePedido();
        detalle.setIdTipoPago(idTipoPago);
        detalle.setIdProducto(idProducto);
        detalle.setIdDetallePedido(idDetallepedido);
        detalle.setCantidad(cantidad);
        detalle.setEstadoPedido(1);
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

    }
}