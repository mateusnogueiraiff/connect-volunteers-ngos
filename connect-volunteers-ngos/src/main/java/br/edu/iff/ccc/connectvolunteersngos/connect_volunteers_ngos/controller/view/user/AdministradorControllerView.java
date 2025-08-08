package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.view.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdministradorControllerView {

    @GetMapping("/home-administrador")
    public String exibirHome(Model model) {
        model.addAttribute("nome", "Mateus");
        
        return "home/home-administrador";
    }
}

