package com.example.crud05.Repartidor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Repartidor {

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("idlocal")
    private String idlocal;

    @Expose
    @SerializedName("nombreRepartidor")
    private String nombreRepartidor;

    @Expose
    @SerializedName("carneRepartidor")
    private String carneRepartidor;

    public Repartidor(String id, String idlocal, String nombreRepartidor, String carneRepartidor) {
        this.id = id;
        this.idlocal = idlocal;
        this.nombreRepartidor = nombreRepartidor;
        this.carneRepartidor = carneRepartidor;
    }

    public Repartidor() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getIdlocal() {
        return idlocal;
    }

    public void setIdlocal(String idlocal) {
        this.idlocal = idlocal;
    }

    public String getNombreRepartidor() {
        return nombreRepartidor;
    }

    public void setNombreRepartidor(String nombreRepartidor) {
        this.nombreRepartidor = nombreRepartidor;
    }

    public String getCarneRepartidor() {
        return carneRepartidor;
    }

    public void setCarneRepartidor(String carneRepartidor) {
        this.carneRepartidor = carneRepartidor;
    }
}
