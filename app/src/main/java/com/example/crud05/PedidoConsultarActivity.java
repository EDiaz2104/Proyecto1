package com.example.crud05;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PedidoConsultarActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_pedido_consultar);
        helper = new ControlBD(this);
        editIdPedido = (EditText) findViewById(R.id.editIdPedido);
        editIdLocal = (EditText) findViewById(R.id.editIdLocal);
        editIdCombo = (EditText) findViewById(R.id.editIdCombo);
        editIdUsuario = (EditText) findViewById(R.id.editIdUsuario);
        editIdDetallePedido = (EditText) findViewById(R.id.editIdDetallePedido);
        editFechaPedido = (EditText) findViewById(R.id.editFechaPedido);
    }
    public void consultarPedido(View v) {
        helper.abrir();
        Pedido pedido = helper.consultarPedido(editIdPedido.getText().toString());
        helper.cerrar();
        if(pedido == null)
            Toast.makeText(this, "Pedido con Id Pedido " +
                    editIdPedido.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editIdPedido.setText(pedido.getIdPedido());
            editIdLocal.setText(pedido.getIdLocal());
            editIdCombo.setText(pedido.getIdCombo());
            editIdUsuario.setText(pedido.getIdUsuario());
            editIdDetallePedido.setText(pedido.getIdDetallePedido());
            editFechaPedido.setText(pedido.getFechaPedido());
        }
    }
    public void limpiarTexto(View v){
        editIdPedido.setText("");
        editIdLocal.setText("");
        editIdCombo.setText("");
        editIdUsuario.setText("");
        editIdDetallePedido.setText("");
        editFechaPedido.setText("");
    }
}