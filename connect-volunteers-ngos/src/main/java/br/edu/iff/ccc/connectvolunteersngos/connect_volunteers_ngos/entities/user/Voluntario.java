package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user;

import jakarta.persistence.Entity;

@Entity
public class Voluntario extends Usuario {

    public Voluntario(){

    }

    public Voluntario(Long id, String nome, String email, String senha, String telefone, String funcao) {
        super(id, nome, email, senha, telefone, funcao);
    }
}
