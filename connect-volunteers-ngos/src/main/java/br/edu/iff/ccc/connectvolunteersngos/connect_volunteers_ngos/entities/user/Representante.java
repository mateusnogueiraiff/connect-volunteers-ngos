package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.Ong;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Representante extends Usuario {

    @ManyToOne // vários representantes para uma ONG
    private Ong ong;

    public Representante(){

    }

    public Representante(Long id, String nome, String email, String senha, String telefone, String funcao, Ong ong) {
        super(id, nome, email, senha, telefone, funcao);
        this.ong = ong;
    }

    public Ong getOng() {
        return ong;
    }
    public void setOng(Ong ong) {
        this.ong = ong;
    }
}