package com.example.crud05;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProductoEliminarActivity extends Activity {

    EditText editidProducto;
    EditText editNombreProducto;
    EditText editidLocal;
    EditText editidProveedor;
    ControlBD controlhelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_eliminar);
        controlhelper=new ControlBD (this);
        editidProducto = (EditText) findViewById(R.id.editidProducto);
        editNombreProducto = (EditText) findViewById(R.id.editNombreProducto);
        editidLocal =(EditText) findViewById(R.id.editidLocal);
        editidProveedor=(EditText) findViewById(R.id.editidProveedor);

    }

    public void eliminarProducto(View v){
        String regEliminadas;
        Producto pro=new Producto();
        pro.setIdProducto(editidProducto.getId());
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(pro);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editidProducto.setText("");
        editNombreProducto.setText("");
        editidLocal.setText("");
        editidProveedor.setText("");
    }
}