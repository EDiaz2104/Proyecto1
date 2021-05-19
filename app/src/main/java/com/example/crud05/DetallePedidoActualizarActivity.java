package com.example.crud05;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DetallePedidoActualizarActivity extends Activity {
    ControlBD helper;
    EditText editidTipoPago;
    EditText editidProducto;
    EditText editidDetallePedido;
    EditText editcantidad;
    Spinner editEstadoPedido;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido_actualizar);
        helper = new ControlBD(this);
        editidTipoPago = (EditText) findViewById(R.id.editidTipoPago);
        editidProducto = (EditText) findViewById(R.id.editidProducto);
        editidDetallePedido = (EditText) findViewById(R.id.editidDetallePedido);
        editcantidad = (EditText) findViewById(R.id.editcantidad);
        editEstadoPedido  = (Spinner) findViewById(R.id.editEstadoPedido);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.estados_pedido, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editEstadoPedido.setAdapter(adapter);
    }
    public void actualizarDetallePedido(View v) {
        DetallePedido detalle = new DetallePedido();
        detalle.setIdTipoPago(Integer.parseInt(editidTipoPago.getText().toString()));
        detalle.setIdProducto(Integer.parseInt(editidProducto.getText().toString()));
        detalle.setIdDetallePedido(Integer.parseInt(editidDetallePedido.getText().toString()));
        detalle.setCantidad(Integer.parseInt(editcantidad.getText().toString()));
        if (editEstadoPedido.getSelectedItem().toString().equals("Recibido")) detalle.setEstadoPedido(1);
        else detalle.setEstadoPedido(0);
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

    }
}