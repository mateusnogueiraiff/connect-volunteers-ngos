package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities;

import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class Voluntario extends Usuario {

    @Id
    @NotNull
    private Long idVoluntario;

    public Voluntario(){

    }

    public Voluntario(Long idVoluntario, Long id, String nome, String email, String senha, String telefone, String funcao) {
        super(id, nome, email, senha, telefone, funcao);
        this.idVoluntario = idVoluntario;
    }

    public Long getIdVoluntario() {
        return idVoluntario;
    }
    public void setIdVoluntario(Long idVoluntario) {
        this.idVoluntario = idVoluntario;
    }
}
