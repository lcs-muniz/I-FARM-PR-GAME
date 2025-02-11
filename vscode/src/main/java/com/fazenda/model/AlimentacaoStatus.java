package com.fazenda.model;

public enum AlimentacaoStatus {
    COMPLETA("Completa"),
    PENDENTE("Pendente");

    private String descricao;

    AlimentacaoStatus(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
