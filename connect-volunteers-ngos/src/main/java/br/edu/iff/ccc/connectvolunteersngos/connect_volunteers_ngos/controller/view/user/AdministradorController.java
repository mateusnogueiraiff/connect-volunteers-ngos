package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.view.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Administrador;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.user.AdministradorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @GetMapping("/{id}")
    public String getAdministradorPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("administrador", administradorService.findAdministradorById(id));
        return "user/administrador/administrador.html";
    }

    @GetMapping
    public String getAllAdministradores(Model model) {
        model.addAttribute("administradores", administradorService.findAllAdministradores());
        return "user/administrador/administradores.html";
    }

    @GetMapping("/new")
    public String createNewAdministrador(Model model) {
        model.addAttribute("administrador", new Administrador());
        return "user/administrador/cadastro-administrador.html";
    }

    @PostMapping
    public String saveAdministrador(@Valid Administrador administrador, BindingResult error, Model model, RedirectAttributes redirectAttributes) {
        if (error.hasErrors()) {
            model.addAttribute("errorMessage", "Erro ao salvar o administrador.");
            return "user/administrador/cadastro-administrador.html";
        }
        administradorService.saveAdministrador(administrador);
        redirectAttributes.addFlashAttribute("successMessage", "Administrador salvo com sucesso!");
        return "redirect:/administradores";
    }

    // --- CRUD ---

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("administrador", administradorService.findAdministradorById(id));
        return "user/administrador/editar-administrador.html";
    }

    @PostMapping("/update/{id}")
    public String updateAdministrador(@PathVariable("id") Long id, @Valid Administrador adminForm, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao atualizar. Verifique os campos.");
            return "redirect:/administradores/edit/" + id;
        }
        try {
            Administrador existente = administradorService.findAdministradorById(id);
            existente.setNome(adminForm.getNome());
            existente.setEmail(adminForm.getEmail());
            existente.setTelefone(adminForm.getTelefone());
            
            administradorService.saveAdministrador(existente);
            redirectAttributes.addFlashAttribute("successMessage", "Perfil atualizado com sucesso!");
            return "redirect:/administradores/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ocorreu um erro ao atualizar o perfil.");
            return "redirect:/administradores/edit/" + id;
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteAdministrador(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            administradorService.deleteAdministrador(id);
            redirectAttributes.addFlashAttribute("successMessage", "Administrador excluído com sucesso!");
            return "redirect:/administradores";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao excluir o administrador.");
            return "redirect:/administradores";
        }
    }
}