package com.example.crud05;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface CategoriaInterface {

    @GET("categoriaProducto/")
    Call<List<Categoria>> obtenerCategorias();

    @GET("categoriaProducto/{id}")
    Call<Categoria> obtenerCategoria(
            @Query("id") String id
    );

    @POST("categoriaProducto/")
    Call<Void> agregarCategoria(
            @Body Categoria categoria
    );

    @PUT("categoriaProducto/{id}")
    Call<Void> editarCategoria(
            @Body Categoria categoria
    );

    @DELETE("categoriaProducto/id")
    Call<Void> eliminarCategoria(
            @Query("id") String id
    );
}
