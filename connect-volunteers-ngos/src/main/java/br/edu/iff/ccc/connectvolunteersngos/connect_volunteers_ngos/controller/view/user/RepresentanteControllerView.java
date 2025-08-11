package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.view.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RepresentanteControllerView {

    @GetMapping("/home-representante")
    public String exibirHome(Model model) {
        model.addAttribute("nome", "Nog");

        return "home/home-representante";
    }
}
