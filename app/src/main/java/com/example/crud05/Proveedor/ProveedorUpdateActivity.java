package com.example.crud05.Proveedor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud05.ControlBD;
import com.example.crud05.Modelos.Proveedor;
import com.example.crud05.R;

public class ProveedorUpdateActivity extends AppCompatActivity {
    EditText edit_idProveedor;
    EditText edit_nombre;
    EditText edit_descripcion;
    EditText edit_telefono;
    ControlBD helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveedor_update);

        edit_idProveedor = (EditText) findViewById(R.id.edit_idProveedor);
        edit_nombre = (EditText) findViewById(R.id.edit_nombre);
        edit_descripcion = (EditText) findViewById(R.id.edit_descripcion);
        edit_telefono = (EditText) findViewById(R.id.edit_telefono);
        helper = new ControlBD(this);

    }

    public void actualizarProveedor(View v){
        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(Integer.parseInt(edit_idProveedor.getText().toString()));
        proveedor.setNombreProveedor(edit_nombre.getText().toString());
        proveedor.setDescripcionProveedor(edit_descripcion.getText().toString());
        proveedor.setTelefonoProveedor(edit_telefono.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(proveedor);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        edit_idProveedor.setText("");
        edit_nombre.setText("");
        edit_descripcion.setText("");
        edit_telefono.setText("");
    }
}