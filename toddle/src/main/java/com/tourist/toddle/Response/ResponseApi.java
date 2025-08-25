package com.tourist.toddle.Response;

public class ResponseApi {
    private String mes;
    private boolean status;
    public String getMes() {
        return mes;
    }
    public void setMes(String mes) {
        this.mes = mes;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public ResponseApi(String mes, boolean status) {
        this.mes = mes;
        this.status = status;
    }
    public ResponseApi() {
    }
}