package com.example.crud05.Proveedor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud05.ControlBD;
import com.example.crud05.Modelos.Proveedor;
import com.example.crud05.R;
import com.example.crud05.Repartidor;

public class ProveedorDeleteActivity extends AppCompatActivity {
    EditText edit_idProveedor;
    ControlBD helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveedor_delete);
        helper = new ControlBD(this);
        edit_idProveedor = (EditText) findViewById(R.id.edit_idProveedor);

    }

    public void eliminarProveedor(View v){
        String regEliminadas;
        Proveedor proveedor=new Proveedor();
        proveedor.setIdProveedor(Integer.parseInt(edit_idProveedor.getText().toString()));
        helper.abrir();
        regEliminadas=helper.eliminar(proveedor);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v){
     edit_idProveedor.setText("");
    }
}