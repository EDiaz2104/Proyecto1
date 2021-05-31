package com.example.crud05;

public class Horario {
    private Integer id;
    private Integer idlocal;
    private String dia;
    private String apertura;
    private String cierre;

    public Horario() {
    }

    public Horario(int id, int idlocal, String dia, String apertura, String cierre) {
        this.id = id;
        this.idlocal = idlocal;
        this.dia = dia;
        this.apertura = apertura;
        this.cierre = cierre;
    }

    public int getId() {
        return id;
    }

    public void setId(int idhorario) {
        this.id = idhorario;
    }

    public int getIdlocal() {
        return idlocal;
    }

    public void setIdlocal(int idlocal) {
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
