package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.view.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VoluntarioControllerView {

    @GetMapping("/home-voluntario")
    public String homeVoluntario(Model model) {
        model.addAttribute("nome", "Romilton");

        return "home/home-voluntario";
    }
}
