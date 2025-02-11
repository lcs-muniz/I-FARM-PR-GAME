package com.fazenda.model;

public enum EstadoSaude {
    SAUDAVEL("Saudável"),
    DOENTE("Doente"),
    RECUPERANDO("Recuperando");

    private String descricao;

    EstadoSaude(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public static EstadoSaude fromDescricao(String descricao) {
        for (EstadoSaude e : EstadoSaude.values()) {
            if (e.getDescricao().equalsIgnoreCase(descricao)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Descrição inválida: " + descricao);
    }
}
