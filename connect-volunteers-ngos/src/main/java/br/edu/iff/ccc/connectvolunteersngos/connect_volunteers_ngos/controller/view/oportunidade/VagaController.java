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
import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "vagas")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    @Autowired
    private OngService ongService;

    @GetMapping
    public String listarVagas(Model model) {
        model.addAttribute("vagas", vagaService.findAllVagas());
        return "oportunidade/vagas.html";
    }

    @GetMapping("/{id}")
    public String getVagaPage(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Vaga vaga = vagaService.findById(id);

        if (vaga == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Vaga não encontrada!");
            return "redirect:/vagas";
        }

        model.addAttribute("vaga", vaga);
        return "oportunidade/vaga.html";
    }

    @GetMapping("/new")
    public String novaVaga(Model model) {
        model.addAttribute("vaga", new Vaga());
        model.addAttribute("ongs", ongService.findAllOngs()); // lista de ongs para selecionar
        return "oportunidade/cadastro-vaga.html";
    }

    @PostMapping
    public String salvarVaga(@Valid Vaga vaga, BindingResult error, Model model, RedirectAttributes redirectAttributes) {
        if (error.hasErrors()) {
            model.addAttribute("errorMessage", "Erro ao salvar a vaga.");
            model.addAttribute("ongs", ongService.findAllOngs());
            return "oportunidade/cadastro-vaga.html";
        }

        // pega a ONG correta pelo ID
        if (vaga.getOng() != null && vaga.getOng().getId() != null) {
            Ong ongSelecionada = ongService.findById(vaga.getOng().getId());
            vaga.setOng(ongSelecionada);
        }

        vagaService.saveVaga(vaga);
        redirectAttributes.addFlashAttribute("successMessage", "Vaga salva com sucesso!");
        return "redirect:/vagas";
    }
}
