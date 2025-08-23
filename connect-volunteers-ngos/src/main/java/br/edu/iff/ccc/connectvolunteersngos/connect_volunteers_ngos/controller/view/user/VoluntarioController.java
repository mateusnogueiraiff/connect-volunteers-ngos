package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.view.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Voluntario;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.VoluntarioService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping(path = "voluntarios")
public class VoluntarioController {

    @Autowired
    VoluntarioService voluntarioService;

    @GetMapping("/{id}")
    public String getVoluntariosPage(@PathVariable("id") Long id, Model model) {

        Voluntario voluntario = voluntarioService.findVolunarioById(id);

        if (voluntario == null) {
            model.addAttribute("errorMessage", "Voluntário não encontrado.");
            return "erro.html";
        }

        model.addAttribute("voluntário", voluntario);
        return "voluntario/voluntario.html";
    }

    @GetMapping("")
    public String getAllVoluntarios(Model model) {
        model.addAttribute("voluntarios", voluntarioService.findAllVoluntarios());
        model.addAttribute("voluntario", new Voluntario());
        return "voluntario/voluntarios.html";
    }

    @PostMapping("")
    public String saveVoluntario(@Valid Voluntario voluntario, BindingResult error, Model model) {
        if (error.hasErrors()) {
            model.addAttribute("errorMessage", "Erro ao salvar o voluntário.");
        }

        voluntarioService.saveVoluntario(voluntario);
        //mudar para redirectAttribute
        model.addAttribute("successMessage", "Voluntário salvo com sucesso.");
        return "redirect:/voluntarios";
    }
}
