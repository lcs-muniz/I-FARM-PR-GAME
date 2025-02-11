package com.fazenda.model;

import java.math.BigDecimal;

public class LojaInsumos {
    private int fkIdLojaUniversal;
    private int fkIdInsumo;
    private BigDecimal precoUnitario;
    private int quantidadeDisponivel;

    public LojaInsumos() {}

    public LojaInsumos(int fkIdLojaUniversal, int fkIdInsumo, BigDecimal precoUnitario, int quantidadeDisponivel) {
        this.fkIdLojaUniversal = fkIdLojaUniversal;
        this.fkIdInsumo = fkIdInsumo;
        this.precoUnitario = precoUnitario;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public int getFkIdLojaUniversal() {return fkIdLojaUniversal;}
    public void setFkIdLojaUniversal(int fkIdLojaUniversal) {this.fkIdLojaUniversal = fkIdLojaUniversal;}
    
    public int getFkIdInsumo() {return fkIdInsumo;}
    public void setFkIdInsumo(int fkIdInsumo) {this.fkIdInsumo = fkIdInsumo;}
    
    public BigDecimal getPrecoUnitario() {return precoUnitario;}
    public void setPrecoUnitario(BigDecimal precoUnitario) {this.precoUnitario = precoUnitario;}
    
    public int getQuantidadeDisponivel() {return quantidadeDisponivel;}
    public void setQuantidadeDisponivel(int quantidadeDisponivel) {this.quantidadeDisponivel = quantidadeDisponivel;}
}
