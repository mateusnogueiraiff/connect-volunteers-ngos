package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.Produto;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.ProdutoService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/principal/testeAula")
public class TesteAula {

    @Autowired
    ProdutoService produtoService;

    @GetMapping("/{id}")
    public String getHomePage(@Valid Produto produto, @PathVariable("id") Long id, Model model) {

        produto = produtoService.findProdutoById(id);
        model.addAttribute("produto", produto);

        return "testeAula.html";
    }

    @PostMapping
    public String processarFormulario(
        @RequestParam("id") Long id,
        @RequestParam("nomeRepresentante") String nomeRepresentante,
        @RequestParam("nomeONG") String nomeONG,
        @RequestParam("descricao") String descricao,
        @RequestParam("qtdVoluntarios") int qtdVoluntarios,
        Model model) {

        model.addAttribute("id", id);
        model.addAttribute("nomeRepresentante", nomeRepresentante);
        model.addAttribute("nomeONG", nomeONG);
        model.addAttribute("descricao", descricao);
        model.addAttribute("qtdVoluntarios", qtdVoluntarios);

        return "testeAula.html";
    }
}
