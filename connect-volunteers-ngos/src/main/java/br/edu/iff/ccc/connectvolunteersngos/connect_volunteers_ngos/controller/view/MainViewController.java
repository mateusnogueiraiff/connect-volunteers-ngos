package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping(path = "principal")
public class MainViewController {
    
    @GetMapping("")
    public String getHomePage() {
        return "index.html";
    }
    
}
