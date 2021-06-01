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
    EditText  etDescripcion;
    Button btnTodosBuscar, btnAgregar;

    RecyclerView rvUbicacion;
    List<Ubicacion> listaUbicacion = new ArrayList<>();

    AdaptadorUbicacion adaptadorUbicacion;

    Retrofit retrofit;
    UbicacionInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion_rest);
        etDescripcion = findViewById(R.id.etDescripcion);
        btnTodosBuscar = findViewById(R.id.btnTodosBuscar);
        btnAgregar = findViewById(R.id.btnAgregar);
        rvUbicacion = findViewById(R.id.rvUbicacion);
        rvUbicacion.setLayoutManager(new GridLayoutManager(this, 1));

        retrofit = new AdaptadorRetrofit().getAdapatador();
        api = retrofit.create(UbicacionInterface.class);

        btnTodosBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUbicaciones(api);
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( etDescripcion.getText().toString().equals("")) {
                    Toast.makeText(UbicacionRestActivity.this, "Se deben de llenar los campos", Toast.LENGTH_SHORT).show();
                } else {
                    agregarUbicacion(api,  etDescripcion.getText().toString());
                }
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



    public void agregarUbicacion(final UbicacionInterface api, String descripcionubicacion) {
        listaUbicacion.clear();
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setDescripcionubicacion(descripcionubicacion);

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


}