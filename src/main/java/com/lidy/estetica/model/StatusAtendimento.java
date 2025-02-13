package com.lidy.estetica.model;

public enum StatusAtendimento {

    AGENDADO("Agendado"),
    CANCELADO("Cancelado"),
    REALIZADO("Realizado");

    private final String descricao;

    StatusAtendimento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
