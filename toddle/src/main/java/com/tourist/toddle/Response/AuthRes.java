package com.tourist.toddle.Response;


public class AuthRes {

    private String token;
    private String mes;
    public String getToken() {
        return token;
    }
    public AuthRes(String token, String mes) {
        this.token = token;
        this.mes = mes;
    }
    public AuthRes() {
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getMes() {
        return mes;
    }
    public void setMes(String mes) {
        this.mes = mes;
    }
}
