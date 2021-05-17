package com.example.crud05;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RepartidorEliminarActivity extends AppCompatActivity {
    EditText editIdRepartidor;
    ControlBD helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repartidor_eliminar);
        editIdRepartidor=(EditText)findViewById(R.id.editIdRepartidor);
        helper=new ControlBD (this);
    }

    public void eliminarPedido(View v){
        String regEliminadas;
        Repartidor repartidor=new Repartidor();
        repartidor.setIdRepartidor(Integer.parseInt(editIdRepartidor.getText().toString()));
        helper.abrir();
        regEliminadas=helper.eliminar(repartidor);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}