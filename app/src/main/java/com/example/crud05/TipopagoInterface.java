package com.example.crud05;


import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TipopagoInterface {

    @GET("tipoPago/")
    Call<List<TipoPagoAPI>> obtenerTipopagos();

    @GET("tipoPago/{id}/")
    Call<TipoPagoAPI> obtenerTipopago(
            @Path("id") String  id
    );

    @POST("tipoPago/")
    Call<Void> agregarTipopago(
            @Body TipoPagoAPI tipopago
    );

    @PUT("tipoPago/{id}/")
    Call<Void> editarTipopago(
            @Body TipoPagoAPI tipopago,
            @Path("id") String id

    );

    @DELETE("tipoPago/{id}/")
    Call<Void> eliminarTipopago(
            @Path("id") String id
    );

}