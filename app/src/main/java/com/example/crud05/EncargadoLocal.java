package com.example.crud05;

public class EncargadoLocal {

    private Integer idencargadolocal;
    private Integer idusuario;
    private String nomencargado;
    private String apelencargado;
    private String telencargado;

    public EncargadoLocal() {
    }

    public EncargadoLocal(Integer idencargadolocal, Integer idusuario, String nomencargado, String apelencargado, String telencargado) {
        this.idencargadolocal = idencargadolocal;
        this.idusuario = idusuario;
        this.nomencargado = nomencargado;
        this.apelencargado = apelencargado;
        this.telencargado = telencargado;
    }

    public Integer getIdencargadolocal() {
        return idencargadolocal;
    }

    public void setIdencargadolocal(Integer idencargadolocal) {
        this.idencargadolocal = idencargadolocal;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getNomencargado() {
        return nomencargado;
    }

    public void setNomencargado(String nomencargado) {
        this.nomencargado = nomencargado;
    }

    public String getApelencargado() {
        return apelencargado;
    }

    public void setApelencargado(String apelencargado) {
        this.apelencargado = apelencargado;
    }

    public String getTelencargado() {
        return telencargado;
    }

    public void setTelencargado(String telencargado) {
        this.telencargado = telencargado;
    }
}
