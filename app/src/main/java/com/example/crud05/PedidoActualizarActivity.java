package com.example.crud05;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PedidoActualizarActivity extends AppCompatActivity {

    ControlBD helper;
    EditText editIdPedido;
    EditText editIdLocal;
    EditText editIdCombo;
    EditText editIdUsuario;
    EditText editIdDetallePedido;
    EditText editFechaPedido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_actualizar);
        helper = new ControlBD(this);
        editIdPedido = (EditText) findViewById(R.id.editIdPedido);
        editIdLocal = (EditText) findViewById(R.id.editIdLocal);
        editIdCombo = (EditText) findViewById(R.id.editIdCombo);
        editIdUsuario = (EditText) findViewById(R.id.editIdUsuario);
        editIdDetallePedido = (EditText) findViewById(R.id.editIdDetallePedido);
        editFechaPedido = (EditText) findViewById(R.id.editFechaPedido);
    }

    public void actualizarPedido(View v) {
        Pedido pedido=new Pedido();
        pedido.setIdPedido(Integer.parseInt(editIdPedido.getText().toString()));
        pedido.setIdLocal(Integer.parseInt(editIdLocal.getText().toString()));
        pedido.setIdCombo(Integer.parseInt(editIdCombo.getText().toString()));
        pedido.setIdUsuario(Integer.parseInt(editIdUsuario.getText().toString()));
        pedido.setIdDetallePedido(Integer.parseInt(editIdDetallePedido.getText().toString()));
        pedido.setFechaPedido(editFechaPedido.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(pedido);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editIdPedido.setText("");
        editIdLocal.setText("");
        editIdCombo.setText("");
        editIdUsuario.setText("");
        editIdDetallePedido.setText("");
        editFechaPedido.setText("");
    }
}