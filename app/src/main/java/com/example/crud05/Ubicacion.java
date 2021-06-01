package com.example.crud05;

public class Ubicacion {
    private String id;
    private String descripcionubicacion;


    public Ubicacion(String id, String descripcionubicacion) {
        this.id = id;
        this.descripcionubicacion = descripcionubicacion;
    }

    public Ubicacion() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcionubicacion() {
        return descripcionubicacion;
    }

    public void setDescripcionubicacion(String descripcionubicacion) {
        this.descripcionubicacion = descripcionubicacion;
    }
}
