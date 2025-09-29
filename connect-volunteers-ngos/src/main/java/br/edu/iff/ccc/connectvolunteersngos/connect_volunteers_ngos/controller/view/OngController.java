package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.view;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.Ong;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.OngService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ongs")
public class OngController {

    @Autowired
    private OngService ongService;

    @GetMapping
    public String getAllOngs(Model model) {
        model.addAttribute("ongs", ongService.findAllOngs());
        return "ong/ongs";
    }

    @GetMapping("/{id}")
    public String getOngById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("ong", ongService.findById(id));
        return "ong/ong";
    }

    @GetMapping("/new")
    public String showNewOngForm(Model model) {
        model.addAttribute("ong", new Ong());
        return "ong/cadastro-ong";
    }

    @PostMapping
    public String saveOng(@Valid Ong ong, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "ong/cadastro-ong";
        }
        ongService.saveOng(ong);
        redirectAttributes.addFlashAttribute("successMessage", "ONG salva com sucesso!");
        return "redirect:/ongs";
    }

    @GetMapping("/edit/{id}")
    public String showEditOngForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("ong", ongService.findById(id));
        return "ong/editar-ong";
    }

    @PostMapping("/update/{id}")
    public String updateOng(@PathVariable("id") Long id, @Valid Ong ongForm, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "redirect:/ongs/edit/" + id;
        }
        try {
            Ong existente = ongService.findById(id);
            existente.setNome(ongForm.getNome());
            existente.setCnpj(ongForm.getCnpj());
            existente.setDescricao(ongForm.getDescricao());
            existente.setEmail(ongForm.getEmail());
            existente.setTelefone(ongForm.getTelefone());
            ongService.saveOng(existente);
            redirectAttributes.addFlashAttribute("successMessage", "ONG atualizada com sucesso!");
            return "redirect:/ongs/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao atualizar a ONG.");
            return "redirect:/ongs/edit/" + id;
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteOng(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            ongService.findById(id);
            ongService.deleteOng(id);
            redirectAttributes.addFlashAttribute("successMessage", "ONG excluída com sucesso!");
            return "redirect:/ongs";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao excluir. A ONG pode estar vinculada a representantes ou vagas.");
            return "redirect:/ongs";
        }
    }
}