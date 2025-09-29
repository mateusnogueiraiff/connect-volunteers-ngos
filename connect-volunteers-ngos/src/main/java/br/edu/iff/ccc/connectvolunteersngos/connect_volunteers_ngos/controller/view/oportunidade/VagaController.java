package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.view.oportunidade;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.Ong;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.oportunidade.Vaga;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.oportunidade.VagaService;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.OngService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "vagas")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    @Autowired
    private OngService ongService;

    @GetMapping
    public String listarVagas(Model model, HttpSession session) {
        model.addAttribute("vagas", vagaService.findAllVagas());
        model.addAttribute("usuarioLogado", session.getAttribute("usuarioLogado"));
        return "oportunidade/vaga/vagas.html";
    }

     @GetMapping("/{id}")
    public String getVagaPage(@PathVariable("id") Long id, Model model, HttpSession session) {
        model.addAttribute("vaga", vagaService.findVagaById(id));
        model.addAttribute("usuarioLogado", session.getAttribute("usuarioLogado"));
        return "oportunidade/vaga/vaga.html";
    }

    @GetMapping("/new")
    public String novaVaga(Model model) {
        model.addAttribute("vaga", new Vaga());
        model.addAttribute("ongs", ongService.findAllOngs()); // lista de ongs para selecionar
        return "oportunidade/vaga/cadastro-vaga.html";
    }

    @PostMapping
    public String salvarVaga(@Valid Vaga vaga, BindingResult error, Model model, RedirectAttributes redirectAttributes) {
        if (error.hasErrors() || vaga.getOng() == null || vaga.getOng().getId() == null) {
            // verificação para garantir que um ID de ONG foi enviado
            model.addAttribute("errorMessage", "Erro ao salvar a vaga. Verifique todos os campos, incluindo a ONG.");
            model.addAttribute("ongs", ongService.findAllOngs());
            return "oportunidade/vaga/cadastro-vaga.html";
        }

        try {
            // Busca a entidade ONG completa e gerenciada pelo JPA
            Ong ongSelecionada = ongService.findById(vaga.getOng().getId());
            
            // Associa a ONG correta à vaga antes de salvar
            vaga.setOng(ongSelecionada);

            vagaService.saveVaga(vaga);
            redirectAttributes.addFlashAttribute("successMessage", "Vaga salva com sucesso!");
            return "redirect:/vagas";

        } catch (Exception e) {
            model.addAttribute("errorMessage", "Ocorreu um erro inesperado ao salvar a vaga.");
            model.addAttribute("ongs", ongService.findAllOngs());
            return "oportunidade/vaga/cadastro-vaga.html";
        }
    }

    // --- CRUD ---

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("vaga", vagaService.findVagaById(id));
        model.addAttribute("ongs", ongService.findAllOngs());
        return "oportunidade/vaga/editar-vaga.html";
    }

    @PostMapping("/update/{id}")
    public String updateVaga(@PathVariable("id") Long id, @Valid Vaga vagaForm, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao atualizar. Verifique os campos.");
            return "redirect:/vagas/edit/" + id;
        }
        try {
            Vaga existente = vagaService.findVagaById(id);
            existente.setNome(vagaForm.getNome());
            existente.setDescricao(vagaForm.getDescricao());
            existente.setRequisitos(vagaForm.getRequisitos());
            existente.setLocal(vagaForm.getLocal());
            existente.setDataHorario(vagaForm.getDataHorario());
            existente.setQuantidadeDeVagas(vagaForm.getQuantidadeDeVagas());
            
            if (vagaForm.getOng() != null && vagaForm.getOng().getId() != null) {
                Ong ongSelecionada = ongService.findById(vagaForm.getOng().getId());
                existente.setOng(ongSelecionada);
            }

            vagaService.saveVaga(existente);
            redirectAttributes.addFlashAttribute("successMessage", "Vaga atualizada com sucesso!");
            return "redirect:/vagas/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ocorreu um erro ao atualizar a vaga.");
            return "redirect:/vagas/edit/" + id;
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteVaga(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            vagaService.deleteVaga(id);
            redirectAttributes.addFlashAttribute("successMessage", "Vaga excluída com sucesso!");
            return "redirect:/vagas";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao excluir. A vaga pode ter inscrições associadas.");
            return "redirect:/vagas";
        }
    }
}
