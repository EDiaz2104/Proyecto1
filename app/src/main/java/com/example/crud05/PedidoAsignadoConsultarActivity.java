package com.example.crud05;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PedidoAsignadoConsultarActivity extends AppCompatActivity {

    ControlBD helper;
    EditText editIdPedidoAsignado;
    EditText editIdPedido;
    EditText editIdRepartidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_asignado_consultar);
        helper = new ControlBD(this);
        editIdPedidoAsignado = (EditText) findViewById(R.id.editIdPedidoAsignado);
        editIdPedido = (EditText) findViewById(R.id.editIdPedido);
        editIdRepartidor = (EditText) findViewById(R.id.editIdRepartidor);

    }
    public void consultarRepartidor(View v) {
        helper.abrir();
        PedidoAsignado pedidoasignado = helper.consultarPedidoAsignado(editIdPedidoAsignado.getText().toString());
        helper.cerrar();
        if(pedidoasignado == null)
            Toast.makeText(this, "Pedido asignado con Id Pedido Asignado " +
                    editIdPedidoAsignado.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editIdPedidoAsignado.setText(pedidoasignado.getIdPedidoAsignado());
            editIdPedido.setText(pedidoasignado.getIdPedido());
            editIdRepartidor.setText(pedidoasignado.getIdRepartidor());           ;

        }
    }
    public void limpiarTexto(View v) {
        editIdPedidoAsignado.setText("");
        editIdPedido.setText("");
        editIdRepartidor.setText("");
    }
}