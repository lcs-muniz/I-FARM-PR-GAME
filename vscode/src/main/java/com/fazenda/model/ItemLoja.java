package com.fazenda.model;

import java.math.BigDecimal;

public class ItemLoja {
    private BigDecimal precoUnitario;
    private int quantidadeDisponivel;
    private int tipoAnimalId;
    private int tipoInsumoId;
    private String nome;
    private String marca;
    
    public BigDecimal getPrecoUnitario() {return precoUnitario;}
    public void setPrecoUnitario(BigDecimal precoUnitario) {this.precoUnitario = precoUnitario;}

    public int getQuantidadeDisponivel() {return quantidadeDisponivel;}
    public void setQuantidadeDisponivel(int quantidadeDisponivel) {this.quantidadeDisponivel = quantidadeDisponivel;}
    
    public int getTipoAnimalId() {return tipoAnimalId;}
    public void setTipoAnimalId(int tipoAnimalId) {this.tipoAnimalId = tipoAnimalId;}

    public int getTipoInsumoId() {return tipoInsumoId;}
    public void setTipoInsumoId(int tipoInsumoId) {this.tipoInsumoId = tipoInsumoId;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getMarca() {return marca;}
    public void setMarca(String marca) {this.marca = marca;}

    
}
