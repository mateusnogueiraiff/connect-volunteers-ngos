package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.view.home;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Usuario;

@Controller
public class HomeController {

    @GetMapping("/home-voluntario")
    public String homeVoluntario(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) {
            return "redirect:/login"; // força login se não estiver autenticado
        }
        model.addAttribute("usuario", usuario);
        return "home/home-voluntario.html";
    }

    @GetMapping("/home-representante")
    public String homeRepresentante(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) {
            return "redirect:/login";
        }
        model.addAttribute("usuario", usuario);
        return "home/home-representante.html";
    }

    @GetMapping("/home-administrador")
    public String homeAdministrador() {
        return "home/home-administrador.html";
    }
}
