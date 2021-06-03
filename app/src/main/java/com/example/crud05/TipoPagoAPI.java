package com.example.crud05;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TipoPagoAPI {

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("tipoPago")
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