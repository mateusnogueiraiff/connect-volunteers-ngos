package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.dto;

import java.io.Serializable;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.Ong;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class VagaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "O nome da Ong não pode ser vazio.")
    @Size(min = 1, max = 100, message = "O nome da Ong entre 1 e 30 caracteres.") 
    private String nome;

    @NotBlank(message = "A descrição da Ong não pode ser vazia.")
    @Size(min = 1, max = 100, message = "O nome da Ong entre 1 e 200 caracteres.")
    private String descricao;

    @NotBlank(message = "Os requisitos da Ong não pode estar vazio.")
    @Size(min = 1, max = 100, message = "Os requisitos da Ong entre 1 e 100 caracteres.")
    private String requisitos;

    @NotBlank(message = "O local da Ong não pode estar vazio.")
    @Size(min = 1, max = 100, message = "O local da Ong deve estar entre 1 e 50 caracteres.")
    private String local;
    
    @NotBlank(message = "A data e hora da Ong não pode estar vazio.")
    @Size(min = 16, max = 16, message = "A formatação deve ser:dd/MM/yyyy HH:mm")
    private String dataHorario;

    @Positive(message = "A quantidade de vagas deve ser maior que 0.")
    private int quantidadeDeVagas;

    private Ong ong; 

    public VagaDTO() {}


    public VagaDTO(Long id, String nome, String descricao, String requisitos,
                   String local, String dataHorario, int quantidadeDeVagas, Ong ong) {
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
