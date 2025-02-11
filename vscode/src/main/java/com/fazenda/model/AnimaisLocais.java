package com.fazenda.model;

public class AnimaisLocais {
    private int fkIdAnimais;
    private int fkIdLocais;

    public AnimaisLocais() {}

    public AnimaisLocais(int fkIdAnimais, int fkIdLocais) {
        this.fkIdAnimais = fkIdAnimais;
        this.fkIdLocais = fkIdLocais;
    }

    public int getFkIdAnimais() {return fkIdAnimais;}
    public void setFkIdAnimais(int fkIdAnimais) {this.fkIdAnimais = fkIdAnimais;}
    
    public int getFkIdLocais() {return fkIdLocais;}
    public void setFkIdLocais(int fkIdLocais) {this.fkIdLocais = fkIdLocais;}
}
