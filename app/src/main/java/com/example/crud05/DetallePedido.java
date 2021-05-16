package com.example.crud05;

public class DetallePedido {
    private int idDetallePedido;
    private int idTipoPago;
    private int idProducto;
    private int cantidad;
    private boolean EstadoPedido;

    public DetallePedido() {
        this.idDetallePedido = idDetallePedido;
        this.idTipoPago = idTipoPago;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        EstadoPedido = EstadoPedido;
    }

    public int getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(String idDetallePedido) {
    }

    public int getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(String idTipoPago) {
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
    }

    public boolean isEstadoPedido() {
        return EstadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
    }
}
