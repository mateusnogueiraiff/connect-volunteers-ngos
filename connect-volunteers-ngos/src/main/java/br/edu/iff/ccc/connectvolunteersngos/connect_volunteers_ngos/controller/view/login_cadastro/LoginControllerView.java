package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.view.login_cadastro;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginControllerView {

    @GetMapping("/login")
    public String mexibirLogin() {
        return "login-cadastro/login";
    }

    @PostMapping("/login")
    public String processarLogin(
        @RequestParam("email") String email,
        @RequestParam("senha") String senha,
        Model model
    ) {
        //aq vai entrar a lógica de autenticação futuramente

        //teste enquanto n tem a lógica ainda
        if ("admin@email.com".equals(email) && "123456".equals(senha)) {
            return "redirect:home-administrador"; // Redireciona para a home após login
        }

        model.addAttribute("erro", "E-mail ou senha inválidos.");
        return "login-cadastro/login";
    }
}
