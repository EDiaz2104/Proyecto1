package com.example.crud05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.crud05.Modelos.UsuarioAPI;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UsuarioRestActivity extends AppCompatActivity {

    EditText edit_IdBuscar, edit_idusuario ,edit_nombre, edit_apellido, edit_telefono, edit_direccion, edit_estado, edit_email, edit_clave;
    Button btnGuardar, btnBuscar, btnBuscarTodos, btnEliminar, btnEditar;

    RecyclerView rvUsuario;
    List<UsuarioAPI> listaUsuarios = new ArrayList<>();

    AdaptadorUsuario adaptadorUsuario;

    Retrofit retrofit;
    UsuarioInterface api;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_rest);
        getSupportActionBar().hide();
        edit_IdBuscar = (EditText) findViewById(R.id.edt_IdBuscar);
        edit_idusuario = (EditText) findViewById(R.id.edit_idusuarioRS);
        edit_nombre = (EditText) findViewById(R.id.edit_nombreRS);
        edit_apellido = (EditText) findViewById(R.id.edit_apellidoRS);
        edit_telefono = (EditText) findViewById(R.id.edit_telefonoRS);
        edit_direccion = (EditText) findViewById(R.id.edit_direccionRS);
        edit_estado = (EditText) findViewById(R.id.edit_estadoRS);
        edit_email = (EditText) findViewById(R.id.edit_emailRS);
        edit_clave = (EditText) findViewById(R.id.edit_claveRS);
        btnGuardar = (Button) findViewById(R.id.bt_guardarRS);
        btnBuscar = (Button) findViewById(R.id.bt_buscarRS);
        btnBuscarTodos = (Button) findViewById(R.id.bt_buscarTodosRS);
        btnEliminar = (Button) findViewById(R.id.bt_eliminarRS);
        btnEditar = (Button) findViewById(R.id.bt_actualizarRS);
        rvUsuario = findViewById(R.id.rvUsuario);
        rvUsuario.setLayoutManager(new GridLayoutManager(this, 1));


        retrofit = new AdaptadorRetrofit().getAdapatador();
        api = retrofit.create(UsuarioInterface.class);

        getUsuarios(api);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_IdBuscar.getText().toString().equals("")){
                    Toast.makeText(UsuarioRestActivity.this, "Inserte un ID para busacar", Toast.LENGTH_SHORT).show();
                }else{
                    getUsuario(api, edit_IdBuscar.getText().toString());
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_IdBuscar.getText().toString().equals("")){
                    Toast.makeText(UsuarioRestActivity.this, "Inserte un ID para busacar", Toast.LENGTH_SHORT).show();
                }else{
                    eliminarUsuarios(api, edit_IdBuscar.getText().toString());
                }
            }
        });

        btnBuscarTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUsuarios(api);
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_nombre.getText().toString().equals("") || edit_apellido.getText().toString().equals("")
                        || edit_telefono.getText().toString().equals("") || edit_direccion.getText().toString().equals("")
                        || edit_email.getText().toString().equals("") || edit_clave.getText().toString().equals("")){
                    Toast.makeText(UsuarioRestActivity.this, "Se deben llenar los campos", Toast.LENGTH_SHORT).show();
                }else{
                    agregarUsuarios(api, edit_nombre.getText().toString(), edit_apellido.getText().toString(),
                            edit_telefono.getText().toString(), edit_direccion.getText().toString(), edit_estado.getText().toString(),
                            edit_email.getText().toString(), edit_clave.getText().toString());
                }
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_nombre.getText().toString().equals("") || edit_apellido.getText().toString().equals("")
                        || edit_telefono.getText().toString().equals("") || edit_direccion.getText().toString().equals("")
                        || edit_email.getText().toString().equals("") || edit_clave.getText().toString().equals("")){
                    Toast.makeText(UsuarioRestActivity.this, "Se deben llenar los campos", Toast.LENGTH_SHORT).show();
                }else{
                    editarUsuarios(api, edit_idusuario.getText().toString(),edit_nombre.getText().toString(), edit_apellido.getText().toString(),
                            edit_telefono.getText().toString(), edit_direccion.getText().toString(), edit_estado.getText().toString(),
                            edit_email.getText().toString(), edit_clave.getText().toString());
                }
            }
        });
    }

    public void getUsuario(final UsuarioInterface api, String id){
        listaUsuarios.clear();
        Call<UsuarioAPI> call = api.obtenerUsuario(id);
        call.enqueue(new Callback<UsuarioAPI>() {
            @Override
            public void onResponse(Call<UsuarioAPI> call, Response<UsuarioAPI> response) {
                switch (response.code()){
                    case 200:
                        listaUsuarios.add(response.body());
                        edit_IdBuscar.setText("");
                        adaptadorUsuario = new AdaptadorUsuario(UsuarioRestActivity.this, listaUsuarios);
                        rvUsuario.setAdapter(adaptadorUsuario);
                        break;
                    case 404:
                        Toast.makeText(UsuarioRestActivity.this, "No existe ese registro", Toast.LENGTH_SHORT).show();
                        edit_IdBuscar.setText("");
                        getUsuarios(api);
                        break;
                }
            }

            @Override
            public void onFailure(Call<UsuarioAPI> call, Throwable t) {
            }
        });
    }

    public void getUsuarios(UsuarioInterface api){
        listaUsuarios.clear();
        Call<List<UsuarioAPI>> call = api.obtenerUsuarios();
        call.enqueue(new Callback<List<UsuarioAPI>>() {
            @Override
            public void onResponse(Call<List<UsuarioAPI>> call, Response<List<UsuarioAPI>> response) {
                listaUsuarios = new ArrayList<UsuarioAPI>(response.body());
                adaptadorUsuario = new AdaptadorUsuario(UsuarioRestActivity.this, listaUsuarios);
                rvUsuario.setAdapter(adaptadorUsuario);
            }

            @Override
            public void onFailure(Call<List<UsuarioAPI>> call, Throwable t) {

            }
        });
    }


    public void eliminarUsuarios(final UsuarioInterface api, String id){
        listaUsuarios.clear();
        Call<Void> call = api.eliminarUsuario(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()){
                    case 204:
                        Toast.makeText(UsuarioRestActivity.this, "Se elimino correctamente", Toast.LENGTH_SHORT).show();
                        edit_IdBuscar.setText("");
                        getUsuarios(api);
                        break;
                    case 404:
                        Toast.makeText(UsuarioRestActivity.this, "No existe ese registro", Toast.LENGTH_SHORT).show();
                        edit_IdBuscar.setText("");
                        getUsuarios(api);
                        break;
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void agregarUsuarios(final UsuarioInterface api, String nombre, String apellido, String telefono, String direccion, String estado, String email, String clave) {
        listaUsuarios.clear();
        UsuarioAPI usuario = new UsuarioAPI();
        usuario.setNombreUsuario(nombre);
        usuario.setApelUsuario(apellido);
        usuario.setTelUsuario(telefono);
        usuario.setDireccionUsuario(direccion);
        usuario.setEstadoUsuario(estado);
        usuario.setEmailUsuario(email);
        usuario.setClaveUsuario(clave);

        Call<Void> call = api.agregarUsuario(usuario);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case 400:
                        Toast.makeText(UsuarioRestActivity.this, "Faltaron campos.", Toast.LENGTH_SHORT).show();
                        edit_nombre.setText("");
                        edit_apellido.setText("");
                        edit_telefono.setText("");
                        edit_direccion.setText("");
                        edit_estado.setText("");
                        edit_email.setText("");
                        edit_clave.setText("");
                        break;
                    case 201:
                        Toast.makeText(UsuarioRestActivity.this, "Se inserto correctamente", Toast.LENGTH_SHORT).show();
                        edit_nombre.setText("");
                        edit_apellido.setText("");
                        edit_telefono.setText("");
                        edit_direccion.setText("");
                        edit_estado.setText("");
                        edit_email.setText("");
                        edit_clave.setText("");
                        getUsuarios(api);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void editarUsuarios(final UsuarioInterface api, String id, String nombre, String apellido, String telefono, String direccion, String estado, String email, String clave) {
        listaUsuarios.clear();
        UsuarioAPI usuario = new UsuarioAPI();
        usuario.setIdUsuario(id);
        usuario.setNombreUsuario(nombre);
        usuario.setApelUsuario(apellido);
        usuario.setTelUsuario(telefono);
        usuario.setDireccionUsuario(direccion);
        usuario.setEstadoUsuario(estado);
        usuario.setEmailUsuario(email);
        usuario.setClaveUsuario(clave);

        Call<Void> call = api.editarUsuario(usuario, id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case 400:
                        Toast.makeText(UsuarioRestActivity.this, "No se puede editar.", Toast.LENGTH_SHORT).show();
                        edit_idusuario.setText("");
                        edit_nombre.setText("");
                        edit_apellido.setText("");
                        edit_telefono.setText("");
                        edit_direccion.setText("");
                        edit_estado.setText("");
                        edit_email.setText("");
                        edit_clave.setText("");
                        break;
                    case 200:
                        Toast.makeText(UsuarioRestActivity.this, "Se edito correctamente", Toast.LENGTH_SHORT).show();
                        edit_idusuario.setText("");
                        edit_nombre.setText("");
                        edit_apellido.setText("");
                        edit_telefono.setText("");
                        edit_direccion.setText("");
                        edit_estado.setText("");
                        edit_email.setText("");
                        edit_clave.setText("");
                        getUsuarios(api);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

}