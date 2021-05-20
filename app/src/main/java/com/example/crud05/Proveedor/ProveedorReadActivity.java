package com.example.crud05.Proveedor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.proto.ProtoOutputStream;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud05.ControlBD;
import com.example.crud05.Modelos.Preferencial;
import com.example.crud05.Modelos.Proveedor;
import com.example.crud05.R;

public class ProveedorReadActivity extends AppCompatActivity {

    EditText edit_idProveedor;
    EditText edit_nombre;
    EditText edit_direccion;
    EditText edit_telefono;
    ControlBD helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveedor_read);
        helper = new ControlBD(this);
        edit_idProveedor = (EditText) findViewById(R.id.edit_idProveedor);
        edit_nombre = (EditText) findViewById(R.id.edit_nombre);
        edit_direccion = (EditText) findViewById(R.id.edit_descripcion);
        edit_telefono = (EditText) findViewById(R.id.edit_telefono);
    }

    public void consultarProveedor(View v){
        helper.abrir();
        Proveedor proveedor = helper.consultarProveedor(edit_idProveedor.getText().toString());
        helper.cerrar();

        if(proveedor == null) Toast.makeText(
                this,
                "Proveedor con id "+ edit_idProveedor.getText().toString() + " no encontrado",
                Toast.LENGTH_LONG).show();
        else{
            edit_idProveedor.setText(proveedor.getIdProveedor());
            edit_nombre.setText(proveedor.getNombreProveedor());
            edit_direccion.setText(proveedor.getDescripcionProveedor());
            edit_telefono.setText(proveedor.getTelefonoProveedor());
        }
    }

    public void limpiarTexto(View v){
        edit_idProveedor.setText("");
        edit_nombre.setText("");
        edit_direccion.setText("");
        edit_telefono.setText("");

    }
}