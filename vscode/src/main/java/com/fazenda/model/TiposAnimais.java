package com.fazenda.model;

public class TiposAnimais {
    private int id;
    private String nome;
    private String tipoAlimentacao;
    private String descricao;

    public TiposAnimais() {}

    public TiposAnimais(int id, String nome, String tipoAlimentacao, String descricao) {
        this.id = id;
        this.nome = nome;
        this.tipoAlimentacao = tipoAlimentacao;
        this.descricao = descricao;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    
    public String getTipoAlimentacao() {return tipoAlimentacao;}
    public void setTipoAlimentacao(String tipoAlimentacao) {this.tipoAlimentacao = tipoAlimentacao;}
    
    public String getDescricao() {return descricao;}
    public void setDescricao(String descricao) {this.descricao = descricao;}
}

