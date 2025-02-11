package com.fazenda.model;

public class Insumos {
    private int id;
    private String nome;
    private String marca;
    private String descricao;
    private TipoInsumo tipoInsumo;

    public Insumos() {}

    public Insumos(int id, String nome, String marca, String descricao, TipoInsumo tipoInsumo) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.descricao = descricao;
        this.tipoInsumo = tipoInsumo;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public TipoInsumo getTipoInsumo() {
        return tipoInsumo;
    }
    public void setTipoInsumo(TipoInsumo tipoInsumo) {
        this.tipoInsumo = tipoInsumo;
    }
}

