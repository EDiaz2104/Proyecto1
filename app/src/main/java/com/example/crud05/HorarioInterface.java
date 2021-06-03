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


public interface HorarioInterface {

    @GET("horario/")
    Call<List<Horario>> obtenerHorarios();

    @GET("horario/{id}/")
    Call<Horario> obtenerHorario(
            @Path("id") String  id
    );

    @POST("horario/")
    Call<Void> agregarHorario(
            @Body Horario horario
    );

    @PUT("horario/{id}/")
    Call<Void> editarHorario(
            @Body Horario horario,
            @Path("id") String id

    );

    @DELETE("horario/{id}/")
    Call<Void> eliminarHorario(
            @Path("id") String id
    );
}
