package com.example.crud05;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import com.example.crud05.Modelos.UsuarioAPI;

public interface UsuarioInterface {

    @GET("usuario/")
    Call<List<UsuarioAPI>> obtenerUsuarios();

    @GET("usuario/{id}")
    Call<UsuarioAPI> obtenerUsuario(
            @Query("id") String id
    );

    @POST("usuario/")
    Call<Void> agregarUsuario(
            @Body UsuarioAPI usuario
    );

    @PUT("usuario/{id}")
    Call<Void> editarUsuario(
            @Body UsuarioAPI usario
    );

    @DELETE("usuario/{id}")
    Call<Void> eliminarUsiaro(
            @Query("id") String id
    );

}
