package com.example.crud05;

public class TipoPagoAPI {

    private String id;
    private String tipoPago;

    public TipoPagoAPI() {
    }

    public TipoPagoAPI(String id, String tipoPago) {
        this.id = id;
        this.tipoPago = tipoPago;
    }

    public String getIdTipoPago() {
        return id;
    }

    public void setIdTipoPago(String id) {
        this.id = id;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }
}