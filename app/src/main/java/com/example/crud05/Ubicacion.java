package com.example.crud05;

public class Ubicacion {
    private Integer idubicacion;
    private String descripcionubicacion;

    public Ubicacion() {
    }

    public Ubicacion(Integer idubicacion, String descripcionubicacion) {
        this.idubicacion = idubicacion;
        this.descripcionubicacion = descripcionubicacion;
    }

    public Integer getIdubicacion() {
        return idubicacion;
    }

    public void setIdubicacion(Integer idubicacion) {
        this.idubicacion = idubicacion;
    }

    public String getDescripcionubicacion() {
        return descripcionubicacion;
    }

    public void setDescripcionubicacion(String descripcionubicacion) {
        this.descripcionubicacion = descripcionubicacion;
    }
}
