package com.fazenda.model;

public class EstoqueAnimais {
    private int id;
    private int fkIdTipoAnimal;
    private int quantidade;
    private int fkIdRancho;

    public EstoqueAnimais() {}

    public EstoqueAnimais(int id, int fkIdTipoAnimal, int quantidade, int fkIdRancho) {
        this.id = id;
        this.fkIdTipoAnimal = fkIdTipoAnimal;
        this.quantidade = quantidade;
        this.fkIdRancho = fkIdRancho;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    
    public int getFkIdTipoAnimal() {return fkIdTipoAnimal;}
    public void setFkIdTipoAnimal(int fkIdTipoAnimal) {this.fkIdTipoAnimal = fkIdTipoAnimal;}
   
    public int getQuantidade() {return quantidade;}
    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}
    
    public int getFkIdRancho() {return fkIdRancho;}
    public void setFkIdRancho(int fkIdRancho) {this.fkIdRancho = fkIdRancho;}
}
