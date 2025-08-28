package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.oportunidade;

import java.io.Serializable;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.Ong;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Vaga implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private String requisitos;
    private String local;
    private String dataHorario;
    private int quantidadeDeVagas;

    @ManyToOne
    private Ong ong; // cada vaga está vinculada a uma ONG

    public Vaga() {}

    public Vaga(Long id, String nome, String descricao, String requisitos, String local, String dataHorario, int quantidadeDeVagas, Ong ong) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.requisitos = requisitos;
        this.local = local;
        this.dataHorario = dataHorario;
        this.quantidadeDeVagas = quantidadeDeVagas;
        this.ong = ong;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRequisitos() {
        return requisitos;
    }
    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getLocal() {
        return local;
    }
    public void setLocal(String local) {
        this.local = local;
    }

    public String getDataHorario() {
        return dataHorario;
    }
    public void setDataHorario(String dataHorario) {
        this.dataHorario = dataHorario;
    }

    public int getQuantidadeDeVagas() {
        return quantidadeDeVagas;
    }
    public void setQuantidadeDeVagas(int quantidadeDeVagas) {
        this.quantidadeDeVagas = quantidadeDeVagas;
    }

    public Ong getOng() {
        return ong;
    }
    public void setOng(Ong ong) {
        this.ong = ong;
    }
}