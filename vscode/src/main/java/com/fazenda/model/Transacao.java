package com.fazenda.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transacao {
    private int id;
    private LocalDateTime dataTransacao;
    private String tipoTransacao;
    private int quantidade;
    private String produto;
    private BigDecimal valorTotal;
    private String tipoItem;
    private Integer fkIdAnimais;
    private Integer fkIdRancho;
    private int fkIdFazendeiro;

    public Transacao() {}

    public Transacao(int id, LocalDateTime dataTransacao, String tipoTransacao, int quantidade,
                     String produto, BigDecimal valorTotal, String tipoItem,
                     Integer fkIdAnimais, Integer fkIdRancho, int fkIdFazendeiro) {
        this.id = id;
        this.dataTransacao = dataTransacao;
        this.tipoTransacao = tipoTransacao;
        this.quantidade = quantidade;
        this.produto = produto;
        this.valorTotal = valorTotal;
        this.tipoItem = tipoItem;
        this.fkIdAnimais = fkIdAnimais;
        this.fkIdRancho = fkIdRancho;
        this.fkIdFazendeiro = fkIdFazendeiro;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    
    public LocalDateTime getDataTransacao() {return dataTransacao;}
    public void setDataTransacao(LocalDateTime dataTransacao) {this.dataTransacao = dataTransacao;}
    
    public String getTipoTransacao() {return tipoTransacao;}
    public void setTipoTransacao(String tipoTransacao) {this.tipoTransacao = tipoTransacao;}
    
    public int getQuantidade() {return quantidade;}
    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}
    
    public String getProduto() {return produto;}
    public void setProduto(String produto) {this.produto = produto;}
    
    public BigDecimal getValorTotal() {return valorTotal;}
    public void setValorTotal(BigDecimal valorTotal) {this.valorTotal = valorTotal;}
    
    public String getTipoItem() {return tipoItem;}
    public void setTipoItem(String tipoItem) {this.tipoItem = tipoItem;}
    
    public Integer getFkIdAnimais() {return fkIdAnimais;}
    public void setFkIdAnimais(Integer fkIdAnimais) {this.fkIdAnimais = fkIdAnimais;}
    
    public Integer getFkIdRancho() {return fkIdRancho;}
    public void setFkIdRancho(Integer fkIdRancho) {this.fkIdRancho = fkIdRancho;}
    
    public int getFkIdFazendeiro() {return fkIdFazendeiro;}
    public void setFkIdFazendeiro(int fkIdFazendeiro) {this.fkIdFazendeiro = fkIdFazendeiro;}
}

