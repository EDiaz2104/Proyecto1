package com.example.crud05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CategoriaConsultarActivity extends AppCompatActivity {

    ControlBD helper;
    EditText editidCategoria;
    EditText editidProducto;
    EditText editNombreCategoria;
    EditText editDescripcionCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_consultar);
        helper = new ControlBD(this);
        editidCategoria = (EditText) findViewById(R.id.editidCategoria);
        editidProducto = (EditText) findViewById(R.id.editidProducto);
        editNombreCategoria = (EditText) findViewById(R.id.editNombreCategoria);
        editDescripcionCategoria = (EditText) findViewById(R.id.editDescripcionCategoria);

    }
    public void consultarCategoria(View v) {
        helper.abrir();
        Categoria categoria = helper.consultarCategoria(editidCategoria.getText().toString());
        helper.cerrar();
        if(categoria == null)
            Toast.makeText(this, "Categoria con Id Categoria " +
                    editidCategoria.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editidCategoria.setText(categoria.getIdCategoria());
            editidProducto.setText(categoria.getIdProducto());
            editNombreCategoria.setText(categoria.getNombreCategoria());
            editDescripcionCategoria.setText(categoria.getDescripcionCategoria());

        }
    }
    public void limpiarTexto(View v) {
        editidCategoria.setText("");
        editidProducto.setText("");
        editNombreCategoria.setText("");
        editDescripcionCategoria.setText("");

    }
}