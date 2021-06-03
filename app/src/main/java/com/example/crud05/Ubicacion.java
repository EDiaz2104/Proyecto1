package com.example.crud05;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ubicacion {

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("descripcionubicacion")
    private String descripcionubicacion;

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

    public Ubicacion(String id, String descripcionubicacion) {
        this.id = id;
        this.descripcionubicacion = descripcionubicacion;
    }
}
