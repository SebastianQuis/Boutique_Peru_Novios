package com.example.dalisaapp.venta.dashclient.categoria.model;

import java.io.Serializable;

public class Categoria implements Serializable {

    private  Integer idCategoria;
    private  String nombre;

    public Categoria(String nameCategoria) {
        this.nombre = nameCategoria;
    }

    public Integer getId() {
        return idCategoria;
    }

    public void setId(Integer id) {
        this.idCategoria = id;
    }

    public String getNameCategoria() {
        return nombre;
    }

    public void setNameCategoria(String nameCategoria) {
        this.nombre = nameCategoria;
    }
}
