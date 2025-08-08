package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/principal/testeAula")
public class TesteAula {

    @GetMapping("/{id}")
    public String getHomePage(@PathVariable("id") String id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("nomeONG", "AmONG Us");
        model.addAttribute("nomeRepresentante", "Juninho");
        model.addAttribute("descricao", "ONG maneira");
        model.addAttribute("qtdVoluntarios", 10);

        return "testeAula.html";
    }

    @PostMapping
    public String processarFormulario(
        @RequestParam("id") String id,
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
