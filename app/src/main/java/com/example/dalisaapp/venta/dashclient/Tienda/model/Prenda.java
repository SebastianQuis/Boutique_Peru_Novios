package com.example.dalisaapp.venta.dashclient.Tienda.model;

import com.example.dalisaapp.venta.dashclient.categoria.model.Categoria;

import java.io.Serializable;

public class Prenda implements Serializable {

    private Categoria categoria;
    private Integer id;
    private String nombre;
    private String descripcion;
    private int colorProd;
    private double precio;
    private double descuento;
    private String colorNumber;
    private String foto;
    private String promocion;
    public Prenda(Integer id,String nombre, String descripcion, int colorProd, double precio) {
        this.id=id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.colorProd = colorProd;
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getColorProd() {
        return colorProd;
    }

    public void setColorProd(int colorProd) {
        this.colorProd = colorProd;
    }

    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public String getColorNumber() {
        return colorNumber;
    }

    public void setColorNumber(String colorNumber) {
        this.colorNumber = colorNumber;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getPromocion() {
        return promocion;
    }

    public void setPromocion(String promocion) {
        this.promocion = promocion;
    }

    @Override
    public String toString() {
        return "Prenda{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", colorProd=" + colorProd +
                ", precio=" + precio +
                ", descuento=" + descuento +
                ", colorNumber='" + colorNumber + '\'' +
                ", foto='" + foto + '\'' +
                ", promocion='" + promocion + '\'' +
                '}';
    }
}