package com.example.crud05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CategoriaEliminarActivity extends AppCompatActivity {

    EditText editIdCategoria;
    ControlBD helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_eliminar);
        editIdCategoria=(EditText)findViewById(R.id.editIdCategoria);
        helper=new ControlBD (this);
    }

    public void eliminarCategoria(View v){
        String regEliminadas;
        Categoria categoria=new Categoria();
        categoria.setIdCategoria(Integer.parseInt(editIdCategoria.getText().toString()));
        helper.abrir();
        regEliminadas=helper.eliminar(categoria);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}