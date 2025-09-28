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

    @GetMapping()
    public String getAllVoluntarios(Model model) {
        model.addAttribute("voluntarios", voluntarioService.findAllVoluntarios());
        return "user/voluntario/voluntarios.html";
    }

    @GetMapping("/{id}")
    public String getVoluntariosPage(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {

        Voluntario voluntario = voluntarioService.findVoluntarioById(id);

        /* Não se aplica mais, pois agora a exceção já é tratada dentro do método "voluntarioService.findVoluntarioById(id)"
        
        if (voluntario == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Voluntário não encontrado!");
            return "redirect:/voluntarios";
        } */

        model.addAttribute("voluntario", voluntario);
        return "user/voluntario/voluntario.html";
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

    // Exibe o formulário de edição
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Voluntario voluntario = voluntarioService.findVoluntarioById(id);
        model.addAttribute("voluntario", voluntario);
        return "user/voluntario/editar-voluntario.html";
    }

    // Processa a atualização do formulário
   @PostMapping("/update/{id}")
    public String updateVoluntario(@PathVariable("id") Long id, @Valid Voluntario voluntarioForm, BindingResult result, RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao atualizar o perfil. Verifique os campos.");
            return "redirect:/voluntarios/edit/" + id;
        }

        try {
            Voluntario voluntarioExistente = voluntarioService.findVoluntarioById(id);

            // Atualiza os dados do objeto persistido
            voluntarioExistente.setNome(voluntarioForm.getNome());
            voluntarioExistente.setEmail(voluntarioForm.getEmail());
            voluntarioExistente.setTelefone(voluntarioForm.getTelefone());
            voluntarioExistente.setDataNasc(voluntarioForm.getDataNasc());

            voluntarioService.saveVoluntario(voluntarioExistente);

            redirectAttributes.addFlashAttribute("successMessage", "Perfil atualizado com sucesso!");
            return "redirect:/voluntarios/" + id;

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ocorreu um erro ao atualizar o perfil.");
            return "redirect:/voluntarios/edit/" + id;
        }
    }

    // Processa a exclusão do voluntário
    @PostMapping("/delete/{id}")
    public String deleteVoluntario(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            voluntarioService.findVoluntarioById(id);
            voluntarioService.deleteVoluntario(id);
            redirectAttributes.addFlashAttribute("successMessage", "Perfil excluído com sucesso!");
            return "redirect:/";
        } catch (Exception e) {
            // Captura outras exceções que possam ocorrer durante a exclusão
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao excluir o perfil. O voluntário pode estar associado a inscrições.");
            return "redirect:/voluntarios";
        }
    }
}