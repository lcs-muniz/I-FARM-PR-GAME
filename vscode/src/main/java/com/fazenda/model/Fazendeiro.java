package com.fazenda.model;

import java.math.BigDecimal;

public class Fazendeiro {
    private int id;
    private String nome;
    private char sexo;
    private int idade;
    private BigDecimal saldo;
    private int nivel;

    public Fazendeiro() {}

    public Fazendeiro(int id, String nome, char sexo, int idade, BigDecimal saldo, int nivel) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.saldo = saldo;
        this.nivel = nivel;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public char getSexo() {return sexo;}
    public void setSexo(char sexo) {this.sexo = sexo;}

    public int getIdade() {return idade;}
    public void setIdade(int idade) {this.idade = idade;}

    public BigDecimal getSaldo() {return saldo;}
    public void setSaldo(BigDecimal saldo) {this.saldo = saldo;}
    
    public int getNivel() {return nivel;}
    public void setNivel(int nivel) {this.nivel = nivel;}
}
