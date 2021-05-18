package com.example.crud05;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PedidoAsignadoActualizarActivity extends AppCompatActivity {

    ControlBD helper;
    EditText editIdPedidoAsignado;
    EditText editIdPedido;
    EditText editIdRepartidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_asignado_actualizar);
        helper = new ControlBD(this);
        editIdPedidoAsignado = (EditText) findViewById(R.id.editIdPedidoAsignado);
        editIdPedido = (EditText) findViewById(R.id.editIdPedido);
        editIdRepartidor = (EditText) findViewById(R.id.editIdRepartidor);

    }
    public void actualizarPedidoAsignado(View v) {
        String idpedidoasignado=editIdPedidoAsignado.getText().toString();
        String idpedido=editIdPedido.getText().toString();
        String idrepartidor=editIdRepartidor.getText().toString();


        String regInsertados;
        PedidoAsignado pedidoasignado=new PedidoAsignado();
        pedidoasignado.setIdPedidoAsignado(Integer.parseInt(idpedidoasignado));
        pedidoasignado.setIdPedido(Integer.parseInt(idpedido));
        pedidoasignado.setIdRepartidor(Integer.parseInt(idrepartidor));


        helper.abrir();
        regInsertados=helper.actualizar(pedidoasignado);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editIdPedidoAsignado.setText("");
        editIdPedido.setText("");
        editIdRepartidor.setText("");
    }
}