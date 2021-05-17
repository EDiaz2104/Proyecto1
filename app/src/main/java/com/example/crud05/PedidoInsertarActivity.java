package com.example.crud05;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PedidoInsertarActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_pedido_insertar);
        helper = new ControlBD(this);
        editIdPedido = (EditText) findViewById(R.id.editIdPedido);
        editIdLocal = (EditText) findViewById(R.id.editIdLocal);
        editIdCombo = (EditText) findViewById(R.id.editIdCombo);
        editIdUsuario = (EditText) findViewById(R.id.editIdUsuario);
        editIdDetallePedido = (EditText) findViewById(R.id.editIdDetallePedido);
        editFechaPedido = (EditText) findViewById(R.id.editFechaPedido);
    }
    public void insertarPedido(View v) {
        String idpedido=editIdPedido.getText().toString();
        String idlocal=editIdLocal.getText().toString();
        String idcombo=editIdCombo.getText().toString();
        String idusuario=editIdUsuario.getText().toString();
        String iddetallepedido=editIdDetallePedido.getText().toString();
        String fechapedido=editFechaPedido.getText().toString();
        String regInsertados;
        Pedido pedido=new Pedido();
        pedido.setIdPedido(Integer.parseInt(idpedido));
        pedido.setIdLocal(Integer.parseInt(idlocal));
        pedido.setIdCombo(Integer.parseInt(idcombo));
        pedido.setIdUsuario(Integer.parseInt(idusuario));
        pedido.setIdDetallePedido(Integer.parseInt(iddetallepedido));
        pedido.setFechaPedido(fechapedido);
        helper.abrir();
        regInsertados=helper.insertar(pedido);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
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