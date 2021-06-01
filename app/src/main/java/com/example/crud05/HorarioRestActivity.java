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

public class HorarioRestActivity extends AppCompatActivity {
    EditText   etIdLocal, etDia,etApertura,etCierre;
    Button btnTodosBuscar, btnAgregar;

    RecyclerView rvHorario;
    List<Horario> listaHorario = new ArrayList<>();

    AdaptadorHorario adaptadorHorario;

    Retrofit retrofit;
    HorarioInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_rest);
        etIdLocal = findViewById(R.id.etIdLocal);
        etDia = findViewById(R.id.etDia);
        etApertura = findViewById(R.id.etApertura);
        etCierre = findViewById(R.id.etCierre);
        btnTodosBuscar = findViewById(R.id.btnTodosBuscar);
        btnAgregar = findViewById(R.id.btnAgregar);
        rvHorario = findViewById(R.id.rvHorario);
        rvHorario.setLayoutManager(new GridLayoutManager(this, 1));

        retrofit = new AdaptadorRetrofit().getAdapatador();
        api = retrofit.create(HorarioInterface.class);

        btnTodosBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getHorarios(api);
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etIdLocal.getText().toString().equals("") || etDia.getText().toString().equals("") || etApertura.getText().toString().equals("")  || etCierre.getText().toString().equals("")  ) {
                    Toast.makeText(HorarioRestActivity.this, "Se deben de llenar los campos", Toast.LENGTH_SHORT).show();
                } else {
                    agregarHorario(api,  etIdLocal.getText().toString(), etDia.getText().toString(), etApertura.getText().toString() , etCierre.getText().toString());
                }
            }
        });


    }



    public void getHorarios(HorarioInterface api) {
        listaHorario.clear();
        Call<List<Horario>> call = api.obtenerHorarios();

        call.enqueue(new Callback<List<Horario>>() {
            @Override
            public void onResponse(Call<List<Horario>> call, Response<List<Horario>> response) {
                listaHorario = new ArrayList<Horario>(response.body());

                adaptadorHorario = new AdaptadorHorario(HorarioRestActivity.this, listaHorario);
                rvHorario.setAdapter(adaptadorHorario);


            }

            @Override
            public void onFailure(Call<List<Horario>> call, Throwable t) {

            }
        });
    }



    public void agregarHorario(final HorarioInterface api, String idlocal, String dia, String apertura, String cierre ) {
        listaHorario.clear();
        Horario horario= new Horario();
        horario.setIdlocal(idlocal);
        horario.setDia(dia);
        horario.setApertura(apertura);
        horario.setCierre(cierre);

        Call<Void> call = api.agregarHorario(horario);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case 400:
                        Toast.makeText(HorarioRestActivity.this, "Faltaron campos.", Toast.LENGTH_SHORT).show();
                        etIdLocal.setText("");
                        etDia.setText("");
                        etApertura.setText("");
                        etCierre.setText("");
                        break;
                    case 200:
                        Toast.makeText(HorarioRestActivity.this, "Se inserto correctamente", Toast.LENGTH_SHORT).show();
                        etIdLocal.setText("");
                        etDia.setText("");
                        etApertura.setText("");
                        etCierre.setText("");

                        getHorarios(api);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }


}