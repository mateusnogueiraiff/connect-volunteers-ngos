package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.Produto;

@Service
public class ProdutoService {

    public void saveProduto(Produto produto) {
        System.out.println("ID: " + produto.getId() +
        "\nNome: " + produto.getNome() + 
        "\nPreço: " + produto.getPreco() +
        "\nDescrição " + produto.getDescricao() +
        "\nQuantidade em Estoque: " + produto.getQuantidadeEstoque() +
        "\nCategoria: " + produto.getCategoria());
    }

    public Produto findProdutoById(Long id) {
        Produto produto = new Produto();

        if (id == null) {
            return null;
        }

        produto.setId(id);
        produto.setNome("Produto exemplo");
        produto.setPreco(100);
        produto.setDescricao("Descrição do Produto Exemplo");
        produto.setQuantidadeEstoque(50);
        produto.setCategoria("Categoria Exemplo");

        return produto;
    }

    public ArrayList<Produto> findAllProdutos() {
        Produto produto1 = new Produto(1L, "Produto 1", 50.0, "Descrição do Produto 1", 20, "Categoria A");
        Produto produto2 = new Produto(2L, "Produto 2", 75.0, "Descrição do Produto 2", 20, "Categoria B");
        Produto produto3 = new Produto(3L, "Produto 3", 120.0, "Descrição do Produto 3", 20, "Categoria C");

        ArrayList<Produto> produtos = new ArrayList<>();
        produtos.add(produto1);
        produtos.add(produto2);
        produtos.add(produto3);

        return produtos;
    }
}
