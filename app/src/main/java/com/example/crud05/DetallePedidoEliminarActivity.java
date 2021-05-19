package com.example.crud05;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class DetallePedidoEliminarActivity extends Activity {
    EditText editidTipoPago;
    EditText editidProducto;
    EditText editidDetallePedido;
    EditText editcantidad;
    EditText editEstadoPedido;
    ControlBD controlhelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido_eliminar);
        controlhelper=new ControlBD(this);
        editidTipoPago=(EditText)findViewById(R.id.editidTipoPago);
        editidProducto=(EditText)findViewById(R.id.editidProducto);
        editidDetallePedido=(EditText)findViewById(R.id.editidDetallePedido);
        editcantidad=(EditText)findViewById(R.id.editcantidad);
        editEstadoPedido=(EditText)findViewById(R.id.editEstadoPedido);

    }

    public void eliminarDetallePedido(View v){
        String regEliminadas;
        DetallePedido detalle = new DetallePedido();
        detalle.setIdTipoPago(Integer.parseInt(editidTipoPago.getText().toString()));
        detalle.setIdProducto(Integer.parseInt(editidProducto.getText().toString()));
        detalle.setIdDetallePedido(Integer.parseInt(editidDetallePedido.getText().toString()));
        detalle.setCantidad(Integer.parseInt(editcantidad.getText().toString()));
        detalle.setEstadoPedido(Integer.parseInt(editEstadoPedido.getText().toString()));
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(detalle);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
    public void limpiar(View v){
        editidTipoPago.setText("");
        editidProducto.setText("");
        editidDetallePedido.setText("");
        editcantidad.setText("");
        editEstadoPedido.setText("");
    }
}


