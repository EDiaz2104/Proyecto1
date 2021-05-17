package com.example.crud05;

public class Repartidor {
    private Integer IdRepartidor;
    private Integer idLocal;
    private String NombreRepartidor;
    private String CarnetRepartidor;

    public Repartidor() {
    }

    public Repartidor(Integer idRepartidor, Integer idLocal, String nombreRepartidor, String carnetRepartidor) {
        IdRepartidor = idRepartidor;
        this.idLocal = idLocal;
        NombreRepartidor = nombreRepartidor;
        CarnetRepartidor = carnetRepartidor;
    }

    public Integer getIdRepartidor() {
        return IdRepartidor;
    }

    public void setIdRepartidor(Integer idRepartidor) {
        IdRepartidor = idRepartidor;
    }

    public Integer getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Integer idLocal) {
        this.idLocal = idLocal;
    }

    public String getNombreRepartidor() {
        return NombreRepartidor;
    }

    public void setNombreRepartidor(String nombreRepartidor) {
        NombreRepartidor = nombreRepartidor;
    }

    public String getCarnetRepartidor() {
        return CarnetRepartidor;
    }

    public void setCarnetRepartidor(String carnetRepartidor) {
        CarnetRepartidor = carnetRepartidor;
    }
}
