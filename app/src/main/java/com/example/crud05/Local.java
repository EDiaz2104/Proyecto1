package com.example.crud05;

public class Local {

    private Integer idlocal;
    private Integer idencargadolocal;
    private Integer idubicacion;
    private String nomlocal;

    public Local() {
    }

    public Local(Integer idlocal, Integer idencargadolocal, Integer idubicacion, String nomlocal) {
        this.idlocal = idlocal;
        this.idencargadolocal = idencargadolocal;
        this.idubicacion = idubicacion;
        this.nomlocal = nomlocal;
    }

    public Integer getIdlocal() {
        return idlocal;
    }

    public void setIdlocal(Integer idlocal) {
        this.idlocal = idlocal;
    }

    public Integer getIdencargadolocal() {
        return idencargadolocal;
    }

    public void setIdencargadolocal(Integer idencargadolocal) {
        this.idencargadolocal = idencargadolocal;
    }

    public Integer getIdubicacion() {
        return idubicacion;
    }

    public void setIdubicacion(Integer idubicacion) {
        this.idubicacion = idubicacion;
    }

    public String getNomlocal() {
        return nomlocal;
    }

    public void setNomlocal(String nomlocal) {
        this.nomlocal = nomlocal;
    }
}
