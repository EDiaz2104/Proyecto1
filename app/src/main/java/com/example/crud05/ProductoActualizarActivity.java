package com.example.crud05;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class ProductoActualizarActivity extends Activity {

    ControlBD helper;
    EditText editidProducto;
    EditText editNombreProducto;
    EditText editidLocal;
    EditText editidProveedor;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_actualizar);
        helper = new ControlBD(this);
        editidProducto = (EditText) findViewById(R.id.editidProducto);
        editNombreProducto = (EditText) findViewById(R.id.editNombreProducto);
        editidLocal =(EditText) findViewById(R.id.editidLocal);
        editidProveedor=(EditText) findViewById(R.id.editidProveedor);
    }
    public void actualizarProducto(View v) {
        Producto p = new Producto();
        p.setIdProducto(editidProducto.getId());
        p.setNombreProducto(editNombreProducto.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(p);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editidProducto.setText("");
        editNombreProducto.setText("");
        editidLocal.setText("");
        editidProveedor.setText("");

    }
}