package com.example.dalisaapp.venta.dashclient.Tienda.model;


import com.example.dalisaapp.venta.dashclient.categoria.model.Categoria;

import java.util.List;

public class Producto {

    private Categoria categoria;
    private List<Prenda> lista;

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Prenda> getLista() {
        return lista;
    }

    public void setLista(List<Prenda> lista) {
        this.lista = lista;
    }
}