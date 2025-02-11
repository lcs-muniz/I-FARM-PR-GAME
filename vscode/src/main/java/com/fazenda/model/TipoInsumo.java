package com.fazenda.model;

public enum TipoInsumo {
    INSUMO("Insumo"),
    PRODUTO("Produto");

    private String descricao;

    TipoInsumo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

