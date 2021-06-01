package com.example.crud05;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ProveedorInterface {

    @GET("proveedor/")
    Call<List<Categoria>> obtenerProveedores();

    @GET("proveedor/{id}")
    Call<Categoria> obtenerProveedor(
            @Query("id") String id
    );

    @POST("proveedor/")
    Call<Void> agregarProveedor(
            @Body Categoria categoria
    );

    @PUT("proveedor/{id}")
    Call<Void> editarProveedor(
            @Body Categoria categoria
    );

    @DELETE("proveedor/{id}")
    Call<Void> eliminarProveedor(
            @Query("id") String id
    );
}
