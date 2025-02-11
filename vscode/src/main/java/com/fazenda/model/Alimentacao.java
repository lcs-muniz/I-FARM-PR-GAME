package com.fazenda.model;

import java.time.LocalDateTime;

public class Alimentacao {
    private int id;
    private LocalDateTime horarioAlimentacao;
    private AlimentacaoStatus status;
    private int fkIdAnimais;
    private int fkIdInsumos;

    public Alimentacao() {}

    public Alimentacao(int id, LocalDateTime horarioAlimentacao, AlimentacaoStatus status, int fkIdAnimais, int fkIdInsumos) {
        this.id = id;
        this.horarioAlimentacao = horarioAlimentacao;
        this.status = status;
        this.fkIdAnimais = fkIdAnimais;
        this.fkIdInsumos = fkIdInsumos;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public LocalDateTime getHorarioAlimentacao() {return horarioAlimentacao;}
    public void setHorarioAlimentacao(LocalDateTime horarioAlimentacao) {this.horarioAlimentacao = horarioAlimentacao;}
    
    public AlimentacaoStatus getStatus() {return status;}
    public void setStatus(AlimentacaoStatus status) {this.status = status;}
   
    public int getFkIdAnimais() {return fkIdAnimais;}
    public void setFkIdAnimais(int fkIdAnimais) {this.fkIdAnimais = fkIdAnimais;}
    
    public int getFkIdInsumos() {return fkIdInsumos;}
    public void setFkIdInsumos(int fkIdInsumos) {this.fkIdInsumos = fkIdInsumos;}
}
