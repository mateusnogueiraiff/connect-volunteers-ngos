package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities;

import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Voluntario {
    @Id
    @NotNull
    private Long idVoluntario;

    @NotEmpty(message = "O campo nome não pode ficar em branco")
    private String nome;
    private String email;

    @Size(min = 8, max = 20, message = "A senha deve conter de 8 a 20 caracteres")
    private String senha;
    private String telefone;
    private String funcao;
    
    public Voluntario() {

    }

    public Voluntario(Long idVoluntario, String nome, String email, String senha, String telefone, String funcao) {
        this.idVoluntario = idVoluntario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.funcao = funcao;
    }

    public Long getIdVoluntario() {
        return idVoluntario;
    }

    public void setIdVoluntario(Long idVoluntario) {
        this.idVoluntario = idVoluntario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}

// Após aplicar herança
/* @Entity
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
} */
