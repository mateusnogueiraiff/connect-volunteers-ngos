package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class Representante extends Usuario {

    @NotNull
    private int idRepresentante;

    public Representante(){

    }

    public Representante(int idRepresentante, Long id, String nome, String email, String senha, String telefone, String funcao) {
        super(id, nome, email, senha, telefone, funcao);
        this.idRepresentante = idRepresentante;
    }

    public int getIdRepresentante() {
        return idRepresentante;
    }
    public void setIdRepresentante(int idRepresentante) {
        this.idRepresentante = idRepresentante;
    }
}

