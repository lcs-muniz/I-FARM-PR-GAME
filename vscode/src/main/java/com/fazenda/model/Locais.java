package com.fazenda.model;

public class Locais {
    private int id;
    private int capacidade;
    private String tipoAlimentacao;
    private int nivel;
    private TipoLocal tipoLocal; // Usando o enum
    private int fkIdRancho;


    public Locais() {}

    public Locais(int id, int capacidade, String tipoAlimentacao, int nivel, TipoLocal tipoLocal, int fkIdRancho) {
        this.id = id;
        this.capacidade = capacidade;
        this.tipoAlimentacao = tipoAlimentacao;
        this.nivel = nivel;
        this.tipoLocal = tipoLocal;
        this.fkIdRancho = fkIdRancho;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getCapacidade() {return capacidade;}
    public void setCapacidade(int capacidade) {this.capacidade = capacidade;}

    public String getTipoAlimentacao() {return tipoAlimentacao;}
    public void setTipoAlimentacao(String tipoAlimentacao) {this.tipoAlimentacao = tipoAlimentacao;}

    public int getNivel() {return nivel;}
    public void setNivel(int nivel) {this.nivel = nivel;}

    public TipoLocal getTipoLocal() {return tipoLocal;}
    public void setTipoLocal(TipoLocal tipoLocal) {this.tipoLocal = tipoLocal;}
    
    public int getFkIdRancho() {return fkIdRancho;}
    public void setFkIdRancho(int fkIdRancho) {this.fkIdRancho = fkIdRancho;}
}
