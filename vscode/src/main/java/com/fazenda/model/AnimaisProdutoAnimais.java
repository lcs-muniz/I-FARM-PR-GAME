package com.fazenda.model;

public class AnimaisProdutoAnimais {
    private int fkIdAnimais;
    private int fkIdProdutoAnimais;
    private int quantidade;

    public AnimaisProdutoAnimais() {}

    public AnimaisProdutoAnimais(int fkIdAnimais, int fkIdProdutoAnimais, int quantidade) {
        this.fkIdAnimais = fkIdAnimais;
        this.fkIdProdutoAnimais = fkIdProdutoAnimais;
        this.quantidade = quantidade;
    }

    public int getFkIdAnimais() {return fkIdAnimais;}
    public void setFkIdAnimais(int fkIdAnimais) {this.fkIdAnimais = fkIdAnimais;}
    
    public int getFkIdProdutoAnimais() {return fkIdProdutoAnimais;}
    public void setFkIdProdutoAnimais(int fkIdProdutoAnimais) {this.fkIdProdutoAnimais = fkIdProdutoAnimais;}
    
    public int getQuantidade() {return quantidade;}
    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}
}

