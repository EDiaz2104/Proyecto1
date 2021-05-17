package com.example.crud05;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PedidoEliminarActivity extends AppCompatActivity {
    EditText editIdPedido;
    ControlBD helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_eliminar);
        editIdPedido=(EditText)findViewById(R.id.editIdPedido);
        helper=new ControlBD (this);
    }

    public void eliminarPedido(View v){
        String regEliminadas;
        Pedido pedido=new Pedido();
        pedido.setIdPedido(Integer.parseInt(editIdPedido.getText().toString()));
        helper.abrir();
        regEliminadas=helper.eliminar(pedido);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}