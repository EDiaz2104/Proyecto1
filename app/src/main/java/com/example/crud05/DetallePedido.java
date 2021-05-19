package com.example.crud05;

public class DetallePedido {
    private int idDetallePedido;
    private int idTipoPago;
    private int idProducto;
    private int cantidad;
    private int EstadoPedido;

    public DetallePedido() {
        this.idDetallePedido = idDetallePedido;
        this.idTipoPago = idTipoPago;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.EstadoPedido = EstadoPedido;
    }

    public int getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(int idDetallePedido) {
    }

    public int getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(int idTipoPago) {
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
    }

    public int getEstadoPedido() {
        return EstadoPedido;
    }

    public void setEstadoPedido(int EstadoPedido) {
    }
}
