package com.fazenda.model;

public enum TipoLocal {
    GALINHEIRO("Galinheiro", 1),
    PASTO("Pasto", 2),
    APRISCO("Aprisco", 3);

    
    private final String descricao;
    private final int codigo;
    
    TipoLocal(String descricao, int codigo) {
        this.descricao = descricao;
        this.codigo = codigo;
    }
    
    // Getters
    
    public String getDescricao() {
        return descricao;
    }

    public int getCodigo() {
        return codigo;
    }
}