package com.example.crud05.Proveedor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.crud05.ControlBD;
import com.example.crud05.Modelos.Proveedor;
import com.example.crud05.R;

public class ProveedorCreateActivity extends AppCompatActivity {

    EditText edit_nombre;
    EditText edit_descripcion;
    EditText edit_telefono;
    ControlBD helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveedor_create);
        helper = new ControlBD(this);
        edit_nombre = (EditText) findViewById(R.id.edit_nombre);
        edit_descripcion = (EditText) findViewById(R.id.edit_descripcion);
        edit_telefono = (EditText) findViewById(R.id.edit_telefono);

    }

    public void insertarProveedor(View v){
        Proveedor proveedor = new Proveedor();
        proveedor.setNombreProveedor(edit_nombre.getText().toString());
        proveedor.setDescripcionProveedor(edit_descripcion.getText().toString());
        proveedor.setTelefonoProveedor(edit_telefono.getText().toString());
        helper.abrir();
        helper.insertar(proveedor);
        helper.cerrar();
    }

    public void limpiarTexto(View v){
        edit_nombre.setText("");
        edit_descripcion.setText("");
        edit_telefono.setText("");
    }
}