package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user;

import jakarta.persistence.Entity;

@Entity
public class Administrador extends Usuario {

    public Administrador(){

    }

    public Administrador(int idAdministrador, Long id, String nome, String email, String senha, String telefone, String funcao) {
        super(id, nome, email, senha, telefone, funcao);
    }
}

