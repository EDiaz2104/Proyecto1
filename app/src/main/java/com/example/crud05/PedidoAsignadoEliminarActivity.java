package com.example.crud05;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PedidoAsignadoEliminarActivity extends AppCompatActivity {

    EditText editIdPedidoAsignado;
    ControlBD helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_asignado_eliminar);
        editIdPedidoAsignado=(EditText)findViewById(R.id.editIdPedidoAsignado);
        helper=new ControlBD (this);
    }

    public void eliminarPedidoAsignado(View v){
        String regEliminadas;
        PedidoAsignado pedidoasignado=new PedidoAsignado();
        pedidoasignado.setIdPedidoAsignado(Integer.parseInt(editIdPedidoAsignado.getText().toString()));
        helper.abrir();
        regEliminadas=helper.eliminar(pedidoasignado);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}