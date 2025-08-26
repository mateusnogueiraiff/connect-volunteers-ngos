package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user;

import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class Voluntario extends Usuario {

    private LocalDate dataNasc;
    private int idade;

    public Voluntario(){

    }

    public Voluntario(Long id, String nome, String email, String senha, String telefone, String funcao, LocalDate dataNasc, int idade) {
        super(id, nome, email, senha, telefone, funcao);
        this.dataNasc = dataNasc;
        this.idade = idade;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }
    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
}
