package com.example.crud05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CategoriaInsertarActivity extends AppCompatActivity {

    ControlBD helper;
    EditText editidCategoria;
    EditText editidProducto;
    EditText editNombreCategoria;
    EditText editDescripcionCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_insertar);
        helper = new ControlBD(this);
        editidCategoria = (EditText) findViewById(R.id.editidCategoria);
        editidProducto = (EditText) findViewById(R.id.editidProducto);
        editNombreCategoria = (EditText) findViewById(R.id.editNombreCategoria);
        editDescripcionCategoria = (EditText) findViewById(R.id.editDescripcionCategoria);

    }
    public void insertarCategoria(View v) {
        String idcategoria=editidCategoria.getText().toString();
        String idproducto=editidProducto.getText().toString();
        String nombrecategoria=editNombreCategoria.getText().toString();
        String descripcioncategoria=editDescripcionCategoria.getText().toString();

        String regInsertados;
        Categoria categoria=new Categoria();
        categoria.setIdCategoria(Integer.parseInt(idcategoria));
        categoria.setIdProducto(Integer.parseInt(idproducto));
        categoria.setNombreCategoria(nombrecategoria);
        categoria.setDescripcionCategoria(descripcioncategoria);

        helper.abrir();
        regInsertados=helper.insertar(categoria);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editidCategoria.setText("");
        editidProducto.setText("");
        editNombreCategoria.setText("");
        editDescripcionCategoria.setText("");

    }
}