package com.fazenda.model;

import java.math.BigDecimal;

public class ProdutoAnimais {
    private int id;
    private String tipoProduto;
    private String nome;
    private BigDecimal precoUnitario;

    public ProdutoAnimais() {}

    public ProdutoAnimais(int id, String tipoProduto, String nome, BigDecimal precoUnitario) {
        this.id = id;
        this.tipoProduto = tipoProduto;
        this.nome = nome;
        this.precoUnitario = precoUnitario;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getTipoProduto() {return tipoProduto;}
    public void setTipoProduto(String tipoProduto) {this.tipoProduto = tipoProduto;}
    
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    
    public BigDecimal getPrecoUnitario() {return precoUnitario;}
    public void setPrecoUnitario(BigDecimal precoUnitario) {this.precoUnitario = precoUnitario;}

}
