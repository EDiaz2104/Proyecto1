package com.example.crud05.Repartidor;

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

public class RepartidorRestActivity extends AppCompatActivity {

    EditText etIdBuscar,etId, etIdLocal, etNombre,etCarnet;
    Button btnIdBuscar, btnEliminar, btnTodosBuscar, btnAgregar, btnEditar;

    RecyclerView rvRepartidor;
    List<Repartidor> listaRepartidor = new ArrayList<>();

    Retrofit retrofit;

    RepartidorInterface api;

    AdapatadorRepartidor adaptadorRepartidor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repartidor_rest);

        etIdBuscar = findViewById(R.id.etIdBuscar);
        etId = findViewById(R.id.etId);
        etIdLocal=findViewById(R.id.etIdLocal);
        etNombre = findViewById(R.id.etNombre);
        etCarnet=findViewById(R.id.etCarnet);
        btnIdBuscar = findViewById(R.id.btnIdBuscar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnTodosBuscar = findViewById(R.id.btnTodosBuscar);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnEditar = findViewById(R.id.btnEditar);
        rvRepartidor = findViewById(R.id.rvRepartidor);
        rvRepartidor.setLayoutManager(new GridLayoutManager(this, 1));

        retrofit = new AdaptadorRetrofit().getAdapatador();
        api = retrofit.create(RepartidorInterface.class);


        btnIdBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etIdBuscar.getText().toString().equals("")) {
                    Toast.makeText(RepartidorRestActivity.this, "Inserta un ID para buscar", Toast.LENGTH_SHORT).show();
                } else {
                    getRepartidor(api, etIdBuscar.getText().toString());
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etIdBuscar.getText().toString().equals("")) {
                    Toast.makeText(RepartidorRestActivity.this, "Inserta un ID para eliminar", Toast.LENGTH_SHORT).show();
                } else {
                    eliminarRepartidor(api, etIdBuscar.getText().toString());
                }
            }
        });

        btnTodosBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRepartidores(api);
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etNombre.getText().toString().equals("")) {
                    Toast.makeText(RepartidorRestActivity.this, "Se deben de llenar los campos", Toast.LENGTH_SHORT).show();
                } else {
                    agregarRepartidor(api,etIdLocal.getText().toString() ,etNombre.getText().toString(),etCarnet.getText().toString());
                                    }
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etId.getText().toString().equals("") || etNombre.getText().toString().equals("") ) {
                    Toast.makeText(RepartidorRestActivity.this, "Se deben de llenar los campos", Toast.LENGTH_SHORT).show();
                } else {
                    editarRepartidor(api, etId.getText().toString(),etIdLocal.getText().toString(), etNombre.getText().toString(),etCarnet.getText().toString());
                }
            }
        });
    }

    public void getRepartidor(final RepartidorInterface api, String id) {
        listaRepartidor.clear();
        Call<Repartidor> call = api.obtenerRepartidor(id);

        call.enqueue(new Callback<Repartidor>() {
            @Override
            public void onResponse(Call<Repartidor> call, Response<Repartidor> response) {
                switch (response.code()) {
                    case 200:
                        listaRepartidor.add(response.body());

                        etIdBuscar.setText("");

                        adaptadorRepartidor = new AdapatadorRepartidor(RepartidorRestActivity.this, listaRepartidor);
                        rvRepartidor.setAdapter(adaptadorRepartidor);

                        break;
                    case 204:
                        Toast.makeText(RepartidorRestActivity.this, "No existe ese registro", Toast.LENGTH_SHORT).show();

                        etIdBuscar.setText("");

                        getRepartidores(api);
                        break;

                }
            }

            @Override
            public void onFailure(Call<Repartidor> call, Throwable t) {

            }
        });
    }

    public void getRepartidores(RepartidorInterface api) {
        listaRepartidor.clear();
        Call<List<Repartidor>> call = api.obtenerRepartidores();

        call.enqueue(new Callback<List<Repartidor>>() {
            @Override
            public void onResponse(Call<List<Repartidor>> call, Response<List<Repartidor>> response) {
                listaRepartidor = new ArrayList<>(response.body());

                adaptadorRepartidor = new AdapatadorRepartidor(RepartidorRestActivity.this, listaRepartidor);
                rvRepartidor.setAdapter(adaptadorRepartidor);

            }

            @Override
            public void onFailure(Call<List<Repartidor>> call, Throwable t) {

            }
        });
    }

    public void eliminarRepartidor(final RepartidorInterface api, String id) {
        listaRepartidor.clear();

        Call<Void> call = api.eliminarRepartidor(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case 204:
                        Toast.makeText(RepartidorRestActivity.this, "Se elimino correctamente", Toast.LENGTH_SHORT).show();
                        etIdBuscar.setText("");
                        getRepartidores(api);
                        break;
                    case 200:
                        Toast.makeText(RepartidorRestActivity.this, "No se elimino el registro", Toast.LENGTH_SHORT).show();
                        etIdBuscar.setText("");
                        break;

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void agregarRepartidor(final RepartidorInterface api,String idlocal, String nombreRepartidor,String carnetRepartidor) {
        listaRepartidor.clear();
        Repartidor repartidor = new Repartidor();
        repartidor.setIdlocal(idlocal);
        repartidor.setNombreRepartidor(nombreRepartidor);
        repartidor.setCarneRepartidor(carnetRepartidor);

        Call<Void> call = api.agregarRepartidor(repartidor);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case 400:
                        Toast.makeText(RepartidorRestActivity.this, "Faltaron campos.", Toast.LENGTH_SHORT).show();
                        etIdLocal.setText("");
                        etNombre.setText("");
                        etCarnet.setText("");

                        break;
                    case 200:
                        Toast.makeText(RepartidorRestActivity.this, "Se inserto correctamente", Toast.LENGTH_SHORT).show();
                        etIdLocal.setText("");
                        etNombre.setText("");
                        etCarnet.setText("");
                        getRepartidores(api);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void editarRepartidor(final RepartidorInterface api,String id,String idlocal, String nombreRepartidor,String carnetRepartidor) {
        listaRepartidor.clear();
        Repartidor repartidor = new Repartidor();
        repartidor.setId(id);
        repartidor.setIdlocal(idlocal);
        repartidor.setNombreRepartidor(nombreRepartidor);
        repartidor.setCarneRepartidor(carnetRepartidor);


        Call<Void> call = api.editarRepartidor(repartidor,id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case 400:
                        Toast.makeText(RepartidorRestActivity.this, "No se puede editar.", Toast.LENGTH_SHORT).show();
                        etId.setText("");
                        etIdLocal.setText("");
                        etNombre.setText("");
                        etCarnet.setText("");
                        break;
                    case 200:
                        Toast.makeText(RepartidorRestActivity.this, "Se edito correctamente", Toast.LENGTH_SHORT).show();
                        etId.setText("");
                        etIdLocal.setText("");
                        etNombre.setText("");
                        etCarnet.setText("");
                        getRepartidores(api);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

}