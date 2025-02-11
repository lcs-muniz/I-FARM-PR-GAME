package com.fazenda.model;

public class Rancho {
    private int id;
    private String nome;
    private int nivel;
    private Integer fkIdFazendeiro;

    public Rancho() {}

    public Rancho(int id, String nome, int nivel, Integer fkIdFazendeiro) {
        this.id = id;
        this.nome = nome;
        this.nivel = nivel;
        this.fkIdFazendeiro = fkIdFazendeiro;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    
    public int getNivel() {return nivel;}
    public void setNivel(int nivel) {this.nivel = nivel;}
    
    public Integer getFkIdFazendeiro() {return fkIdFazendeiro;}
    public void setFkIdFazendeiro(Integer fkIdFazendeiro) {this.fkIdFazendeiro = fkIdFazendeiro;}
}
