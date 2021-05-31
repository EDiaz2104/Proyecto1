package com.example.crud05;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import com.example.crud05.Modelos.Usuario;

public interface UsuarioInterface {

    @GET("usuario/")
    Call<List<Usuario>> obtenerUsuarios();

    @GET("usuario/{id}")
    Call<Usuario> obtenerUsuario(
            @Query("id") String id
    );

    @POST("usuario/")
    Call<Void> agregarUsuario(
            @Body Usuario usuario
    );

    @PUT("usuario/{id}")
    Call<Void> editarUsuario(
            @Body Usuario usario
    );

    @DELETE("usuario/{id}")
    Call<Void> eliminarUsiaro(
            @Query("id") String id
    );

}
