package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities;

import java.io.Serializable;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

@Entity
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    private Long id;

    @NotEmpty(message = "O campo nome não pode ficar em branco")
    private String nome;

    @Size(min = 1, max = 100, message = "Valor inválido")
    private double preco;

    private String descricao;
    private int quantidadeEstoque;
    private String categoria;

    public Produto(){

    }

    public Produto(Long id, String nome, double preco, String descricao, int quantidadeEstoque, String categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoria = categoria;
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
