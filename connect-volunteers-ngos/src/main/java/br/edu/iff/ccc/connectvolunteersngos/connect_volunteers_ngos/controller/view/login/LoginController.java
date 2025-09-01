package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.view.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.*;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.LoginService;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        return "login/login.html";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String email, @RequestParam String senha, Model model, RedirectAttributes redirectAttributes) {
        Usuario usuario = loginService.autenticar(email, senha);

        if (usuario == null) {
            model.addAttribute("errorMessage", "Email ou senha inválidos!");
            return "login/login.html";
        }

        /*verifica se o objeto é do tipo Voluntario ou Representante
        if (usuario instanceof Voluntario) {
            redirectAttributes.addFlashAttribute("successMessage", "Login realizado com sucesso!");
            return "redirect:/home-voluntario";
        } else if (usuario instanceof Representante) {
            redirectAttributes.addFlashAttribute("successMessage", "Login realizado com sucesso!");
            return "redirect:/home-representante";
        }*/

        // verifica se o objeto é do tipo Voluntario, Representante ou Administrador
        if (usuario instanceof Voluntario) {
            redirectAttributes.addFlashAttribute("successMessage", "Login realizado com sucesso!");
            return "redirect:/home-voluntario";
        } else if (usuario instanceof Representante) {
            redirectAttributes.addFlashAttribute("successMessage", "Login realizado com sucesso!");
            return "redirect:/home-representante";
        } else if (usuario instanceof Administrador) {
            redirectAttributes.addFlashAttribute("successMessage", "Login realizado com sucesso!");
            return "redirect:/home-administrador";
        }


        // fallback
        model.addAttribute("errorMessage", "Erro inesperado!");
        return "login/login.html";
    }

    @GetMapping("/logout")
    public String logout(RedirectAttributes redirectAttributes) {
        // aqui futuramente podemos limpar a sessão se usarmos HttpSession
        redirectAttributes.addFlashAttribute("successMessage", "Logout realizado com sucesso!");
        return "redirect:/login";
    }
}
