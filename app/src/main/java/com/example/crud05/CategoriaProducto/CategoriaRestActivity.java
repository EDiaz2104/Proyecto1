package com.example.crud05.CategoriaProducto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.crud05.AdaptadorRetrofit;
import com.example.crud05.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoriaRestActivity extends AppCompatActivity {
    EditText etNombreCategoria, etDescripcion;
    Button btnTodosBuscar, btnAgregar;

    RecyclerView rvCategoria;
    List<Categoria> listaCategorias = new ArrayList<>();

    AdaptadorCategoria adaptadorCategoria;

    Retrofit retrofit;
    CategoriaInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_rest);
        etNombreCategoria = findViewById(R.id.etNombreCategoria);
        etDescripcion = findViewById(R.id.etDescripcion);
        btnTodosBuscar = findViewById(R.id.btnTodosBuscar);
        btnAgregar = findViewById(R.id.btnAgregar);
        rvCategoria = findViewById(R.id.rvCategoria);
        rvCategoria.setLayoutManager(new GridLayoutManager(this, 1));

        retrofit = new AdaptadorRetrofit().getAdapatador();
        api = retrofit.create(CategoriaInterface.class);

        btnTodosBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCategorias(api);
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etNombreCategoria.getText().toString().equals("") || etDescripcion.getText().toString().equals("")) {
                    Toast.makeText(CategoriaRestActivity.this, "Se deben de llenar los campos", Toast.LENGTH_SHORT).show();
                } else {
                    agregarCategoria(api, etNombreCategoria.getText().toString(), etDescripcion.getText().toString());
                }
            }
        });


    }



    public void getCategorias(CategoriaInterface api) {
        listaCategorias.clear();
        Call<List<Categoria>> call = api.obtenerCategorias();

        call.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                listaCategorias = new ArrayList<Categoria>(response.body());

                adaptadorCategoria = new AdaptadorCategoria(CategoriaRestActivity.this, listaCategorias);
                rvCategoria.setAdapter(adaptadorCategoria);


            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {

            }
        });
    }



    public void agregarCategoria(final CategoriaInterface api, String nombreCategoria, String descripcion) {
        listaCategorias.clear();
        Categoria categoria = new Categoria();
        categoria.setNombreCategoria(nombreCategoria);
        categoria.setDescripcionCategoria(descripcion);

        Call<Void> call = api.agregarCategoria(categoria);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case 400:
                        Toast.makeText(CategoriaRestActivity.this, "Faltaron campos.", Toast.LENGTH_SHORT).show();
                        etNombreCategoria.setText("");
                        etDescripcion.setText("");
                        break;
                    case 200:
                        Toast.makeText(CategoriaRestActivity.this, "Se inserto correctamente", Toast.LENGTH_SHORT).show();
                        etNombreCategoria.setText("");
                        etDescripcion.setText("");
                        getCategorias(api);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }


}