package com.example.crud05;

public class Producto {
    private int idProducto;
    private String NombreProducto;
    private int idLocal;
    private int idProveedor;


    public Producto() {
        this.idProducto = idProducto;
        this.NombreProducto = NombreProducto;
        this.idLocal = idLocal;
        this.idProveedor = idProveedor;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        NombreProducto = nombreProducto;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(String idLocal) {
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {

    }
}
