package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.view.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Administrador;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.AdministradorService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequestMapping(path = "administradores")
public class AdministradorController {

    @Autowired
    AdministradorService administradorService;

    @GetMapping("/{id}")
    public String getAdministradoresPage(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {

       Administrador administrador = administradorService.findAdministradorById(id);

        if (administrador == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Administrador não encontrado!");
            return "redirect:/administradores";
        }

        model.addAttribute("administrador", administrador);
        return "user/administrador/administrador.html";
    }

    @GetMapping()
    public String getAllAdministradores(Model model) {
        model.addAttribute("administradores", administradorService.findAllAdministradores());
        return "user/administrador/administradores.html";
    }

    @GetMapping("/new")
    public String createNewAdministrador(Model model) {
        model.addAttribute("administrador", new Administrador());
        return "user/administrador/cadastro-administrador.html";
    }
    

    @PostMapping()
    public String saveAdministrador(@Valid Administrador administrador, BindingResult error, Model model, RedirectAttributes redirectAttributes) {
        if (error.hasErrors()) {
            model.addAttribute("errorMessage", "Erro ao salvar o administrador.");
            return "user/administrador/cadastro-administrador.html";
        }

        administradorService.saveAdministrador(administrador);
        redirectAttributes.addFlashAttribute("successMessage", "Administrador salvo com sucesso!");
        return "redirect:/administradores";
    }
}
