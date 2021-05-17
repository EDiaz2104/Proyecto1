package com.example.crud05;

public class Pedido {

    private Integer IdPedido;
    private Integer idLocal;
    private Integer IdCombo;
    private Integer IdUsuario;
    private Integer IdDetallePedido;
    private String FechaPedido;

    public Pedido() {
    }

    public Pedido(Integer idPedido, Integer idLocal, Integer idCombo, Integer idUsuario, Integer idDetallePedido, String fechaPedido) {
        IdPedido = idPedido;
        this.idLocal = idLocal;
        IdCombo = idCombo;
        IdUsuario = idUsuario;
        IdDetallePedido = idDetallePedido;
        FechaPedido = fechaPedido;
    }

    public Integer getIdPedido() {
        return IdPedido;
    }

    public void setIdPedido(Integer idPedido) {
        IdPedido = idPedido;
    }

    public Integer getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Integer idLocal) {
        this.idLocal = idLocal;
    }

    public Integer getIdCombo() {
        return IdCombo;
    }

    public void setIdCombo(Integer idCombo) {
        IdCombo = idCombo;
    }

    public Integer getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        IdUsuario = idUsuario;
    }

    public Integer getIdDetallePedido() {
        return IdDetallePedido;
    }

    public void setIdDetallePedido(Integer idDetallePedido) {
        IdDetallePedido = idDetallePedido;
    }

    public String getFechaPedido() {
        return FechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        FechaPedido = fechaPedido;
    }
}
