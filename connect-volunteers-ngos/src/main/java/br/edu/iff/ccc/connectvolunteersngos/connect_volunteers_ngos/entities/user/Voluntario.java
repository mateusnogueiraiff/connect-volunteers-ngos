package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class Voluntario extends Usuario {

    @NotNull
    private int idVoluntario;

    public Voluntario(){

    }

    public Voluntario(int idVoluntario, Long id, String nome, String email, String senha, String telefone, String funcao) {
        super(id, nome, email, senha, telefone, funcao);
        this.idVoluntario = idVoluntario;
    }

    public int getIdVoluntario() {
        return idVoluntario;
    }
    public void setIdVoluntario(int idVoluntario) {
        this.idVoluntario = idVoluntario;
    }
}
