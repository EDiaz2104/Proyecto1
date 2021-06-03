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

public class TipopagoRestActivity extends AppCompatActivity {

    EditText edit_IdBuscar, edit_idtipopago ,edit_tipopago;
    Button btnGuardar, btnBuscar, btnBuscarTodos, btnEliminar, btnEditar;

    RecyclerView rvTipopago;
    List<TipoPagoAPI> listaTipopagos = new ArrayList<>();

    AdaptadorTipopago adaptadorTipopago;

    Retrofit retrofit;
    TipopagoInterface api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipopago_rest);
        getSupportActionBar().hide();
        edit_IdBuscar = (EditText) findViewById(R.id.edt_IdBuscarTP);
        edit_idtipopago = (EditText) findViewById(R.id.edit_idtipoTP);
        edit_tipopago = (EditText) findViewById(R.id.edit_tipoPagoTP);
        btnGuardar = (Button) findViewById(R.id.bt_guardarTP);
        btnBuscar = (Button) findViewById(R.id.bt_buscarTP);
        btnBuscarTodos = (Button) findViewById(R.id.bt_buscarTodosTP);
        btnEliminar = (Button) findViewById(R.id.bt_eliminarTP);
        btnEditar = (Button) findViewById(R.id.bt_actualizarTP);
        rvTipopago = findViewById(R.id.rvTipopago);
        rvTipopago.setLayoutManager(new GridLayoutManager(this, 1));

        retrofit = new AdaptadorRetrofit().getAdapatador();
        api = retrofit.create(TipopagoInterface.class);

        getTipopagos(api);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_IdBuscar.getText().toString().equals("")){
                    Toast.makeText(TipopagoRestActivity.this, "Inserte un ID para busacar", Toast.LENGTH_SHORT).show();
                }else{
                    getTipopago(api, edit_IdBuscar.getText().toString());
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_IdBuscar.getText().toString().equals("")){
                    Toast.makeText(TipopagoRestActivity.this, "Inserte un ID para busacar", Toast.LENGTH_SHORT).show();
                }else{
                    eliminarTipopagos(api, edit_IdBuscar.getText().toString());
                }
            }
        });

        btnBuscarTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTipopagos(api);
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_tipopago.getText().toString().equals("")){
                    Toast.makeText(TipopagoRestActivity.this, "Se deben llenar los campos", Toast.LENGTH_SHORT).show();
                }else{
                    agregarTipopagos(api, edit_tipopago.getText().toString());
                }
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_tipopago.getText().toString().equals("")){
                    Toast.makeText(TipopagoRestActivity.this, "Se deben llenar los campos", Toast.LENGTH_SHORT).show();
                }else{
                    editarTipopagos(api, edit_idtipopago.getText().toString(), edit_tipopago.getText().toString());
                }
            }
        });
    }

    public void getTipopago(final TipopagoInterface api, String id){
        listaTipopagos.clear();
        Call<TipoPagoAPI> call = api.obtenerTipopago(id);
        call.enqueue(new Callback<TipoPagoAPI>() {
            @Override
            public void onResponse(Call<TipoPagoAPI> call, Response<TipoPagoAPI> response) {
                switch (response.code()){
                    case 200:
                        listaTipopagos.add(response.body());
                        edit_IdBuscar.setText("");
                        adaptadorTipopago = new AdaptadorTipopago(TipopagoRestActivity.this, listaTipopagos);
                        rvTipopago.setAdapter(adaptadorTipopago);
                        break;
                    case 204:
                        Toast.makeText(TipopagoRestActivity.this, "No existe ese registro", Toast.LENGTH_SHORT).show();
                        edit_IdBuscar.setText("");
                        getTipopagos(api);
                        break;
                }
            }

            @Override
            public void onFailure(Call<TipoPagoAPI> call, Throwable t) {
            }
        });
    }

    public void getTipopagos(TipopagoInterface api){
        listaTipopagos.clear();
        Call<List<TipoPagoAPI>> call = api.obtenerTipopagos();
        call.enqueue(new Callback<List<TipoPagoAPI>>() {
            @Override
            public void onResponse(Call<List<TipoPagoAPI>> call, Response<List<TipoPagoAPI>> response) {
                listaTipopagos = new ArrayList<TipoPagoAPI>(response.body());
                adaptadorTipopago = new AdaptadorTipopago(TipopagoRestActivity.this, listaTipopagos);
                rvTipopago.setAdapter(adaptadorTipopago);
            }

            @Override
            public void onFailure(Call<List<TipoPagoAPI>> call, Throwable t) {

            }
        });
    }


    public void eliminarTipopagos(final TipopagoInterface api, String id){
        listaTipopagos.clear();
        Call<Void> call = api.eliminarTipopago(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()){
                    case 200:
                        Toast.makeText(TipopagoRestActivity.this, "Se elimino correctamente", Toast.LENGTH_SHORT).show();
                        edit_IdBuscar.setText("");
                        getTipopagos(api);
                        break;
                    case 204:
                        Toast.makeText(TipopagoRestActivity.this, "No existe ese registro", Toast.LENGTH_SHORT).show();
                        edit_IdBuscar.setText("");
                        getTipopagos(api);
                        break;
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void agregarTipopagos(final TipopagoInterface api, String tipo) {
        listaTipopagos.clear();
        TipoPagoAPI tipopago = new TipoPagoAPI();
        tipopago.setTipoPago(tipo);

        Call<Void> call = api.agregarTipopago(tipopago);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case 400:
                        Toast.makeText(TipopagoRestActivity.this, "Faltaron campos.", Toast.LENGTH_SHORT).show();
                        edit_tipopago.setText("");
                        break;
                    case 200:
                        Toast.makeText(TipopagoRestActivity.this, "Se inserto correctamente", Toast.LENGTH_SHORT).show();
                        edit_tipopago.setText("");
                        getTipopagos(api);
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void editarTipopagos(final TipopagoInterface api, String id, String tipo) {
        listaTipopagos.clear();
        TipoPagoAPI tipopago = new TipoPagoAPI();
        tipopago.setIdTipoPago(id);
        tipopago.setTipoPago(tipo);

        Call<Void> call = api.editarTipopago(tipopago);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case 400:
                        Toast.makeText(TipopagoRestActivity.this, "No se puede editar.", Toast.LENGTH_SHORT).show();
                        edit_idtipopago.setText("");
                        edit_tipopago.setText("");
                        break;
                    case 200:
                        Toast.makeText(TipopagoRestActivity.this, "Se edito correctamente", Toast.LENGTH_SHORT).show();
                        edit_idtipopago.setText("");
                        edit_tipopago.setText("");
                        getTipopagos(api);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}