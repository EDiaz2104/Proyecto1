package com.example.crud05;

public class Proveedor {
    String id;
    String nombreProveedor;
    String descripcionProveedor;
    String telefonoProveedor;

    public Proveedor() {
    }

    public Proveedor(String id, String nombreProveedor, String descripcionProveedor, String telefonoProveedor) {
        this.id = id;
        this.nombreProveedor = nombreProveedor;
        this.descripcionProveedor = descripcionProveedor;
        this.telefonoProveedor = telefonoProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcionProveedor() {
        return descripcionProveedor;
    }

    public void setDescripcionProveedor(String descripcionProveedor) {
        this.descripcionProveedor = descripcionProveedor;
    }

    public String getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(String telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }
}
