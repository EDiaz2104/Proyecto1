package com.example.crud05;

public class DetalleProducto {

    private Integer idDetalleProduc;
    private Integer idProducto;
    private Integer cantidadProducto;
    private String precioProducto;

    public DetalleProducto() {
    }

    public DetalleProducto(Integer idDetalleProduc, Integer idProducto, Integer cantidadProducto, String precioProducto) {
        this.idDetalleProduc = idDetalleProduc;
        this.idProducto = idProducto;
        this.cantidadProducto = cantidadProducto;
        this.precioProducto = precioProducto;
    }

    public Integer getIdDetalleProduc() {
        return idDetalleProduc;
    }

    public void setIdDetalleProduc(Integer idDetalleProduc) {
        this.idDetalleProduc = idDetalleProduc;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public String getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(String precioProducto) {
        this.precioProducto = precioProducto;
    }
}
