package com.example.crud05.Repartidor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RepartidorInterface {
    @GET("repartidor/")
    Call<List<Repartidor>> obtenerRepartidores();

    @GET("repartidor/{id}/")
    Call<Repartidor> obtenerRepartidor(
            @Path("id") String  id
    );

    @POST("repartidor/")
    Call<Void> agregarRepartidor(
            @Body Repartidor repartidor
    );

    @PUT("repartidor/{id}/")
    Call<Void> editarRepartidor(
            @Body Repartidor repartidor,
            @Path("id") String id

    );

    @DELETE("repartidor/{id}/")
    Call<Void> eliminarRepartidor(
            @Path("id") String id
    );
}
