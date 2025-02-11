package com.fazenda.model;

import java.math.BigDecimal;

public class Produto {
    private String nome;
    private BigDecimal precoUnitario;
    
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    
    public BigDecimal getPrecoUnitario() {return precoUnitario;}
    public void setPrecoUnitario(BigDecimal precoUnitario) {this.precoUnitario = precoUnitario;}
}
