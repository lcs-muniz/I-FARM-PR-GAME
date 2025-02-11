package com.fazenda.model;

import java.math.BigDecimal;

public class LojaAnimais {
    private int fkIdLojaUniversal;
    private int fkIdTipoAnimal;
    private BigDecimal precoUnitario;
    private int quantidadeDisponivel;
    private String nomeAnimal;

    public LojaAnimais() {}

    public LojaAnimais(int fkIdLojaUniversal, int fkIdTipoAnimal, BigDecimal precoUnitario, int quantidadeDisponivel, String nomeAnimal) {
        this.fkIdLojaUniversal = fkIdLojaUniversal;
        this.fkIdTipoAnimal = fkIdTipoAnimal;
        this.precoUnitario = precoUnitario;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.nomeAnimal = nomeAnimal;
    }

    public int getFkIdLojaUniversal() {return fkIdLojaUniversal;}
    public void setFkIdLojaUniversal(int fkIdLojaUniversal) {this.fkIdLojaUniversal = fkIdLojaUniversal;}
    
    public int getFkIdTipoAnimal() {return fkIdTipoAnimal;}
    public void setFkIdTipoAnimal(int fkIdTipoAnimal) {this.fkIdTipoAnimal = fkIdTipoAnimal;}
    
    public BigDecimal getPrecoUnitario() {return precoUnitario;}
    public void setPrecoUnitario(BigDecimal precoUnitario) {this.precoUnitario = precoUnitario;}
    
    public int getQuantidadeDisponivel() {return quantidadeDisponivel;}
    public void setQuantidadeDisponivel(int quantidadeDisponivel) {this.quantidadeDisponivel = quantidadeDisponivel;}
   
    public String getNomeAnimal() {return nomeAnimal;}
    public void setNomeAnimal(String nomeAnimal) {this.nomeAnimal = nomeAnimal;}
}

