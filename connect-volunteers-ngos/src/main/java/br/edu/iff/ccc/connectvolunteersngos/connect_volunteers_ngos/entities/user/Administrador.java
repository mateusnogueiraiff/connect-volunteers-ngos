package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class Administrador extends Usuario {

    @NotNull
    private int idAdministrador;

    public Administrador(){

    }

    public Administrador(int idAdministrador, Long id, String nome, String email, String senha, String telefone, String funcao) {
        super(id, nome, email, senha, telefone, funcao);
        this.idAdministrador = idAdministrador;
    }

    public int getIdAdminstrador() {
        return idAdministrador;
    }
    public void setIdAdminstrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }
}

