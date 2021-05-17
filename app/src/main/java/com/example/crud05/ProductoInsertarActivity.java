package com.example.crud05;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class ProductoInsertarActivity extends Activity {
    ControlBD helper;
    EditText editidProducto;
    EditText editNombreProducto;
    EditText editidLocal;
    EditText editidProveedor;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_insertar);
        helper = new ControlBD(this);
        editidProducto = (EditText) findViewById(R.id.editidProducto);
        editNombreProducto = (EditText) findViewById(R.id.editNombreProducto);
        editidLocal = (EditText) findViewById(R.id.editidLocal);
        editidProveedor = (EditText) findViewById(R.id.editidProveedor);
    }
    public void insertarProducto(View v) {
        Integer idProducto= Integer.valueOf(editidProducto.getText().toString());
        String NombreProducto=editNombreProducto.getText().toString();
        Integer idLocal= Integer.valueOf(editidLocal.getText().toString());
        Integer idProveedor= Integer.valueOf(editidProveedor.getText().toString());
        String regInsertados;
        Producto pro=new Producto();
        pro.setIdProducto(idProducto);
        pro.setNombreProducto(NombreProducto);
        pro.setIdLocal(String.valueOf(idLocal));
        pro.setIdProveedor(String.valueOf(idProveedor));
        helper.abrir();
        regInsertados=helper.insertar(pro);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editidProducto.setText("");
        editNombreProducto.setText("");
        editidLocal.setText("");
        editidProveedor.setText("");
    }

}

