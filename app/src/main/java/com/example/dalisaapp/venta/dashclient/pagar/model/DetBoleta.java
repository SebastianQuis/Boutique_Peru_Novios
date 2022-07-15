package com.example.dalisaapp.venta.dashclient.pagar.model;


public class DetBoleta {

    private Integer idProducto;
    private Double precio;
    private Double descuento;
    private Integer caqntidad;
    private Integer punto;
    private Double importePagar;
    private Integer idCabecera;

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Integer getCaqntidad() {
        return caqntidad;
    }

    public void setCaqntidad(Integer caqntidad) {
        this.caqntidad = caqntidad;
    }

    public Integer getPunto() {
        return punto;
    }

    public void setPunto(Integer punto) {
        this.punto = punto;
    }

    public Double getImportePagar() {
        return importePagar;
    }

    public void setImportePagar(Double importePagar) {
        this.importePagar = importePagar;
    }

    public Integer getIdCabecera() {
        return idCabecera;
    }

    public void setIdCabecera(Integer idCabecera) {
        this.idCabecera = idCabecera;
    }
}
