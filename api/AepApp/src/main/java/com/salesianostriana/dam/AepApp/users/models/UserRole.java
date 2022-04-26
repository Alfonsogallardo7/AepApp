package com.salesianostriana.dam.AepApp.users.models;

public enum UserRole {

    ADMINISTRADOR("ADMINISTRADOR"), COMPETIDOR("COMPETIDOR"), USUARIO("USUARIO");

    private String valor;

    private UserRole(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
