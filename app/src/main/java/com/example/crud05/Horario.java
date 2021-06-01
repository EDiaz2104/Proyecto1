package com.example.crud05;

public class Horario {
    private String id;
    private String idlocal;
    private String dia;
    private String apertura;
    private String cierre;


    public Horario(String id, String idlocal, String dia, String apertura, String cierre) {
        this.id = id;
        this.idlocal = idlocal;
        this.dia = dia;
        this.apertura = apertura;
        this.cierre = cierre;
    }

    public Horario() {

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

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getApertura() {
        return apertura;
    }

    public void setApertura(String apertura) {
        this.apertura = apertura;
    }

    public String getCierre() {
        return cierre;
    }

    public void setCierre(String cierre) {
        this.cierre = cierre;
    }
}
