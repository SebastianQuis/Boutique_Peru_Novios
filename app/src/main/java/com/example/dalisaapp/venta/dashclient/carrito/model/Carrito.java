package com.example.dalisaapp.venta.dashclient.carrito.model;

import com.example.dalisaapp.venta.dashclient.Tienda.model.Prenda;

public class Carrito {

    private Integer id;
    private Prenda  prenda;
    private Integer cantidad;
    private double precio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Prenda getPrenda() {
        return prenda;
    }

    public void setPrenda(Prenda prenda) {
        this.prenda = prenda;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getImporteTotal() {
        return this.cantidad * this.precio;
    }
}
