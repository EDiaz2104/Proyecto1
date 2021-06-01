package com.example.crud05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProveedorRestActivity extends AppCompatActivity {
    EditText etDescripcionProveedor, etTelefono;
    Button btnTodosBuscar, btnAgregar;

    RecyclerView rvProveedor;
    List<Proveedor> listaProveedor = new ArrayList<>();

    AdaptadorProveedor adaptadorProveedor;

    Retrofit retrofit;
    ProveedorInterface api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveedor_rest);
        etDescripcionProveedor = findViewById(R.id.etDescripcionProveedor);
        etTelefono = findViewById(R.id.etTelefono);
        btnTodosBuscar = findViewById(R.id.btnTodosBuscar);
        btnAgregar = findViewById(R.id.btnAgregar);
        rvProveedor = findViewById(R.id.rvProveedor);
        rvProveedor.setLayoutManager(new GridLayoutManager(this, 1));
    }

    public void getProveedores(ProveedorInterface api) {
        listaProveedor.clear();
        Call<List<Proveedor>> call = api.obtenerProveedores();
        call.enqueue(new Callback<List<Proveedor>>() {
            @Override
            public void onResponse(Call<List<Proveedor>> call, Response<List<Proveedor>> response) {
                listaProveedor = new ArrayList<Proveedor>(response.body());

                adaptadorProveedor = new AdaptadorProveedor(ProveedorRestActivity.this, listaProveedor);
                rvProveedor.setAdapter(adaptadorProveedor);
            }
            @Override
            public void onFailure(Call<List<Proveedor>> call, Throwable t) {
            }
        });
    }

    public void agregarProveedor(final ProveedorInterface api, String descripcionProveedor, String Telefono) {
        listaProveedor.clear();
        Proveedor proveedor = new Proveedor();
        proveedor.setDescripcionProveedor(descripcionProveedor);
        proveedor.setTelefonoProveedor(Telefono);

        Call<Void> call = api.agregarProveedor(proveedor);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case 400:
                        Toast.makeText(ProveedorRestActivity.this, "Faltaron campos.", Toast.LENGTH_SHORT).show();
                        etDescripcionProveedor.setText("");
                        etTelefono.setText("");
                        break;
                    case 200:
                        Toast.makeText(ProveedorRestActivity.this, "Se inserto correctamente", Toast.LENGTH_SHORT).show();
                        etDescripcionProveedor.setText("");
                        etTelefono.setText("");
                        getProveedores(api);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }



}