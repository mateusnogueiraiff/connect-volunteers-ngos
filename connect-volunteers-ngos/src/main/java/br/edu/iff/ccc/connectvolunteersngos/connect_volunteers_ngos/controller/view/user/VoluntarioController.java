package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.view.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Voluntario;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.user.VoluntarioService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "voluntarios")
public class VoluntarioController {

    @Autowired
    VoluntarioService voluntarioService;

    @GetMapping("/{id}")
    public String getVoluntariosPage(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {

        Voluntario voluntario = voluntarioService.findVoluntarioById(id);

        /* Não se aplica mais, pois agora a exceção já é tratada dentro do método "voluntarioService.findVoluntarioById(id)"
        
        if (voluntario == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Voluntário não encontrado!");
            return "redirect:/voluntarios";
        } */

        model.addAttribute("voluntário", voluntario);
        return "user/voluntario/voluntario.html";
    }

    @GetMapping()
    public String getAllVoluntarios(Model model) {
        model.addAttribute("voluntarios", voluntarioService.findAllVoluntarios());
        return "user/voluntario/voluntarios.html";
    }

    @GetMapping("/new")
    public String createNewVoluntario(Model model) {
        model.addAttribute("voluntario", new Voluntario());
        return "user/voluntario/cadastro-voluntario.html";
    }
    

    @PostMapping()
    public String saveVoluntario(@Valid Voluntario voluntario, BindingResult error, Model model, RedirectAttributes redirectAttributes) {
        if (error.hasErrors()) {
            model.addAttribute("errorMessage", "Erro ao salvar o voluntário.");
            return "user/voluntario/cadastro-voluntario.html";
        }

        voluntarioService.saveVoluntario(voluntario);
        redirectAttributes.addFlashAttribute("successMessage", "Voluntário salvo com sucesso!");
        return "redirect:/voluntarios";
    }
}