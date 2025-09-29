package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.view.user;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.Ong;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Representante;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.OngService;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.user.RepresentanteService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;



@Controller
@RequestMapping(path = "representantes")
public class RepresentanteController {

    @Autowired
    RepresentanteService representanteService;

    @Autowired
    OngService ongService;

    @GetMapping("/{id}")
    public String getRepresentantesPage(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {

        Representante representante = representanteService.findRepresentanteById(id);

        if (representante == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Representante não encontrado!");
            return "redirect:/representantes";
        }

        model.addAttribute("representante", representante);
        return "user/representante/representante.html";
    }

    @GetMapping()
    public String getAllRepresentantes(Model model) {
        model.addAttribute("representantes", representanteService.findAllRepresentantes());
        return "user/representante/representantes.html";
    }

    @GetMapping("/new")
    public String createNewRepresentante(Model model) {
        model.addAttribute("representante", new Representante());
        model.addAttribute("ongs", ongService.findAllOngs()); //enviando a lista de ONGs para o select
        return "user/representante/cadastro-representante.html";
    }

    @PostMapping
    public String saveRepresentante(@Valid Representante representante, BindingResult error, @RequestParam("ongOption") String ongOption, Model model, RedirectAttributes redirectAttributes) {
        if (error.hasErrors()) {
            model.addAttribute("errorMessage", "Erro ao salvar o representante.");
            model.addAttribute("ongs", ongService.findAllOngs());
            return "user/representante/cadastro-representante.html";
        }

        // Lógica para lidar com a ONG
        if ("existente".equals(ongOption)) {
            // Se a opção for "existente", busca a ONG pelo ID que veio do formulário
            Ong ongSelecionada = ongService.findById(representante.getOng().getId());
            representante.setOng(ongSelecionada);

        } else if ("nova".equals(ongOption)) {
            // Se a opção for "nova", primeiro salva a nova ONG no banco
            Ong novaOng = ongService.saveOng(representante.getOng());
            // Depois, associa a ONG recém-criada (com ID) ao representante
            representante.setOng(novaOng);
        }

        representanteService.saveRepresentante(representante);
        redirectAttributes.addFlashAttribute("successMessage", "Representante salvo com sucesso!");
        return "redirect:/representantes";
    }

     // --- CRUD ---

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("representante", representanteService.findRepresentanteById(id));
        return "user/representante/editar-representante.html";
    }

    @PostMapping("/update/{id}")
    public String updateRepresentante(@PathVariable("id") Long id, @Valid Representante representanteForm, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao atualizar. Verifique os campos.");
            return "redirect:/representantes/edit/" + id;
        }

        try {
            Representante existente = representanteService.findRepresentanteById(id);
            existente.setNome(representanteForm.getNome());
            existente.setEmail(representanteForm.getEmail());
            existente.setTelefone(representanteForm.getTelefone());
            
            representanteService.saveRepresentante(existente);
            redirectAttributes.addFlashAttribute("successMessage", "Perfil atualizado com sucesso!");
            return "redirect:/representantes/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ocorreu um erro ao atualizar o perfil.");
            return "redirect:/representantes/edit/" + id;
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteRepresentante(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            representanteService.findRepresentanteById(id);
            representanteService.deleteRepresentante(id);
            redirectAttributes.addFlashAttribute("successMessage", "Representante excluído com sucesso!");
            return "redirect:/representantes";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao excluir. O representante pode estar vinculado a vagas.");
            return "redirect:/representantes";
        }
    }
}
