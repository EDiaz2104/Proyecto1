package com.example.crud05;

public class PedidoAsignado {
    private Integer IdPedidoAsignado;
    private Integer IdPedido;
    private Integer IdRepartidor;

    public PedidoAsignado() {
    }

    public PedidoAsignado(Integer idPedidoAsignado, Integer idPedido, Integer idRepartidor) {
        IdPedidoAsignado = idPedidoAsignado;
        IdPedido = idPedido;
        IdRepartidor = idRepartidor;
    }

    public Integer getIdPedidoAsignado() {
        return IdPedidoAsignado;
    }

    public void setIdPedidoAsignado(Integer idPedidoAsignado) {
        IdPedidoAsignado = idPedidoAsignado;
    }

    public Integer getIdPedido() {
        return IdPedido;
    }

    public void setIdPedido(Integer idPedido) {
        IdPedido = idPedido;
    }

    public Integer getIdRepartidor() {
        return IdRepartidor;
    }

    public void setIdRepartidor(Integer idRepartidor) {
        IdRepartidor = idRepartidor;
    }
}
