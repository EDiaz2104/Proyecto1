package com.example.crud05;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface UbicacionInterface {

    @GET("ubicacion/")
    Call<List<Ubicacion>> obtenerUbicaciones();

    @GET("ubicacion/{idUbicacion}/")
    Call<Ubicacion> obtenerUbicacion(
            @Path("id") String  id
    );

    @POST("ubicacion/")
    Call<Void> agregarUbicacion(
            @Body Ubicacion ubicacion
    );

    @PUT("ubicacion/{id}/")
    Call<Void> editarUbicacion(
            @Body Ubicacion ubicacion,
            @Path("id") String id

    );

    @DELETE("ubicacion/{id}/")
    Call<Void> eliminarUbicacion(
            @Path("id") String id
    );
}


