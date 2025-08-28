package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.view.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home-voluntario")
    public String homeVoluntario() {
        return "home/home-voluntario.html";
    }

    @GetMapping("/home-representante")
    public String homeRepresentante() {
        return "home/home-representante.html";
    }
}
