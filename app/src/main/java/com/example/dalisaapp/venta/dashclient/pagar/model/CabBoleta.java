package com.example.dalisaapp.venta.dashclient.pagar.model;

import com.example.dalisaapp.user.model.User;

import java.util.ArrayList;
import java.util.List;

public class CabBoleta {
    private Integer  id;
    private User usuario;
    private String fechaGenerada;
    private String estado;//pendiente,en camino, finalizado, canelado
    private String tipoPago;//efectivo o con tarjeta
    private String fechaEntrada;
    private double importeTotal;
    private List<DetBoleta> detalleBoleta = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public String getFechaGenerada() {
        return fechaGenerada;
    }

    public void setFechaGenerada(String fechaGenerada) {
        this.fechaGenerada = fechaGenerada;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(double importeTotal) {
        importeTotal = importeTotal;
    }

    public List<DetBoleta> getDetalleBoleta() {
        return detalleBoleta;
    }

    public void setDetalleBoleta(List<DetBoleta> detalleBoleta) {
        this.detalleBoleta = detalleBoleta;
    }
}
