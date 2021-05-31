package com.example.crud05;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface HorarioInterface {

    @GET("horario/")
    Call<List<Horario>> obtenerHorarios();

    @GET("horario/{id}")
    Call<Horario> obtenerHorario(
            @Query("id") String id
    );

    @POST("horario/")
    Call<Void> agregarHorario(
            @Body Horario categoria
    );

    @PUT("horario/{id}")
    Call<Void> editarHorario(
            @Body Horario horario
    );

    @DELETE("horario/{id}")
    Call<Void> eliminarHorario(
            @Query("id") String id
    );
}