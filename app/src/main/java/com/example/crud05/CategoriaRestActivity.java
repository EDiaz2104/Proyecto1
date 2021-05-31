package com.example.crud05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoriaRestActivity extends AppCompatActivity {
    EditText etIdBuscar, etId, etNombreCategoria, etDescripcion;
    Button btnIdBuscar, btnEliminar, btnTodosBuscar, btnAgregar, btnEditar;

    RecyclerView rvCategoria;
    List<Categoria> listaCategorias = new ArrayList<>();

    AdaptadorCategoria adaptadorCategoria;

    Retrofit retrofit;
    CategoriaInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_rest);
        etIdBuscar = findViewById(R.id.etIdBuscar);
        etId = findViewById(R.id.etId);
        etNombreCategoria = findViewById(R.id.etNombreCategoria);
        etDescripcion = findViewById(R.id.etDescripcion);
        btnIdBuscar = findViewById(R.id.btnIdBuscar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnTodosBuscar = findViewById(R.id.btnTodosBuscar);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnEditar = findViewById(R.id.btnEditar);
        rvCategoria = findViewById(R.id.rvCategoria);
        rvCategoria.setLayoutManager(new GridLayoutManager(this, 1));

        retrofit = new AdaptadorRetrofit().getAdapatador();
        api = retrofit.create(CategoriaInterface.class);
        btnIdBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etIdBuscar.getText().toString().equals("")) {
                    Toast.makeText(CategoriaRestActivity.this, "Inserta un ID para buscar", Toast.LENGTH_SHORT).show();
                } else {
                    getCategoria(api, etIdBuscar.getText().toString());
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etIdBuscar.getText().toString().equals("")) {
                    Toast.makeText(CategoriaRestActivity.this, "Inserta un ID para eliminar", Toast.LENGTH_SHORT).show();
                } else {
                    eliminarCategoria(api, etIdBuscar.getText().toString());
                }
            }
        });

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

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etId.getText().toString().equals("") || etNombreCategoria.getText().toString().equals("") || etDescripcion.getText().toString().equals("")) {
                    Toast.makeText(CategoriaRestActivity.this, "Se deben de llenar los campos", Toast.LENGTH_SHORT).show();
                } else {
                    editarCategoria(api, etId.getText().toString(), etNombreCategoria.getText().toString(), etDescripcion.getText().toString());
                }
            }
        });
    }
    public void getCategoria(final CategoriaInterface api, String id) {
        listaCategorias.clear();
        Call<Categoria> call = api.obtenerCategoria(id);

        call.enqueue(new Callback<Categoria>() {
            @Override
            public void onResponse(Call<Categoria> call, Response<Categoria> response) {
                switch (response.code()) {
                    case 200:
                        listaCategorias.add(response.body());

                        etIdBuscar.setText("");

                        adaptadorCategoria = new AdaptadorCategoria(CategoriaRestActivity.this, listaCategorias);
                        rvCategoria.setAdapter(adaptadorCategoria);

                        break;
                    case 204:
                        Toast.makeText(CategoriaRestActivity.this, "No existe ese registro", Toast.LENGTH_SHORT).show();

                        etIdBuscar.setText("");

                        getCategorias(api);
                        break;

                }
            }

            @Override
            public void onFailure(Call<Categoria> call, Throwable t) {

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

    public void eliminarCategoria(final CategoriaInterface api, String id) {
        listaCategorias.clear();

        Call<Void> call = api.eliminarCategoria(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case 200:
                        Toast.makeText(CategoriaRestActivity.this, "Se elimino correctamente", Toast.LENGTH_SHORT).show();
                        etIdBuscar.setText("");
                        getCategorias(api);
                        break;
                    case 204:
                        Toast.makeText(CategoriaRestActivity.this, "No se elimino el registro", Toast.LENGTH_SHORT).show();
                        etIdBuscar.setText("");
                        break;

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

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
                        etNombreCategoria.setText("");
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

    public void editarCategoria(final CategoriaInterface api, String id, String nombreCategoria, String descripcion) {
        listaCategorias.clear();
        Categoria categoria = new Categoria();
        categoria.setId(Integer.parseInt(id));
        categoria.setNombreCategoria(nombreCategoria);
        categoria.setDescripcionCategoria(descripcion);

        Call<Void> call = api.editarCategoria(categoria);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case 400:
                        Toast.makeText(CategoriaRestActivity.this, "No se puede editar.", Toast.LENGTH_SHORT).show();
                        etId.setText("");
                        etNombreCategoria.setText("");
                        etDescripcion.setText("");
                        break;
                    case 200:
                        Toast.makeText(CategoriaRestActivity.this, "Se edito correctamente", Toast.LENGTH_SHORT).show();
                        etId.setText("");
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