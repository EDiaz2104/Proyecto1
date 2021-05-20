package com.example.crud05.Modelos;

public class Proveedor {
    private Integer idProveedor;
    private String nombreProveedor;
    private String descripcionProveedor;
    private String telefonoProveedor;

    public Proveedor() {
    }

    public Proveedor(Integer idProveedor, String nombreProveedor, String descripcionProveedor, String telefonoProveedor) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.descripcionProveedor = descripcionProveedor;
        this.telefonoProveedor = telefonoProveedor;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
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

