package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.view.oportunidade;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.oportunidade.Vaga;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Usuario;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Voluntario;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.oportunidade.InscricaoService;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.oportunidade.VagaService;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.user.VoluntarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/inscricoes")
public class InscricaoController {

    @Autowired
    private InscricaoService inscricaoService;

    @Autowired
    private VagaService vagaService;

    @Autowired
    private VoluntarioService voluntarioService;

    @PostMapping("/vaga/{vagaId}")
    public String inscreverEmVaga(@PathVariable Long vagaId, HttpSession session, RedirectAttributes redirectAttributes) {

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (!(usuarioLogado instanceof Voluntario)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Apenas voluntários podem se inscrever.");
            return "redirect:/vagas";
        }
        
        Voluntario voluntario = (Voluntario) usuarioLogado;
        Vaga vaga = vagaService.findVagaById(vagaId);

        inscricaoService.createInscricao(voluntario, vaga);
        redirectAttributes.addFlashAttribute("successMessage", "Inscrição realizada com sucesso!");
        
        return "redirect:/vagas";
    }

    @GetMapping("/voluntario")
    public String listarInscricoesVoluntario(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (!(usuarioLogado instanceof Voluntario)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Acesso negado.");
            return "redirect:/";
        }

        Voluntario voluntario = (Voluntario) usuarioLogado;
        model.addAttribute("inscricoes", inscricaoService.findAllByVoluntario(voluntario.getIdUser()));
        model.addAttribute("voluntario", voluntario);
        return "oportunidade/inscricoes-voluntario.html";
    }

    @GetMapping("/vaga/{vagaId}")
    public String listarInscricoesVaga(@PathVariable Long vagaId, Model model) {
        Vaga vaga = vagaService.findVagaById(vagaId);
        model.addAttribute("inscricoes", inscricaoService.findAllByVaga(vaga.getId()));
        model.addAttribute("vaga", vaga);
        return "oportunidade/inscricoes-vaga.html";
    }

    @PostMapping("/cancelar/{inscricaoId}")
    public String cancelarInscricao(@PathVariable Long inscricaoId, RedirectAttributes redirectAttributes) {
        try {
            inscricaoService.cancelInscricao(inscricaoId);
            redirectAttributes.addFlashAttribute("successMessage", "Inscrição cancelada com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao cancelar a inscrição.");
        }
        return "redirect:/inscricoes/voluntario";
    }
}