package com.example.dalisaapp.zUtilidad.model;

public class ResponseData {

    private boolean status;
    private String message;

    public ResponseData(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseData() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMesanje() {
        return message;
    }

    public void setMesanje(String mesanje) {
        this.message = mesanje;
    }
}
