package com.example.crud05;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class ProductoConsultarActivity extends Activity {
    ControlBD helper;
    EditText editidProducto;
    EditText editNombreProducto;
    EditText editidLocal;
    EditText editidProveedor;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_consultar);
        helper = new ControlBD(this);
        editidProducto = (EditText) findViewById(R.id.editidProducto);
        editNombreProducto = (EditText) findViewById(R.id.editNombreProducto);
        editidLocal=(EditText) findViewById(R.id.editidLocal);
        editidProveedor=(EditText) findViewById(R.id.editidProveedor);

    }
    public void consultarProducto(View v) {
        helper.abrir();
        Producto p = helper.consultarProducto(editidProducto.getText().toString());
        helper.cerrar();
        if(p == null)
            Toast.makeText(this, "Producto con id " +
                    editidProducto.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editNombreProducto.setText(p.getNombreProducto());
           // editidLocal.setText(p.getIdLocal());
            //editidProveedor.setText(p.getIdProveedor());
        }
    }
    public void limpiarTexto(View v){
        editidProducto.setText("");
        editNombreProducto.setText("");
       // editidLocal.setText("");
        //editidProveedor.setText("");

    }
}
