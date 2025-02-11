package com.fazenda.model;

import java.util.Date;

public class Animal {
    private int id;
    private String nome;
    private char sexo;
    private float peso;
    private EstadoSaude estadoSaude;
    private Date dataNascimento;
    private int fkIdTipoAnimal;   
    private int fkIdRancho;       
    
    private String tipoAnimal;   

    public Animal(int id, String nome, char sexo, float peso, 
                  EstadoSaude estadoSaude, Date dataNascimento, int fkIdTipoAnimal, int fkIdRancho) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.peso = peso;
        this.estadoSaude = estadoSaude;
        this.dataNascimento = dataNascimento;
        this.fkIdTipoAnimal = fkIdTipoAnimal;
        this.fkIdRancho = fkIdRancho;
    }

    public Animal() {}

    public int getId() { return id;}
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public char getSexo() { return sexo; }
    public void setSexo(char sexo) { this.sexo = sexo; }

    public float getPeso() { return peso; }
    public void setPeso(float peso) { this.peso = peso; }

    public EstadoSaude getEstadoSaude() { return estadoSaude; }
    public void setEstadoSaude(EstadoSaude estadoSaude) { this.estadoSaude = estadoSaude; }

    public Date getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; }

    public int getFkIdTipoAnimal() { return fkIdTipoAnimal; }
    public void setFkIdTipoAnimal(int fkIdTipoAnimal) {  this.fkIdTipoAnimal = fkIdTipoAnimal;  }

    public int getFkIdRancho() { return fkIdRancho;  }
    public void setFkIdRancho(int fkIdRancho) { this.fkIdRancho = fkIdRancho; }

    public String getTipoAnimal() { return tipoAnimal; }
    public void setTipoAnimal(String tipoAnimal) {  this.tipoAnimal = tipoAnimal;}
}
