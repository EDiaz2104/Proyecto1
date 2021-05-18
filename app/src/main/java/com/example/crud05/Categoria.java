package com.example.crud05;

public class Categoria {
    private Integer idCategoria;
    private Integer idProducto;
    private String NombreCategoria;
    private String DescripcionCategoria;

    public Categoria(Integer idCategoria, Integer idProducto, String nombreCategoria, String descripcionCategoria) {
        this.idCategoria = idCategoria;
        this.idProducto = idProducto;
        NombreCategoria = nombreCategoria;
        DescripcionCategoria = descripcionCategoria;
    }

    public Categoria() {
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreCategoria() {
        return NombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        NombreCategoria = nombreCategoria;
    }

    public String getDescripcionCategoria() {
        return DescripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        DescripcionCategoria = descripcionCategoria;
    }
}
