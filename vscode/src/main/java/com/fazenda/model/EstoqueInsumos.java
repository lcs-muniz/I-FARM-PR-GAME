package com.fazenda.model;

public class EstoqueInsumos {
    private int id;
    private int quantidade;
    private int fkIdInsumo;
    private int fkIdRancho;

    public EstoqueInsumos() {}

    public EstoqueInsumos(int id, int quantidade, int fkIdInsumo, int fkIdRancho) {
        this.id = id;
        this.quantidade = quantidade;
        this.fkIdInsumo = fkIdInsumo;
        this.fkIdRancho = fkIdRancho;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    
    public int getQuantidade() {return quantidade;}
    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}
    
    public int getFkIdInsumo() {return fkIdInsumo;}
    public void setFkIdInsumo(int fkIdInsumo) {this.fkIdInsumo = fkIdInsumo;}
    
    public int getFkIdRancho() {return fkIdRancho;}
    public void setFkIdRancho(int fkIdRancho) {this.fkIdRancho = fkIdRancho;}
}
