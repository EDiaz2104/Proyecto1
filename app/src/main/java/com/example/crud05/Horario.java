package com.example.crud05;

public class Horario {
    private int idhorario;
    private int idlocal;
    private String dia;
    private String apertura;
    private String cierre;

    public Horario() {
    }

    public Horario(int idhorario, int idlocal, String dia, String apertura, String cierre) {
        this.idhorario = idhorario;
        this.idlocal = idlocal;
        this.dia = dia;
        this.apertura = apertura;
        this.cierre = cierre;
    }

    public int getIdhorario() {
        return idhorario;
    }

    public void setIdhorario(int idhorario) {
        this.idhorario = idhorario;
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
