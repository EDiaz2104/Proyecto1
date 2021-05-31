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

public class UbicacionRestActivity extends AppCompatActivity {
    EditText etIdBuscar, etId, etDescripcion;
    Button btnIdBuscar, btnEliminar, btnTodosBuscar, btnAgregar, btnEditar;

    RecyclerView rvUbicacion;
    List<Ubicacion> listaUbicacion = new ArrayList<>();

    AdaptadorUbicacion adaptadorUbicacion;

    Retrofit retrofit;
    UbicacionInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion_rest);
        etIdBuscar = findViewById(R.id.etIdBuscar);
        etId = findViewById(R.id.etId);
        etDescripcion = findViewById(R.id.etDescripcion);
        btnIdBuscar = findViewById(R.id.btnIdBuscar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnTodosBuscar = findViewById(R.id.btnTodosBuscar);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnEditar = findViewById(R.id.btnEditar);
        rvUbicacion = findViewById(R.id.rvUbicacion);
        rvUbicacion.setLayoutManager(new GridLayoutManager(this, 1));

        retrofit = new AdaptadorRetrofit().getAdapatador();
        api = retrofit.create(UbicacionInterface.class);
        btnIdBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etIdBuscar.getText().toString().equals("")) {
                    Toast.makeText(UbicacionRestActivity.this, "Inserta un ID para buscar", Toast.LENGTH_SHORT).show();
                } else {
                    getUbicacion(api, etIdBuscar.getText().toString());
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etIdBuscar.getText().toString().equals("")) {
                    Toast.makeText(UbicacionRestActivity.this, "Inserta un ID para eliminar", Toast.LENGTH_SHORT).show();
                } else {
                    eliminarUbicacion(api, etIdBuscar.getText().toString());
                }
            }
        });

        btnTodosBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUbicaciones(api);
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etDescripcion.getText().toString().equals("")) {
                    Toast.makeText(UbicacionRestActivity.this, "Se deben de llenar los campos", Toast.LENGTH_SHORT).show();
                } else {
                    agregarUbicacion(api, etDescripcion.getText().toString());
                }
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etId.getText().toString().equals("") || etDescripcion.getText().toString().equals("")) {
                    Toast.makeText(UbicacionRestActivity.this, "Se deben de llenar los campos", Toast.LENGTH_SHORT).show();
                } else {
                    editarUbicacion(api, etId.getText().toString(), etDescripcion.getText().toString());
                }
            }
        });
    }
    public void getUbicacion(final UbicacionInterface api, String id) {
        listaUbicacion.clear();
        Call<Ubicacion> call = api.obtenerUbicacion(id);

        call.enqueue(new Callback<Ubicacion>() {
            @Override
            public void onResponse(Call<Ubicacion> call, Response<Ubicacion> response) {
                switch (response.code()) {
                    case 200:
                        listaUbicacion.add(response.body());

                        etIdBuscar.setText("");

                        adaptadorUbicacion = new AdaptadorUbicacion(UbicacionRestActivity.this, listaUbicacion);
                        rvUbicacion.setAdapter(adaptadorUbicacion);

                        break;
                    case 204:
                        Toast.makeText(UbicacionRestActivity.this, "No existe ese registro", Toast.LENGTH_SHORT).show();

                        etIdBuscar.setText("");

                        getUbicaciones(api);
                        break;

                }
            }

            @Override
            public void onFailure(Call<Ubicacion> call, Throwable t) {

            }
        });
    }

    public void getUbicaciones(UbicacionInterface api) {
        listaUbicacion.clear();
        Call<List<Ubicacion>> call = api.obtenerUbicaciones();

        call.enqueue(new Callback<List<Ubicacion>>() {
            @Override
            public void onResponse(Call<List<Ubicacion>> call, Response<List<Ubicacion>> response) {
                listaUbicacion = new ArrayList<Ubicacion>(response.body());

                adaptadorUbicacion = new AdaptadorUbicacion(UbicacionRestActivity.this, listaUbicacion);
                rvUbicacion.setAdapter(adaptadorUbicacion);


            }

            @Override
            public void onFailure(Call<List<Ubicacion>> call, Throwable t) {

            }
        });
    }

    public void eliminarUbicacion(final UbicacionInterface api, String id) {
        listaUbicacion.clear();

        Call<Void> call = api.eliminarUbicacion(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case 200:
                        Toast.makeText(UbicacionRestActivity.this, "Se elimino correctamente", Toast.LENGTH_SHORT).show();
                        etIdBuscar.setText("");
                        getUbicaciones(api);
                        break;
                    case 204:
                        Toast.makeText(UbicacionRestActivity.this, "No se elimino el registro", Toast.LENGTH_SHORT).show();
                        etIdBuscar.setText("");
                        break;

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void agregarUbicacion(final UbicacionInterface api, String descripcion) {
        listaUbicacion.clear();
        Ubicacion ubicacion= new Ubicacion();
        ubicacion.setDescripcionUbicacion(descripcion);

        Call<Void> call = api.agregarUbicacion(ubicacion);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case 400:
                        Toast.makeText(UbicacionRestActivity.this, "Faltaron campos.", Toast.LENGTH_SHORT).show();
                        etDescripcion.setText("");
                        break;
                    case 200:
                        Toast.makeText(UbicacionRestActivity.this, "Se inserto correctamente", Toast.LENGTH_SHORT).show();

                        etDescripcion.setText("");
                        getUbicaciones(api);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void editarUbicacion(final UbicacionInterface api, String id, String descripcion) {
        listaUbicacion.clear();
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setId(Integer.parseInt(id));
        ubicacion.setDescripcionUbicacion(descripcion);

        Call<Void> call = api.editarUbicacion(ubicacion);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case 400:
                        Toast.makeText(UbicacionRestActivity.this, "No se puede editar.", Toast.LENGTH_SHORT).show();
                        etId.setText("");
                        etDescripcion.setText("");
                        break;
                    case 200:
                        Toast.makeText(UbicacionRestActivity.this, "Se edito correctamente", Toast.LENGTH_SHORT).show();
                        etId.setText("");
                        etDescripcion.setText("");
                        getUbicaciones(api);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

}
